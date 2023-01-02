<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header 시작 -->
<div id="main_logo">
	<h1 class="align-center"><a href="${pageContext.request.contextPath}/main/main.do">회원제 게시판</a></h1>
</div>
<div id="main_nav">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/board/list.do">게시판</a>
		</li>
		<c:if test="${!empty user_num && user_auth == 2}"> <%-- !(비어있거나 null이면) --%>
		<li><a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a></li>
		</c:if>
		
		<c:if test="${!empty user_num && !empty user_photo}"> <%-- 로그인했고 프사를 업로드했으면 --%>
		<li class="menu-profile"><img src="${pageContext.request.contextPath}/upload/${user_photo}" 
								width="25" height="25" class="my-photo"></li>
		</c:if>
		<c:if test="${!empty user_num && empty user_photo}"> <%-- 로그인했는데 프사가 없으면 --%>
		<li class="menu-profile"><img src="${pageContext.request.contextPath}/images/face.png" 
								width="25" height="25" class="my-photo"></li>
		</c:if>
		
		<c:if test="${!empty user_num}"> <%-- 로그인이 된 경우 --%>
		<li class="menu-logout">
			[<span>${user_id}</span>]
			<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
		</li>
		</c:if>
		<c:if test="${empty user_num}"> <%-- 로그인이 안 된 경우 --%>
		<li><a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a></li>
		<li><a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a></li>
		</c:if>
	</ul>
</div>
<!-- header 끝 -->

