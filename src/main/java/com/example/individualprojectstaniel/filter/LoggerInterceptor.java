package com.example.individualprojectstaniel.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class LoggerInterceptor extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.info("-----> Start of request {}", request.getRequestURI());
        filterChain.doFilter(request, response);
        log.info("-----> End of request {} with status code: {}", request.getRequestURI(), response.getStatus());
    }
}
