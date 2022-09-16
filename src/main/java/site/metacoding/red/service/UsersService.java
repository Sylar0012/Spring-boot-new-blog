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
public class UsersService {
	
	private final UsersDao usersDao;
	private final BoardsDao boardsDao;
	
	
	public void 회원가입(JoinDto joinDto) { // username, password, email
		//1. Dto를 entity로 변경하는 코드.
		Users users = joinDto.toEntity();
		//2. entity로 DB수행
		usersDao.insert(users);
	}
	
	public Users 로그인(LoginDto loginDto) { //username, password
		Users usersPS= usersDao.findByUsername(loginDto.getUsername());
		
		if(usersPS == null) {
			return null;
		}
		// if로 usersPs의 password와 Dto password 비교
		if(!(usersPS.getPassword().equals(loginDto.getPassword()))) {
			return null;
		}
		
		return usersPS;
		
	} 
	
	public Users 회원수정(Integer id, UpdateDto updateDto) { // id, Dto(password, email)
		// 1. 영속화
		Users usersPS = usersDao.findById(id);
		
		// 2. 영속화된 객체 변경
		usersPS.update(updateDto);
		
		// 3. DB 수행
		usersDao.update(usersPS);
		
		return usersPS;
		
	}
	
	@Transactional(rollbackFor = RuntimeException.class)
	public void 회원탈퇴(Integer id) { // users - delete, boards -update
		usersDao.deleteById(id);
		
		boardsDao.updateByUsersId(id);
	}
	
	public boolean 유저이름중복확인(String username) {
		Users usersPS = usersDao.findByUsername(username);		
		// 있으면 true, 없으면 false
		if (usersPS == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public Users 회원정보보기(Integer id) {
		Users usersPS = usersDao.findById(id);
		return usersPS;
	}
}
