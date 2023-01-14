<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>질문게시판 글쓰기</title>

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
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>

	<div class="content-main">
  		<main id="register-main" class="register-main">
			<div class="pagetitle">
				<h1>질문게시판 작성</h1>
			</div>

				<section class="section">
					<div class="row">
						<div class="col-lg-12">

							<div class="card">
								<div class="card-body">
									<h5 class="card-title"></h5>

									<form id="write_form" action="write.do" method="post" enctype="multipart/form-data">
										<div class="row mb-3">
											<div class="col-sm-12">
												<input type="text" name="qna_title" id="qna_title" class="form-control" maxlength="50" placeholder="제목을 입력해주세요">
											</div>
										</div>

										<div class="row mb-3">
											<div class="col-sm-12">
												<input class="form-control" name="qna_photo" id="qna_photo" type="file" id="formFile">
											</div>
										</div>

										<div class="row mb-3">
											<div class="col-sm-12">
												<textarea class="form-control" name="qna_content" id="qna_content" style="height: 300px" placeholder="내용을 입력해주세요"></textarea>
											</div>
										</div>

										<!-- 익명체크 -->
										<div class="check-anony">
											<label class="form-check-label" for="inlineCheckbox1">익명으로 작성</label>&nbsp;
											<input class="form-check-input" type="hidden"
												id="inlineCheckbox1" name="qna_status" value="0">
											<input class="form-check-input" type="checkbox"
												id="inlineCheckbox1" name="qna_status" value="1">
										</div>

										<div class="text-end">
											<input type="button" value="취소" class="btn btn-secondary" onclick="location.href='list.do'">
											<input type="submit" value="등록" class="btn btn-primary">
										</div>

									</form>
									
								</div>
							</div>
							
						</div>
					</div>
				</section>

			</main><!-- end of register-main -->
		</div><!-- end of content-main -->
	</div><!-- end of page-main -->
	
</body>
</html>