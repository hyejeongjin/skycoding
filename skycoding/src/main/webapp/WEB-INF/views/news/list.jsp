<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<!-- initial-scale 기본값 -->
<title>공지사항</title>
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">
<!-- 순서가 중요함(맨위에 있으면 스타일 달라짐) -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<!-- 순서가 중요함(맨위에 있으면 스타일 달라짐) -->
</head>
<body>
	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">
		<div class="d-flex align-items-center justify-content-between">
			<a href="${pageContext.request.contextPath}/main/main.do"
				class="logo d-flex align-items-center"> <span
				class="d-none d-lg-block">SkyCoding</span>
			</a>
		</div>
	</header>


	<main id="main" class="main">

		<!-- 검색창 시작 -->
		<div class="section">
			<div class="search-bar">
				<form class="search-form d-flex align-items-center" method="POST"
					action="/news/list.do">
					<select class="form-select" id="form-select1" name="category"
						onchange="" style="width:10%;">
						<option value="1">제목</option>
						<option value="2">내용</option>
					</select> <input type="text" name="query" placeholder="검색어를 입력하세요" style="width:70%">
					<button type="submit" title="Search">
						<i class="bi bi-search"></i>
					</button>
					
				</form>
			</div>
		</div>
		<!-- 검색창 끝 -->


		<!-- content 시작 -->
		
		<div class="content-main">
		<c:if test="${count == 0}">
		<div class="result-display">
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
			<table class="table table-hover align-center" id="t1" style="width:80%">
				<thead class="table-head">
					<tr>
						<th scope="col" width="10%">글번호</th>
						<th scope="col" width="40%">제목</th>
						<th scope="col" width="25%">작성일</th>
						<th scope="col" width="10%">조회수</th>
					</tr>
		
				</thead>
					<c:forEach var="news" items="${list}">
			<tr>
				<td>${news.news_id}</td>
				<td><a href="detail.do?board_num=${news.news_id}">${news.news_title}</a></td>
				<td>${news.news_reg_date}</td>
				<td>${news.news_hit}</td>
			</tr>
			</c:forEach>
			
			</table>
			<div class="align-right">
				<input class="btn btn-primary" type="button" value="글쓰기" onclick="location.href='admin_writeForm.do'">
			</div>
		   <div class="align-center"><nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link" href="/news/list.do"
						aria-label="Previous" style="border: 0px; color: black;"> 
						<span aria-hidden="true">&laquo;</span>
					</a></li>
				
					<li class="page-item"><a class="page-link" href="/news/list.do"
						style="border: 0px; color: black;">${page}</a></li></ul></nav></div></c:if>


			
		<!-- content 끝 -->
	
		<!-- End Pagination with icons -->
		</div>

			</main>
	<!-- End #main -->
</body>
</html>