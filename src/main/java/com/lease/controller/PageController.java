package com.lease.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lease.api.ApiResponse;
import com.lease.domain.Menu;
import com.lease.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-11-30
 */
@RestController
public class PageController {

    @Autowired
    private IMenuService menuService;

    @RequestMapping(value = "/loadMenu")
    public ApiResponse loadMenu() {
        JSONObject data = new JSONObject();

        List<Menu> rootList = menuService.selectById(Menu.ROOT);
        data.put("navList", rootList);
        data.put("menuList",buildMenuList());
        return ApiResponse.getInstance(data);
    }

    private List<Menu> buildMenuList() {
        List<Menu> rootList = menuService.selectById(Menu.ROOT);
        rootList.forEach(menu -> {
            buildNodes(menu);
        });
        return rootList;
    }

    private void buildNodes(Menu menu) {
        List<Menu> nodes = menuService.selectByUpId(menu.getId());
        if (!nodes.isEmpty()) {
            menu.setNodes(nodes);
            nodes.forEach(node-> {
                buildNodes(node);
            });
        }
    }


}
