package site.metacoding.red.web.dto.response.boards;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LovesDto {
	private Integer usersId;
	private Integer boardsId;
	private Integer totalLoves;
	private Integer lovesCheck;
	private boolean isLoves;
	
	
	public void lovesInfo(Integer usersId, Integer boardsId) {
		this.usersId = usersId;
		this.boardsId = boardsId;
	}
	
	public void lovesChecking(Integer lovesCheck, boolean isLoves) {
		if (lovesCheck == 1) {
			this.isLoves = true;
		}else {
			this.isLoves = false;
		}
	}

}
