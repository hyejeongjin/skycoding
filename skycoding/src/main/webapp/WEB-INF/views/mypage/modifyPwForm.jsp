<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 수정</title>
  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/mypage-style.css" rel="stylesheet">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		//비밀번호 수정 체크
		$('#password_form').submit(function(){
			if($('#id').val().trim()==''){
				alert('아이디를 입력하세요!');
				$('#id').val('').focus();
				return false;
			}
			if($('#origin_passwd').val().trim()==''){
				alert('현재 비밀번호를 입력하세요!');
				$('#origin_passwd').val('').focus();
				return false;
			}
			if($('#passwd').val().trim()==''){
				alert('새비밀번호를 입력하세요!');
				$('#passwd').val('').focus();
				return false;
			}
			if($('#cpasswd').val().trim()==''){
				alert('새비밀번호 확인을 입력하세요!');
				$('#cpasswd').val('').focus();
				return false;
			}
			if($('#passwd').val()!=$('#cpasswd').val()){
				alert('새비밀번호와 새비밀번호 확인이 불일치합니다.');
				$('#passwd').val('').focus();
				$('#cpasswd').val('');
				return false;
			}
		});//end of submit
	});
</script>
</head>
<body>
  <jsp:include page="/WEB-INF/views/common/mypagemain.jsp"/>
  
	<div class="page-main">
	<div class="content-main">
	<div class="content-right">
    <h4><b>비밀번호 수정</b></h4><br> 
       <form id="password_form" action="modifyPw.do" method="post">
         <div class="row mb-3">
            <label for="fullName" class="col-md-4 col-lg-3 col-form-label">id</label>
            <div class="col-md-8 col-lg-9">
              <input name="id" type="text" class="form-control" id="id">
         	</div>
         </div>

         <div class="row mb-3">
            <label for="fullName" class="col-md-4 col-lg-3 col-form-label">현재 비밀번호</label>
            <div class="col-md-8 col-lg-9">
              <input name="origin_passwd" type="password" class="form-control" id="origin_passwd" maxlength="12">
         	</div>
         </div>
                  
         <div class="row mb-3">
            <label for="fullName" class="col-md-4 col-lg-3 col-form-label">새비밀번호</label>
            <div class="col-md-8 col-lg-9">
              <input name="passwd" type="password" class="form-control" id="passwd" maxlength="12">
         	</div>
         </div>
         
         <div class="row mb-3">
            <label for="fullName" class="col-md-4 col-lg-3 col-form-label">새비밀번호 확인</label>
            <div class="col-md-8 col-lg-9">
              <input name="cpasswd" type="password" class="form-control" id="cpasswd" maxlength="12">
         	</div>
         </div>
         
         <div class="text-center">
            <input type="submit" class="btn btn-primary" value="비밀번호 수정">
            <input type="button" class="btn btn-primary" value="My페이지" onclick="location.href='profilePage.do'">
         </div>
      </form>
    </div>
    </div>
    </div>
  
</body>
</html>

