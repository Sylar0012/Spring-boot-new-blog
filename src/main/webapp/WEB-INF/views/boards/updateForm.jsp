<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<input id="id" type="hidden" value="${boards.id}">
		<div class="mb-3 mt-3">
			<input id="title" type="text" class="form-control" value="${boards.title}">
		</div>
		<div class="mb-3">
			<textarea id="content" class="form-control" rows="8"  >${boards.content }</textarea>
		</div>
		<button id="btnUpdate"type="button" class="btn btn-primary">수정완료</button>
	</form>
</div>

<script src="/js/boards.js"></script>

<%@ include file="../layout/footer.jsp"%>

