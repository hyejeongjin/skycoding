<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>이벤트 등록 폼 - admin only</title>
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
<!-- Vendor CSS Files -->
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/event-style.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/course-style.css" >
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#event_form').submit(function(){
		if($('#title').val().trim() == ''){
			alert('이벤트 제목을 입력하세요!');
			$('#title').val('').focus();
			return false;
		}
		if($('input[type=radio]:checked').length < 0){
			alert('이벤트 대상강의를 지정하세요!');
			return false;
		}
		
		let deadline = document.getElementById('deadline').value.trim();
		if(deadline == ''){
			alert('이벤트 마감일을 입력하세요!');
			$('#deadline').val('').focus();
			return false;
		}else if(/^0/.test(deadline)){					//마감일 정규식 검사
			alert('마감일 년도는 0으로 시작할 수 없습니다.');
			$('#deadline').val('').focus();
			return false;
		}else if(!/\d{4}-\d{2}-\d{2}/.test(deadline)){
			alert('마감일을 yyyy-MM-dd 형식으로 입력해주세요');
			return false;
		}
		if($('#photo').val().trim() == ''){
			alert('이벤트 썸네일을 첨부하세요!');
			$('#photo').val('').focus();
			return false;
		}
		if($('#content').val().trim() == ''){
			alert('이벤트 요약내용을 입력하세요!');
			$('#content').val('').focus();
			return false;
		}
		if($('#detail_content').val().trim() == ''){
			alert('이벤트 세부내용을 입력하세요!');
			$('#detail_content').val('').focus();
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
		<div class="pagetitle">
      		<h1>이벤트 수정하기</h1>
   		</div><!-- 타이틀끝 -->
   		
   	   <section class="section">
    	<div class="row">
      	  <div class="col-lg-12">
            <div class="card-write">
             <div class="card-body">
          	  <h5 class="card-title"></h5>
				<form action="registerEvent.do" id="event_form" method="post" enctype="multipart/form-data">
						<div class="row mb-3">
							<div class="col-sm-12">
							<label class="register_title" for="title">제목</label>
							<input type="text" name="title" id="title" 
								   class="form-control" placeholder="이벤트 제목을 입력하세요">
							</div>
						</div>
						<h3 id="select_event_course">대상 강의 선택</h3>
						<div class="row mb-1">
							<div class="col-sm-12">
						<hr>
							<ul id="register_event_subject">
								<c:forEach var="course" items="${courseMap}">
									<li>
										<input type="radio" name="course_id" id="course_id${course.value}" value="${course.value}">
										<label for="course_id${course.value}">${course.key}</label>
									</li>
								</c:forEach>
							</ul>
						<hr>
							</div>
						</div>
						<div class="row mb-3">
							<input type="hidden" name="attr" value="1">
						</div>
						<div class="row mb-3">
							<div class="col-sm-12">
								<label class="register_title" for="deadline">마감일</label>
								<input type="text" name="deadline" id="deadline" 
								   class="form-control" placeholder="yyyy-MM-dd 형식으로 입력하세요">
							</div>
						</div>
						<div class="row mb-3">
							<div class="col-sm-12">
								<label class="register_title" for="photo">파일</label>
								<input class="form-control col-" type="file" name="photo" id="photo" accept="image/gif,image/jpeg,image/png">
							</div>
						</div>
						<div class="row mb-3">
							<div class="col-sm-12">
								<label class="register_title" for="content">요약 내용</label>
								<textarea class="form-control" name="content" id="content"
									cols="30" rows="4" placeholder="이벤트 요약 내용을 입력하세요"></textarea>
							</div>
						</div>
						<div class="row mb-3">
							<div class="col-sm-12">
								<label class="register_title" for="detail_content">내용</label>
								<textarea class="form-control" name="detail_content" id="detail_content"
									cols="50" rows="4" placeholder="이벤트 세부 내용을 입력하세요"></textarea>
							</div>
						</div>
					<div class="text-end">
						<input type="button" class="btn btn-secondary" value="돌아가기" onclick="location.href='eventList.do'">
						<input type="submit" class="btn btn-primary" value="강의 등록">
					</div>
				</form>
			 </div>
			  </div>
			   </div>
				</div>
				 </section>
		</div><!-- .content-main 끝 -->
	</div><!-- .page-main 끝 -->
</body>
</html>