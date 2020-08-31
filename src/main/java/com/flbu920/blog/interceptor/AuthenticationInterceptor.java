package com.flbu920.blog.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.flbu920.blog.annotation.AdminUserLoginToken;
import com.flbu920.blog.annotation.PassToken;
import com.flbu920.blog.model.AdminUser;
import com.flbu920.blog.service.AdminUserService;
import com.flbu920.blog.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private AdminUserService userService;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        log.info("开始进行Token拦截");
        String token = httpServletRequest.getHeader("token");
        // 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(AdminUserLoginToken.class)) {
            AdminUserLoginToken userLoginToken = method.getAnnotation(AdminUserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (StringUtils.isEmpty(token)) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                Integer adminUserId;
                try {
                    adminUserId = TokenUtil.getUserId(token);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                AdminUser user = userService.getAdminUserById(adminUserId);
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                if (!tokenUtil.verify(token)){

                    throw new RuntimeException("token信息错误，请登录");
                }
//                // 验证 token
//                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getLoginPassword())).build();
//                try {
//                    jwtVerifier.verify(token);
//                } catch (JWTVerificationException e) {
//                    throw new RuntimeException("401");
//                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
        log.info("HandlerInterceptor 拦截完成");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}