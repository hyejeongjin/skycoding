<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- 로그인 된 경우에만 글쓰기버튼 활성화 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세글</title>
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
</head>
<body>  
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
	   <div>
	   <div class="content-right">
	   	<div>
		 <div class="detail-top">
		<h2>${news.news_title}</h2>
		<!-- 관리자만 수정, 삭제 가능 -->
					<c:if test="${mem_auth==9}">
					<div class="top-button" align="right" >
						<input type="button" value="수정" class="btn btn-primary btn-sm" 
								onclick="location.href='updateForm.do?news_id=${news.news_id}'">
						<input type="button" value="삭제" class="btn btn-primary btn-sm" id="delete-btn">
						<script type="text/javascript">
							$(function(){
								$('#delete-btn').click(function(){
									let choice = confirm('정말 삭제하시겠습니까?');
									if(choice){
										location.replace('delete.do?news_id=${news.news_id}');
									}
								});
							});
						</script>
					</div>
					</c:if>					
				</div>
		<hr class="hr-style" size="1" width="100%">		
		<ul class="detail-info1" >
			
			<li>운영자&nbsp;&nbsp;
			<c:if test="${!empty news.news_modify_date}">
			수정일 ${news.news_modify_date}&nbsp;&nbsp;
					</c:if>
			작성일 ${news.news_reg_date}</li>
					<li><i class="fa-solid fa-eye"></i> ${news.news_hit}</li>
		
		  </ul>
		</div>
		<hr class="hr-style" size="1" width="100%">
		<c:if test="${!empty news.news_photo}">
		
			<img src="${pageContext.request.contextPath}/upload/${news.news_photo}" class="detail-img">
		</c:if>
		<div class="detail-content">
			${news.news_content}
		</div>
		  
	<hr class="hr-style" size="1" width="100%">
			<div class="bottom-btn">
				<div>${page}</div>
				<input class="btn btn-primary list-btn" type="button" value="목록" onclick="location.href='list.do'">
	</div>
</div>
</div>
</div>
</div>
	<jsp:include page="/WEB-INF/views/common/footer1.jsp" />
</body>
</html>
