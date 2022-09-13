package site.metacoding.red.web.dto.response.boards;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingDto {
	private boolean notResult;
	private Integer startNum;
	private Integer totalCount;
	private Integer totalPage;
	private Integer currentPage;
	private boolean isLast;
	private boolean isFirst;
	private Integer startPageNum;
	private Integer lastPageNum; 
	private Integer blockCount;
	private Integer currentBlock;
	private String keyword;
	private Integer id;
	private Integer page;
	private List<MainDto> mainDtos;

	
	public void makeBlockInfo(String keyword) {
		this.blockCount = 5;
		this.keyword = keyword;
		this.currentBlock = currentPage/blockCount;
		this.startNum = 1+(currentPage/blockCount);
		this.lastPageNum = startNum+blockCount-1;
		if(totalPage < lastPageNum) {
			this.lastPageNum = totalPage;
		}
	}

}
