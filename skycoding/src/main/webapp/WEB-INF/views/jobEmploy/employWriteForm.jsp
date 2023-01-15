<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	let idChecked = 0;//0:id중볶체크 미실시,id 미확인
	                  //1:id 확인
	$('#write_form').submit(function(){
		if($('#title').val().trim()==''){
			alert('제목을 입력하세요');
			$('#title').val('').focus();
			return false;
		}
		if($('#mem_id2').val().trim()==''){
			alert('회원 아이디를 입력하세요');
			$('#mem_id2').val('').focus();
			return false;
		}
		if(idChecked==0){
			alert('아이디를 확인하세요');
			return false;
		}
		if($('#content').val().trim()==''){
			alert('내용을 입력하세요');
			$('#content').val('').focus();
			return false;
		}
	});//end of submit
	
	$('#id_check').on('click',function(){
		if($('#mem_id2').val().trim()==''){
			alert('회원 아이디를 입력하세요');
			$('#mem_id2').val('').focus();
			return;
		}
		
		//서버와 통신
		$.ajax({
			url:'${pageContext.request.contextPath}/hmember/checkDuplicatedId.do',
			type:'post',
			data:{mem_id:$('#mem_id2').val()},
			dataType:'json',
			success:function(param){
				if(param.result=='idNotFound'){//아이디 없음
					idChecked = 0;
					$('#write_form span').css('color','red').text('아이디가 존재하지 않음');
				}else if(param.result=='idDuplicated'){//아이디 있음
					idChecked = 1;
					$('#write_form span').css('color','#000000').text('아이디가 존재');
				}else{
					idChecked = 0;
					alert('아이디 확인 오류 발생');
				}
			},
			error:function(){
				idChecked = 0;
				alert('네트워크 통신 오류');
			}
		});
	});//end of click
	
	$('#mem_id2').keydown(function(){
		idChecked = 0;
		$('#write_form span').text('');
	});
	//end of keydown
	
	
});
</script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>

		<div class="content-main">
			<main id="register-main" class="register-main">
				<div class="pagetitle">
					<h1>취업현황 작성</h1>
				</div>
				<!-- End Page Title -->

				<section class="section">
					<div class="row">
						<div class="col-lg-12">

							<div class="card">
								<div class="card-body">
									<h5 class="card-title"></h5>

									<form id="write_form" action="employWrite.do" method="post" enctype="multipart/form-data">
										<div class="row mb-3">
											<div class="col-sm-12">
												<input type="text" class="form-control" id="title" name="title"
													placeholder="제목을 입력해주세요">
											</div>
										</div>
										<div class="row mb-3">
											<div class="col-sm-8">
												<input type="text" class="form-control" id="mem_id2" name="mem_id2"
													placeholder="회원 아이디를 입력해주세요">
											</div>
											<div class="col-sm-4 p-0">
												<input type="button" value="아이디 확인" id="id_check" class="btn btn-light"> &nbsp;<span></span>
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
											<input type="button" value="취소" class="btn btn-secondary" onclick="location.href='employList.do'">
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