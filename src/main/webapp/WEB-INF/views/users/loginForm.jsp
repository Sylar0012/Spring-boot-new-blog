<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="mb-3 mt-3">
			<input id="username" type="text" class="form-control" placeholder="Enter username" >
		</div>
		<div class="mb-3">
			<input id ="password" type="password" class="form-control" placeholder="Enter password" >
		</div>
		<button id="btnLogin" type="button" class="btn btn-primary">로그인</button>
	</form>
</div>

<script>
	$("#btnLogin").click(()=>{
		// 0. 통신 오브젝트 생성
		let data ={
				username:$("#username").val(),
				password:$("#password").val()
		};
		
		$.ajax("/login",{
			type:"POST",
			dataType:"json",
			data : JSON.stringify(data),
			headers:{
				"Content-Type" : "application/json; charset=utf-8"
			}
		}).done((res)=>{
			if(res.code == 1){
				location.href="/";
			}else{
				alert("아이디 또는 비밀번호를 확인해주세요")
			}
		});
	});
</script>

<%@ include file="../layout/footer.jsp"%>

