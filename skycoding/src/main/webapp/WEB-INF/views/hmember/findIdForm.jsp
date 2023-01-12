<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
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
		$('#find_id_form').submit(function(){
			if($('#mem_email').val().trim()==''){
			alert('이메일을 입력하세요');
			$('#mem_email').val('').focus();
			return false;
			}
			if($('#mem_cell').val().trim()==''){
				alert('전화번호를 입력하세요');
				$('#mem_cell').val('').focus();
				return false;
			}
		});//end of submit
		
	});
</script>
</head>
<body>
<div class="container">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>	
	<div class="container row" style="float: none; margin-top: 100px;">
		<div class="col-md-5" style="float: none; margin: auto;">
			<h4>아이디 찾기</h4>
			<form id="find_id_form" action="findId.do" method="post">
				<div class="row mb-3">
					<label for="email">이메일</label>
					<div class="col-md-15">
						<input type="text" class="form-control" id="mem_email" name="mem_email">
					</div>
				</div>
				<div class="row mb-3">
					<label for="tel">전화번호</label>
					<div class="col-md-15">
						<input type="text" class="form-control" id="mem_cell" name="mem_cell">
					</div>
				</div>
				<button type="submit" class="btn btn-primary">아이디 찾기</button>
			</form>
		</div>
	</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

