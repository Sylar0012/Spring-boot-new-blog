<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br /> <br />


		<div class="d-flex">
		
			<a href="/boards/${boards.id}/updateForm" class="btn btn-warning">수정하러가기</a>

			<form method="post" action="/boards/${boards.id}/delete">
				<button class="btn btn-danger">삭제</button>
			</form>
		</div>


	<br />
	<div>
		<h3>${boards.title }</h3>
	</div>
	<hr />

	<div>${boards.content }</div>


</div>

<%@ include file="../layout/footer.jsp"%>

