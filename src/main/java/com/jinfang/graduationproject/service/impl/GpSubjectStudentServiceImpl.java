package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.constants.teacher.SubjectStudentConstants;
import com.jinfang.graduationproject.domain.GpSubjectStudent;
import com.jinfang.graduationproject.domain.GpSubjectStudentHistory;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpSubjectMapper;
import com.jinfang.graduationproject.mapper.GpSubjectStatisticsMapper;
import com.jinfang.graduationproject.mapper.GpSubjectStudentHistoryMapper;
import com.jinfang.graduationproject.mapper.GpSubjectStudentMapper;
import com.jinfang.graduationproject.service.GpSubjectStudentService;
import com.jinfang.graduationproject.service.StudentService;
import com.jinfang.graduationproject.vo.student.topic.SubjectStudentPageRes;
import com.jinfang.graduationproject.vo.teacher.topic.SeeReceiveStudentReqVo;
import com.jinfang.graduationproject.vo.teacher.topic.SeeReceiveStudentRespVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --------------------------- 学生选题关系表一对多） (GpSubjectStudentServiceImpl)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
@Slf4j
@Service
@AllArgsConstructor
public class GpSubjectStudentServiceImpl implements GpSubjectStudentService {

    private GpSubjectMapper gpSubjectMapper;
    private GpSubjectStudentMapper gpSubjectStudentMapper;
    private GpSubjectStatisticsMapper subjectStatisticsMapper;
    private GpSubjectStudentHistoryMapper gpSubjectStudentHistoryMapper;
    private StudentService studentService;

    /**
     * 本地锁，防止计数冲突
     */
    private final Object monitor = new Object();

    @Override
    public int save(GpSubjectStudent record) {
        if (record.getId() == null || record.getId() == 0) {
            record.setCreateDate(new Date());
            return gpSubjectStudentMapper.add(record);
        }
        record.setModifyDate(new Date());
        return gpSubjectStudentMapper.update(record);
    }

    @Override
    public int delete(GpSubjectStudent record) {
        return gpSubjectStudentMapper.delete(record.getId());
    }

    @Override
    public int delete(List<GpSubjectStudent> records) {
        for (GpSubjectStudent record : records) {
            delete(record);
        }

        return 1;
    }

    @Override
    public GpSubjectStudent findById(Long id) {
        return gpSubjectStudentMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Long studentId = (Long) pageRequest.getExtProps().get("studentId");
        if (studentId == null || studentId == 0L) {
            log.warn("StudentId is necessary");
            return null;
        }

        boolean isEduClassStudent = studentService.isGraduationProjectMember(studentId);
        if (!isEduClassStudent) {
            log.warn("StudentId[{}] is not in eduClass, not allow show any subjects", studentId);
            return null;
        }

        PageResult page = MybatisPageHelper.findPage(pageRequest, gpSubjectStudentMapper, "findPage",
                pageRequest.getAttrValue("subjectName"),
                pageRequest.getAttrValue("teacherName"));

        // 根据课题id获取已接收学生列表
        if (page != null && page.getContent().size() > 0) {
            // 获取课题学生映射，判断学生是否已经有选题
            Map<Long, Integer> subjectStudents = getMappingByStudentId(studentId);
            if (MapUtils.isEmpty(subjectStudents)) {
                return page;
            }

            // 如果学生选题中包括 待处理或者 已接收 的课题，则下面所有可以设置为不能操作
            boolean isNotAllowOperate = subjectStudents.values().contains(SubjectStudentConstants.ApproveStatus.WAITING.getCode())
                    || subjectStudents.values().contains(SubjectStudentConstants.ApproveStatus.ACCEPT.getCode());

            for (Object data : page.getContent()) {
                SubjectStudentPageRes subjectStudentPageRes = (SubjectStudentPageRes) data;
                if (isNotAllowOperate) {
                    subjectStudentPageRes.setAllowOperate(false);
                }
            }

        }

        return page;
    }

    /**
     * 根据学生ID查询 所有已选课题的 状态关系
     *
     * @param studentId 学生ID
     * @return 课题-状态 关系
     */
    private Map<Long, Integer> getMappingByStudentId(Long studentId) {
        List<GpSubjectStudent> subjectStudents = gpSubjectStudentMapper.selectSubjectByStudentId(studentId);
        if (CollectionUtils.isEmpty(subjectStudents)) {
            return null;
        }

        Map<Long, Integer> mapping = new HashMap<>();
        for (GpSubjectStudent subjectStudent : subjectStudents) {
            mapping.put(subjectStudent.getSubjectId(), subjectStudent.getStatus());
        }

        return mapping;
    }

    @Override
    public List<SeeReceiveStudentRespVo> seeReceiveStudent(SeeReceiveStudentReqVo req) {
        return gpSubjectStudentMapper.seeReceiveStudent(req);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult accept(GpSubjectStudent student) {
        synchronized (monitor) {
            try {
                Long subjectId = student.getSubjectId();

                // 学生上限综述和 已接收学生数 差值
                int dValue = gpSubjectMapper.computeMemberLimit(subjectId);
                if (dValue == 0) {
                    return HttpResult.error("当前课题接受人数已达到");
                }

                student.setApproveTime(new Date());
                student.setStatus(SubjectStudentConstants.ApproveStatus.ACCEPT.getCode());
                int rows = save(student);
                if (rows > 0) {

                    if (dValue == 1) {
                        // 如果差值为1，则表明为 最后一个名额，需要更新其他 待处理的全部变成已拒绝
                        int rejectAmount = gpSubjectStudentMapper.updateWaitingToRefuse(subjectId);
                        log.info("updateWaitingToRefuse count:{} by subjectId:{}", rejectAmount, subjectId);
                        if (rejectAmount > 0) {
                            int count = subjectStatisticsMapper.updateRejectAmountAndClearWaiting(subjectId, rejectAmount);
                            log.info("updateRejectAmountAndClearWaiting count:{} by subjectId:{}", count, subjectId);
                        }
                    }

                    copyAttrAndSaveHistory(student);

                    // 累加 已接受数+1
                    int result = subjectStatisticsMapper.updateAcceptAmount(subjectId, 1);
                    if (result == 0) {
                        throw new RuntimeException("更新已接收数失败");
                    }

                    // 待处理数 -1
                    subjectStatisticsMapper.minusOneWaitingAmount(subjectId);

                    return HttpResult.ok("接受成功");
                }

                return HttpResult.ok("接受失败");
            } catch (Exception e) {
                log.error("Failed to accept by student[{}]", student, e);
                throw e;
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult refuse(GpSubjectStudent student) {
        try {
            student.setApproveTime(new Date());
            student.setStatus(SubjectStudentConstants.ApproveStatus.REFUSE.getCode());
            int rows = save(student);
            if (rows > 0) {
                // 更新拒绝数
                rows = subjectStatisticsMapper.updateRejectAmount(student.getSubjectId(), 1);
                if (rows == 0) {
                    throw new RuntimeException("更新已拒绝数失败");
                }

                // 待处理数 -1
                subjectStatisticsMapper.minusOneWaitingAmount(student.getSubjectId());

                // 增加历史记录
                copyAttrAndSaveHistory(student);

                return HttpResult.ok("拒绝成功");
            }

            return HttpResult.ok("拒绝失败");
        } catch (Exception e) {
            log.error("Failed to refuse by student[{}]", student, e);
            throw e;
        }
    }

    @Override
    public HttpResult choiceResultList(String studentNo) {
        try {
            if (StringUtils.isEmpty(studentNo)) {
                return HttpResult.error("参数异常【studentNo】");
            }
            return HttpResult.ok(gpSubjectStudentMapper.choiceResultList(studentNo));
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error("查询失败" + e.getMessage());
        }
    }

    @Override
    public Long getStudentId(Long id) {
        GpSubjectStudent gpSubjectStudent = findById(id);
        if (gpSubjectStudent == null) {
            throw new RuntimeException("Can't find gpSubjectStudent by id[" + id + "]");
        }

        return gpSubjectStudent.getStudentId();
    }

    private void copyAttrAndSaveHistory(GpSubjectStudent student) {
        // 去数据库中拿到该课题选题ID的所有属性数据
        GpSubjectStudent source = gpSubjectStudentMapper.findById(student.getId());

        // 因为考虑是 当前线程事务，修改的状态和审批时间还没生效，顾需要从新设置下值
        source.setApproveTime(student.getApproveTime());
        source.setStatus(student.getStatus());

        addHistoryRecord(source);
    }

    private void addHistoryRecord(GpSubjectStudent gpSubjectStudent) {
        GpSubjectStudentHistory gpSubjectStudentHistory = new GpSubjectStudentHistory();
        BeanUtils.copyProperties(gpSubjectStudent, gpSubjectStudentHistory, "id");
        gpSubjectStudentHistory.setSubjectStudentId(gpSubjectStudent.getId());

        gpSubjectStudentHistoryMapper.add(gpSubjectStudentHistory);
    }

}
