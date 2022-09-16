<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br />
	<form>
		<button id="btnDelete" type="button" class="btn btn-danger">회원탈퇴</button>
	</form>
	<form>
		<input id="id" type="hidden" value="${users.id}">
		<div class="mb-3 mt-3">
			<input type="text" class="form-control" placeholder="Enter username"
				value="${users.username}" readonly="readonly">
		</div>
		<div class="mb-3">
			<input id="password" type="password" class="form-control"
				placeholder="Enter password">
		</div>
		<div class="mb-3">
			<input id="email" type="email" class="form-control"
				placeholder="Enter email" value="${users.email }">
		</div>
		<button id="btnUpdate" type="button" class="btn btn-primary">회원정보수정</button>
	</form>
</div>
<script>
$("#btnUpdate").click(()=>{
	// 0. 통신 오브젝트 생성
	let data ={
			password:$("#password").val(),
			email:$("#email").val()
	};
	
	let id =$("#id").val();
	$.ajax("/users/"+id,{
		type:"PUT",
		dataType:"json",
		data : JSON.stringify(data),
		headers:{
			"Content-Type" : "application/json; charset=utf-8"
		}
	}).done((res)=>{
		if(res.code == 1){
			alert("회원정보가 수정되었습니다");
			location.href="/";
		}else{
			alert("비밀번호 또는 이메일 입력이 잘못되었습니다.")
		}
	});
});

$("#btnDelete").click(()=>{
	let id =$("#id").val();
	
	$.ajax("/users/"+id,{
		type:"DELETE",
		dataType:"json"
	}).done((res)=>{
		if(res.code ==  1){
			location.href="/";
		}else{
			alert("회원탈퇴 실패")
		}
	})
})
</script>

<%@ include file="../layout/footer.jsp"%>

