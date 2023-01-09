<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>취업후기 글쓰기</title>
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
<!-- Vendor CSS Files -->
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/job_style.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#write_form').submit(function(){
			if($('#title').val().trim()==''){
				alert('제목을 입력하세요');
				$('#title').val('').focus();
				return false;
			}
			if($('#content').val().trim()==''){
				alert('내용을 입력하세요');
				$('#content').val('').focus();
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
					<h1>취업후기 작성</h1>
				</div>

				<section class="section">
					<div class="row">
						<div class="col-lg-12">

							<div class="card">
								<div class="card-body">
									<h5 class="card-title"></h5>

									<form id="write_form" action="reviewWrite.do" method="post" enctype="multipart/form-data">
										<div class="row mb-3">
											<div class="col-sm-12">
												<input type="text" class="form-control" id="title" name="title"
													placeholder="제목을 입력해주세요">
											</div>
										</div>
										<div class="row mb-3">
											<div class="col-sm-12">
												<input class="form-control" type="file" id="formFile" name="photo">
											</div>
										</div>
										<div class="row mb-3">
											<div class="col-sm-12">
												<textarea class="form-control" id="content" name="content" style="height: 300px"
													placeholder="내용을 입력해주세요"></textarea>
											</div>
										</div>

										<div class="text-end">
											<input type="button" value="취소" class="btn btn-secondary" onclick="location.href='reviewList.do'">
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