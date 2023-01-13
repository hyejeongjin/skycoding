<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>종료된 이벤트 더보기</title>
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
<!-- Vendor CSS Files -->
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/event-style.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
<!-- 진행중 이벤트 카드 시작 -->
     <h3 style="font-size:20px;">진행중인 이벤트</h3>
     <div class="container" id="progress_event">
     <!-- 게시물이 없을 때 -->
     <c:if test="${count == 0}">
      <table class="table table-group-divider align-center">
     	<tr>
			<td>표시할 게시물이 없습니다</td>
		</tr>
      </table>
     </c:if>
     <!-- 게시물이 없을 때 -->
     
     <!-- 게시물이 존재할 때 -->
     <c:if test="${count > 0}">
      <div class="row row-cols-1 row-cols-md-2 g-2">
		<c:forEach var="event" items="${list}">
            <div class="col">
              <div class="card">
                <img src="${pageContext.request.contextPath}/upload/${event.event_photo}" class="card-img-top">
                <div class="card-body">
                  <h5 class="card-title">${event.event_course_name}</h5>
                  <a class="event_btn btn btn-primary float-start" href="eventDetail.do?event_id=${event.event_id}">상세보기</a>
                  
                  		<!-- 마감까지 남은 일수 계산 -->
			       	 	<jsp:useBean id="now" class="java.util.Date"/>
						<fmt:parseNumber var="today" value="${now.time/(1000*60*60*24)}" integerOnly="true"/>
 	                    <fmt:parseDate var="deadlineDate" value="${event.event_deadline}" pattern="yyyy-MM-dd"/>
 	                    <fmt:parseNumber var="deadline" value="${deadlineDate.time/(1000*60*60*24)}" integerOnly="true"/>
                  		<!-- 마감까지 남은 일수 계산 -->
                  		
                  <p class="event_btn btn btn-primary float-end disabled">마감
                  	<span class="event_rest_date">${deadline - today}</span>일 전
                  </p>
                </div>
              </div>
            </div>
         </c:forEach>
         <!-- 페이지 블럭 시작 -->
         <c:if test="${count > 0}">
			<div class="align-center col-md-12 col-sm-12">${page}</div>
		</c:if>
         <!-- 페이지 블럭 끝 -->
	</div>
	</c:if>
    <!-- 게시물이 존재할 때 -->
    </div>
    <!-- 이벤트 카드 끝 -->
    </div><!-- .content-main -->
</div><!-- end of container -->
<!-- 진행중 이벤트 카드 끝 -->
</body>
</html>