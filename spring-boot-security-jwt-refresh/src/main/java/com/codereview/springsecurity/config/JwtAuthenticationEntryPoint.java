package com.codereview.springsecurity.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		Map<String, Object> resp = new HashMap<>();
		if (request.getAttribute("exception") instanceof Exception) {
			Exception exception = (Exception) request.getAttribute("exception");
			resp.put("message", exception.getMessage());
			resp.put("cause", exception.getCause());
		} else if (!Objects.isNull(request.getAttribute("message"))) {
			resp.put("message", request.getAttribute("message"));
		} else {
			resp.put("message", authException.getMessage());
			resp.put("cause", authException.getCause());
		}
		byte[] body = new ObjectMapper().writeValueAsBytes(resp);
		response.getOutputStream().write(body);
	}

}
