<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>이벤트 등록 폼 - 관리자만</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">
<!-- Vendor CSS Files -->
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/job_style.css" rel="stylesheet">
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
					// $('#deadline').val().trim();  제이쿼리로 변수를 사용할 때는 else if 문을 전부 if문으로 바꾸고 사용할 것 
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
			alert('강의파일을 첨부하세요!');
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
		<form action="registerEvent.do" id="event_form" method="post" enctype="multipart/form-data">
			<ul>
				<li>
					<label for="title">제목</label>
					<input type="text" name="title" id="title" placeholder="이벤트 제목을 입력하세요">
				</li>
				<ins>대상 강의 선택</ins>
				<li>
					<ul style="list-style:none;">
						<c:forEach var="course" items="${courseMap}">
							<li>
								<label for="course_id${course.value}">${course.key}</label>
								<input type="radio" name="course_id" id="course_id${course.value}" value="${course.value}">
							</li>
						</c:forEach>
					</ul>
				</li>
				<li>
					<input type="hidden" name="attr" value="1">
					
				<li>
					<label for="deadline">마감일</label>
					<input type="text" name="deadline" id="deadline" placeholder="yyyy-MM-dd 형식으로 입력하세요">
				</li>
				<li>
					<label for="photo">파일</label>
					<input type="file" name="photo" id="photo">
				</li>
				<li>
					<label for="content">요약 내용</label>
					<textarea name="content" id="content"
							cols="30" rows="4" placeholder="이벤트 요약 내용을 입력하세요"></textarea>
				</li>
				<li>
					<label for="detail_content">내용</label>
					<textarea name="detail_content" id="detail_content"
							cols="50" rows="4" placeholder="이벤트 세부 내용을 입력하세요"></textarea>
				</li>
			</ul>
			<div>
				<input type="submit" value="강의 등록">
				<input type="button" value="돌아가기" onclick="location.href='eventList.do'">
			</div>
		</form>
	</div>
</body>
</html>