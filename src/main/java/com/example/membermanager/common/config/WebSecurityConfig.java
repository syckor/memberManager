package com.example.membermanager.common.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/member","member/signIn").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthFilter(jwtUtil) , UsernamePasswordAuthenticationFilter.class);

        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException)->{
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.setContentType("application/json; charset=utf8");
                            response.getWriter().write(
                                    new ObjectMapper().writeValueAsString("인증 실패")
                            );
                        }
                )
                .and().headers().frameOptions().disable()
                .and().cors();

        return httpSecurity.build();
    }
}
