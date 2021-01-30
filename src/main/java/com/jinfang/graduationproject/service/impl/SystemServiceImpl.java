package com.jinfang.graduationproject.service.impl;

import com.google.common.collect.Sets;
import com.jinfang.graduationproject.constants.SystemRole;
import com.jinfang.graduationproject.constants.teacher.DefenseTeacherType;
import com.jinfang.graduationproject.domain.CcStudent;
import com.jinfang.graduationproject.domain.CcTeacher;
import com.jinfang.graduationproject.domain.GpDefenseTeacher;
import com.jinfang.graduationproject.exception.AuthException;
import com.jinfang.graduationproject.security.GrantedAuthorityImpl;
import com.jinfang.graduationproject.service.*;
import com.jinfang.graduationproject.util.DateUtil;
import com.jinfang.graduationproject.util.JwtTokenUtils;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SystemServiceImpl implements SystemService {

    private StudentService studentService;
    private TeacherService teacherService;
    private DefenseTeacherService defenseTeacherService;
    private CheckerTeacherService checkerTeacherService;

    private SysMenuService sysMenuService;

    private static volatile ConcurrentHashMap<Long, LoginUserMeta> USER_META_MAP = new ConcurrentHashMap<>();

    /**
     * 教师角色映射（本地缓存）
     */
    private static volatile ConcurrentHashMap<Long, Set<String>> TEACHER_ROLE_MAP = new ConcurrentHashMap<>();

    /**
     * 学生角色名称定义
     */
    private static final String STUDENT_ROLE_NAME = "student";

    @Override
    public LoginUserMeta login(String token, boolean refresh) {
        Claims claims = JwtTokenUtils.getClaimsFromToken(token);
        if (claims == null) {
            throw new AuthException("Can't analysis token");
        }


        String id = claims.getSubject();
        String role = JwtTokenUtils.getRole(claims);
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(role)) {
            throw new RuntimeException("Can't analysis role");
        }

        Long _id = Long.valueOf(id);

        // 非刷新模式，取内存已有数据
        if (!refresh && !USER_META_MAP.isEmpty() && USER_META_MAP.get(_id) != null) {
            return USER_META_MAP.get(_id);
        }

        LoginUserMeta loginUserMeta;
        if (STUDENT_ROLE_NAME.equals(role)) {
            CcStudent student = studentService.findInfoById(_id);
            loginUserMeta = LoginUserMeta.builder().isStudent(true).grade(student.getGrade() + "")
                    .roles(Sets.newHashSet(SystemRole.STUDENT.getRoleName()))
                    .schoolId(student.getSchoolId()).majorId(student.getMajorId())
                    .studentId(student.getId()).studentNo(student.getStudentNo())
                    .studentName(student.getName()).build();


        } else {
            CcTeacher teacher = teacherService.getTeacherInfo(_id);
            loginUserMeta = LoginUserMeta.builder().isStudent(false)
                    .grade(DateUtil.getCurrentYear() + "")
                    .roles(getTeacherRole(teacher.getId(), refresh))
                    .schoolId(teacher.getSchoolId()).majorId(teacher.getMajorId())
                    .teacherName(teacher.getName()).teacherId(teacher.getId()).build();
        }

        USER_META_MAP.put(_id, loginUserMeta);

        return loginUserMeta;
    }

    /**
     * 根据教师ID/用户ID 获取教师角色名称
     *
     * @param teacherId 教师ID
     * @return 角色名称
     */
    private Set<String> getTeacherRole(Long teacherId, boolean refresh) {
        if(refresh) {
            TEACHER_ROLE_MAP.remove(teacherId);
        } else {
            if (!CollectionUtils.isEmpty(TEACHER_ROLE_MAP.get(teacherId))) {
                return TEACHER_ROLE_MAP.get(teacherId);
            }
        }

        Set<String> roles = new HashSet<>();
        // 是否为专业负责人
        boolean isLeader = teacherService.isSchoolLeader(teacherId);
        if (isLeader) {
            roles.add(SystemRole.LEADER.getRoleName());
        }

        GpDefenseTeacher defenseTeacher = defenseTeacherService.findByTeacherId(teacherId);
        if (defenseTeacher != null) {
            // 答辩组组长
            if (DefenseTeacherType.HEADMAN.getCode() == defenseTeacher.getType()) {
                roles.add(SystemRole.HEADMAN.getRoleName());
            } else {
                roles.add(SystemRole.DEFENSE.getRoleName());
            }

            // 评阅教师是在 答辩组成员的基础上设置
            boolean isChecker = checkerTeacherService.isTeacherChecker(defenseTeacher.getId());
            if (isChecker) {
                roles.add(SystemRole.CHECKER.getRoleName());
            }
        }

        roles.add(SystemRole.GUIDER.getRoleName());
        TEACHER_ROLE_MAP.put(teacherId, roles);

        // 默认角色为 指导教师
        return roles;
    }

    @Override
    public List<GrantedAuthority> getBySubjectId(String subjectId) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(subjectId)) {
            log.error("subjectId is empty");
            throw new UsernameNotFoundException("subjectId is empty");
        }

        Set<String> roleNames = new HashSet<>();
        CcTeacher ccTeacher = teacherService.findById(Long.valueOf(subjectId));
        Long schoolId = null;
        if (ccTeacher != null) {
            roleNames = getTeacherRole(ccTeacher.getId(), false);
            schoolId = ccTeacher.getSchoolId();
        } else {
            CcStudent ccStudent = studentService.findInfoById(Long.valueOf(subjectId));
            if (ccStudent != null) {
                roleNames = Sets.newHashSet(SystemRole.STUDENT.getRoleName());
                schoolId = ccStudent.getSchoolId();
            }
        }

        if (CollectionUtils.isEmpty(roleNames)) {
            throw new UsernameNotFoundException("Invalid roles");
        }

        List<String> permissions = sysMenuService.findPermsByRoleName(roleNames, schoolId);
        if (CollectionUtils.isEmpty(permissions)) {
            return null;
        }

        return permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
    }
}
