package com.lease.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lease.api.ApiResponse;
import com.lease.domain.Menu;
import com.lease.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
        return ApiResponse.getInstance(menuService.selectMenuList());
    }

    @RequestMapping("/cacheEvict")
    public ApiResponse CacheEvict() {
        return ApiResponse.getInstance(menuService.CacheEvict());
    }
}
