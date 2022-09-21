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



function join() {
	if (isUsernameSameCheck == false) {
		alert("유저네임 중복 체크를 진행해주세요");
		return;
	}

	if (KoreanCheck() == true) {
		alert("유저네임에 한글이 있으면 안됩니다.");
		return;
	}

	if (capitalCheck() == true) {
		alert("유저네임에 대문자 하나는 꼭 포함되어야 합니다.");
		return;
	}

	if (usernameSpaceCheck() == true) {
		alert("유저네임에 공백 있으면 안됩니다")
		return;
	}

	if (passwordCheck() == true) {
		alert("비밀번호가 다릅니다. ");
		return;
	}
	if (passwordSpaceCheck() == true) {
		alert("비밀번호에 공백이 있으면 안됩니다. ");
		return;
	}

	if (emailCheck() == true) {
		alert("이메일을 올바르게 입력해주세요");
		return;
	}


	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		email: $("#email").val()
	};

	$.ajax("/api/join", {
		type: "POST",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json"
		}

	}).done((res) => {

		if (res.code == 1) {
			alert("회원가입이 완료되었습니다. 로그인페이지로 이동합니다.");
			location.href = "/loginForm";
		}else{
			alert(res.msg);
			history.back();
		}
	});
}

function checkUsername() {

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


function login() {
	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		remember: $("#remember").prop("checked")
	};

	$.ajax("/api/login", {
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

function update() {
	// 0. 통신 오브젝트 생성
	let data = {
		password: $("#password").val(),
		email: $("#email").val()
	};

	let id = $("#id").val();
	$.ajax("/s/api/users/" + id, {
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

function resign() {
	let id = $("#id").val();

	$.ajax(`/s/api/users/${id}`, {
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
function KoreanCheck() {
	let username = $("#username").val();
	let korean = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

	if (korean.test(username)) {
		return true;
	} else {
		return false;
	}
}

function capitalCheck() {
	let username = $("#username").val();
	let capital = /[A-Z]/;

	if (!(capital.test(username))) {
		return true;
	} else {
		return false;
	}
}

function passwordCheck() {
	let password = $("#password").val();
	let passwordCheck = $("#passwordSame").val();

	if (password != passwordCheck) {
		return true;
	} else {
		return false;
	}
}

function emailCheck() {
	let userEmail = $("#email").val();
	let email = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;

	if (!(email.test(userEmail))) {
		return true;
	} else {
		return false;
	}
}

function usernameSpaceCheck() {
	let username = $("#username").val();
	let space = /\s/;

	if (space.test(username)) {
		return true;
	} else {
		return false;
	}
}

function passwordSpaceCheck() {
	let password = $("#password").val();
	let space = /\s/;

	if (space.test(password)) {
		return true;
	} else {
		return false;
	}
}


