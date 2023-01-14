<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- 로그인 된 경우에만 글쓰기버튼 활성화 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>취업현황 상세글</title>
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

<!-- Vendor CSS Files -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/job_style.css">
<!-- 순서가 중요함(맨위에 있으면 스타일 달라짐) -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/dce0734714.js" crossorigin="anonymous"></script>
</head> 
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 사이드바 시작-->
	<aside id="sidebar" class="sidebar">

		<ul class="sidebar-nav" id="sidebar-nav">
			<li class="nav-item"><a class="nav-link "
				href="${pageContext.request.contextPath}/jobEmploy/employList.do"> <span>취업현황</span>
			</a></li>

			<li class="nav-item"><a class="nav-link collapsed"
				href="${pageContext.request.contextPath}/jobReview/reviewList.do"> <span>취업후기</span>
			</a></li>
		</ul>
	</aside>
	<!-- 사이드바 끝-->
		
	<div class="content-main"><!-- 전체 화면의 80% -->
		<!-- 사이드바 오른쪽 화면 시작 -->
		<div class="content-right">
			<div>
				<div class="detail-top">
					<h2>${employ.emp_title}</h2>
					<!-- 관리자만 수정, 삭제 가능 -->
					<c:if test="${mem_auth==9}">
					<div class="top-button">
						<input type="button" value="수정" class="btn btn-primary btn-sm" 
								onclick="location.href='employUpdateForm.do?emp_id=${employ.emp_id}'">
						<input type="button" value="삭제" class="btn btn-primary btn-sm" id="delete-btn">
						<script type="text/javascript">
							$(function(){
								$('#delete-btn').click(function(){
									let choice = confirm('정말 삭제하시겠습니까?');
									if(choice){
										location.replace('employDelete.do?emp_id=${employ.emp_id}');
									}
								});
							});
						</script>
					</div>
					</c:if>					
				</div>
				<hr class="hr-style" size="1" width="100%">
				<ul class="detail-info1">
					<li>
						<c:if test="${!empty employ.mem_photo}">
						<img src="${pageContext.request.contextPath}/upload/${employ.mem_photo}" width="40px" height="40px" class="my-photo">
						</c:if>
						<c:if test="${empty employ.mem_photo}">
						<img src="${pageContext.request.contextPath}/images/face.png" width="40px" height="40px" class="my-photo">
						</c:if>
					</li>
					<li>${employ.mem_id}</li>
				</ul>
				<ul class="detail-info2">
					<li>작성일 ${employ.emp_reg_date}</li>
					<c:if test="${!empty employ.emp_modify_date}">
					<li>수정일 ${employ.emp_modify_date}</li>
					</c:if>
					<li><i class="fa-solid fa-eye"></i> ${employ.emp_hit}</li>
				</ul>
			</div>
			<hr class="hr-style" size="1" width="100%">
			<div class="detail-content">
				${employ.emp_content}<br><br>
				[수강한 강의 내역]<br>
				<c:forEach var="course" items="${list}">
					${course.course_name}<br>
				</c:forEach>
			</div>
			<hr class="hr-style" size="1" width="100%">
			
			<div class="bottom-btn">
				<!-- 이전글, 다음글 -->
				<ul class="bottom-prevNext">
					<li>
						<c:if test="${pnEmploy.prev!=0}">
 								이전글 | <a href="employDetail.do?emp_id=${pnEmploy.prev}">${pnEmploy.prev_title}</a>
 								</c:if></li>
 							<li>
								<c:if test="${pnEmploy.next!=0}">
								다음글 | <a href="employDetail.do?emp_id=${pnEmploy.next}">${pnEmploy.next_title}</a>
								</c:if>
					</li>
				</ul>
			
				<input class="btn btn-primary list-btn my-3" type="button" value="목록" onclick="location.href='employList.do'">
			</div>
			
		</div><!-- 사이드바 오른쪽 화면 끝 -->	
	</div><!-- 컨텐트 메인 끝 -->
</div><!-- 페이지 메인 -->
<jsp:include page="/WEB-INF/views/common/footer1.jsp" />
</body>
</html>