<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>이벤트 상세페이지</title>
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
<!-- Vendor CSS Files -->
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/event-style.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	//수강 내용과 혜택 부분 글자 분리
	window.onload = function(){
			let event_content = "${event.event_content}";
			let arr_content = event_content.split('+');
			
			//각각 span에 넣어줌
			$('.event_content').append(arr_content[0]);
			$('#event_benefit').append(arr_content[1]);
		};
		
	$(function(){
		$('#detail_delete_btn').click(function(){
			$.ajax({
				url:'deleteEvent.do',
				type:'post',
				data:{event_id:${event.event_id}},
				dataType:'json',
				success:function(param){
					if(param.result == 'success'){
						alert('이벤트를 삭제했습니다.');
						location.href='eventList.do?attr=1&attr=0';
					}else{
						alert('이벤트 삭제 오류 발생');
					}
				},
				error:function(){
					alert('네트워크 오류 발생');
				},
			});
		});
	});
</script>	
</head>
<body>
<div class="page-main">
	<!-- header 시작 -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <!-- header 끝 -->
	<!-- 이벤트 신청 배너 시작 -->
	<div id="register_banner" class="row">
		<div class="col"></div>
		<span id="detail_rest_date" class="col-sm-3 col-md-3">
			<c:if test="${event_diffDay<0}">이벤트 종료</c:if>
			<c:if test="${event_diffDay>=0}">마감까지 D-${event_diffDay+1}</c:if>
		</span>
		<!-- 마감일 지나면 안 눌러지게 처리 -->
		<div id="register_btn" class="col-sm-2 col-md-2">
		<input type="button" value="마감 전 신청하기 👉" onclick="location.href='../course/detail.do?course_id=${event.event_course_id}'"
				class="btn btn-outline-info" <c:if test="${event_diffDay<0}">disabled="disabled"</c:if> >
		</div>
		<div class="col"></div>
	</div>
	<!-- 이벤트 신청 배너 끝 -->
	<div class="content-main row">
		<!-- 이벤트 내용 시작 -->
		<div id="event_detail_img" class="col-sm col-md-6 container">
			<img class="rounded img-fluid" src="${pageContext.request.contextPath}/upload/${event.event_photo}">
		</div>
		<div id="about_event_detail" class="col-sm col-md-6 container">
			<div id="event_detail_period">
				<span class="badge rounded-pill text-bg-dark" style="font-size:15px;">&nbsp;이벤트 기간&nbsp;</span>
				<span id="period_date">
				<!-- 이벤트 기간 -->
				<fmt:formatDate value="${event.event_reg_date}" dateStyle="long" var="event_reg_date"/>
				<c:out value="${event_reg_date}"/> - <fmt:parseDate value="${event.event_deadline}" pattern="yyyy-MM-dd" var="event_deadline"/>
			   	<fmt:formatDate value="${event_deadline}" pattern="yyyy년 M월 d일"/>
				<!-- 이벤트 기간 -->
			    </span>
			</div>
			<div id="event_detail_info">
				<h4 id="info_title">🖥[ ${event.event_course_name} ]</h4>
				<p id="event_content">
				<span class="event_content"></span> + 
				<strong id="event_benefit"></strong>
				</p>
				<p>✔${event.event_detail_content}</p>
			</div>
		</div>
		<div class="row" id="eventDetail_btn">
			<div class="col-sm-6 col-md-6 lg-6">
				<input type="button" class="btn btn-primary" value="목록으로" onclick="location.href='eventList.do?attr=1&attr=0'">
			</div>
			<c:if test="${sessionScope.mem_auth == 9 }">
			<div class="col-sm-6 col-md-6 lg-6 text-end">
			<c:if test="${event_diffDay>=0}">
			<input id="detail_update_btn" type="button" value="수정" 
					class="btn btn-outline-primary" onclick="location.href='updateEventForm.do?event_id=${event.event_id}'">
			</c:if>
			<input id="detail_delete_btn" type="button" value="삭제" class="btn btn-outline-secondary">
			</div>
			</c:if>
		</div>
		<!-- 이벤트 내용 끝 -->
		
	</div><!-- .content-main 끝 -->
		<!-- 페이지 하단 주의사항 -->
		<jsp:include page="event_notice.jsp"/>
		<!-- 페이지 하단 주의사항 -->
</div><!-- .page-main 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>