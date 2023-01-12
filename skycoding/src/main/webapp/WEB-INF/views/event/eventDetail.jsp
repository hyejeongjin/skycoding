<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 상세페이지</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
	#register_banner {
	font-size:25px; 
	vertical-align:bottom;
	}
	#event_benefit{
	color:red;
	}
</style>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" 
	 	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
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
	
</script>	
	
</head>
<body>
<div class="container">
	<!-- 이벤트 신청 배너 시작 -->
	<div id="register_banner">
		<span id="detail_rest_date">
			<c:if test="${event_diffDay<0}">이벤트 종료</c:if>
			<c:if test="${event_diffDay>=0}">마감까지 D-${event_diffDay+1}</c:if>
		</span>
		<!-- 마감일 지나면 안 눌러지게 처리 -->
		<input id="register_btn" type="button" value="마감 전 신청하기 👉" onclick="location.href='registerEvent.do'"
				class="btn btn-outline-info" <c:if test="${event_diffDay<0}">disabled="disabled"</c:if> >
	</div>
	<!-- 이벤트 신청 배너 끝 -->
	
	<!-- 이벤트 내용 시작 -->
	<div id="event_detail_img">
		<img src="${pageContext.request.contextPath}/upload/${event.event_photo}">
	</div>
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
		<h4>[ ${event.event_course_name} ]</h4>
		<p id="event_content">
		<span class="event_content"></span> + 
		<span id="event_benefit"></span>
		</p>
		<p>${event.event_detail_content}</p>
	</div>
	<c:if test="${sessionScope.mem_auth == 9 }">
	<c:if test="${event_diffDay>=0}">
	<input id="deatil_modify_btn" type="button" value="수정" onclick="location.href='modifyEventForm.do?event_id=${event.event_id}'">
	</c:if>
	<input id="deatil_delete_btn" type="button" value="삭제" onclick="location.href='deleteEvent.do?event_id=${event.event_id}'">
	</c:if>
	<!-- 이벤트 내용 끝 -->
	
	<!-- 페이지 하단 주의사항 -->
	<jsp:include page="event_notice.jsp"/>
	<!-- 페이지 하단 주의사항 -->
</div>
</body>
</html>