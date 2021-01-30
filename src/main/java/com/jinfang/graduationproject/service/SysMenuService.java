package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理
 *
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysMenuService extends CurdService<SysMenu> {

    /**
     * 查询菜单树,用户ID和用户名为空则查询全部
     *
     * @param roleNames 角色名称
     * @param menuType  获取菜单类型，0：获取所有菜单，包含按钮，1：获取所有菜单，不包含按钮
     */
    List<SysMenu> findTree(Set<String> roleNames, int menuType, Long schoolId);

    /**
     * 根据用户名查找菜单列表
     *
     * @param userName
     * @return
     */
    List<SysMenu> findByUser(String userName);

    /**
     * 根绝角色名称获取所有菜单列表
     *
     * @param roleNames 角色名称
     */
    List<SysMenu> findMenusByRoleName(Set<String> roleNames, Long schoolId);

    /**
     * 根绝角色名称获取所有权限列表
     *
     * @param roleNames 角色名称
     */
    List<String> findPermsByRoleName(Set<String> roleNames, Long schoolId);

    /**
     * 根据学校ID和 环节代码更新菜单和权限
     *
     * @param schoolId 学校ID
     * @param nodeCode 环节代码
     * @return 更新行数
     */
    int updateByNodeCode(int isOpen, Long schoolId, String nodeCode);
}
