<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%-- 로그인 된 경우에만 글쓰기버튼 활성화 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>취업센터</title>
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

<!-- Vendor CSS Files -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/job_style.css">
<!-- 순서가 중요함(맨위에 있으면 스타일 달라짐) -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/dce0734714.js" crossorigin="anonymous"></script>
</head> 
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="page-main">
		<!-- 사이드바 시작-->
		<div id="sidebar" class="sidebar">
	
			<ul class="sidebar-nav" id="sidebar-nav">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/jobEmploy/employList.do"> <span>취업현황</span>
				</a></li>
	
				<li class="nav-item"><a class="nav-link collapsed"
					href="${pageContext.request.contextPath}/jobReview/reviewList.do"> <span>취업후기</span>
				</a></li>
			</ul>
		</div>
		<!-- 사이드바 끝-->
		
		<div class="content-main"><!-- 전체 화면의 87% -->
			<!-- 사이드바 오른쪽 화면 시작 -->
			<div class="content-right">
				<!-- 검색폼 시작 -->
				<div class="section"> 
					<div class="search-bar">
						<form class="search-form d-flex align-items-center" action="employList.do" method="get"><!-- 자동 인코딩 처리를 위해 -->
							<select class="form-select" id="form-select1" name="keyfield" aria-label="form-select">
								<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>제목</option>
								<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>내용</option>
								<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>작성자</option>
							</select> 
							<!--                            //검색했을 때 값이 남아있게 하기 위해서 -->
							<!--                            //자기자신에게 값을 보내기 때문에 param 이용 -->
							<input type="text" name="keyword" value="${param.keyword}" placeholder="검색어를 입력하세요">
							<button type="submit" title="Search"><i class="bi bi-search"></i></button>
						</form>
					</div>
				</div>
				<!-- 검색폼 끝 -->
		
				<!-- dropdwon 시작 -->
				<div id="sub-select" class="content1">
					<span id="list-num">전체 ${count}개</span>
					<select class="form-select" id="list_sort" name="sort" aria-label="form-select">
						<option value="1" <c:if test="${param.sort==1}">selected</c:if>>최신순</option>
						<option value="2" <c:if test="${param.sort==2}">selected</c:if>>조회순</option>
					</select>
				</div>
				
				<script>
				$(function(){
			  		$('#list_sort').change(function(){
			  			location.href='employList.do?course_cate=${param.course_cate}&sort='+$(this).val();
			  		});
			  	});
				</script>
			    <!-- dropdwon 끝 -->
		
				<!-- 카드 시작 -->
				<div class="content-margin">
				<c:if test="${count == 0}">
				<table class="table table-group-divider align-center">
					<tr>
						<td>표시할 게시물이 없습니다</td>
					</tr>
				</table>
				</c:if>
				<c:if test="${count>0}">
				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4" id="job_cards">
					<c:forEach var="employ" items="${list}">
					  <div class="col job-card">
					    <div class="card h-100">
					      <a href="employDetail.do?emp_id=${employ.emp_id}">
					      <img src="${pageContext.request.contextPath}/upload/${employ.emp_photo}" class="card-img-top" width="100%" height="100%">
					      </a>
					      <div class="card-body">
					        <h6 class="card-title">	<a href="employDetail.do?emp_id=${employ.emp_id}">${employ.emp_title}</a></h6>
					        <p class="card-text">
					        	${employ.emp_reg_date} &nbsp;${employ.emp_hit}
					        </p>
					      </div>
				    	</div>
				  	  </div>
					 </c:forEach>
				</div>
				</c:if>
				</div>
				<!-- 카드 끝 -->
				
				<c:if test="${mem_auth==9}"><!-- 관리자만 버튼 활성화 -->
				<div class="align-right">
					<input class="btn btn-primary" type="button" value="글쓰기" id="write_btn"
								onclick="location.href='employWriteForm.do'">
				</div>
				</c:if>
			
				<!-- 페이지 번호 시작 -->
				<c:if test="${count>0}">
				<div class="align-center">${page}</div>
				</c:if>
			</div><!-- 사이드바 오른쪽 화면 끝 -->	
		</div><!-- 컨텐트 메인 끝 -->
	</div><!-- 페이지 메인 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer1.jsp" />
</body>
</html>