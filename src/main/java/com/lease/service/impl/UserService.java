package com.lease.service.impl;

import com.lease.api.ApiResponse;
import com.lease.domain.User;
import com.lease.mapper.UserMapper;
import com.lease.service.IUserService;
import com.lease.util.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-11-30
 */
@Service
public class UserService implements IUserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public ApiResponse login(User user, HttpSession session) {
        ApiResponse apiResponse = new ApiResponse();
        User record = userMapper.selectByAccount(user.getUserName());
        if (record == null) {
            apiResponse.setCode(-1);
            apiResponse.setMsg("用户不存在");
            return apiResponse;
        }

        String userPwd = user.getUserPwd();
        try{
            if (!BCrypt.checkpw(userPwd,record.getUserPwd())) {
                apiResponse.setCode(-1);
                apiResponse.setMsg("密码错误");
                return apiResponse;
            }
            session.setAttribute("userInfo",record);
        }catch (Exception e) {
            apiResponse.setCode(-1);
            apiResponse.setMsg("登录失败");
        }
        return apiResponse;
    }

}
