package com.tosinsa.toy.interceptor;


import com.tosinsa.toy.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("authorization check intercept start {}", requestURI);

        HttpSession session = request.getSession();
        if (session == null || (session.getAttribute(SessionConst.LOGIN_USER) == null && session.getAttribute(SessionConst.LOGIN_ADMINISTER) == null)){
            log.info("unauthorized user request");
            //로그인으로 redirect
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }
        return true;
    }
}
