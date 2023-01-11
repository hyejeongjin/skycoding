<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- start of footer -->
	<footer class="py-5 bg-dark">
	<div id="footer1_container">
		
		<div class="text-white container footer1-right">
			<div class="container d-flex justify-content-center" style="margin-bottom:15px;">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0" style="list-style-type: none;">
						<li class="nav-item dropdown">
							<a class="nav-link">강의</a>
							<a class="nav-link" href="#">HTML</a>
							<a class="nav-link" href="#">CSS</a>
							<a class="nav-link" href="#">JAVA</a>
							<a class="nav-link" href="#">DB</a>
						</li>
				</ul>
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4" style="list-style-type: none;">
						<li class="nav-item dropdown">
							<a class="nav-link">커뮤니티</a>
							<a class="nav-link"
								<c:if test="${empty mem_num}">
								href="${pageContext.request.contextPath}/hmember/loginForm.do"
								</c:if>
								<c:if test="${!empty mem_num && (mem_auth == 1 || mem_auth == 9)}"> 
								href="${pageContext.request.contextPath}/board_qna/list.do"
								</c:if>>질문게시판</a>
							<a class="nav-link" 
								<c:if test="${empty mem_num}">
								href="${pageContext.request.contextPath}/hmember/loginForm.do"
								</c:if>
								<c:if test="${!empty mem_num && (mem_auth == 1 || mem_auth == 9)}"> 
								href="${pageContext.request.contextPath}/board_free/list.do"
								</c:if>
							>자유게시판</a>
						</li>
				</ul>
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4" style="list-style-type: none;">
						<li class="nav-item dropdown">
							<a class="nav-link" href="#">공지사항</a>
						</li>
				</ul>
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4" style="list-style-type: none;">
						<li class="nav-item dropdown">
							<a class="nav-link" href="#">이벤트</a>
						</li>
				</ul>
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4" style="list-style-type: none;">
						<li class="nav-item dropdown">
							<a class="nav-link">취업센터</a>
							<a class="nav-link" href="${pageContext.request.contextPath}/jobEmploy/employList.do">취업현황</a>
							<a class="nav-link" href="${pageContext.request.contextPath}/jobReview/reviewList.do">취업후기</a>
						</li>
				</ul>
			</div>
		</div>
		
		<div class="text-white container footer1-right" id="footer1_right">
			<span class="footer-brand ">하늘코딩</span> | 개인정보처리방침 | 이용약관<br>
			(주)하늘코딩 | 대표자 : SKY | 사업자번호 : 123-45-67890 사업자 정보 확인<br>
			통신판매업:2022-강남역삼B-1612 | 개인정보보호책임자 : SKY | 이메일 : test@semitest.com<br>
			주소 : 서울특별시 강남구 테헤란로 132 8층 쌍용교육센터
		</div>
		</div>
	</footer>
	<!-- end of footer -->
</body>
</html>