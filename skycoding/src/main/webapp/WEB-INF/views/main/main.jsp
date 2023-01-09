<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						<img src="${pageContext.request.contextPath}/image/mainimg01.jpg"
							class="d-block w-100">
					</div>
					<div class="carousel-item">
						<img src="${pageContext.request.contextPath}/image/mainimg02.jpg"
							class="d-block w-100">
					</div>
					<div class="carousel-item">
						<img src="${pageContext.request.contextPath}/image/mainimg04.jpg"
							class="d-block w-100">
					</div>
				</div>
				<!-- end of carousel-inner -->

				<!-- <button class="carousel-control-prev" type="button"
					data-bs-target="#carousel" data-bs-slide="prev">
					<span class="carousel-control-prev-icon"
						style="margin-right: 20px;"></span> <span class="visually-hidden">Previous</span>
				</button>

				<button class="carousel-control-next" type="button"
					data-bs-target="#carousel" data-bs-slide="next">
					<span class="carousel-control-next-icon"></span> <span
						class="visually-hidden">Next</span>
				</button> -->
			</div>
			<!-- end of carousel -->

			<!-- start of card -->
			<section class="py-5">
				<div class="container px-4 px-lg-5">
					<h2 class="py-3">신규 강의</h2>
					<div
						class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-3 justify-content-center">
						<div class="col mb-5">
							<div class="card h-50">
								<!-- Product image-->
								<img class="card-img-top"
									src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg"
									alt="..." />

								<!-- Product actions-->
								<div class="card-footer p-4 pt-3 border-top-0 bg-transparent">
									<div class="text-center">
										<a class="btn btn-outline-dark mt-auto" href="#">수강하러가기</a>
									</div>
								</div>
							</div>
						</div>
						<div class="col mb-5">
							<div class="card h-50">
								<!-- Product image-->
								<img class="card-img-top"
									src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg"
									alt="..." />

								<!-- Product actions-->
								<div class="card-footer p-4 pt-3 border-top-0 bg-transparent">
									<div class="text-center">
										<a class="btn btn-outline-dark mt-auto" href="#">수강하러가기</a>
									</div>
								</div>
							</div>
						</div>
						<div class="col mb-5">
							<div class="card h-50">
								<!-- Product image-->
								<img class="card-img-top"
									src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg"
									alt="..." />

								<!-- Product actions-->
								<div class="card-footer p-4 pt-3 border-top-0 bg-transparent">
									<div class="text-center">
										<a class="btn btn-outline-dark mt-auto" href="#">수강하러가기</a>
									</div>
								</div>
							</div>
						</div>
						<div class="col mb-5">
							<div class="card h-50">
								<!-- Product image-->
								<img class="card-img-top"
									src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg"
									alt="..." />

								<!-- Product actions-->
								<div class="card-footer p-4 pt-3 border-top-0 bg-transparent">
									<div class="text-center">
										<a class="btn btn-outline-dark mt-auto" href="#">수강하러가기</a>
									</div>
								</div>
							</div>
						</div>
						<div class="col mb-5">
							<div class="card h-50">
								<!-- Product image-->
								<img class="card-img-top"
									src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg"
									alt="..." />

								<!-- Product actions-->
								<div class="card-footer p-4 pt-3 border-top-0 bg-transparent">
									<div class="text-center">
										<a class="btn btn-outline-dark mt-auto" href="#">수강하러가기</a>
									</div>
								</div>
							</div>
						</div>
						<div class="col mb-5">
							<div class="card h-50">
								<!-- Product image-->
								<img class="card-img-top"
									src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg"
									alt="..." />

								<!-- Product actions-->
								<div class="card-footer p-4 pt-3 border-top-0 bg-transparent">
									<div class="text-center">
										<a class="btn btn-outline-dark mt-auto" href="#">수강하러가기</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
	<!-- end of card -->
	<!-- start of footer -->
	<footer class="py-5 bg-dark">
		<div class="text-white container">
			<div class="container d-flex justify-content-center" style="margin-bottom:15px;">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4" style="list-style-type: none;">
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
							<a class="nav-link" href="#">HTML</a>
							<a class="nav-link" href="#">CSS</a>
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
							<a class="nav-link" href="#">취업후기</a>
							<a class="nav-link" href="#">취업현황</a>
						</li>
				</ul>
			</div>
		</div>
		<div class="text-white container">
			<span class="footer-brand">하늘코딩</span> | 개인정보처리방침 | 이용약관<br>
			(주)하늘코딩 | 대표자 : SKY | 사업자번호 : 123-45-67890 사업자 정보 확인<br>
			통신판매업:2022-강남역삼B-1612 | 개인정보보호책임자 : SKY | 이메일 : test@semitest.com<br>
			주소 : 서울특별시 강남구 테헤란로 132 8층 쌍용교육센터
		</div>
	</footer>
	<!-- end of footer -->
</body>
</html>