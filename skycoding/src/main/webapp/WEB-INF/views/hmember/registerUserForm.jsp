<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/style.css"
	rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>	
<%-- 회원가입 스크립트 --%>
<script type="text/javascript">
	$(function() {
		let idChecked = 0; //0:id 중복체크 미실시/ id 중복
		//1:id 미중복
		$('#id_check').click(
				function() {
					if ($('#id').val().trim() == '') {
						alert('아이디를 입력하세요.');
						$('#id').val('').focus();
						return;
					}

					//서버와 통신
					$.ajax({
						url : 'checkDuplicatedId.do',
						type : 'post',
						data : {mem_id:$('#id').val()
						},
						dataType : 'json',
						success : function(param) {
							if (param.result == 'idNotFound') {
								idChecked = 1;
								$('#message_id').css('color', '#000000').text(
										'등록 가능 ID');
							} else if (param.result == 'idDuplicated') {
								idChecked = 0;
								$('#message_id').css('color', 'red').text(
										'등록된 ID');
								$('#id').val('').focus();
							} else {
								idChecked = 0;
								alert('아이디 중복 체크 오류 발생');
							}
						},
						error : function() {
							idChecked = 0;
							alert('네트워크 오류 발생');
						}
					});
				});//end of click

		//아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
		$('#register_form #id').keydown(function() {
			idChecked = 0;
			$('#message_id').text('');
		});//end of keydown

		//회원 정보 등록 유효성 체크
		$('#register_form').submit(function() {
			if ($('#id').val().trim() == '') {
				alert('아이디를 입력하세요!');
				$('#id').val('').focus();
				return false;
			}
			if (idChecked == 0) {
				alert('아이디 중복 체크 필수!');
				return false;
			}
			if ($('#name').val().trim() == '') {
				alert('이름을 입력하세요!');
				$('#name').val('').focus();
				return false;
			}
			if ($('#pass').val().trim() == '') {
				alert('비밀번호를 입력하세요!');
				$('#passwd').val('').focus();
				return false;
			}
			if ($('#pass2').val().trim() == '') {
				alert('비밀번호를 입력하세요!');
				$('#passwd').val('').focus();
				return false;
			}

			if ($('#passans').val().trim() == '') {
				alert('비밀번호 답변을 입력하세요!');
				$('#passans').val('').focus();
				return false;
			}
			if ($('#cell').val().trim() == '') {
				alert('전화번호를 입력하세요!');
				$('#cell').val('').focus();
				return false;
			}
			if ($('#email').val().trim() == '') {
				alert('이메일을 입력하세요!');
				$('#email').val('').focus();
				return false;
			}
		});//end of submit

	});
</script>
<style type="text/css">
ul {
	text-decoration: none;
}

ul li {
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="container" style="float: none; margin-top: 100px; margin-bottom: 100px;">
			<div class="col-md-5" style="float: none; margin: auto;">
				<h4>회원가입</h4>
				<form id="register_form" action="registerUser.do" method="post">
					<div class="row mb-3">
						<label for="id">아이디</label>
						<div class="input-group">
							<input type="text" class="form-control" name="mem_id" id="id"> <span
								class="input-group-btn">
								<button class="btn btn-primary" type="button" id="id_check">아이디 중복 체크</button>
								<span id="message_id"></span>
							</span>
						</div>
						<!-- /input-group -->
					</div>
					<div class="row mb-3">
						<label for="name">이름</label>
						<div class="col-md-15">
							<input type="text" class="form-control" id="name" name="mem_name">
						</div>
					</div>
					<div class="row mb-3">
						<label for="pass">비밀번호</label>
						<div class="col-md-15">
							<input type="password" class="form-control" id="pass" name="mem_pw">
						</div>
					</div>
					<div class="row mb-3">
						<label for="pass2">비밀번호 확인</label>
						<div class="col-md-15">
							<input type="password" class="form-control" id="pass2" name="mem_pw">
						</div>
					</div>
					<div class="row mb-3">
						<label for="passque">비밀번호 질문</label>
						<div class="col-md-15">
							<select  name="mem_pwq" id="mem_pwq" class="form-select" aria-label="Default select example" required>
								<c:forEach var="qna" items="${qnaList}">
									<option value="${qna.mem_pwq}">${qna.qna_detail}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row mb-3">
						<label for="passans">비밀번호 답변</label>
						<div class="col-md-15">
							<input type="text" class="form-control" id="passans" name="mem_pwa">
						</div>
					</div>
					<div class="row mb-3">
						<label for="cell">전화번호</label>
						<div class="col-md-15">
							<input type="text" class="form-control" id="cell" name="mem_cell">
						</div>
					</div>
					<div class="row mb-3">
						<label for="email">이메일</label>
						<div class="col-md-15">
							<input type="email" class="form-control" id="email" name="mem_email">
						</div>
					</div>
					<button type="submit" class="btn btn-primary">회원가입</button>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

