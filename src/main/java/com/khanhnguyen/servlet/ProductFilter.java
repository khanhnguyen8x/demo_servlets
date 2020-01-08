package com.khanhnguyen.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/product/*")
public class ProductFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Init filter product");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filtering stuff...");
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.addHeader("extra-header", "This is a new header from filter");
        chain.doFilter(request, httpResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Destroy filter product");
    }
}
