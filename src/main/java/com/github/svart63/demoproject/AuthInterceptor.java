package com.github.svart63.demoproject;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = String.valueOf(request.getSession().getAttribute("token"));
        if (token==null) {
            response.sendRedirect("/#/login");
        }
        return true;
    }
}
