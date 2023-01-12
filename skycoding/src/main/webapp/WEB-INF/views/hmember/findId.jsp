<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${!empty my_id}">
	<script>
		alert('당신의 아이디는 ${my_id} 입니다.');
		location.href='loginForm.do';
	</script>
</c:if>
<c:if test="${empty my_id}">
	<script>
		alert('회원정보에 일치하는 아이디가 없습니다!');
		history.go(-1);
	</script>
</c:if>