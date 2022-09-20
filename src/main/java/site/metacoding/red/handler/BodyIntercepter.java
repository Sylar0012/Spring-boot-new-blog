package site.metacoding.red.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.red.web.dto.response.CMRespDto;

public class BodyIntercepter implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		BufferedReader br = request.getReader();
		StringBuilder sb = new StringBuilder();
		//StringBuilder : 스트링 List 버전
		
		while(true) {
			String temp = br.readLine();
			// temp에 request.getReader()값 저장
			if(temp == null)
				break;
			// temp에 값이 없으면 break
			sb.append(temp);
			// StringBuilder에 temp에 들어간값 집어넣기
		}
			if(sb.toString().contains("바보")) {
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = response.getWriter();
				CMRespDto<?> cmRespDto = new CMRespDto<>(-1, "니얼굴", null);
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(cmRespDto);
				out.println(json);
				return false;
			}
		return true;
	}

}
