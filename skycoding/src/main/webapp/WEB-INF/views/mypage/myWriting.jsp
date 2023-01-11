<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>작성게시글</title>

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>


<body>
  <jsp:include page="/WEB-INF/views/common/mywritingmain.jsp"/>

	<main id="main" class="main">
		<!-- 전체선택 및 삭제 -->
		<section class="section">
		    <span class="likecourse-button">
	          <button type="button" class="btn btn-secondary" style="margin-right:0.5em;"><i class="bi bi-check-lg"></i>전체선택</button>
	          <button type="button" class="btn btn-danger"><i class="bi bi-x-lg"></i>삭제</button>
	        </span>	
		</section>
		
		<!-- 검색창 시작 -->
		<div class="section">
			<div class="search-bar">
				<form class="search-form d-flex align-items-center" method="POST"
					action="#">
					<select class="form-select" style="width:160px; float:left;" id="form-select1" name="category"
						onchange="">
						<option value="1" selected>제목 + 내용</option>
						<option value="2">제목</option>
						<option value="3">내용</option>
					</select> <input type="text" name="query" placeholder="검색어를 입력하세요">
					<button type="submit" title="Search">
						<i class="bi bi-search"></i>
					</button>
				</form>
			</div>
		</div>
		<!-- 검색창 끝 -->

		<!-- content 시작 -->
		<div class="content-main align-center">
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col" width="5%"></th>
						<th scope="col" width="10%">글번호</th>
						<th scope="col" width="10%">카테고리</th>
						<th scope="col" width="25%">제목</th>
						<th scope="col" width="15%">작성자</th>
						<th scope="col" width="15%">작성일</th>
						<th scope="col" width="10%">추천수</th>
						<th scope="col" width="10%">조회수</th>
					</tr>
				</thead>
				<tbody>
					<tr>				
						<td><input type="checkbox" name="checkbox"></td>
						<th scope="row">3</th>
						<td>취업후기</td>
						<td>가나다라마바사</td>
						<td>Otto</td>
						<td>2023-01-05</td>
						<td>123</td>
						<td>178</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkbox"></td>
						<th scope="row">2</th>
						<td>자유게시판</td>
						<td>가나다라마바사</td>
						<td>Thornton</td>
						<td>2022-12-25</td>
						<td>150</td>
						<td>409</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkbox"></td>
						<th scope="row">1</th>
						<td>취헙후기</td>
						<td>가나다라마바사</td>
						<td>Thornton</td>
						<td>2022-12-13</td>
						<td>3</td>
						<td>144</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- content 끝 -->
		
		<!-- 글쓰기 버튼 -->
		<div>
			<input type="button" class="btn btn-primary" style="float:right" value="글쓰기" >
		</div><br><br>
				
	    <div class="text-center">
	      <!-- Pagination with icons -->
	      <nav aria-label="Page navigation example">
	                <ul class="pagination justify-content-center">
	                  <li class="page-item">
	                    <a class="page-link" href="#" aria-label="Previous" style="border:0px; color:black;">
	                      <span aria-hidden="true">&laquo;</span>
	                    </a>
	                  </li>
	                  <li class="page-item"><a class="page-link" href="#" style="border:0px; color:black;">1</a></li>
	                  <li class="page-item"><a class="page-link" href="#" style="border:0px; color:black;">2</a></li>
	                  <li class="page-item"><a class="page-link" href="#" style="border:0px; color:black;">3</a></li>
	                  <li class="page-item"><a class="page-link" href="#" style="border:0px; color:black;">4</a></li>
	                  <li class="page-item">
	                    <a class="page-link" href="#" aria-label="Next" style="border:0px; color:black;">
	                      <span aria-hidden="true">&raquo;</span>
	                    </a>
	                  </li>
	                </ul>
	              </nav><!-- End Pagination with icons -->
	    </div>

	</main>
	<!-- End #main -->
</body>

</html>