<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>질문글</h2>
		<%--검색 폼 시작 --%>
		<form id="search_form" action="list.do" method="get">
			<ul class="search">
				<li>
					<select name="keyfield">
						<%--제목을 선택한 게 남아있게 하기 위해 value, c:if 작성. value가 keyfield에 전송된다--%>
						<%--제목,작성자,내용이 keyfield, 검색창이 keyword --%>
						<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>제목</option> 
						<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>작성자</option>
						<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>내용</option>
					</select>
				</li>
				<li>
					<input type="search" size="15" name="keyword" id="keyword" value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색">
				</li>
			</ul>
		</form>
		<%--검색 폼 끝 --%>
		<%--회원 완료 될 때까지 주석처리 
		
		<div class="list-space align-right">
			<!-- 로그인 안 되어 있으면 태그가 보여지긴 하는데 비활성화 -->
			<input type="button" value="글쓰기" onclick="location.href='writeForm.do'"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>/>
			<input type="button" value="목록" onclick="location.href='list.do'">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
		
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
			<input type="button" value="글쓰기" onclick="location.href='writeForm.do'">
				
				<%--회원 완료 될 때까지 주석처리 
				<c:if test="${empty user_num}">disabled="disabled"</c:if>/>
				 --%>
		</div>
	</div>
</div>
</body>
</html>