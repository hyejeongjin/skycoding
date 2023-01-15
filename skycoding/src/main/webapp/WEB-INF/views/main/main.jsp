<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하늘코딩</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/style.css"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/dce0734714.js" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="container" style="margin-top: 100px;">
			<!-- start of carousel -->
			<div id="carousel" class="carousel slide" data-bs-ride="carousel">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#carousel"
						data-bs-slide-to="0" class="active"></button>
					<button type="button" data-bs-target="#carousel"
						data-bs-slide-to="1"></button>
					<button type="button" data-bs-target="#carousel"
						data-bs-slide-to="2"></button>
				</div>
				<!-- end of carousel-indicators -->

				<div class="carousel-inner">
					<div class="carousel-item active" data-bs-interval="2000">
						<img src="${pageContext.request.contextPath}/images/banner1.jpg"
							class="d-block w-100">
					</div>
					<div class="carousel-item" data-bs-interval="2000">
						<img src="${pageContext.request.contextPath}/images/banner2.png"
							class="d-block w-100">
					</div>
					<div class="carousel-item" data-bs-interval="2000">
						<img src="${pageContext.request.contextPath}/images/banner3.png"
							class="d-block w-100">
					</div>
				</div>
				<!-- end of carousel-inner -->
			</div>
			<!-- end of carousel -->
			
			<!-- 강의 4개 목록 바로가기 -->
			<div id="main_course_btn">
				<ul>
					<li><a href="${pageContext.request.contextPath}/course/list.do?course_cate=1">
						<i class="fa-brands fa-html5 fa-2x" style="color:orange;"></i><br>
						# HTML
					</a></li>
					<li><a href="${pageContext.request.contextPath}/course/list.do?course_cate=2">
						<i class="fa-brands fa-css3-alt fa-2x" style="color:blue;"></i><br>
						# CSS
					</a></li>
					<li><a href="${pageContext.request.contextPath}/course/list.do?course_cate=3">
						<i class="fa-brands fa-java fa-2x" style="color:red;"></i><br>
						# JAVA
					</a></li>
					<li><a href="${pageContext.request.contextPath}/course/list.do?course_cate=4">
						<i class="fa-solid fa-database fa-2x" style="color:gray;"></i><br>
						# DB
					</a></li>
				</ul>
			</div>
			
			<!-- start of card -->
			<section class="py-3" id="main_list">
				<div class="container px-4 px-lg-5">
					<h4 class="py-3"><a href="${pageContext.request.contextPath}/course/list.do">신규 강의 <i class="fa-regular fa-star" style="color:orange"></i> &nbsp; ></a></h4>
					<div
						class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-3 justify-content-center">
						<!-- 카드1 시작 -->
						<c:forEach var="course" items="${courseList}">
						<div class="col mb-5">
							<div class="card h-50">
								<!-- Product image-->
									<img class="card-img-top"
									src="${pageContext.request.contextPath}/upload/${course.course_photo}"
									<c:if test="${course.course_photo==null}">
									src="${pageContext.request.contextPath}/images/blank.png"
									</c:if>>
								<!-- Product actions-->
								<div class="card-footer p-4 pt-3 border-top-0 bg-transparent">
									<div class="text-center">
										<a class="btn btn-outline-dark mt-auto" 
												href="${pageContext.request.contextPath}/course/detail.do?course_id=${course.course_id}&course_cate=${course.course_cate}">수강하러가기</a>
									</div>
								</div>
							</div><!-- end of card -->
						</div>
						</c:forEach>
						<!-- 카드1 끝 -->
						<!-- 카드2 시작 -->
						<c:forEach var="course2" items="${courseList2}">
						<div class="col mb-5">
							<div class="card h-50">
								<!-- Product image-->
									<img class="card-img-top"
									src="${pageContext.request.contextPath}/upload/${course2.course_photo}"
									<c:if test="${course2.course_photo==null}">
									src="${pageContext.request.contextPath}/images/blank.png"
									</c:if>>
								<!-- Product actions-->
								<div class="card-footer p-4 pt-3 border-top-0 bg-transparent">
									<div class="text-center">
										<a class="btn btn-outline-dark mt-auto" 
												href="${pageContext.request.contextPath}/course/detail.do?course_id=${course2.course_id}&course_cate=${course2.course_cate}">수강하러가기</a>
									</div>
								</div>
							</div>
						</div>
						</c:forEach>
						<!-- 카드2 끝 -->
						<!-- 카드3 시작 -->
						<c:forEach var="course3" items="${courseList3}">
						<div class="col mb-5">
							<div class="card h-50">
								<!-- Product image-->
									<img class="card-img-top"
									src="${pageContext.request.contextPath}/upload/${course3.course_photo}"
									<c:if test="${course3.course_photo==null}">
									src="${pageContext.request.contextPath}/images/blank.png"
									</c:if>>
								<!-- Product actions-->
								<div class="card-footer p-4 pt-3 border-top-0 bg-transparent">
									<div class="text-center">
										<a class="btn btn-outline-dark mt-auto" 
												href="${pageContext.request.contextPath}/course/detail.do?course_id=${course3.course_id}&course_cate=${course3.course_cate}">수강하러가기</a>
									</div>
								</div>
							</div>
						</div>  
						</c:forEach>
						<!-- 카드3 끝 -->
						<!-- 카드4 시작 -->
						<c:forEach var="course4" items="${courseList4}">
						<div class="col mb-5">
							<div class="card h-50">
								<!-- Product image-->
									<img class="card-img-top"
									src="${pageContext.request.contextPath}/upload/${course4.course_photo}"
									<c:if test="${course4.course_photo==null}">
									src="${pageContext.request.contextPath}/images/blank.png"
									</c:if>>
								<!-- Product actions-->
								<div class="card-footer p-4 pt-3 border-top-0 bg-transparent">
									<div class="text-center">
										<a class="btn btn-outline-dark mt-auto" 
												href="${pageContext.request.contextPath}/course/detail.do?course_id=${course4.course_id}&course_cate=${course4.course_cate}">수강하러가기</a>
									</div>
								</div>
							</div>
						</div>
						</c:forEach>
						<!-- 카드4 끝 -->
					</div>
				</div><!-- 신규강의 -->
				
				<hr size="1" width="100%" style="color:gray;margin:30px 0;">
				
				<div class="px-4 px-lg-5 py-4" id="main_board_list">
					<div class="col-6"><!-- 왼쪽 글 정보 -->
						<h2><span class="text-primary">${count}</span>명이<br>스카이코딩과<br>함께합니다!<br></h2><br>
						<p>학교에서 배우기 어렵거나 큰 비용을 지불해야만<br> 배울 수 있는 지식을 스카이코딩이 무료로 제공합니다</p>
						실력있는 강사님들이 제공하는 다양한 강의를 통해<br>전문적인 지식을 쌓아보세요!
					</div>
					
					<div class="col-6"><!-- 자유게시판 -->
						<h4 class="pb-3"><a href="${pageContext.request.contextPath}/board_free/list.do">자유게시판 <i class="fa-solid fa-chalkboard"></i> &nbsp; ></a></h4>
					
						<table class="table table-hover table-group-divider">
						<c:forEach var="freeBoard" items="${boardList}">
						<tr>
							<td>
								<div><a class="title-link" href="${pageContext.request.contextPath}/board_free/detail.do?free_id=${freeBoard.free_id}">${freeBoard.free_title}</a></div>
								<span class="t-sub-info">
									<c:if test="${freeBoard.free_status == 0}">${freeBoard.mem_id}</c:if>
									<c:if test="${freeBoard.free_status == 1}">익명</c:if>
								</span> &nbsp; 
								<span class="t-sub-info">${freeBoard.free_reg_date}</span> &nbsp;
								<span class="t-sub-info"><i class="fa-solid fa-eye"></i> ${freeBoard.free_hit}</span>
							</td>
						</tr>
						</c:forEach>
						</table>
					</div>
				</div>
			</section>
		</div>
	</div>
	<!-- end of card -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>