package site.metacoding.red.web;

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
	public String loginForm() { // 쿠키 배워보기
		return "users/loginForm";
	}
	
	@PostMapping("/join")
	public @ResponseBody CMRespDto<?> join(@RequestBody JoinDto joinDto) {
		usersService.회원가입(joinDto);
		return new CMRespDto<>(1, "회원가입 성공", null);
	}
	
	@PostMapping("/login")
	public @ResponseBody CMRespDto<?> join(@RequestBody LoginDto loginDto) {
		Users principal = usersService.로그인(loginDto);
	if(principal == null) {
		return new CMRespDto<>(-1, "로그인 실패", null);
	}
	session.setAttribute("principal", principal);
	return new CMRespDto<>(1, "로그인 성공", null);
	}
	
	@GetMapping("/users/{id}/updateForm")
	public String updateForm(@PathVariable Integer id, Model model) {
		Users usersPS = usersService.회원정보보기(id);
		model.addAttribute("users",usersPS);
		return "users/updateForm";
	}
	
	@PutMapping("users/{id}")
	public  @ResponseBody CMRespDto<?> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {
		Users usersPS = usersService.회원수정(id, updateDto);
		session.setAttribute("principal", usersPS);
		return new CMRespDto<>(1, "회원정보 수정 성공", null);
	}
	
	@DeleteMapping("/users/{id}")
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
