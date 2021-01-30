package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.constants.SysConstants;
import com.jinfang.graduationproject.domain.SysMenu;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.SysMenuMapper;
import com.jinfang.graduationproject.service.SysMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {

    private SysMenuMapper sysMenuMapper;

    @Override
    public int save(SysMenu record) {
        if (record.getId() == null || record.getId() == 0) {
            return sysMenuMapper.insertSelective(record);
        }
        if (record.getParentId() == null) {
            record.setParentId(0L);
        }
        return sysMenuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysMenu record) {
        return sysMenuMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysMenu> records) {
        for (SysMenu record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysMenu findById(Long id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysMenuMapper);
    }

    @Override
    public List<SysMenu> findTree(Set<String> roleNames, int menuType, Long schoolId) {
    	/*roleNames.add("答辩组教师");
    	roleNames.add("答辩组组长");
    	roleNames.add("专业负责人");*/
        List<SysMenu> sysMenus = new ArrayList<>();
        List<SysMenu> menus = findMenusByRoleName(roleNames, schoolId);
        for (SysMenu menu : menus) {
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                menu.setLevel(0);
                if (!exists(sysMenus, menu)) {
                    sysMenus.add(menu);
                }
            }
        }
        sysMenus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
        findChildren(sysMenus, menus, menuType);
        return sysMenus;
    }

    @Override
    public List<SysMenu> findByUser(String userName) {
        if (userName == null || "".equals(userName) || SysConstants.ADMIN.equalsIgnoreCase(userName)) {
            return sysMenuMapper.findAll();
        }
        return sysMenuMapper.findByUserName(userName);
    }

    private void findChildren(List<SysMenu> SysMenus, List<SysMenu> menus, int menuType) {
        for (SysMenu SysMenu : SysMenus) {
            List<SysMenu> children = new ArrayList<>();
            for (SysMenu menu : menus) {
                if (menuType == 1 && menu.getType() == 2) {
                    // 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
                    continue;
                }
                if (SysMenu.getId() != null && SysMenu.getId().equals(menu.getParentId())) {
                    menu.setParentName(SysMenu.getName());
                    menu.setLevel(SysMenu.getLevel() + 1);
                    if (!exists(children, menu)) {
                        children.add(menu);
                    }
                }
            }
            SysMenu.setChildren(children);
            children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
            findChildren(children, menus, menuType);
        }
    }

    private boolean exists(List<SysMenu> sysMenus, SysMenu sysMenu) {
        boolean exist = false;
        for (SysMenu menu : sysMenus) {
            if (menu.getId().equals(sysMenu.getId())) {
                exist = true;
            }
        }
        return exist;
    }

    @Override
    public List<SysMenu> findMenusByRoleName(Set<String> roleNames, Long schoolId) {
        return sysMenuMapper.selectMenusByRoleName(schoolId, roleNames);
    }

    @Override
    public List<String> findPermsByRoleName(Set<String> roleNames, Long schoolId) {
        List<String> permissions = sysMenuMapper.selectPermsByRoleName(schoolId, roleNames);

        return permissions;
//        if (CollectionUtils.isEmpty(permissions)) {
//            return null;
//        }
//
//        return permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
    }

    @Override
    public int updateByNodeCode(int isOpen, Long schoolId, String nodeCode) {
        return sysMenuMapper.updateByNodeCode(isOpen, schoolId, nodeCode);
    }
}
