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

<script src="/js/boards.js"></script>

<%@ include file="../layout/footer.jsp"%>

