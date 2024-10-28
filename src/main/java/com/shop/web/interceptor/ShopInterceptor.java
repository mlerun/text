package com.shop.web.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.web.common.JWTUtils;
import com.shop.web.common.TokenBlacklist;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public class ShopInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token==null){
            response.sendRedirect("http://localhost:8080/api/admin/error");
            return false;
        }
        if (TokenBlacklist.isBlacklisted(token)){
            response.sendRedirect("http://localhost:8080/api/admin/error");
            return false;
        }
        Map<String,String> map = JWTUtils.getClaims(token);
        if (map.get("shopId")==null){
            response.sendRedirect("http://localhost:8080/api/admin/error");
            return false;
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

