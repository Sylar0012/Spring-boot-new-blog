package site.metacoding.red.domain.boards;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.web.dto.request.boards.UpdateDto;



@Getter
@Setter
public class Boards {
	private Integer id;
	private String title;
	private String content;
	private Integer usersId;
	private Timestamp createdAt; // At 시 분 초 다 표현함, Dt 연 원 일만 표시.

	public Boards(Integer usersId, String title, String content) {
		this.usersId = usersId;
		this.title = title;
		this.content = content;
		
	}
	
	public void update(UpdateDto updateDto) {
		this.title = updateDto.getTitle();
		this.content = updateDto.getContent();
	}
	
}
