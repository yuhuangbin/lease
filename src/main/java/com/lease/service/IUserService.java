package com.lease.service;

import com.lease.api.ApiResponse;
import com.lease.domain.User;

import javax.servlet.http.HttpSession;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-11-30
 */
public interface IUserService {
    ApiResponse login(User user,  HttpSession session);
}
