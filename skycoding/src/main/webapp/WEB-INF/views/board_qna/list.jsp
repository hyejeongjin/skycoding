<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>게시판 목록</h2>
		
		<%--검색폼 시작 나중에 추가--%>
		<%--검색폼 끝 --%>
		
		<%--잠깐 주석처리
		<c:if test="${count == 0}">
		<div class="result-display">
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		--%>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회</th>
			</tr>
		</table>
		
		<div class="list-space align-right">
			<!-- 로그인 안 되어 있으면 태그가 보여지긴 하는데 비활성화 -->
			<input type="button" value="글쓰기" onclick="location.href='writeForm.do'">
				<%--<c:if test="${empty user_num}">disabled="disabled"</c:if>/> --%>
		</div>
	</div>
</div>
</body>
</html>