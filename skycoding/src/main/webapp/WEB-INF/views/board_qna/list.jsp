<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>커뮤니티</title>

<!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#search_form').submit(function(){
		if($('#keyword').val().trim()==''){
			alert('검색어를 입력하세요');
			$('#keyword').val('').focus();
			return false;
		}	
	});
});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">
    <ul class="sidebar-nav" id="sidebar-nav">
      <li class="nav-item">
        <a class="nav-link collapsed" href="list.do">
          <i class="bi bi-grid"></i>
          <span>질문글</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link collapsed" href="#">
          <span>자유글</span>
        </a>
      </li>
    </ul>
  </aside>
<!-- End Sidebar-->

<!-- Start #main -->
<main id="main" class="main">
	<br>
	<span style="font-size:25pt;">질문글</span>
	<hr size="1" noshade="noshade" width="100%">
	
		<%--검색 폼 시작 --%>

			<form id="search_form" action="list.do" method="get" class="d-inline-flex">
				<select name="keyfield" class="form-select m-1"
					aria-label="Default select example" style="width: auto;">
					<%--제목을 선택한 게 남아있게 하기 위해 value, c:if 작성. value가 keyfield에 전송된다--%>
					<%--제목,작성자,내용이 keyfield, 검색창이 keyword --%>
					<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>작성자</option>
					<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>내용</option>
				</select> 
				<input class="navbar-search form-control m-1" type="search" name="keyword"
					id="keyword" placeholder="Search" value="${param.keyword}"
					aria-label="Default select example" style="width: auto;">
				<button class="btn btn-outline-primary m-1" type="submit">검색</button>
			
			</form>
	
		<%--검색 폼 끝 --%>
		
		<%--조회순, 최신순 시작 --%>
		<%--조회순, 최신순 끝 --%>
		
		<table class="table caption-top mt-3" id="table-main">
			<thead>
				<tr style="border-top: 1px solid">
					<th scope="col" style="width: 5%" class="text-center">번호</th>
					<th scope="col" style="width: 40%" class="text-center">제목</th>
					<th scope="col" style="width: 10%">작성자</th>
					<th scope="col" style="width: 15%" class="text-center">작성일</th>
					<th scope="col" style="width: 5%" class="text-center">조회</th>
				</tr>
			</thead>
		
		<c:if test="${count == 0}">
		<div class="result-display">
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		
		<c:forEach var="qnaBoard" items="${list}">
			<tr>
				<td>${qnaBoard.qna_id}</td>
				<td><a href="detail.do?qna_id=${qnaBoard.qna_id}">${qnaBoard.qna_title}</a></td>
				<td>${qnaBoard.mem_id}</td>
				<td>${qnaBoard.qna_reg_date}</td>
				<td>${qnaBoard.qna_hit}</td>
			</tr>
		</c:forEach>
		</table>
		<div class="list-space align-right">
			<!-- 로그인 안 되어 있으면 태그가 보여지긴 하는데 비활성화 -->
			<button type="button" class="btn btn-primary" style="float:right;" onclick="location.href='writeForm.do'"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>>글쓰기</button>
		</div>
</main>
</body>
</html>