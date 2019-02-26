package com.lease.controller;

import com.lease.api.ApiResponse;
import com.lease.config.ConfigTest;
import com.lease.config.EscapeLogin;
import com.lease.domain.User;
import com.lease.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-11-30
 */
@RestController
public class LoginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ConfigTest configTest;

    @RequestMapping(value = "/login")
    public ApiResponse login(User user, HttpSession session) {
        return userService.login(user, session);
    }

    @RequestMapping(value = "/loginOut")
    public ApiResponse login(HttpSession session) {
        session.removeAttribute("userInfo");
        return ApiResponse.SUCCESS;
    }
}
