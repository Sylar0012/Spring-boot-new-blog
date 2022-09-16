<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br /> <br />
		<div class="d-flex">
			<input id="id" type="hidden" value="${boards.id}">
			<a href="/boards/${boards.id}/updateForm" class="btn btn-warning">수정하러가기</a>
			<form>
				<button id="btnDelete" type="button" class="btn btn-danger">삭제</button>
			</form>
		</div>


	<br />
	<div class="d-flex justify-content-between">
		<h3>${boards.title }</h3>
		<div>좋아요 : 10 <i id="iconHeart" class="fa-regular fa-heart" ></i></div>
	</div>
	<hr />

	<div>${boards.content }</div>
	<hr/>
</div>

<script>
	$("#iconHeart").click(()=>{
		let check = $("#iconHeart").hasClass("fa-regular")
		
		if(check == true){
			$("#iconHeart").removeClass("fa-regular");
			$("#iconHeart").addClass("fa-solid");
			$("#iconHeart").css("color","red");
		}else{
			$("#iconHeart").removeClass("fa-solid");
			$("#iconHeart").addClass("fa-regular");
			$("#iconHeart").css("color","black");
		}
	})

	$("#btnDelete").click(()=>{
		let id = $("#id").val();
			resign();
	});
	
	function resign(){
		let id = $("#id").val();

		$.ajax("/boards/"+id+"/delete", {
			type: "DELETE",
			dataType: "json"
		}).done((res) => {
			if (res.code == 1) {
				location.href = "/";
				alert("게시글 삭제성공")
			} else {
				alert("게시글 삭제실패")
			}
		});
	}
	
</script>

<%@ include file="../layout/footer.jsp"%>

