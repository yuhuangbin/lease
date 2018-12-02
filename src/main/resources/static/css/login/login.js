function changeAgentNo(userType) {
	if (userType == 1 || userType == 2 || userType == 3) {
		$("#agentNoDiv").hide();
	} else if (userType == 3 || userType == 5) {
		$("#agentNoDiv").show();
	}
}

function encryptAES(pwd) {
	var salt, iv, saltPwd, hashValue;
	$.ajax({
		async: false,
		type: "POST",
		url: context_path + "/getSaltAndIV.json",
		success: function (res) {
			if ("S" == res.resCode) {
				salt = res.data.salt;
				iv = res.data.iv;
			}
		}
	});
	salt = CryptoJS.enc.Hex.parse(salt);
	iv = CryptoJS.enc.Hex.parse(iv);
	pwd = CryptoJS.enc.Utf8.parse(pwd);
	saltPwd = salt;
	saltPwd.concat(pwd);
	hashValue = CryptoJS.SHA256(saltPwd);
	var em = CryptoJS.AES.encrypt(pwd, hashValue, {
		iv: iv,
		mode: CryptoJS.mode.CBC,
		keySize: 256 / 32,
		padding: CryptoJS.pad.Pkcs7
	});
	return em.toString();
}

function login() {
	var userCode = $("#userCode").val();
	var password = $("#pwd").val();

    var params = {};
    params.userName = userCode;
    params.userPwd = hex_md5(password);
	$.ajax({
		async: false,
		type: "post",
		url: "/login",
		data: params,
		success: function (res) {
			if (res.code == 0) {
                location.href = "/index";
			} else {

			}
			if (typeof(res) == "string" || "0000" == res.code) {
				location.href = cpx;
			} else {
				alert(res.msg);
				$("#pwd").val("");
			}
		}
	});
}
