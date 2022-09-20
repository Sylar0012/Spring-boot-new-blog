package site.metacoding.red.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.loves.Loves;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.service.BoardsService;
import site.metacoding.red.service.UsersService;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.CMRespDto;
import site.metacoding.red.web.dto.response.boards.DetailDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {
	private final BoardsService boardsService;
	private final HttpSession session;

	// 어떤게시글을 누가 좋아하는지 ( boardsId, usersId )
	@PostMapping("/boards/{id}/loves")
	public @ResponseBody CMRespDto<?> insertLoves(@PathVariable Integer id) {
		Users principal = (Users) session.getAttribute("principal");
		Loves loves = new Loves(principal.getId(), id);
		boardsService.좋아요(loves);
		return new CMRespDto<>(1, "좋아요 누름", loves);
	}

	@DeleteMapping({"/boards/{id}/loves/{lovesId}"})
	public @ResponseBody CMRespDto<?> deleteLoves(@PathVariable(value = "id") Integer id,@PathVariable(value="lovesId") Integer lovesId) {
		boardsService.좋아요취소(lovesId);
		return new CMRespDto<>(1, "좋아요 취소", null);
	}

	@GetMapping({ "/", "/boards" })
	public String getBoardsList(Model model, @Param("page") Integer page, @Param("keyword") String keyword) {
		PagingDto pagingDto = boardsService.게시글목록보기(page, keyword);
		model.addAttribute("pagingDto", pagingDto);
		Map<String, Object> referer = new HashMap<>();
		referer.put("page", pagingDto.getCurrentPage());
		referer.put("keyword", pagingDto.getKeyword());
		session.setAttribute("referer", referer);
		return "boards/main";
	}

	@GetMapping("/boards/{id}")
	public String getBoardDetail(@PathVariable Integer id, Model model) {
		Users principal = (Users) session.getAttribute("principal");
		if(principal == null) {
			model.addAttribute("detailDto", boardsService.게시글상세보기(id,0));
		} else {
			model.addAttribute("detailDto", boardsService.게시글상세보기(id, principal.getId()));
		}
		
		return "boards/detail";
	}

	@GetMapping("/boards/{id}/updateForm")
	public String getBoardsUpdate(@PathVariable Integer id, Model model) {
		Boards boardsPS = boardsService.게시글수정화면데이터가져오기(id);
		model.addAttribute(boardsPS);

		return "/boards/updateForm";
	}

	@PutMapping("/boards/{id}/update")
	public @ResponseBody CMRespDto<?> boardsUpdate(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {
		Boards boardsPS = boardsService.게시글수정화면데이터가져오기(id);
		boardsPS.update(updateDto);
		boardsService.게시글수정하기(id, updateDto);
		return new CMRespDto<>(1, "게시글 수정성공", null);
	}

	@DeleteMapping("/boards/{id}")
	public @ResponseBody CMRespDto<?> getBoardsDelete(@PathVariable Integer id) {
		boardsService.게시글삭제하기(id);
		return new CMRespDto<>(1, "게시글 삭제 성공", null);
	}

	@GetMapping("/boards/writeForm")
	public String getBoardsWrite() {
		return "/boards/writeForm";
	}

	@PostMapping("/boards/write")
	public @ResponseBody CMRespDto<?> boardsWrite(@RequestBody WriteDto writeDto) {
		boardsService.게시글쓰기(writeDto);
		return new CMRespDto<>(1, "게시글 쓰기 성공", null);
	}

}
