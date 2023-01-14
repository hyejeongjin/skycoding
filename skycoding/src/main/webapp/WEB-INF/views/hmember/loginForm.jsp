<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#login_form').submit(function(){
			if($('#id').val().trim()==''){
				alert('아이디를 입력하세요');
				$('#id').val('').focus();
				return false;
			}
			if($('#pw').val().trim()==''){
				alert('비밀번호를 입력하세요');
				$('#pw').val('').focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
<div class="container">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="container" style="float: none; margin-top:100px;">
		<div class="col-md-5" style="float: none; margin: auto;">
			<h4>로그인</h4>
			<form id="login_form" action="login.do" method="post">
				<div class="row mb-3">
					<label for="id">아이디</label>
					<div class="col-md-15">
						<input type="text" class="form-control" id="id" name="mem_id">
					</div>
				</div>
				<div class="row mb-3">
					<label for="pw">비밀번호</label>
					<div class="col-md-15">
						<input type="password" class="form-control" id="pw" name="mem_pw">
					</div>
				</div>
				<div class="col mb-3" style="text-align: center;">
								<a href="findIdForm.do">아이디 찾기</a>
								|
								<a href="findPwForm.do">비밀번호 찾기</a>
								|
								<a href="registerUserForm.do">회원가입</a>
							</div>
				<button type="submit" class="btn btn-primary">로그인</button>
			</form>
		</div>
	</div>
</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

