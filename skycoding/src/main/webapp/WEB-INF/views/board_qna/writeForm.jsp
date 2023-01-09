<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>커뮤니티</title>

<!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/qna-style.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	//유효성 체크
	$(function(){
		$('#write_form').submit(function(){
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
					<h2>질문글 글쓰기</h2>
				</div>
					<div class="card">
						<div class="card-body">
							<h5 class="card-title"></h5>

							<form id="write_form" action="write.do" method="post"
								enctype="multipart/form-data">
								<div class="row mb-3">
									<div class="col-sm-12">
										<input type="text" name="qna_title" id="qna_title"
											class="form-control" maxlength="50" placeholder="제목을 입력해주세요">
									</div>
								</div>

								<div class="row mb-3">
									<div class="col-sm-12">
										<input class="form-control" name="qna_photo" id="qna_photo"
											type="file" id="formFile">
									</div>
								</div>

								<div class="row mb-3">
									<div class="col-sm-12">
										<textarea class="form-control" name="qna_content"
											id="qna_content" style="height: 300px"
											placeholder="내용을 입력해주세요"></textarea>
									</div>
								</div>

								<div class="text-end">
									<button type="button" class="btn btn-secondary"
										onclick="location.href='list.do'">취소</button>
									<button type="submit" class="btn btn-primary">등록</button>
								</div>

							</form>
						</div>
					</div>

		</section>

	</main><!-- End #main -->
	</div>
	<!-- 우측 여백 col -->
	<div class="col-1"></div>
</div>
</body>
</html>