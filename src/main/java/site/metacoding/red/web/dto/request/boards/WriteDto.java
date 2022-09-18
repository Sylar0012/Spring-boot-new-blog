package site.metacoding.red.web.dto.request.boards;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;

@Getter
@Setter
public class WriteDto {
	private Integer usersId;
	private String title;
	private String content;
	
	public Boards toEntity(Integer usersid) {
		Boards boards = new Boards( usersid,this.title, this.content);
		return boards;
	}
	
}
