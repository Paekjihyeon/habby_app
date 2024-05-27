package org.pubHabby.program.security.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class AccessDeny implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // FORBIDDEN 상태 처리
        request.setAttribute("msg", "권한이 없는 사용자입니다.");
        request.setAttribute("nextPage", "/login");
        request.getRequestDispatcher("/error/redirect").forward(request, response);
    }
}
