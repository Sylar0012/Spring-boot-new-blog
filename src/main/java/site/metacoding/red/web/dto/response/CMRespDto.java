package site.metacoding.red.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class CMRespDto<T> { // 공통응답 DTO
	private Integer code; // 1 정상, -1 실패
	private String msg;   // 실패의 이유, 성공의 이유
	private T data;         // 응답할 데이터
}
