<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
</head>
<body>
	<header id="header" class="header fixed-top d-flex align-items-center">
		<div class="container px-4 px-lg-5" style="float: none; margin: auto;">
			<nav class="navbar navbar-expand-lg navbar-light">
				<a class="navbar-brand" href="#"
					style="font-size: 26px; font-weight: 600; color: #012970; font-family: Nunito, sans-serif;">하늘코딩</a>
				<button class="navbar-toggler" type="button" 
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button> 
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/main/main.do">HOME</a></li>

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">강의</a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="${pageContext.request.contextPath}/course/list.do?course_cate=1">HTML</a></li>
			       					<li><a class="dropdown-item" href="${pageContext.request.contextPath}/course/list.do?course_cate=2">CSS</a></li>
								<li><a class="dropdown-item" href="${pageContext.request.contextPath}/course/list.do?course_cate=3">JAVA</a></li>
								<li><a class="dropdown-item" href="${pageContext.request.contextPath}/course/list.do?course_cate=4">DB</a></li>
							</ul></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">커뮤니티</a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" 
									href="${pageContext.request.contextPath}/board_qna/list.do">질문게시판</a></li>
								<li><a class="dropdown-item"
									href="${pageContext.request.contextPath}/board_free/list.do">자유게시판</a></li>
							</ul></li>
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/news/list.do">공지사항</a></li>
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/event/eventList.do?attr=1&attr=0">이벤트</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">취업센터</a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="${pageContext.request.contextPath}/jobEmploy/employList.do">취업현황</a></li>
								<li><a class="dropdown-item" href="${pageContext.request.contextPath}/jobReview/reviewList.do">취업후기</a></li>
							</ul></li>
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/mypage/profilePage.do">마이페이지(임시)</a></li>	
					</ul>

					<c:if test="${!empty mem_num}">
						<%-- 로그인이 된 경우 --%>

						<span style="margin-right: 10px;">${mem_id} 님</span>
						<Button type="button" class="btn btn-primary"
							onclick="location.href='${pageContext.request.contextPath}/hmember/logout.do'">로그아웃</Button>
					</c:if>

					<c:if test="${empty mem_num}">
						<button type="button" class="btn btn-primary"
							onclick="location.href='${pageContext.request.contextPath}/hmember/loginForm.do'"
							style="float: right;">로그인</button>
						<button class="btn btn-outline-black" type="button"
							onclick="location.href='${pageContext.request.contextPath}/hmember/registerUserForm.do'">회원가입</button>
					</c:if>
				</div>
			</nav>
		</div>
	</header>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>