package org.pubHabby.program.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pubHabby.program.projectEnum.ApiAddress;
import org.pubHabby.program.security.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.ErrorResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Slf4j
@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailService userDetailsService;

    private static final String[] WHITE_LIST = {
            "/",
            "/api/**",
            "/login"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowCredentials(true);
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));   //도메인
                    config.setAllowedMethods(Collections.singletonList("*"));   //메소드
                    config.setAllowedHeaders(Collections.singletonList("*"));   //헤더
                    source.registerCorsConfiguration("/api/**", config);

                    config.setMaxAge(3600L); //1시간
                    return config;
                }
            }))
            .csrf(csrf -> {
                csrf.disable();
                csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
            })
            .headers(headersConfigurer -> headersConfigurer
                    .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                    .contentSecurityPolicy(policy -> policy.policyDirectives("script-src 'self'"))
                    .disable()
            )
            .authorizeHttpRequests((request) -> request
                    .requestMatchers(WHITE_LIST).permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin((formLogin) -> formLogin
                    .loginPage("/login")
                    .usernameParameter("userId")
                    .passwordParameter("password")
                    .loginProcessingUrl("/api/login")
                    .defaultSuccessUrl("/", true)
                    .failureHandler(new CustomAuthenticationFailureHandler())
            )
            .userDetailsService(userDetailsService)
            .logout((logout) -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
            )
            .sessionManagement(sessionManagement ->
                    sessionManagement
                            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                            .sessionFixation().migrateSession()
                            .maximumSessions(1).maxSessionsPreventsLogin(false)
                            .expiredUrl("/")
            );
        return http.build();
    }
    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return new AccessDeny();
    }
    private final AuthenticationEntryPoint unauthorizedEntryPoint =
            (request, response, authException) -> {
                ErrorResponse fail = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Spring security unauthorized...");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                String json = new ObjectMapper().writeValueAsString(fail);

                log.error("Request Uri : {}", request.getRequestURI());
                log.error(json);

                request.setAttribute("msg", "로그인이 필요합니다. 다시 로그인 해주세요.");
                request.setAttribute("nextPage", "/login");
                request.getRequestDispatcher("/error/redirect").forward(request, response);
            };

    @Getter
    @RequiredArgsConstructor
    public class ErrorResponse {

        private final HttpStatus status;
        private final String message;
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // 모든 도메인 허용
        config.addAllowedHeader("*"); // 모든 헤더 허용
        config.addAllowedMethod("*"); // 모든 메소드 허용
        source.registerCorsConfiguration("/api/**", config); // "/api/**" 경로에 대해 CORS 적용
        return new CorsFilter(source);
    }
}
