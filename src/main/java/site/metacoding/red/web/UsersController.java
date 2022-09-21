package site.metacoding.red.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.handler.ex.MyApiException;
import site.metacoding.red.service.UsersService;
import site.metacoding.red.util.Script;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;
import site.metacoding.red.web.dto.response.CMRespDto;

@RequiredArgsConstructor
@Controller
public class UsersController {

	private final UsersService usersService;
	private final HttpSession session;

	@GetMapping("users/usernameSameCheck")
	public @ResponseBody CMRespDto<Boolean> usernameSameCheck(String username) {
		boolean isSame = usersService.유저이름중복확인(username);
		return new CMRespDto<>(1, "성공", isSame);
	}

	@GetMapping("/joinForm")
	public String joinForm() {
		return "users/joinForm";
	}

	@GetMapping("/loginForm")
	public String loginForm(Model model, HttpServletRequest request) { // 쿠키 배워보기
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("username"))
				model.addAttribute(cookie.getName(), cookie.getValue());
		}
		
		return "users/loginForm";
	}

	@PostMapping("/api/join")
	public @ResponseBody CMRespDto<?> join(@RequestBody JoinDto joinDto) {
		//유효성 검사
		if(joinDto.getUsername().length()>20) {
			throw new MyApiException("usersname의 길이가 너무 깁니다");
		}
		usersService.회원가입(joinDto);
		return new CMRespDto<>(1, "회원가입 성공", null);
	}

	@PostMapping("/api/login")
	public @ResponseBody CMRespDto<?> join(@RequestBody LoginDto loginDto, HttpServletResponse response) {
		if(loginDto.isRemember()) {
			Cookie cookie = new Cookie("username",loginDto.getUsername());
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
			//response.setHeader("Set-Cookie", "username="+loginDto.getUsername());
		}else {
			Cookie cookie = new Cookie("username",null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		Users principal = usersService.로그인(loginDto);
		if (principal == null) {
			return new CMRespDto<>(-1, "로그인 실패", null);
		}
		session.setAttribute("principal", principal);
		return new CMRespDto<>(1, "로그인 성공", null);
	}
	
	//인증 필요
	@GetMapping("/s/users/{id}")
	public String updateForm(@PathVariable Integer id, Model model) {
		Users usersPS = usersService.회원정보보기(id);
		model.addAttribute("users", usersPS);
		return "users/updateForm";
	}
	
	//인증 필요
	@PutMapping("/s/api/users/{id}")
	public @ResponseBody CMRespDto<?> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {
		Users usersPS = usersService.회원수정(id, updateDto);
		session.setAttribute("principal", usersPS);
		return new CMRespDto<>(1, "회원정보 수정 성공", null);
	}
	
	//인증 필요
	@DeleteMapping("/s/api/users/{id}")
	public @ResponseBody CMRespDto<?> delete(@PathVariable Integer id) {
		usersService.회원탈퇴(id);
		session.invalidate();
		return new CMRespDto<>(1, "회원탈퇴 성공", null);
	}

	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/loginForm";
	}
}
