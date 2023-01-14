<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- 로그인 된 경우에만 글쓰기버튼 활성화 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>취업후기 상세글</title>
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

<!-- Vendor CSS Files -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/job_style.css">
<!-- 순서가 중요함(맨위에 있으면 스타일 달라짐) -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jobReview.comment.js"></script>
<script src="https://kit.fontawesome.com/dce0734714.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 사이드바 시작-->
	<aside id="sidebar" class="sidebar">

		<ul class="sidebar-nav" id="sidebar-nav">
			<li class="nav-item"><a class="nav-link collapsed"
				href="${pageContext.request.contextPath}/jobEmploy/employList.do"> <span>취업현황</span>
			</a></li>

			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/jobReview/reviewList.do"> <span>취업후기</span>
			</a></li>
		</ul>
	</aside>
	<!-- 사이드바 끝-->
		
	<div class="content-main"><!-- 전체 화면의 80% -->
		<!-- 사이드바 오른쪽 화면 시작 -->
		<div class="content-right">
			<div>
				<div class="detail-top">
					<h2>${review.rev_title}</h2>
					<!-- 로그인한 회원번호와 작성자 회원번호가 같아야만 수정, 삭제 가능 -->
					<c:if test="${mem_num==review.mem_num}">
					<div class="top-button">
						<input type="button" value="수정" class="btn btn-primary btn-sm" 
								onclick="location.href='reviewUpdateForm.do?rev_id=${review.rev_id}'">
						<input type="button" value="삭제" class="btn btn-primary btn-sm" class="delete-btn">
						<script type="text/javascript">
							$(function(){
								$('#delete_btn').click(function(){
									let choice = confirm('정말 삭제하시겠습니까?');
									if(choice){
										location.replace('reviewDelete.do?rev_id=${review.rev_id}');
									}
								});
							});
						</script>
					</div>
					</c:if>					
				</div>
				<hr class="hr-style" size="1" width="100%">
				<ul class="detail-info1">
					<li>
						<c:if test="${!empty review.mem_photo}">
						<img src="${pageContext.request.contextPath}/upload/${review.mem_photo}" width="40px" height="40px" class="my-photo">
						</c:if>
						<c:if test="${empty review.mem_photo}">
						<img src="${pageContext.request.contextPath}/images/face.png" width="40px" height="40px" class="my-photo">
						</c:if>
					</li>
					<li>${review.mem_id}</li>
				</ul>
				<ul class="detail-info2">
					<li>작성일 ${review.rev_reg_date}</li>
					<c:if test="${!empty review.rev_modify_date}">
					<li>수정일 ${review.rev_modify_date}</li>
					</c:if>
					<li><i class="fa-solid fa-eye"></i> ${review.rev_hit}</li>
				</ul>
			</div>
			<hr class="hr-style" size="1" width="100%">
			<c:if test="${!empty review.rev_photo}">
				<img src="${pageContext.request.contextPath}/upload/${review.rev_photo}" class="detail-img">
			</c:if>
			<div class="detail-content"> 
				${review.rev_content}
			</div>
			<hr class="hr-style" size="1" width="100%">
			
			
			
					<!-- 댓글 목록 출력 시작 -->
					<div id="output"></div>
					<div class="paging-button" style="display: none;">
						<input class="btn btn-outline-secondary" type="button" value="+">
					</div>
					<div id="loading" style="display: none;">
						<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
					</div>
					<!-- 댓글 목록 출력 끝 -->

					<!-- 댓글 시작 -->
					<div id="comment_div">
						<form id="com_form">
							<div id="com_title">
								<span class="com-title" style="font-size: 15pt;">댓글</span>&nbsp; 
								<span class="letter-count" id="com_first">300 / 300</span>
							</div>
							<!-- submit하면 ajax 통신하도록 만듦. qnaBoard.reply.js에서 댓글 등록 부분 참고 -->
							<input type="hidden" name="rev_id" id="rev_id" value="${review.rev_id}">
							<div class="inner-text">
								<textarea class="form-control com-content inner-text"
									name="com_content" id="com_content" placeholder="댓글을 입력하세요"
									<c:if test="${empty mem_num}">disabled="disabled"</c:if>><c:if
										test="${empty mem_num}">로그인 해야 작성할 수 있습니다.</c:if></textarea>
									<c:if test="${!empty mem_num}">
									<button type="submit" class="btn btn-outline-primary" id="inner-submit"><i class="fa-solid fa-paper-plane fa-2x"></i></button>
									</c:if>
							</div>
						</form>
					</div>
					<!-- 댓글 끝 -->
					<div class="bottom-btn">
						<!-- 이전글, 다음글 -->
						<ul class="bottom-prevNext">
   							<li>
  								<c:if test="${pnReview.next!=0}">
  								다음글 | <a href="reviewDetail.do?rev_id=${pnReview.next}">${pnReview.next_title}</a>
  								</c:if>
							</li>
							<li>
								<c:if test="${pnReview.prev!=0}">
   								이전글 | <a href="reviewDetail.do?rev_id=${pnReview.prev}">${pnReview.prev_title}</a>
   								</c:if>
   							</li>
						</ul>
					
						<input class="btn btn-primary list-btn my-3" type="button" value="목록" onclick="location.href='reviewList.do'">
					</div>
					
		</div><!-- 사이드바 오른쪽 화면 끝 -->	
	</div><!-- 컨텐트 메인 끝 -->
</div><!-- 페이지 메인 -->
</body>
</html>