<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>취업현황 글쓰기</title>
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
<!-- Vendor CSS Files -->
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/job_style.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#update_form').submit(function(){
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
					<h1>취업현황 글수정</h1>
				</div>
				<section class="section">
					<div class="row">
						<div class="col-lg-12">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title"></h5>

									<form id="update_form" action="employUpdate.do" method="post" enctype="multipart/form-data">
										<input type="hidden" name="emp_id" value="${employ.emp_id}">
										<div class="row mb-3">
											<div class="col-sm-12">
												<input type="text" class="form-control" id="title" name="emp_title"
													value="${employ.emp_title}">
											</div>
										</div>
										<div class="row mb-3">
											<div class="col-sm-12">
												<input class="form-control" type="file" id="formFile" name="emp_photo"
														accept="image/png,image/gif,image/jpeg">
												<c:if test="${!empty employ.emp_photo}">
												<div id="photo_detail">
												<input type="button" value="파일 삭제" id="photo_del" class="btn btn-secondary">
												(${employ.emp_photo}) 파일이 저장되어 있음
												</div>
												<script type="text/javascript">
													$(function(){
														$('#photo_del').click(function(){
															let choice = confirm('정말 삭제하시겠습니까?');
															if(choice){
																$.ajax({
																	url:'employDeleteFile.do',
																	type:'post',
																	// 파라미터 이름:파라미터 값
																	data:{emp_id:${employ.emp_id}},//el은 jsp에서만 사용 가능
																	dataType:'json',
																	success:function(param){
																		if(param.result=='logout'){
																			alert('로그인 후 사용하세요');
																		}else if(param.result=='success'){
																			$('#photo_detail').hide();
																		}else if(param.result=='wrongAccess'){
																			alert('잘못된 접속입니다');
																		}else{
																			alert('파일 삭제 오류 발생');
																		}
																	},
																	error(){
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
												<textarea class="form-control" id="content" name="emp_content" style="height: 300px">${employ.emp_content}</textarea>
											</div>
										</div>

										<div class="text-end">
											<input type="button" value="취소" class="btn btn-secondary" 
													onclick="location.href='employDetail.do?emp_id=${employ.emp_id}'">
											<input type="submit" value="수정" class="btn btn-primary">
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