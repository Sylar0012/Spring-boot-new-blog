<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="mb-3 mt-3">
			<input type="text" class="form-control" placeholder="Enter title" value="제목입니다">
		</div>
		<div class="mb-3">
			<textarea class="form-control" rows="8" >내용입니다</textarea>
		</div>
		<button type="submit" class="btn btn-primary">수정완료</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>

