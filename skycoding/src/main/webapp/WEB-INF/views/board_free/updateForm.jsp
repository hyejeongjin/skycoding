<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		$('#update_form').submit(function(){
			if($('#free_title').val().trim()==''){
				alert('제목을 입력하세요!');
				$('#free_title').val('').focus();
				return false;
			}
			if($('#free_content').val().trim()==''){
				alert('내용을 입력하세요!');
				$('#free_content').val('').focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

		<div class="content-main">
			<main id="register-main" class="register-main">
				<div class="pagetitle">
					<h1>질문게시판 글수정</h1>
				</div>
				<section class="section">
					<div class="row">
						<div class="col-lg-12">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title"></h5>

									<form action="update.do" method="post" enctype="multipart/form-data">
										<input type="hidden" name="free_id" value="${freeBoard.free_id}">
										<div class="row mb-3">
											<div class="col-sm-12">
												<input type="text" name="free_title" id="free_title" class="form-control" maxlength="50" value="${freeBoard.free_title}">
											</div>
										</div>

										<div class="row mb-3">
											<div class="col-sm-12">
												<input class="form-control" name="free_photo" id="free_photo"
													type="file" id="formFile">
												<c:if test="${!empty freeBoard.free_photo}">
													<div id="file_detail">
														(${freeBoard.free_photo}) 파일이 등록되어 있습니다. <input type="button"
															value="파일삭제" id="file_del" style="margin-top: 10px;">
													</div>
													<script type="text/javascript">
														$(function(){
															$('#file_del').click(function(){
																let choice = confirm('정말 삭제하시겠습니까?');
																if(choice){
																	$.ajax({
																		url:'deleteFile.do',
																		type:'post', 	//el은 오직 jsp에서만 사용가능, js,html 등에서는 불가능
																		data:{free_id:${freeBoard.free_id}},
																		dataType:'json',
																		success:function(param){
																			if(param.result == 'logout'){
																				alert('로그인 후 사용하세요');
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
												<textarea class="form-control" name="free_content"
													id="free_content" style="height: 300px">${freeBoard.free_content}</textarea>
											</div>
										</div>

										<div class="text-end">
											<input type="button" value="취소" class="btn btn-secondary" 
													onclick="location.href='detail.do?free_id=${freeBoard.free_id}'">
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