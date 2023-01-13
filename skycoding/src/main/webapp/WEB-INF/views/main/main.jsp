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
					<div class="carousel-item active">
						<img src="${pageContext.request.contextPath}/images/banner1.jpg"
							class="d-block w-100">
					</div>
					<div class="carousel-item">
						<img src="${pageContext.request.contextPath}/images/banner2.png"
							class="d-block w-100">
					</div>
					<div class="carousel-item">
						<img src="${pageContext.request.contextPath}/images/banner3.png"
							class="d-block w-100">
					</div>
				</div>
				<!-- end of carousel-inner -->
			</div>
			<!-- end of carousel -->

			<!-- start of card -->
			<section class="py-5">
				<div class="container px-4 px-lg-5">
					<h2 class="py-3">신규 강의</h2>
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
												href="${pageContext.request.contextPath}/course/detail.do?course_id=${course.course_id}">수강하러가기</a>
									</div>
								</div>
							</div>
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
												href="${pageContext.request.contextPath}/course/detail.do?course_id=${course2.course_id}">수강하러가기</a>
									</div>
								</div>
							</div>
						</div>
						</c:forEach>
						<!-- 카드2 끝 -->
						<!-- 카드3 시작 -->
						<c:forEach var="course3" items="${courseList}">
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
												href="${pageContext.request.contextPath}/course/detail.do?course_id=${course3.course_id}">수강하러가기</a>
									</div>
								</div>
							</div>
						</div>  
						</c:forEach>
						<!-- 카드3 끝 -->
						<!-- 카드4 시작 -->
						<c:forEach var="course4" items="${courseList}">
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
												href="${pageContext.request.contextPath}/course/detail.do?course_id=${course4.course_id}">수강하러가기</a>
									</div>
								</div>
							</div>
						</div>
						</c:forEach>
						<!-- 카드4 끝 -->
					</div>
				</div>
			</section>
		</div>
	</div>
	<!-- end of card -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>