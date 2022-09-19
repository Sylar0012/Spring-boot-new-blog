package site.metacoding.red.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.loves.Loves;
import site.metacoding.red.domain.loves.LovesDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.DetailDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;
import site.metacoding.red.web.dto.response.loves.LovesDto;

@RequiredArgsConstructor
@Service
public class BoardsService {
	private final UsersDao usersDao;
	private final BoardsDao boardsDao;
	private final LovesDao lovesDao;
	private final HttpSession session;

	public void 좋아요취소(Integer id) {
		lovesDao.deleteById(id);
	}

	public void 좋아요(Loves loves) {
		lovesDao.insert(loves);
	}

	public PagingDto 게시글목록보기(@Param("page") Integer page, @Param("keyword") String keyword) {
		if (page == null) {
			page = 0;
		}
		int startNum = page * 5;
		List<MainDto> boardsList = boardsDao.findAll(startNum, keyword);
		PagingDto pagingDto = boardsDao.paging(page, keyword);

		if (boardsList.size() == 0)
			pagingDto.setNotResult(true);

		pagingDto.makeBlockInfo(keyword);
		pagingDto.setMainDtos(boardsList);

		return pagingDto;

	}

	public Boards 게시글수정화면데이터가져오기(Integer id) {
		return boardsDao.findById(id);
	}

	public DetailDto 게시글상세보기(Integer id, Integer principalId) {
	      Boards boardsPS = boardsDao.findById(id);
	      LovesDto lovesDto = lovesDao.findByBoardsId(id, principalId);   
	      if(lovesDto == null) {
	         lovesDto = new LovesDto();
	         lovesDto.setCount(0);
	         lovesDto.setLoved(false);
	      }
	      System.out.println("============");
	      System.out.println("lovesId : "+lovesDto.getLovesId());
	      System.out.println("============");
	      return new DetailDto(boardsPS, lovesDto);
	   }
	public void 게시글수정하기(Integer id, UpdateDto updateDto) {
		Users principal = (Users) session.getAttribute("principal");
		Boards boardsPS = boardsDao.findById(id);

		boardsPS.update(updateDto);

		boardsDao.update(boardsPS);
	}

	public void 게시글삭제하기(Integer id) {
		boardsDao.deleteById(id);
	}

	public void 게시글쓰기(WriteDto writeDto) {
		Users principal = (Users) session.getAttribute("principal");
		boardsDao.insert(writeDto.toEntity(principal.getId()));
	}

}
