package com.example.holaserver.Auth;

import com.example.holaserver.Common.response.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String exception = (String)request.getAttribute("exception");

        if(exception == null) {
            setResponse(response, "UNKNOWN_ERROR");
        }
        //잘못된 타입의 토큰인 경우
        else if(exception.equals("WRONG_TYPE_TOKEN")) {
            setResponse(response, "WRONG_TYPE_TOKEN");
        }
        //토큰 만료된 경우
        else if(exception.equals("EXPIRED_TOKEN")) {
            setResponse(response, "EXPIRED_TOKEN");
        }
        //지원되지 않는 토큰인 경우
        else if(exception.equals("UNSUPPORTED_TOKEN")) {
            setResponse(response, "UNSUPPORTED_TOKEN");
        }
        else {
            setResponse(response, "ACCESS_DENIED");
        }
    }
    //한글 출력을 위해 getWriter() 사용
    private void setResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", message);
        responseJson.put("status", HttpStatus.UNAUTHORIZED);

        response.getWriter().print(responseJson);
    }
}