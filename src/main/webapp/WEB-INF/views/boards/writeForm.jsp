<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="mb-3 mt-3">
			<input id="id" type="hidden" value="${boards.usersId}">
			<input id="title" type="text" class="form-control" placeholder="Enter title">
		</div>
		<div class="mb-3">
			<textarea id="content" class="form-control" rows="8"></textarea>
		</div>
		<button id="btnSave" type="button" class="btn btn-primary">글쓰기완료</button>
	</form>
</div>
<script>
	$('#content').summernote({
		height : 400
	});
	
	$("#btnSave").click(()=>{
		save();
	});
	
	
	function save(){
		let data={
				title : $("#title").val(),
				content : $("#content").val(),
				usersid: $("#id").val()
			}
		let id = $("#id").val();
		$.ajax("/boards/write", {
			type: "POST",
			dataType: "json",
			data: JSON.stringify(data),
			headers: {
				"Content-Type": "application/json"
			}
		}).done((res) => {
			console.log(res);
			if (res.code == 1) {
				alert("게시글 입력완료 ")
				location.href="/boards/"+id;

			} else {
				alert("게시글 입력실패");
				location.href = "/boards/"+id;
			}
	});
}
	
</script>

<%@ include file="../layout/footer.jsp"%>

