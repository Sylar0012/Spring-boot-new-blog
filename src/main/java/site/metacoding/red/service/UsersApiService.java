package site.metacoding.red.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;


@RequiredArgsConstructor
@Service
public class UsersApiService {
	
	private final UsersDao usersDao;
	private final BoardsDao boardsDao;
	
	
	public void 회원가입(JoinDto joinDto) { // username, password, email
		//1. Dto를 entity로 변경하는 코드.
		Users users = joinDto.toEntity();
		//2. entity로 DB수행
		usersDao.insert(users);
	}
	
	public Users 로그인(LoginDto loginDto) { //username, password
		Users usersPs = usersDao.findByUsername(loginDto.getUsername());

		// if로 usersPs의 password와 Dto password 비교
		if(!(usersPs.getPassword().equals(loginDto.getPassword()))) {
			return null;
		}
		
		return usersPs;
		
	} 
	
	public void 회원수정(Integer id, UpdateDto updateDto) { // id, Dto(password, email)
		// 1. 영속화
		Users usersPs = usersDao.findById(id);
		
		// 2. 영속화된 객체 변경
		usersPs.update(updateDto);
		
		// 3. DB 수행
		usersDao.update(usersPs);
		
	}
	
	@Transactional(rollbackFor = RuntimeException.class)
	public void 회원탈퇴(Integer id) { // users - delete, boards -update
		usersDao.deleteById(id);
		
		boardsDao.UserDelete(id);
	}
	
	public boolean 유저이름중복확인(String username) {
		Users usersPs = usersDao.usernameCheck(username);
		
		// 있으면 true, 없으면 false
		if (usersPs.getUsername().equals(username) ) {
			
			return true;
		}

		return false;
		
	}
	
	public Users 회원정보보기(Integer id) {
		Users usersPs = usersDao.findById(id);
		return usersPs;
	}
}
