package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.constants.teacher.SubjectConstants;
import com.jinfang.graduationproject.constants.teacher.SubjectConstants.ApproveStatus;
import com.jinfang.graduationproject.constants.teacher.SubjectConstants.ReModifyStatus;
import com.jinfang.graduationproject.constants.teacher.SubjectStatisticsConstants;
import com.jinfang.graduationproject.domain.*;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.ColumnFilter;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.dto.teacher.topic.GpSubjectListResqDto;
import com.jinfang.graduationproject.mapper.*;
import com.jinfang.graduationproject.service.GpSubjectLiteratureService;
import com.jinfang.graduationproject.service.GpSubjectService;
import com.jinfang.graduationproject.service.ModifyPermissionService;
import com.jinfang.graduationproject.service.SettingService;
import com.jinfang.graduationproject.util.DateUtil;
import com.jinfang.graduationproject.util.ParameterUtil;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import com.jinfang.graduationproject.vo.teacher.leader.topic.ExamineReqVo;
import com.jinfang.graduationproject.vo.teacher.leader.topic.FindExaminePageReqVo;
import com.jinfang.graduationproject.vo.teacher.topic.GpSubjectSaveResqVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * --------------------------- 课题表 (GpSubjectServiceImpl)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
@Slf4j
@Service
@AllArgsConstructor
public class GpSubjectServiceImpl implements GpSubjectService {

    private GpSubjectMapper gpSubjectMapper;
    private GpSubjectLiteratureService subjectLiteratureService;
    private GpSubjectStatisticsMapper subjectStatisticsMapper;
    private GpSubjectStudentMapper gpSubjectStudentMapper;

    private CcStudentMapper studentMapper;
    private CcTeacherMapper teacherMapper;
    private SettingService settingService;
    private ModifyPermissionService modifyPermissionService;

    private final Lock checkAmountLock = new ReentrantLock();

    @Override
    public int save(GpSubject record) {
        try {
            record.setSubmitTime(new Date());
            gpSubjectMapper.add(record);
            return record.getId().intValue();

        } catch (Exception e) {
            log.error("Failed to save gpSubject[{}]", record, e);
            return 0;
        }
    }

    @Override
    public int delete(GpSubject record) {
        return gpSubjectMapper.delete(record.getId());
    }

    @Override
    public int delete(List<GpSubject> records) {
        for (GpSubject record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public GpSubject findById(Long id) {
        return gpSubjectMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, gpSubjectMapper, "findPageByName",
                setGpSubject(pageRequest));
        if (page != null && page.getContent().size() > 0) {
            List<GpSubjectListResqDto> list = new ArrayList<>();
            for (Object data : page.getContent()) {
                GpSubjectListResqDto dto = new GpSubjectListResqDto();
                BeanUtils.copyProperties(data, dto);


                dto.setLiteratureList(subjectLiteratureService.getBySubjectIdList(dto.getId()));
                // 已通过查询 统计数据
                if (dto.getStatus() == SubjectConstants.ApproveStatus.PASS.getCode()) {
                    dto.setSubjectStatistics(subjectStatisticsMapper.findBySubjectId(dto.getId()));
                }

                List<String> studentNames = gpSubjectStudentMapper.findReceiveStudentNames(dto.getId());
                if (!CollectionUtils.isEmpty(studentNames)) {
                    dto.setStudentNames(StringUtils.join(studentNames, "，"));
                }

                // 设置是否允许修改标识
                dto.setAllowModify(isAllowModify(dto, (long) pageRequest.getExtProps().get("schoolId")));

                list.add(dto);
            }

            page.setContent(list);
        }
        return page;
    }

    /**
     * 课题是否允许修改，一下三种条件允许修改
     * (1)专业负责人开放全专业的课题修改权限时，可以直接修改。
     * (2)“未提交审核”和“审核失败”的状态下可以直接修改课题；
     * (3)如果要修改已经“审核通过”的课题，需先发起“申请修改”，等到专业负责人审核通过后，才能修改课题；
     *
     * @param subjectListResqDto 课题
     * @return true/false
     */
    private boolean isAllowModify(GpSubjectListResqDto subjectListResqDto, Long schoolId) {
        if (subjectListResqDto == null) {
            return false;
        }

        boolean isAllowModify = modifyPermissionService.isAllowModify(DateUtil.getCurrentYear() + "", schoolId);
        if (isAllowModify) {
            return true;
        }

        if (ApproveStatus.TEMP.getCode() == subjectListResqDto.getStatus()
                || ApproveStatus.REJECT.getCode() == subjectListResqDto.getStatus()) {
            return true;
        }

        return ApproveStatus.PASS.getCode() == subjectListResqDto.getStatus()
                && ReModifyStatus.ALLOW.getCode() == subjectListResqDto.getRemodifyStatus();

    }

    /**
     * 设置查询入参
     */
    private GpSubject setGpSubject(PageRequest pageRequest) {
        String grade = getColumnFilterValue(pageRequest, "grade");
        String name = getColumnFilterValue(pageRequest, "name");
        String type = getColumnFilterValue(pageRequest, "type");
        String source = getColumnFilterValue(pageRequest, "source");
        String status = getColumnFilterValue(pageRequest, "status");
        Long teacherId = (Long) pageRequest.getExtProps().get("teacherId");
        GpSubject subject = new GpSubject();
        subject.setGrade(grade);
        subject.setName(name);
        subject.setTeacherId(String.valueOf(teacherId));
        // subject.setTeacherName(teacherName);
        if (StringUtils.isNotEmpty(type)) {
            subject.setType(Integer.parseInt(type));
        }
        if (StringUtils.isNotEmpty(source)) {
            subject.setSource(Integer.parseInt(source));
        }
        if (StringUtils.isNotEmpty(status)) {
            subject.setStatus(Integer.parseInt(status));
        }
        return subject;
    }

    /**
     * 获取过滤字段的值
     *
     * @param filterName
     * @return
     */
    private String getColumnFilterValue(PageRequest pageRequest, String filterName) {
        String value = null;
        ColumnFilter columnFilter = pageRequest.getColumnFilter(filterName);
        if (columnFilter != null) {
            value = columnFilter.getValue();
        }
        return value;
    }

    /**
     * 如果当前教师已经设置的 学生限制总和 大于全局设置的 阈值，则拦截
     *
     * @param loginUserMeta 当前登录教师信息
     * @param memberLimit   输入学生上限数
     * @return true: 已超限， false:未超限
     */
    private boolean checkIsStudentBeyondInSameTeacher(LoginUserMeta loginUserMeta, Long subjectId,
                                                      Integer memberLimit) {
        GpSetting setting = settingService.findBySchoolIdAndGrade(loginUserMeta.getSchoolId(),
                loginUserMeta.getGrade());
        if (setting == null) {
            log.warn("Global setting is not set, do it.");
            return false;
        }

        if (setting.getStudentLimit() == null || setting.getStudentLimit() <= 0) {
            log.warn("Invalid student limit value [{}], update it.", setting.getStudentLimit());
            return false;
        }

        if (memberLimit > setting.getStudentLimit()) {
            log.warn("memberLimit[{}] > studentLimit[{}]", memberLimit, setting.getStudentLimit());
            return true;
        }

        checkAmountLock.lock();
        try {
            // 此届别下 该教师 已有的所有课题 学生设置总数
            Long existsTeacherLimit = gpSubjectMapper.selectCountByTeacherId(loginUserMeta.getTeacherId(),
                    loginUserMeta.getGrade(), subjectId);

            existsTeacherLimit = existsTeacherLimit == null ? 0 : existsTeacherLimit;

            Long totalLimit = existsTeacherLimit + memberLimit;

            boolean isBeyond = totalLimit > setting.getStudentLimit();
            if (isBeyond) {
                log.warn("current limit:{}, total teacher limit : {}, global limit: {}", existsTeacherLimit, totalLimit,
                        setting.getStudentLimit());
            }

            return isBeyond;
        } finally {
            checkAmountLock.unlock();
        }
    }

    /**
     * 保存课题记录
     *
     * @param subjectSaveResqVo 课题信息
     * @return 处理结果（影响行数）
     */
    private int saveSubjectProcess(GpSubjectSaveResqVo subjectSaveResqVo) {
        CcTeacher teacher = teacherMapper.findById(Long.valueOf(subjectSaveResqVo.getTeacherId()));
        if (teacher != null) {
            subjectSaveResqVo.setTeacherName(teacher.getName());
        }

        subjectSaveResqVo.setSubmitTime(new Date());
        int rows = gpSubjectMapper.add(subjectSaveResqVo);
        if (rows <= 0) {
            log.error("Failed to insert Subject[{}], effect rows -> '{}'", subjectSaveResqVo, rows);
            return rows;
        }

        subjectLiteratureService.batchSave(subjectSaveResqVo.getLiteratureList(), subjectSaveResqVo.getId());

        return initSubjectStatistics(subjectSaveResqVo.getId());
    }

    /**
     * 修改课题逻辑
     *
     * @param subjectSaveResqVo 课题信息
     * @return 处理结果
     */
    private int updateSubjectProcess(GpSubjectSaveResqVo subjectSaveResqVo) {
        subjectSaveResqVo.setModifyDate(new Date());
        int rows = gpSubjectMapper.update(subjectSaveResqVo);
        if (rows <= 0) {
            log.error("Failed to i[date Subject[{}], effect rows -> '{}'", subjectSaveResqVo, rows);
            return rows;
        }

        subjectLiteratureService.deleteBySubjectId(subjectSaveResqVo.getId());

        subjectLiteratureService.batchSave(subjectSaveResqVo.getLiteratureList(), subjectSaveResqVo.getId());

        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult saveOrUpdateSubject(GpSubjectSaveResqVo subjectSaveResqVo, LoginUserMeta loginUserMeta) {
        if (subjectSaveResqVo == null) {
            return HttpResult.error("参数数据为空");
        }

        if (subjectSaveResqVo.getMemberLimit() == null || subjectSaveResqVo.getMemberLimit() <= 0) {
            log.warn("memberLimit[{}] invalid", subjectSaveResqVo.getMemberLimit());
            return HttpResult.error("学生上限数参数有误");
        }

        try {
            if (checkIsStudentBeyondInSameTeacher(loginUserMeta, subjectSaveResqVo.getId(), subjectSaveResqVo.getMemberLimit())) {
                return HttpResult.error("当前教师学生已超限");
            }

            if (subjectSaveResqVo.getId() == null || subjectSaveResqVo.getId() == 0) {
                return HttpResult.ok(saveSubjectProcess(subjectSaveResqVo));
            }
            //校验是否申请修改做修改数据
            GpSubject subject = findById(subjectSaveResqVo.getId());
            if (subject != null) {
                if (subject.getRemodifyStatus() == ReModifyStatus.ALLOW.getCode()) {
                    subjectSaveResqVo.setStatus(ApproveStatus.WAITING.getCode());
                    subjectSaveResqVo.setRemodifyStatus(ReModifyStatus.INIT.getCode());
                }
            }
            return HttpResult.ok(updateSubjectProcess(subjectSaveResqVo));
        } catch (Exception e) {
            log.error("Failed to save subject[{}]", subjectSaveResqVo, e);
            return HttpResult.error();
        }
    }

    /**
     * 初始化课题统计信息（方便列表查询）
     *
     * @param subjectId 课题ID
     */
    private int initSubjectStatistics(Long subjectId) {
        GpSubjectStatistics gpSubjectStatistics = new GpSubjectStatistics();
        gpSubjectStatistics.setSubjectId(subjectId);
        gpSubjectStatistics.setStatus(SubjectStatisticsConstants.FinishStatus.NO.getCode());
        gpSubjectStatistics.setCreateDate(new Date());
        return subjectStatisticsMapper.add(gpSubjectStatistics);
    }

    @Override
    public HttpResult censorship(Long id) {
        try {
            GpSubject sub = this.findById(id);
            if (sub == null) {
                return HttpResult.error("未查询当该课题！");
            }
            // 校验课题状态 是否允许送审
            int status = sub.getStatus();
            if (status == SubjectConstants.ApproveStatus.WAITING.getCode()) {
                return HttpResult.error("当前课题在审核中，请耐心等待！");
            }
            if (status == SubjectConstants.ApproveStatus.PASS.getCode()) {
                return HttpResult.error("当前课题已审核通过，不允许在提交送审！");
            }
            // 每次重新送审都要将原来的审批意见清空
            sub.setApproveReason("");

            // 修改
            sub.setModifyDate(new Date());
            sub.setStatus(SubjectConstants.ApproveStatus.WAITING.getCode());
            int i = gpSubjectMapper.update(sub);
            if (i > 0) {
                return HttpResult.ok("送审成功！");
            }
            return HttpResult.error("送审失败！" + i);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error("送审失败！" + e.getMessage());
        }

    }

    @Override
    public HttpResult applyModify(Long id, Long teacherId) {
        try {
            GpSubject sub = this.findById(id);
            if (sub == null) {
                return HttpResult.error("未查询当该课题！");
            }
            // 校验课题状态 是否允许送审
            int status = sub.getStatus();
            if (status != SubjectConstants.ApproveStatus.PASS.getCode()) {
                return HttpResult.error("当前申请修改课题状态必须是审核通过，请检查！");
            }
            // 修改
            sub.setModifyDate(new Date());
            sub.setRemodifyStatus(SubjectConstants.ReModifyStatus.WAITING.getCode());
            sub.setRemodifySubmitTime(new Date());
            sub.setRemodifySubmitUser(String.valueOf(teacherId));
            int i = gpSubjectMapper.update(sub);
            if (i > 0) {
                return HttpResult.ok("申请修改成功！");
            }
            return HttpResult.error("申请修改失败！" + i);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error("申请修改失败！" + e.getMessage());
        }
    }

    @Override
    public HttpResult getSubBySubmitUserList(String submitUser) {
        if (StringUtils.isEmpty(submitUser)) {
            return HttpResult.error("查询失败！参数异常submitUser【】空");
        }
        GpSubject sub = new GpSubject();
        sub.setTeacherId(submitUser);
        // sub.setTeacherName(teacherName);
        sub.setStatus(SubjectConstants.ApproveStatus.PASS.getCode());
        return HttpResult.ok(gpSubjectMapper.findBySubmitUser(sub));
    }

    @Override
    public HttpResult details(Long id) {
        if (id == null || id == 0L) {
            return HttpResult.error("查询失败！参数异常id【】空");
        }
        GpSubject sb = findById(id);
        if (sb == null) {
            return HttpResult.error("查询失败！未查询到相关数据");
        }
        GpSubjectListResqDto dto = new GpSubjectListResqDto();
        BeanUtils.copyProperties(sb, dto);
        dto.setLiteratureList(subjectLiteratureService.getBySubjectIdList(dto.getId()));
        // 已通过查询 统计数据
        if (dto.getStatus() == SubjectConstants.ApproveStatus.PASS.getCode()) {
            dto.setSubjectStatistics(subjectStatisticsMapper.findBySubjectId(dto.getId()));
        }
        return HttpResult.ok(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult studentChoiceTopic(GpSubjectStudent record) {
        if (record == null) {
            return HttpResult.error("请求参数异常");
        }

        if (record.getStudentId() == null) {
            log.error("StudentId must be not null");
            return HttpResult.error("请求参数异常");
        }

        try {
            // 根据学生编号获取学生信息
            CcStudent student = studentMapper.findInfoById(record.getStudentId());
            if (student == null) {
                log.error("No student by student-no [{}]", record.getStudentNo());
                return HttpResult.error("当前操作学生数据异常");
            }

            record.setStudentId(student.getId());
            record.setChooseTime(new Date());
            int rows = gpSubjectStudentMapper.add(record);

            if (rows > 0) {
                subjectStatisticsMapper.addOneWaitingAmount(record.getSubjectId());
                return HttpResult.ok("选择成功");
            }

            return HttpResult.error("选择失败");
        } catch (Exception e) {
            log.error("Failed execute by record[{}]", record, e);
            throw e;
        }
    }

    @Override
    public HttpResult findExaminePage(PageRequest pageRequest) {
        try {
            PageResult page = MybatisPageHelper.findPage(pageRequest, gpSubjectMapper, "selectExaminePage", setFindExaminePageReqVo(pageRequest));
            if (page != null && page.getContent().size() > 0) {
                for (Object data : page.getContent()) {
                    GpSubjectListResqDto dto = (GpSubjectListResqDto) data;
                    dto.setLiteratureList(subjectLiteratureService.getBySubjectIdList(dto.getId()));

                    List<String> studentNames = gpSubjectStudentMapper.findReceiveStudentNames(dto.getId());
                    if (!CollectionUtils.isEmpty(studentNames)) {
                        dto.setStudentNames(StringUtils.join(studentNames, "，"));
                    }

                }
            }
            return HttpResult.ok(page);
        } catch (Exception e) {
            log.error("Failed to find page by reqVo[{}]", pageRequest.toString());
            return HttpResult.error();
        }
    }


    /**
     * 设置参数
     */
    private FindExaminePageReqVo setFindExaminePageReqVo(PageRequest pageRequest) {
        FindExaminePageReqVo resq = new FindExaminePageReqVo();
        String grade = ParameterUtil.getColumnFilterValue(pageRequest, "grade");
        String teacherName = ParameterUtil.getColumnFilterValue(pageRequest, "teacherName");
        String subjectName = ParameterUtil.getColumnFilterValue(pageRequest, "name");
        String type = ParameterUtil.getColumnFilterValue(pageRequest, "type");
        String source = ParameterUtil.getColumnFilterValue(pageRequest, "source");
        String status = ParameterUtil.getColumnFilterValue(pageRequest, "status");
        resq.setGrade(grade);
        resq.setStatus(status);
        resq.setName(subjectName);
        resq.setSource(source);
        resq.setType(type);
        resq.setTeacherName(teacherName);
        return resq;
    }

    @Override
    public HttpResult modifyJurisdiction(Long id, String teacherId) {
        try {
            GpSubject sub = this.findById(id);
            if (sub == null) {
                return HttpResult.error("未查询当该课题！");
            }
            sub.setModifyDate(new Date());
            sub.setRemodifyStatus(SubjectConstants.ReModifyStatus.ALLOW.getCode());
            sub.setRemodifyApproveTime(new Date());
            sub.setRemodifyApproveUser(teacherId);
            int i = gpSubjectMapper.update(sub);
            if (i > 0) {
                return HttpResult.ok("开放修改权限成功！");
            }
            return HttpResult.error("开放修改权限失败！" + i);
        } catch (Exception e) {
            log.error("Failed to modifyJurisdiction by id[{}] and teacherId[{}]", id, teacherId, e);
            return HttpResult.error("开放修改权限失败！" + e.getMessage());
        }
    }

    @Override
    public HttpResult examine(ExamineReqVo reqVo) {
        try {
            Long id = reqVo.getId();
            if (id == null) {
                return HttpResult.error("参数异常【id】为空！");
            }
            GpSubject sub = this.findById(id);
            if (sub == null) {
                return HttpResult.error("未查询当该课题！");
            }
            sub.setApproveTime(new Date());
            sub.setApproveReason(reqVo.getApproveReason());
            sub.setApproveRemark(reqVo.getApproveRemark());
            sub.setApproveUser(reqVo.getApproveUser());
            sub.setStatus(reqVo.getStatus());
            int i = gpSubjectMapper.update(sub);
            if (i > 0) {
                return HttpResult.ok("审核成功！");
            }
            return HttpResult.error("审核失败！" + i);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error("审核失败！" + e.getMessage());
        }
    }

}
