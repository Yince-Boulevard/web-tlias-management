package com.yince.interceptor;

import com.yince.pojo.JwtProperties;
import com.yince.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private JwtProperties jwtProperties;
    /**
     * 请求处理之前调用
     * @param request 请求
     * @param response 响应
     * @param handler 处理器
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 3. 如果不是登录请求，则需要校验token
        String token = request.getHeader("token");

        // 判断token是否为空
        if (token == null || token.isEmpty()) {
            // 响应错误信息
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("token is null");
            return false;
        }
        // 校验token
        try {
            // 获取token
            Claims claims = jwtUtils.parseJWT(jwtProperties.getUserSecretKey(), token);
        } catch (Exception e) {
            // 响应错误信息
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("token is error");
            return false;
        }

        return true;
    }

    /**
     * 请求处理之后调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("请求处理之后调用");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 请求处理完成之后调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("试图渲染完成之后调用");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
