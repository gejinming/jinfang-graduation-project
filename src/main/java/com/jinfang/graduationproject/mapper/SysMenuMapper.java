package com.jinfang.graduationproject.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> findPage();

    List<SysMenu> findPageByName(@Param(value = "name") String name);

    List<SysMenu> findAll();

    List<SysMenu> findByUserName(@Param(value = "userName") String userName);

    List<SysMenu> findRoleMenus(@Param(value = "roleId") Long roleId);

    /**
     * 根据角色名称获取所有的菜单
     *
     * @param roleNames 角色名称
     */
    List<SysMenu> selectMenusByRoleName(@Param("schoolId") Long schoolId,
                                        @Param("roleNames") Set<String> roleNames);

    /**
     * 根据角色名称获取所有的权限清单
     *
     * @param roleNames 角色名称
     */
    List<String> selectPermsByRoleName(@Param("schoolId") Long schoolId,
                                       @Param("roleNames") Set<String> roleNames);

    /**
     * 根据学校ID和毕业环节更新 是否允许操作
     *
     * @param isOpen   是否开放功能
     * @param schoolId 学校ID
     * @param nodeCode 环节代码
     * @return 影响行数
     */
    int updateByNodeCode(@Param("isOpen") int isOpen,
                         @Param("schoolId") Long schoolId,
                         @Param("nodeCode") String nodeCode);
}