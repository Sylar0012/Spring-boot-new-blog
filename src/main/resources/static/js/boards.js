let id = $("#id").val();

$("#btnDelete").click(() => {
	deleteById();
});

$("#iconLove").click(() => {
	let isLovedState = $("#iconLove").hasClass("fa-solid");
	if (isLovedState) {
		deleteLove();
	} else {
		insertLove();
	}
});

$('#content').summernote({
	height: 400
});

$("#btnUpdate").click(() => {
	update();
});

$("#btnSave").click(() => {
	save();
});


function save() {
	let data = {
		title: $("#title").val(),
		content: $("#content").val(),
		usersid: $("#id").val()
	}
	$.ajax("/s/api/boards/write", {
		type: "POST",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json"
		}
	}).done((res) => {
		if (res.code == 1) {
			alert("게시글 입력완료 ")
			location.href = "/boards/" + id;

		} else {
			alert("게시글 입력실패");
			location.href = "/boards/" + id;
		}
	});
}

function update() {
	let data = {
		title: $("#title").val(),
		content: $("#content").val()
	};
	$.ajax("/s/api/boards/" + id + "/update", {
		type: "PUT",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json"
		}
	}).done((res) => {
		if (res.code == 1) {
			alert("게시글 수정 완료 ")
			location.href = "/boards/" + id;

		} else {
			alert("게시글 수정 실패");

		}
	});
}

function deleteById() {

	let page = $("#page").val();
	let keyword = $("#keyword").val();

	$.ajax("/s/api/boards/" + id, {
		type: "DELETE",
		dataType: "json" // 응답 데이터
	}).done((res) => {
		if (res.code == 1) {
			//location.href = document.referrer;
			location.href = "/?page=" + page + "&keyword=" + keyword;  
		} else {
			alert("글삭제 실패");
		}
	});
}


function insertLove() {
	
	$.ajax("/s/api/boards/" + id + "/loves", {
		type: "POST",
		dataType: "json"
	}).done((res) => {
		if (res.code == 1) {
			renderLoves();
			let count = $("#countLove").text();
			$("#countLove").text(Number(count) + 1);
			$("#lovesId").val(res.data.id);
		} else {
			alert("좋아요 실패했습니다");
		}
	});
}

function deleteLove() {
	let lovesId = $("#lovesId").val();
	let boardsId = $("#id").val()
	$.ajax("/s/api/boards/" + boardsId + "/loves/" + lovesId, {
		type: "DELETE",
		dataType: "json"
	}).done((res) => {
		if (res.code == 1) {
			renderCancelLoves();
			let count = $("#countLove").text();
			$("#countLove").text(Number(count) - 1);
		} else {
			alert("좋아요 취소 실패");
		}
	});
}


function renderLoves() {
	$("#iconLove").removeClass("fa-regular");
	$("#iconLove").addClass("fa-solid");
}

function renderCancelLoves() {
	$("#iconLove").removeClass("fa-solid");
	$("#iconLove").addClass("fa-regular");
}