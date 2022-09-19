package site.metacoding.red.domain.loves;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Loves {
	private Integer id;
	private Integer usersId;
	private Integer boardsId;
	private Timestamp createdAt;
	
	public Loves(Integer usersId, Integer boardsId) {
		this.usersId = usersId;
		this.boardsId = boardsId;
	}	

}
