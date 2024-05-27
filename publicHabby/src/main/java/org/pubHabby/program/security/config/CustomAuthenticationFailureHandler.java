package org.pubHabby.program.security.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

public class CustomAuthenticationFailureHandler  implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // 로그인 실패 시 처리할 작업 수행
        // 예외 유형에 따라 다른 메시지를 설정하거나, 특정 로직 수행
        String errorMessage;
        if (exception instanceof UsernameNotFoundException) {
            errorMessage = "존재하지 않는 아이디 입니다.";
        } else if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
            errorMessage = "아이디 또는 비밀번호가 맞지 않습니다.";
        } else {
            errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요.";
        }

        String encodedErrorMessage = URLEncoder.encode(errorMessage, "UTF-8");
        response.sendRedirect("/login?error=true&exception=" + encodedErrorMessage);



    }
}