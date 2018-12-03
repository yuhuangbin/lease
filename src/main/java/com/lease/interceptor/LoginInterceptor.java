package com.lease.interceptor;

import com.alibaba.fastjson.JSON;
import com.lease.api.ApiResponse;
import com.lease.api.ResponseInfo;
import com.lease.config.EscapeLogin;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * Description:
 * author: yu.hb
 * Date: 2018-12-01
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            EscapeLogin escapeLogin = handlerMethod.getMethodAnnotation(EscapeLogin.class);
            if (escapeLogin != null) {
                return true;
            }
        }
        Object userInfo = request.getSession().getAttribute("userInfo");
        if (userInfo == null) {
            handleFailure(response, ResponseInfo.LOGIN_EXPRIED);
            return false;
        }
        return true;
    }

    private void handleFailure(HttpServletResponse response, ResponseInfo responseInfo) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try {
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(new ApiResponse(responseInfo)));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
