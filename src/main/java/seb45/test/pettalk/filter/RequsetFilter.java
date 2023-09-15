package seb45.test.pettalk.filter;

import java.io.IOException;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequsetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("[aop-test] jakarta.servlet.Filter : RequsetFilter init()");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        log.info("[aop-test] jakarta.servlet.Filter : RequsetFilter doFilter()");
        log.info("[aop-test] jakarta.servlet.Filter : RequsetFilter url : " + request.getServerName());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("[aop-test] jakarta.servlet.Filter : RequsetFilter destroy()");

    }

}
