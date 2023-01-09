<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>질문글 수정</title>

<!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	
	//유효성 체크
	$(function(){
		$('#update_form').submit(function(){
			if($('#qna_title').val().trim()==''){
				alert('제목을 입력하세요!');
				$('#qna_title').val('').focus();
				return false;
			}
			if($('#qna_content').val().trim()==''){
				alert('내용을 입력하세요!');
				$('#qna_content').val('').focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">
    <ul class="sidebar-nav" id="sidebar-nav">
      <li class="nav-item">
        <a class="nav-link collapsed" href="list.do">
          <i class="bi bi-grid"></i>
          <span>질문글</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link collapsed" href="#">
          <span>자유글</span>
        </a>
      </li>
    </ul>
  </aside>
<!-- End Sidebar-->

<!-- Start #main -->
<!-- 헤더 제외 전체 row -->
<div class="row">
<!-- 좌측 여백col -->
<div class="col-3"></div>
<!-- 폼 col -->
<div class="col-8">
  <main id="register-main" class="register-main">

		<section class="section">
			<div class="pagetitle">
				<h2>질문글 글수정</h2>
			</div>
				<div class="card">
						<div class="card-body">
							<h5 class="card-title"></h5>
							
		<form action="update.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="qna_id" value="${qnaBoard.qna_id}">
				
			<div class="row mb-3">
				<div class="col-sm-12">
					<input type="text" name="qna_title" id="qna_title"
							class="form-control" maxlength="50" value="${qnaBoard.qna_title}">
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-sm-12">
					<input class="form-control" name="qna_photo" id="qna_photo"
						type="file" id="formFile">
						<c:if test="${!empty qnaBoard.qna_photo}">
					<div id="file_detail">
						(${qnaBoard.qna_photo})파일이 등록되어 있습니다.
						<input type="button" value="파일삭제" id="file_del" style="margin-top:10px;">
					</div>
					<script type="text/javascript">
						$(function(){
							$('#file_del').click(function(){
								let choice = confirm('삭제하시겠습니까?');
								if(choice){
									$.ajax({
										url:'deleteFile.do',
										type:'post', 	//el은 오직 jsp에서만 사용가능, js,html 등에서는 불가능
										data:{qna_id:${qnaBoard.qna_id}},
										dataType:'json',
										success:function(param){
											if(param.result == 'logout'){
												alert('로그인 후 사용하세요!');
											}else if(param.result == 'success'){
												$('#file_detail').hide(); //div 숨김
											}else if(param.result == 'wrongAccess'){
												alert('잘못된 접속입니다.');
											}else{
												alert('파일 삭제 오류 발생');
											}
										},
										error:function(){
											alert('네트워크 오류 발생');
										}
									});
								}
							});
						});
					</script>
					</c:if>
				</div>
			</div>
			
			<div class="row mb-3">
				<div class="col-sm-12">
					<textarea class="form-control" name="qna_content" id="qna_content" style="height: 300px">${qnaBoard.qna_content}</textarea>
				</div>
			</div>
				
			<div class="text-end">
				<button type="button" class="btn btn-secondary" onclick="location.href='detail.do?qna_id=${qnaBoard.qna_id}'">취소</button>
				<button type="submit" class="btn btn-primary">수정</button>
			</div>
			
		</form>
		</div>
	</div>
	</section>
	</main><!-- End #main -->
	<!-- 우측 여백 col -->
	<div class="col-1"></div>
</div>
</div>
</body>
</html>