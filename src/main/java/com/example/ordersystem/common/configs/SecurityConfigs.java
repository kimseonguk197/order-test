package com.example.ordersystem.common.configs;

import com.example.ordersystem.common.auth.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //pre : 사전검증, post : 사후검증
public class SecurityConfigs {
    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .cors().and() //CORS활성화
                .httpBasic().disable()
                .authorizeRequests()
                    .antMatchers("/member/create", "/", "/doLogin", "/refresh-token", "/product/list", "/member/reset-password")
                    .permitAll()
                .anyRequest().authenticated()
                .and()
//                세션로그인이 아닌 stateless한 token을 사용하겠다라는 의미
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
                .and()
//                로그인시 사용자는 서버로부터 토큰을 발급받고,
//                매요청마다 해당 토큰을 http header 넣어 요청
//                아래 코드는 사용자로부터 받아온 토큰이 정상인지 아닌지를 검증하는 코드
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }
}
