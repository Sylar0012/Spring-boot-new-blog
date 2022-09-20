package site.metacoding.red.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import site.metacoding.red.handler.LoginIntercepter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginIntercepter())
		.addPathPatterns("/s/**");
		//.addPathPatterns("/admin/**")
		//.excludePathPatterns("/s/boards/**");
		
		// /s/* -> /s/boards, /s/users 는 허용, /s/boards/1/updateFrom와 같이 boards/뒤에 뭔가 더 적히면 안먹힘.
	
	}
}
