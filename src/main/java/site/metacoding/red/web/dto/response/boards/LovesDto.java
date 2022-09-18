package site.metacoding.red.web.dto.response.boards;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LovesDto {
	private Integer usersId;
	private Integer boardsId;
	private Integer totalLoves;
	private boolean IsLovesCheck;
	
	public void lovesInfo(Integer usersId, Integer boardsId) {
		this.usersId = usersId;
		this.boardsId = boardsId;
	}

}
