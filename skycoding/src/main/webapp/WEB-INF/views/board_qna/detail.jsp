<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문글상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.fav.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.reply.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>${qnaBoard.qna_title}</h2>
		<ul class="detail-info">
			<li> <%--프사 있는 경우 --%>
				<c:if test="${!empty qnaBoard.photo}">
				<img src="${pageContext.request.contextPath}/upload/${qnaBoard.photo}" 
							width="40" height="40" class="my-photo">
				</c:if>
				<%--프사 없는 경우 --%>
				<c:if test="${empty qnaBoard.photo}">
				<img src="${pageContext.request.contextPath}/images/face.png" 
							width="40" height="40" class="my-photo">
				</c:if>
			</li>
			<li>
				${qnaBoard.mem_id}<br>
				조회 : ${qnaBoard.qna_hit} ${qnaBoard.qna_photo}
			</li>
		</ul>
		<hr size="1" noshade="noshade" width="100%">
		<%--이미지가 있을 때만 보여지게 --%>
		<c:if test="${!empty qnaBoard.qna_photo}">
		<div class="align-center">
			<img src="${pageContext.request.contextPath}/upload/${qnaBoard.qna_photo}" class="detail-img"> 
		</div>
		</c:if>
		<p>
			${qnaBoard.qna_content}
		</p>
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-sub">
			<li>
				<c:if test="${!empty qnaBoard.qna_modify_date}">
					최근 수정일 : ${qnaBoard.qna_modify_date}
				</c:if>
					작성일 : ${qnaBoard.qna_reg_date}
					<%--로그인한 회원번호와 작성자 회원번호가 일치해야 수정,삭제 가능 --%>
					<c:if test="${user_num == qnaBoard.mem_num}">
					<input type="button" value="수정"
					onclick="location.href='updateForm.do?qna_id=${qnaBoard.qna_id}'">
					<input type="button" value="삭제" id="delete_btn">
					<script type="text/javascript">
						let delete_btn = document.getElementById('delete_btn');
						//이벤트 연결
						delete_btn.onclick=function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								location.replace('delete.do?qna_id=${qnaBoard.qna_id}');
							}
						};
					</script>
					</c:if> 
			</li>
		</ul>
		<%--댓글 미완성이라 주석처리
		<!-- 댓글 시작 -->
		<div id="reply_div">
			<span class="re-title">댓글 달기</span>
			<form id="re_form"> <!-- submit하면 ajax 통신하도록 만듦. board.reply.js에서 댓글 등록 부분 참고 -->
				<input type="hidden" name="board_num" value="${board.board_num}" id="board_num">
				<textarea rows="3" cols="50" name="re_content" id="re_content" class="rep-content"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>
				><c:if test="${empty user_num}">로그인 해야 작성할 수 있습니다.</c:if></textarea>
				<c:if test="${!empty user_num}">
				<div id="re_first">
					<span class="letter-count">300/300</span>
				</div>
				<div id="re_second" class="align-right">
					<input type="submit" value="전송">
				</div>
				</c:if>
			</form>
		</div>
		<!-- 댓글 목록 출력 시작 -->
		<div id="output"></div>
		<div class="paging-button" style="display:none;">
			<input type="button" value="다음글 보기">
		</div>
		<div id="loading" style="display:none;">
			<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
		</div>
		<!-- 댓글 목록 출력 끝 -->
		<!-- 댓글 끝 -->
		 --%>
	</div>
</div>
</body>
</html>