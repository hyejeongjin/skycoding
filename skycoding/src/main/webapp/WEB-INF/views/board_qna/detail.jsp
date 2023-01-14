<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>질문게시판 상세</title>
<!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/qna-style.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/qnaBoard.reply.js"></script>
<script src="https://kit.fontawesome.com/dce0734714.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<!-- 사이드바 시작 -->
		<aside id="sidebar" class="sidebar">
		   <ul class="sidebar-nav" id="sidebar-nav">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/board_qna/list.do"> <span>질문게시판</span>
				</a></li>
	
				<li class="nav-item"><a class="nav-link collapsed" 
					href="${pageContext.request.contextPath}/board_free/list.do"> <span>자유게시판</span> 
				</a></li>
			</ul>
		 </aside>
		<!-- 사이드바 끝 -->

		<div class="content-main"><!-- 전체 화면의 80% -->
			<!-- 사이드바 오른쪽 화면 시작 -->
			<div class="content-right">
				<div>
					<div class="detail-top">
					<h2>${qnaBoard.qna_title}</h2>
					<%--로그인한 회원번호와 작성자 회원번호가 일치해야 수정,삭제 가능 --%>
					<div class="top-button">
					<c:if test="${mem_num == qnaBoard.mem_num}">
						<input type="button" value="수정" class="btn btn-primary btn-sm" 
								onclick="location.href='updateForm.do?qna_id=${qnaBoard.qna_id}'">
					</c:if>
					<%--관리자는 본인 글 아니어도 삭제 가능 --%>
					<c:if test="${mem_num == qnaBoard.mem_num || mem_auth == 9}">
					<input type="button" value="삭제" class="btn btn-primary btn-sm" id="delete-btn">
					<script type="text/javascript">
						$(function(){
							$('#delete-btn').click(function(){
								let choice = confirm('삭제하시겠습니까?');
								if(choice){
									location.replace('delete.do?qna_id=${qnaBoard.qna_id}');
								}
							});
						});
					</script>
					</c:if>
					</div>
					</div>
					<hr class="hr-style" size="1" width="100%">
					<ul class="detail-info1">
						<li class="inline">
							<%--프사 있는 경우 --%>
							<c:if test="${!empty qnaBoard.mem_photo}">
								<img src="${pageContext.request.contextPath}/upload/${qnaBoard.mem_photo}" width="40" height="40" class="my-photo">
							</c:if> <%--프사 없는 경우 --%> <c:if test="${empty qnaBoard.mem_photo}">
								<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
							</c:if>
						</li>
						<li>
							<c:if test="${qnaBoard.qna_status == 0}">${qnaBoard.mem_id}</c:if>
							<c:if test="${qnaBoard.qna_status == 1}">익명</c:if>
						</li>
					</ul>
					<ul class="detail-info2">
						<li>작성일 ${qnaBoard.qna_reg_date}
						<c:if test="${!empty qnaBoard.qna_modify_date}">
						<li>수정일  ${qnaBoard.qna_modify_date}
						</c:if>
						<li><i class="fa-solid fa-eye"></i> ${qnaBoard.qna_hit}</li>
					</ul>
					</div>
					<hr class="hr-style" size="1" width="100%">
					<%--이미지가 있을 때만 보여지게 --%>
					<c:if test="${!empty qnaBoard.qna_photo}">
						<img src="${pageContext.request.contextPath}/upload/${qnaBoard.qna_photo}" class="detail-img">
					</c:if>	
					<div class="detail-content">
						${qnaBoard.qna_content}
					</div>
					<hr class="hr-style" size="1" width="100%">
					
					<!-- 댓글 목록 출력 시작 -->
					<div id="output"></div>
					<div class="paging-button" style="display: none;">
						<input class="btn btn-outline-secondary" type="button" value="더보기">
					</div>
					<div id="loading" style="display: none;">
						<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
					</div>
					<!-- 댓글 목록 출력 끝 -->

					<!-- 댓글 시작 -->
					<div id="reply_div">
						<span class="re-title" style="font-size: 15pt">댓글</span>&nbsp; <span
							id="re_first"> <span class="letter-count">50/50</span>
						</span>
						<form id="re_form">
							<!-- submit하면 ajax 통신하도록 만듦. qnaBoard.reply.js에서 댓글 등록 부분 참고 -->
							<input type="hidden" name="qna_id" value="${qnaBoard.qna_id}"
								id="qna_id">
							<div class="inner-text">
								<textarea class="form-control rep-content inner-text"
									name="qnaComm_content" id="qnaComm_content"
									<c:if test="${empty mem_num}">disabled="disabled"</c:if>><c:if test="${empty mem_num}">로그인 해야 작성할 수 있습니다.</c:if></textarea>
									<c:if test="${!empty mem_num}">
									<button type="submit" class="btn btn-outline-primary" id="inner-submit"><i class="fa-solid fa-paper-plane fa-2x"></i></button>
									</c:if>
							</div>
						</form>
						
						
					</div>
					<!-- 댓글 끝 -->
					<div class="bottom-btn row">
					<div>${page}</div>
					
					<div class="col-sm-12 col-md-6">
						<ul class="bottom-prevNext">
							<li>
  								<c:if test="${pnBoard.next!=0}">
  								다음글 | <a href="detail.do?qna_id=${pnBoard.next}">${pnBoard.next_title}</a>
  								</c:if>
  								<li>
								<c:if test="${pnBoard.prev!=0}">
   								이전글 | <a href="detail.do?qna_id=${pnBoard.prev}">${pnBoard.prev_title}</a>
   								</c:if></li>
							</li>
						</ul>
					</div>
					<div class="col-sm-12 col-md-6" align="right">
						<input class="btn btn-primary list-btn my-3" type="button" value="목록" onclick="location.href='list.do'">
					</div>
					</div>
					
			</div><!-- 사이드바 오른쪽 화면 끝 -->	
	</div><!-- 컨텐트 메인 끝 -->
</div><!-- 페이지 메인 -->
</body>
</html>