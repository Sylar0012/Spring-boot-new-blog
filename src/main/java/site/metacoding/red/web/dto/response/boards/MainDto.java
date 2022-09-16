package site.metacoding.red.web.dto.response.boards;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class MainDto {
	private Integer id;
	private String title;
	private String username;
	private PagingDto pagingDto;
	

}
