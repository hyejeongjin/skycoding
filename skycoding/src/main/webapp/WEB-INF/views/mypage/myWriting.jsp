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
  <link href="${pageContext.request.contextPath}/assets/css/mypage-style.css" rel="stylesheet">
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
  <!-- 사이드바 -->
  <jsp:include page="/WEB-INF/views/common/mywritingmain.jsp"/>

	<main id="main" class="main">
		<!-- 전체선택 및 삭제 -->
		<section class="section">
		    <span class="likecourse-button">
	          <button type="button" class="btn btn-secondary" style="margin-right:0.5em;"><i class="bi bi-check-lg"></i>전체선택</button>
	          <button type="button" class="btn btn-danger"><i class="bi bi-x-lg"></i>삭제</button>
	        </span>	
		</section>
		
		<!-- 검색폼 시작 -->
				<div class="section"> 
					<div class="search-bar">
						<form class="search-form d-flex align-items-center" action="list.do" method="get"><!-- 자동 인코딩 처리를 위해 -->
							<select class="form-select" id="form-select1" name="keyfield" aria-label="form-select">
								<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>제목</option>
								<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>내용</option>
							</select> 
							<%--제목을 선택한 게 남아있게 하기 위해 value, c:if 작성. value가 keyfield에 전송된다--%>
							<%--제목,작성자,내용이 keyfield, 검색창이 keyword.  자기자신에게 값을 보내기 때문에 param 이용 --%>
							<input type="text" name="keyword" value="${param.keyword}" placeholder="검색어를 입력하세요">
							<button type="submit" title="Search"><i class="bi bi-search"></i></button>
						</form>
					</div>
				</div>
				<!-- 검색폼 끝 -->
		
		<%-----------------------------------------신나리가 쓴 부분 -----------------------------%>
		<%-- qna table 시작 --%>
				<div class="table-content">
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
								<th scope="col" width="40%">제목</th>
								<th scope="col" width="20%">작성일</th>
								<th scope="col" width="20%">수정일</th>
								<th scope="col" width="10%">조회수</th>
							</tr>
						</thead>
						<tbody class="table-body table-group-divider">
							<c:forEach var="myWrite" items="${list}">
							<tr>
								<td>
								<c:if test="${myWrite.qna_id>0}">${myWrite.qna_id}</c:if>
								<c:if test="${myWrite.free_id>0}">${myWrite.free_id}</c:if>
								<c:if test="${myWrite.rev_id>0}">${myWrite.rev_id}</c:if>
								</td>
								<td><a class="title-link" href="
								<c:if test="${myWrite.qna_id>0}">
								${pageContext.request.contextPath}/board_qna/detail.do?qna_id=${myWrite.qna_id}
								</c:if>
								<c:if test="${myWrite.free_id>0}">
								${pageContext.request.contextPath}/board_free/detail.do?free_id=${myWrite.free_id}
								</c:if>
								<c:if test="${myWrite.rev_id>0}">
								${pageContext.request.contextPath}/jobReview/reviewDetail.do?rev_id=${myWrite.rev_id}
								</c:if>
								">${myWrite.title}</a></td>
								<td>${myWrite.reg_date}</td>
								<td>${myWrite.modify_date}</td>
								<td>${myWrite.hit}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<%-- 작은 화면용 테이블 --%>
					<table class="table table-hover table-group-divider" id="t2">
						<c:forEach var="myWrite" items="${list}">
						<tr>
							<td>
								<div><a class="title-link" href="detail.do?qna_id=${myWrite.id}">${myWrite.title}</a></div>
								<span class="t-sub-info">${myWrite.reg_date}</span> &nbsp; 
								<span class="t-sub-info">${myWrite.modify_date}</span> &nbsp;
								<span class="t-sub-info"><i class="fa-solid fa-eye"></i> ${myWrite.hit}</span>
							</td>
						</c:forEach>
					</table>
				</c:if>
			</div><%--qna table 끝 --%>
			<%-- qna 페이지 번호 시작 --%>
				<c:if test="${count>0}">
				<div class="align-center">${page}</div>
				</c:if>
			<%-- qna 페이지 번호 끝 --%>	
			

<%-----------------------------------------신나리가 쓴 부분 끝-----------------------------%>

	</main>
	<!-- End #main -->
	<jsp:include page="/WEB-INF/views/common/footer1.jsp" />
</body>

</html>