package com.lease.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lease.api.ApiResponse;
import com.lease.domain.Menu;
import com.lease.mapper.MenuMapper;
import com.lease.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-11-30
 */
@Service
public class MenuService implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectById(Integer id) {
        return menuMapper.selectById(id);
    }

    @Override
    public List<Menu> selectAll() {
        return menuMapper.selectAll();
    }

    @Override
    public List<Menu> selectByUpId(Integer upId) {
        return menuMapper.selectByUpId(upId);
    }

    @Override
    @Cacheable(cacheNames = "menuList")
    public Map<String, Object> selectMenuList() {
        Map<String,Object> data = new HashMap<>();
        List<Menu> rootList = selectById(Menu.ROOT);
        data.put("navList", rootList);
        data.put("menuList",buildMenuList());
        return data;
    }

    @Override
    @CacheEvict(cacheNames = "menuList")
    public ApiResponse CacheEvict() {
        return ApiResponse.SUCCESS;
    }

    private List<Menu> buildMenuList() {
        List<Menu> rootList = selectById(Menu.ROOT);
        rootList.forEach(menu -> {
            buildNodes(menu);
        });
        return rootList;
    }

    private void buildNodes(Menu menu) {
        List<Menu> nodes = selectByUpId(menu.getId());
        if (!nodes.isEmpty()) {
            menu.setNodes(nodes);
            nodes.forEach(node-> {
                buildNodes(node);
            });
        }
    }
}
