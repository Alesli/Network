package org.itstep.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class SecutiryFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (((HttpServletRequest) request).getRequestURI().compareTo("/login") == 0 ||
                ((HttpServletRequest) request).getRequestURI().compareTo("/") == 0) {
            chain.doFilter(request, response);
        } else {
            Object isLogin = ((HttpServletRequest) request).getSession().getAttribute("isLogin");
            if (isLogin != null && (Boolean) isLogin) {
                chain.doFilter(request, response);
            } else {
                response.getWriter().println("accsess deny");
            }
        }

    }

    @Override
    public void destroy() {

    }
}
