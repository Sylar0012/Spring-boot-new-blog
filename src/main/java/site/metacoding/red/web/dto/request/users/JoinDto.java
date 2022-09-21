package site.metacoding.red.web.dto.request.users;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.users.Users;

@Setter
@Getter
public class JoinDto {
	
	@Size(min = 2, max = 20, message="username은 최소 2자, 최대 20자 입력 가능합니다")
	@NotBlank(message ="username이 Null이거나 공백일수 없습니다.")
	private String username;
	@Size(min = 2, max = 20, message="password은 최소 2자, 최대 20자 입력 가능합니다")
	@NotBlank(message ="password이 Null이거나 공백일수 없습니다.")
	private String password;
	@Size(min = 4, max = 50, message="email은 최소 4자, 최대 50자 입력 가능합니다")
	@NotBlank(message ="email이 Null이거나 공백일수 없습니다.")
	private String email;
	
	public Users toEntity() {
		Users users = new Users(this.username, this.password, this.email);
		return users;
	}
}
