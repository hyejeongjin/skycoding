<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문글 글쓰기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#write_form').submit(function(){
			if($('#qna_title').val().trim()==''){
				alert('제목을 입력하세요!');
				$('#qna_title').val('').focus();
				return false;
			}
			if($('#qna_content').val().trim()==''){
				alert('내용을 입력하세요!');
				$('#qna_content').val('').focus();
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
		<h2>게시판 글쓰기</h2>
		<form id="write_form" action="write.do" method="post" enctype="multipart/form-data">
			<ul>
			
				<%--제목--%>
				<li>
					<label for="qna_title"></label>
					<input type="text" name="qna_title" id="qna_title" maxlength="50" placeholder="제목을 입력해주세요">
				</li>
			
				<%--사진파일--%>
				<li>
					<label for="qna_photo"></label>
					<input type="file" name="qna_photo" id="qna_photo" accept="image/gif,image/png,image/jpeg">
				</li>
				
				<%--내용--%>
				<li>
					<label for="content"></label>
					<textarea rows="5" cols="30" name="qna_content" id="qna_content" placeholder="내용을 입력해주세요"></textarea>
				</li>
				
			</ul>
			<div class="align-center">
				<input type="button" value="취소" onclick="location.href='list.do'">
				<input type="submit" value="등록">
			</div>
		</form>
	</div>
</div>
</html>