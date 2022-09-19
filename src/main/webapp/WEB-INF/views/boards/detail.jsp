<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<input id="page" type ="hidden" value="${sessionScope.referer.page }">
	<input id="keyword" type ="hidden" value="${sessionScope.referer.keyword }">
	<br /> <br />
	<div class="d-flex">
		<input id="uid" type="hidden" value="${principal.id}"> <a
			href="/boards/${boards.id}/updateForm" class="btn btn-warning">수정하러가기</a>
		<form>
			<input id="bid" type="hidden" value="${boards.id}">
			<button id="btnDelete" type="button" class="btn btn-danger">삭제</button>
		</form>
	</div>


	<br />
	<div class="d-flex justify-content-between">
		<h3>${boards.title }</h3>
		<div>
			좋아요 : ${loves2.totalLoves} <i id="iconHeart"
				class="fa-regular fa-heart"></i>
		</div>
	</div>
	<hr />

	<div>${boards.content }</div>
	<hr />
	<div>
		<p>LovesDto.usersId : ${loves1.usersId}</p>
		<p>LovesDto.boardsId : ${loves1.boardsId}</p>
		<p>LovesDto.totalLoves : ${loves2. totalLoves}</p>
		<p>LovesDto.LovesCheck : ${loves2. lovesCheck}</p>
		<p>LovesDto.loves : ${loves2.loves}</p>
		<p>currentPage : ${referer.page}</p>
		<p>keyword : ${referer.keyword }</p>
	</div>
</div>

<script>
	$("#iconHeart").click(()=>{
		love();
	});

	$("#btnDelete").click(()=>{
		deleteById();
	});
	
	function deleteById(){
		let id = $("#bid").val();
		let page = $("#page").val();
		let keyword = $("#keyword").val();	
		$.ajax("/boards/"+id, {
			type: "DELETE",
			dataType: "json"
		}).done((res) => {
			if (res.code == 1) {
				location.href = "/boards?page="+page+"&keyword="+keyword;
			} else {
				alert("게시글 삭제실패")
			}
		});
	}
	
	function love(){
		let check = $("#iconHeart").hasClass("fa-regular");
		let data = {
				usersId : $("#uid").val(),
				boardsId : $("#bid").val()
		};
		let id = $("#bid").val();
		
		if(check == true){
			$("#iconHeart").removeClass("fa-regular");
			$("#iconHeart").addClass("fa-solid");
			$("#iconHeart").css("color","red");
			
			$.ajax("/loves/"+id, {
				type: "POST",
				dataType: "json",
				data: JSON.stringify(data),
				headers: {
					"Content-Type": "application/json"
				}
			}).done((res) => {
				console.log(res);
				if (res.code == 1) {
					location.href = "/boards/"+id;
					alert("좋아요 누름 성공 ")
				} else {
					alert("좋아요 누름 실패");
				}
			});
		}else{
			$("#iconHeart").removeClass("fa-solid");
			$("#iconHeart").addClass("fa-regular");
			$("#iconHeart").css("color","black");
			
			$.ajax("/loves/"+id, {
				type: "DELETE",
				dataType: "json"
			}).done((res) => {
				if (res.code == 1) {
					location.href = "/boards/"+id;
					alert("좋아요 취소")
				} else {
					alert("좋아요 취소 실패")
				}
			});
		}
	}
</script>

<%@ include file="../layout/footer.jsp"%>

