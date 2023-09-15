package seb45.test.pettalk.interceptor;


import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {

    @PostConstruct
    public void init() {
        log.info("[aop-test] interceptor : RequestInterceptor init()");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("[aop-test] interceptor : RequestInterceptor preHandle()");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        log.info("[aop-test]  interceptor : RequestInterceptor afterCompletion()");
    }

}
