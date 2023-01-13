<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>이벤트 메인페이지</title>
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
<!-- Vendor CSS Files -->
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/event-style.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<div id="event_list_nav" class="btn_nav">
				<c:if test="${mem_auth == 9}">
                <input id="list_register_btn" class="btn btn-outline-warning btn-md float-start" type="button" value="이벤트 등록" style="display:block"
                			onclick="location.href='registerEventForm.do'">
                </c:if>
					<select id="event_list_select" class="float-end btn btn-outline-danger btn-md" id="form-select2" name="keyfield2" aria-label="form-select">
						<option value="1" <c:if test="${param.keyfield2==1}">selected</c:if>>마감임박순</option>
						<option value="2" <c:if test="${param.keyfield2==2}">selected</c:if>>조회순</option>
						<option value="2" <c:if test="${param.keyfield2==3}">selected</c:if>>최신순</option>
					</select>
        </div>
        <div style="clear:both;"></div>
        <!-- 진행중 이벤트 카드 시작 -->
        <h3 class="event_cate_title">진행중인 이벤트</h3>
	    <hr size="1" noshade>
        <div class="container" id="progress_event">
        	<c:if test="${empty list}">
		    	<h4 class="locked_event_notice" align="center" style="color:gray;">등록된 게시물이 없습니다</h4>
		    	</c:if>
        	<c:if test="${!empty list}">
	        <div class="row row-cols-1 row-cols-md-2 g-2">
			<c:forEach var="event" items="${list}">
	            <div class="col">
	              <div class="card "><!-- shadow p-3 mb-5 bg-body rounded --><!-- shadow-sm p-3 mb-5 bg-body rounded -->
	                <img class="card-img-top" src="${pageContext.request.contextPath}/upload/${event.event_photo}">
	                <div class="card-body">
	                  <h5 class="card-title">🖥 ${event.event_course_name}</h5>
	                  <a class="event_btn btn btn-primary float-start" href="eventDetail.do?event_id=${event.event_id}">상세보기</a>
	                  	<!-- 마감까지 남은 날짜 계산 -->
				        <jsp:useBean id="now" class="java.util.Date"/>
						<fmt:parseNumber var="today" value="${now.time/(1000*60*60*24)}" integerOnly="true"/>
  	                    <fmt:parseDate var="deadlineDate" value="${event.event_deadline}" pattern="yyyy-MM-dd"/>
  	                    <fmt:parseNumber var="deadline" value="${deadlineDate.time/(1000*60*60*24)}" integerOnly="true"/>
	                  	<!-- 마감까지 남은 날짜 계산 -->
	                  <p class="event_btn btn btn-primary float-end disabled">마감
	                  	<span class="event_rest_date">${deadline - today + 1}</span>일 전
	                  </p>
	                </div>
	              </div>
	            </div>
	            <c:if test="${fn:length(list) < 2}">
	            <div id=locked_event class="col">
	              <div class="card">
	                <img class="card-img-top" src="${pageContext.request.contextPath}/images/ready.jpg" height="250px">
	                <div class="card-body">
	                  <h5 class="card-title" style="color:gray">아직 등록된 이벤트가 없습니다</h5>
	                  <a class="event_btn btn btn-outline-secondary float-start disabled" href="eventDetail.do">준비 중</a>
	                  <p class="event_btn btn btn-outline-secondary float-end disabled">준비 중</p>
	                </div>
	              </div>
	            </div>
	            </c:if>
	         </c:forEach>
		</div>
        <div class="row row-cols-1 row-cols-md-1 g-1 justify-content-center">
        <input type="button" class="btn btn-outline-dark btn-md col-md-5 col-sm-5 col" value="더보기🔍" 
        		id="more_view" onclick="location.href='moreViewEvent.do?attr=1'">
        </div>
		</c:if>
		</div>
		<!-- 진행중 이벤트 카드 끝 -->
		
		<!-- 종료된 이벤트 카드 시작 -->
	    <h3 class="event_cate_title">종료된 이벤트</h3>
	    <hr size="1" noshade>
		    <div class="container" id="end_event">
		    	<c:if test="${empty list2}">
		    	<h4 class="locked_event_notice" align="center" style="color:gray;">등록된 게시물이 없습니다</h4>
		    	</c:if>
		    	<c:if test="${!empty list2}">
		     	<div class="end_event row row-cols-1 row-cols-md-2 g-2">
					<c:forEach var="event" items="${list2}">
		         	<div class="end_event_ col">
			           <div class="card">
			             <img class="card-img-top" src="${pageContext.request.contextPath}/upload/${event.event_photo}">
			             <div class="card-body">
			               <h5 class="card-title">${event.event_course_name}</h5>
			               <a class="event_btn btn btn-primary float-start" 
			                  href="eventDetail.do?event_id=${event.event_id}">상세보기</a>
			               <p class="event_btn btn btn-secondary float-end disabled">마감</p>
			             </div>
			           </div>
			         </div>
			         <c:if test="${fn:length(list2) < 2}">
	            	<div id=locked_event class="col">
	              		<div class="card">
	                		<img class="card-img-top" src="${pageContext.request.contextPath}/images/ready.jpg" height="230">
	                	<div class="card-body">
	                  		<h5 class="card-title" style="color:gray">아직 등록된 이벤트가 없습니다</h5>
	                  		<a class="event_btn btn btn-outline-secondary float-start disabled" href="eventDetail.do">준비 중</a>
	                  		<p class="event_btn btn btn-outline-secondary float-end disabled">준비 중</p>
	                	</div>
	              </div>
	            </div>
	            </c:if>
		     		</c:forEach>
		     	</div>
		     		<div style="clear:both; height:20px;"></div>
				<div class="row row-cols-1 row-cols-md-1 g-1 justify-content-center">
	     		<input type="button" class="btn btn-outline-dark btn-md col-md-5 col-sm-5 col" value="더보기🔍"  
	     			   id="more_view" onclick="location.href='moreViewEvent.do?attr=0'">
	     		</div>
			</c:if>
		</div>
		<!-- 종료된 이벤트 카드 끝 -->
		</div><!-- end of content-main -->
	</div><!-- end of page-main -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>