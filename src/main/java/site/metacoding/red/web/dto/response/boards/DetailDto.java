package site.metacoding.red.web.dto.response.boards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.web.dto.response.loves.LovesDto;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetailDto {
	private Boards boards;
	private LovesDto lovesDto;
}
