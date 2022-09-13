package site.metacoding.red.web;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.service.BoardsService;
import site.metacoding.red.service.UsersService;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {
	private final BoardsService boardsService;
	private final HttpSession session;
	
	@GetMapping({"/","/boards"})
	public String getBoardsList(Model model, @Param("page") Integer page, @Param("keyword")String keyword) {
		PagingDto pagingDto = boardsService.게시글목록보기(page, keyword);
		model.addAttribute("pagingDto", pagingDto);
		return "boards/main";
	}
	
	@GetMapping("/boards/{id}")
	public String getBoardsDetail(@PathVariable Integer id, Model model) {
		Boards boardsPS = boardsService.게시글상세보기(id);
		model.addAttribute(boardsPS);
		return "boards/detail";
	}
	
	@GetMapping("/boards/{id}/updateForm")
	public String getBoardsUpdate(@PathVariable Integer id, Model model) {
		Boards boardsPS = boardsService.게시글상세보기(id);
		model.addAttribute(boardsPS);
		return"/boards/updateForm";
	}
	
	@PostMapping("/boards/{id}/update")
	public String boardsUpdate(@PathVariable Integer id, UpdateDto updateDto) {
		Boards boardsPS = boardsService.게시글상세보기(id);
		boardsPS.update(updateDto);
		boardsService.게시글수정하기(id, updateDto);
		return "redirect:/boards/"+id;
	}
	
	@PostMapping("/boards/{id}/delete")
	public String getBoardsDelete(@PathVariable Integer id) {
		boardsService.게시글삭제하기(id);
		return"redirect:/";
	}
	
	@GetMapping("/boards/writeForm")
	public String getBoardsWrite() {
		return"/boards/writeForm";
	}
	
	@PostMapping("/boards/write")
	public String boardsWrite(WriteDto writeDto) {
		boardsService.게시글쓰기(writeDto);
		return"redirect:/";
	}
}
