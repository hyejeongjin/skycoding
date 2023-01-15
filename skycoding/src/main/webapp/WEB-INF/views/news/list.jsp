<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%-- 로그인 된 경우에만 글쓰기버튼 활성화 --%>
<!DOCTYPE html>
<html>
<head>  
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>공지사항</title>
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

<!-- Vendor CSS Files -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/news_style.css">
<!-- 순서가 중요함(맨위에 있으면 스타일 달라짐) -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/dce0734714.js" crossorigin="anonymous"></script>
 <script type="text/javascript">
  
  <!--dropdwon-->
  	$(function(){
  		$('#list_sort').change(function(){
  			location.href='list.do?course_cate=${param.course_cate}&sort='+$(this).val();
  		});
  	});
  </script>    
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>공지사항 목록</h2>
		<!-- 검색폼 시작 -->
				<div class="section"> 
					<div class="search-bar">
						<form class="search-form d-flex align-items-center" action="list.do" method="get"><!-- 자동 인코딩 처리를 위해 -->
							<select class="form-select" id="form-select1" name="keyfield" aria-label="form-select">
								<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>제목</option>
								<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>내용</option>
							</select> 
							<!--                            //검색했을 때 값이 남아있게 하기 위해서 -->
							<!--                            //자기자신에게 값을 보내기 때문에 param 이용 -->
							<input type="text" name="keyword" value="${param.keyword}" placeholder="검색어를 입력하세요">
							<button type="submit" title="Search"><i class="bi bi-search"></i></button>
						</form>
					</div>
				</div>
				<!-- 검색폼 끝 -->
				<!-- dropdown -->
				<div id="sub-select" class="content1">
					<span id="list-num">전체 ${count}개</span>
					<select class="form-select" name="sort" id="list_sort" aria-label="form-select" style="width:120px; margin-left: auto;">
						<option value="1" <c:if test="${param.sort == 1}">selected</c:if>>최신순</option>
						<option value="2" <c:if test="${param.sort == 2}">selected</c:if>>조회순</option>
					</select>
				</div>
				<!--dropdown끝-->
				<!-- 목록 -->
				<div class="content-margin">
				<c:if test="${count == 0}">
				<table class="table table-group-divider align-center">
					<tr>
						<td>표시할 게시물이 없습니다</td>
					</tr>
				</table>
				</c:if>
				<c:if test="${count>0}">
					<table class="table table-hover align-center" id="t1">
						<thead class="table-head">
							<tr>
								<th scope="col" width="10%">글번호</th>
								<th scope="col" width="10%">분류</th>
								<th scope="col" width="40%">제목</th>
								<th scope="col" width="25%">작성일</th>
								<th scope="col" width="10%">조회수</th>
							</tr>
						</thead>
						<tbody class="table-body table-group-divider">
							<c:forEach var="news" items="${list}"><!-- var는 자바빈(VO) -->
							<tr>
								<th scope="row">${news.news_id}</th>
								<th>
									<c:if test="${news.news_attr == 0}">필독</c:if>
								</th>
								<td><a class="title-link" href="detail.do?news_id=${news.news_id}">${news.news_title}</a></td>
								<td>${news.news_reg_date}</td>
								<td>${news.news_hit}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<table class="table table-hover table-group-divider" id="t2">
						<c:forEach var="news" items="${list}">
						<tr>
							<td>
								<div><a class="title-link" href="detail.do?rev_id=${news.news_id}">${news.news_title}</a></div>
								<span class="t-sub-info">${news.news_reg_date}</span> &nbsp;
								<span class="t-sub-info"><i class="fa-solid fa-eye"></i> ${news.news_hit}</span>
							</td>
						</tr>
						</c:forEach>
					</table>
				</c:if>
				</div>
        
				<!-- 목록끝 -->
				<c:if test="${mem_auth==9}"><!-- 관리자만 버튼 활성화 -->
				<div class="align-right">
					<input class="btn btn-primary" type="button" value="글쓰기" id="write_btn"
								onclick="location.href='admin_writeForm.do'">
				</div>
				</c:if>
			
				<!-- 페이지 번호 시작 -->
				<c:if test="${count>0}">
				<div class="align-center">${page}</div>
				</c:if>
	
		</div><!-- 컨텐트 메인 끝 -->
	</div><!-- 페이지 메인 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>