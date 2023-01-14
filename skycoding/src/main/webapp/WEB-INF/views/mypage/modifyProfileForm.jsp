<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
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
		//회원 정보 수정 유효성 체크
		$('#modify_form').submit(function(){
			if($('#name').val().trim()==''){
				alert('이름을 입력하세요!');
				$('#name').val('').focus();
				return false;
			}
			if($('#phone').val().trim()==''){
				alert('전화번호를 입력하세요!');
				$('#phone').val('').focus();
				return false;
			}
			if($('#email').val().trim()==''){
				alert('이메일을 입력하세요!');
				$('#email').val('').focus();
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
    <h4><b>회원정보 수정</b></h4><br> 
       <form id="modify_form" action="modifyProfile.do" method="post">
         <div class="row mb-3">
            <label for="fullName" class="col-md-4 col-lg-3 col-form-label">이름</label>
            <div class="col-md-8 col-lg-9">
              <input name="name" type="text" class="form-control" id="name" maxlength="10" value="${member.name}">
         	</div>
         </div>

         <div class="row mb-3">
            <label for="fullName" class="col-md-4 col-lg-3 col-form-label">전화번호</label>
            <div class="col-md-8 col-lg-9">
              <input name="phone" type="text" class="form-control" id="phone" maxlength="15" value="${member.phone}">
         	</div>
         </div>
                  
         <div class="row mb-3">
            <label for="fullName" class="col-md-4 col-lg-3 col-form-label">Email</label>
            <div class="col-md-8 col-lg-9">
              <input name="email" type="email" class="form-control" id="email" maxlength="50" value="${member.email}">
         	</div>
         </div>
         

         <div class="text-center">
            <button type="submit" class="btn btn-primary">수정</button>
            <input type="button" class="btn btn-primary" value="My페이지" onclick="location.href='profilePage.do'">
         </div>
      </form>
    </div>
    </div>
    </div>
  
</body>
</html>






