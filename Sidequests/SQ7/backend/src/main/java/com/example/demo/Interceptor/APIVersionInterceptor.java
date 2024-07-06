package com.example.demo.Interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class APIVersionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String apiVersion = request.getHeader("X-API-Version");
        if (apiVersion != null) {
            if (apiVersion.equals("1")) {
                request.getRequestDispatcher("/api/v1" + request.getRequestURI()).forward(request, response);
            } else if (apiVersion.equals("2")) {
                request.getRequestDispatcher("/api/v2" + request.getRequestURI()).forward(request, response);
            }
        }
        return true;
    }
}
