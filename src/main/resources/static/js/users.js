let isUsernameSameCheck = false;

$("#btnJoin").click(() => {
	join();
});

$("#btnUsernameSameCheck").click(() => {
	checkUsername();
});

$("#btnLogin").click(() => {
	login();
});

$("#btnUpdate").click(() => {
	update();
});

$("#btnDelete").click(() => {
	resign();
});

function join(){
	if (isUsernameSameCheck == false) {
		alert("유저네임 중복 체크를 진행해주세요");
		return;
	}

	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		email: $("#email").val()
	};

	$.ajax("/join", {
		type: "POST",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json"
		}
	}).done((res) => {
		if ($("#password") != $("#passwordSame")) {
			alert("비밀번호가 다릅니다. ")
			return;
		} else if (res.code == 1 && $("#password") == $("#passwordSame")) {
			alert("회원가입이 완료되었습니다. 로그인페이지로 이동합니다.");
			location.href = "/loginForm";
		}
	});
}

function checkUsername(){

	let username = $("#username").val();

	$.ajax(`/users/usernameSameCheck?username=${username}`, {
		type: "GET",
		dataType: "json"
	}).done((res) => {
		if (res.code == 1) {
			//통신성공
			if ($("#username").val() == "") {
				alert("아이디를 입력해주세요");
				isUsernameSameCheck = false;
			} else if (res.data == false) {
				alert("아이디가 중복되지 않았습니다.");
				isUsernameSameCheck = true;
			} else {
				alert("아이디가 중복되었습니다. 다른아이디를 사용해주세요");
				isUsernameSameCheck = false;
				$("#username").val("");
			}
		}
	});
}

function login(){
	alert("login 함수 실행");
	// 0. 통신 오브젝트 생성
	let data = {
		username: $("#username").val(),
		password: $("#password").val()
	};

	$.ajax("/login", {
		type: "POST",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	}).done((res) => {
		if (res.code == 1) {
			location.href = "/";
		} else {
			alert("아이디 또는 비밀번호를 확인해주세요")
		}
	});
}

function update(){
	// 0. 통신 오브젝트 생성
	let data = {
		password: $("#password").val(),
		email: $("#email").val()
	};

	let id = $("#id").val();
	$.ajax("/users/" + id, {
		type: "PUT",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	}).done((res) => {
		if (res.code == 1) {
			alert("회원정보가 수정되었습니다");
			location.href = "/";
		} else {
			alert("비밀번호 또는 이메일 입력이 잘못되었습니다.")
		}
	});
}

function resign(){
	let id = $("#id").val();

	$.ajax(`/users/${id}`, {
		type: "DELETE",
		dataType: "json"
	}).done((res) => {
		if (res.code == 1) {
			location.href = "/";
		} else {
			alert("회원탈퇴 실패")
		}
	});
}
