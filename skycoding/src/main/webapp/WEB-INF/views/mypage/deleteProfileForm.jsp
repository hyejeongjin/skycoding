<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		//회원탈퇴 체크
		$('#delete_form').submit(function(){
			if($('#id').val().trim()==''){
				alert('아이디를 입력하세요!');
				$('#id').val('').focus();
				return false;
			}
			if($('#email').val().trim()==''){
				alert('이메일을 입력하세요!');
				$('#email').val('').focus();
				return false;
			}
			if($('#passwd').val().trim()==''){
				alert('비밀번호를 입력하세요!');
				$('#passwd').val('').focus();
				return false;
			}
			if($('#cpasswd').val().trim()==''){
				alert('비밀번호 확인을 입력하세요!');
				$('#cpasswd').val('').focus();
				return false;
			}
			if($('#passwd').val()!=$('#cpasswd').val()){
				alert('비밀번호와 비밀번호 확인이 불일치합니다.');
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
  
  <main id="main" class="main">  
    <section class="section profile">
    <h4><b>회원탈퇴</b></h4><br> 
       <form id="delete_form" action="deleteProfile.do" method="post">
         <div class="row mb-3">
            <label for="fullName" class="col-md-4 col-lg-3 col-form-label">id</label>
            <div class="col-md-8 col-lg-9">
              <input name="id" type="text" class="form-control" id="id" maxlength="12">
         	</div>
         </div>

         <div class="row mb-3">
            <label for="fullName" class="col-md-4 col-lg-3 col-form-label">이메일</label>
            <div class="col-md-8 col-lg-9">
              <input name="email" type="email" class="form-control" id="email" maxlength="50">
         	</div>
         </div>
                  
         <div class="row mb-3">
            <label for="fullName" class="col-md-4 col-lg-3 col-form-label">비밀번호</label>
            <div class="col-md-8 col-lg-9">
              <input name="passwd" type="password" class="form-control" id="passwd" maxlength="12">
         	</div>
         </div>
         
         <div class="row mb-3">
            <label for="fullName" class="col-md-4 col-lg-3 col-form-label">비밀번호 확인</label>
            <div class="col-md-8 col-lg-9">
              <input name="cpasswd" type="password" class="form-control" id="cpasswd" maxlength="12">
         	</div>
         </div>
         
         <div class="text-center">
            <input type="submit" class="btn btn-primary" value="회원탈퇴">
            <input type="button" class="btn btn-primary" value="My페이지" onclick="location.href='profilePage.do'">
         </div>
      </form>
    </section>
  </main><!-- End #main -->
  
</body>
</html>






