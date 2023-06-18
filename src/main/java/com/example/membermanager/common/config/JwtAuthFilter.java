package com.example.membermanager.common.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);

        if (token == null) {
            filterChain.doFilter(request, response);//다음 체인으로 보내줌
            return;
        }

        if (!jwtUtil.validateToken(token)) {
            log.info("잘못된 토큰입니다.");
            return;
        }
        Claims info = jwtUtil.getUserInfoFromToken(token);

        SecurityContext ctx = null;
        try {
            ctx = SecurityContextHolder.createEmptyContext();
            Authentication authentication = jwtUtil.createAuthentication(info.getSubject());
            ctx.setAuthentication(authentication);

            SecurityContextHolder.setContext(ctx);
        } catch (UsernameNotFoundException e) {
            log.info("해당 토큰에 해당하는 유저 정보를 찾을 수 없습니다.");
        }


    }

}
