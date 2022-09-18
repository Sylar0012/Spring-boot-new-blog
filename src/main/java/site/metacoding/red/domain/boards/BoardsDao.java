package site.metacoding.red.domain.boards;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.LovesDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

public interface BoardsDao {
	public void insert(Boards boards);
	public List<MainDto> findAll(@Param("startNum") int startNum, @Param("keyword")String keyword);
	public Boards findById(Integer id);
	public void update(Boards boards);
	public void deleteById(Integer id);
	public void updateByUsersId(Integer id);
	public PagingDto paging(@Param("page") Integer page, @Param("keyword")String keyword);
	public List<MainDto> findSearch();
	public void love(LovesDto lovesDto);
	public void disLove(LovesDto lovesDto);
	public LovesDto lovesChecking (@Param ("usersId") Integer usersId, @Param ("boardsId")Integer boardsId);
	public LovesDto totalLoves(Integer boardsId);


	
	
}
