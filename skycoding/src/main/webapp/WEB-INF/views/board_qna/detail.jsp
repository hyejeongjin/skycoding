<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>질문글상세</title>
<!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/qnaBoard.reply.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">
    <ul class="sidebar-nav" id="sidebar-nav">
      <li class="nav-item">
        <a class="nav-link collapsed" href="list.do">
          <i class="bi bi-grid"></i>
          <span>질문글</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link collapsed" href="#">
          <span>자유글</span>
        </a>
      </li>
    </ul>
  </aside>
<!-- End Sidebar-->

<!-- Start #main -->
<main id="main" class="main">
	<%--로그인한 회원번호와 작성자 회원번호가 일치해야 수정,삭제 가능 --%>
	<%--관리자는 본인 글 아니어도 삭제 가능 --%>
	<div style="float:right;">
	<c:if test="${user_num == qnaBoard.mem_num}">
	<button type="button" class="btn btn-primary" onclick="location.href='updateForm.do?qna_id=${qnaBoard.qna_id}'"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>>수정</button>
	</c:if>
	<c:if test="${user_num == qnaBoard.mem_num || user_auth == 9}">
	
	<button type="button" class="btn btn-primary" id="delete_btn"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>>삭제</button>
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick = function() {
				let choice = confirm('삭제하시겠습니까?');
				if (choice) {
					location.replace('delete.do?qna_id=${qnaBoard.qna_id}');
				}
			}
		</script>
	</c:if>
	</div>
		<h2>${qnaBoard.qna_title}</h2>
		<hr size="1" noshade="noshade" width="100%">
			<ul class="detail-info">
				<li class="inline"><%--프사 있는 경우 --%> 
					<c:if test="${!empty qnaBoard.mem_photo}">
						<img src="${pageContext.request.contextPath}/upload/${qnaBoard.mem_photo}"
							width="40" height="40" class="my-photo">
					</c:if>
					<%--프사 없는 경우 --%>
					<c:if test="${empty qnaBoard.mem_photo}">
						<img src="${pageContext.request.contextPath}/images/face.png"
							width="40" height="40" class="my-photo">
					</c:if>
				</li>
				<li class="inline" style="font-size:15pt;">${qnaBoard.mem_id}</li>
				<li>
				작성일  ${qnaBoard.qna_reg_date}&nbsp;
				<c:if test="${!empty qnaBoard.qna_modify_date}">
				| 수정일  ${qnaBoard.qna_modify_date}&nbsp;
				</c:if>
				| <i class="bi bi-eye-fill"></i>&nbsp;&nbsp;${qnaBoard.qna_hit}
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
		<!-- 댓글 목록 출력 시작 -->
		<div id="output"></div>
		<div class="paging-button" style="display:none;">
			<input type="button" value="다음글 보기">
		</div>
		<div id="loading" style="display:none;">
			<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
		</div>
		<!-- 댓글 목록 출력 끝 -->
		
		<!-- 댓글 시작 -->
		<div id="reply_div">
			<span class="re-title" style="font-size:15pt">댓글</span>&nbsp;
			<span id="re_first">
					<span class="letter-count">50/50</span>
			</span>
			<form id="re_form"> <!-- submit하면 ajax 통신하도록 만듦. qnaBoard.reply.js에서 댓글 등록 부분 참고 -->
				<input type="hidden" name="qna_id" value="${qnaBoard.qna_id}" id="qna_id">
				<div class="inner-text">
				<textarea class="form-control rep-content inner-text" name="qnaComm_content" id="qnaComm_content"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>
				><c:if test="${empty user_num}">로그인 해야 작성할 수 있습니다.</c:if></textarea>
				<button class="btn btn-outline-primary m-1 inner-submit" id="re_second" type="submit">등록</button></div>
				<c:if test="${!empty user_num}">
				
				<!--
				<button class="btn btn-outline-primary m-1" id="re_second" type="submit">등록</button>
				
				<div id="re_second" class="align-right">
					<input type="submit" value="등록">
				</div>
				-->
				</c:if>
			</form>
		</div>
		<!-- 댓글 끝 -->
		<button type="button" class="btn btn-primary" style="float:right;" onclick="location.href='list.do'"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>>목록</button>
</main>
</body>
</html>