package site.metacoding.red.domain.users;

import java.util.List;

import site.metacoding.red.web.dto.request.users.LoginDto;

public interface UsersDao {
	public void insert(Users users);
	public Users login(LoginDto loginDto);
	public Users findByUsername(String username);
	public List<Users> findAll();
	public Users findById(Integer id);
	public void update(Users users);
	public void deleteById(Integer id);
}
