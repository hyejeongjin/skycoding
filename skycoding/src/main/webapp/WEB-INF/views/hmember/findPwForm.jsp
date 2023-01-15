<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/style.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		let idChecked = 0; //아이디 없을 시
		let questChecked = 0;
		$('#id_check_pwa').click(function(){
			if ($('#id').val().trim() == '') {
				alert('아이디를 입력하세요.');
				$('#id').val('').focus();
				return;
			}
			
			$.ajax({
				url:'checkPwAction.do',
				type:'post',
				data: {mem_id:$('#id').val()},
				dataType:'json',
				success: function(param){
					if(param.result == 'failure'){
						idChecked = 0;
						$('#message_id').text('일치하는 아이디가 없습니다.');
						$('#qna_content').text('');
					}else if(param.result == 'success'){
						idChecked = 1;
						$('#qna_content').text(param.qna_detail);
					}else{
						alert('비밀번호 찾기 오류');
					}
				},
				error:function(){
					idChecked = 0;
					alert('네트워크 오류 발생');
				}
			});		
		});
		
		$('#check').click(function(){
			if($('#mem_pwa').val().trim()==''){
				alert('비밀번호 답을 입력하세요!');
				$('#mem_pwa').val('').focus();
				return false;
			}
			
			$.ajax({
				url:'changeNewPwAction.do',
				type:'post',
				data: {mem_id:$('#id').val(),mem_pwa:$('#mem_pwa').val()},
				dataType:'json',
				success: function(param){
					if(param.result == 'failure'){
						alert('답변이 불일치합니다.');
						questChecked = 0;
					}else if(param.result == 'success'){
						$('#changePw').show();
						questChecked = 1;
					}else{
						questChecked = 0;
						alert('비밀번호 찾기 오류');
					}
				},
				error:function(){
					idChecked = 0;
					alert('네트워크 오류 발생');
				}
			});
		});
		
	
		$('#find_pw_form').submit(function(){
			if (idChecked == 0 || questChecked == 0) {//아이디 확인을 누르지 않았을 경우를 위한 체크
				alert('아이디 인증 필수');
				return false;
			}
			if($('#mem_id').val().trim()==''){
				alert('아이디를 입력하세요');
				$('#mem_id').val('').focus();
				return false;
				}
			if($('#mem_pwa').val().trim()==''){
					alert('비밀번호 답을 입력하세요');
					$('#mem_pwa').val('').focus();
					return false;
			}
		});//end of submit
		
		$('#id').keydown(function() {
			//아이디 적는 창의 내용을 지울 시 메시지 초기화
			$('#message_id').text('');
			$('#qna_content').text('');
			idChecked = 0;
		});//end of keydown
		
		$('#check').keydown(function() {
			questChecked = 0;
		});
	});
</script>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="container row" style="float: none; margin-top: 100px; margin-bottom:150px;">
			<div class="col-md-5" style="float: none; margin: auto;">
				<h4>비밀번호 찾기</h4>
				<form id="find_pw_form" action="changePw.do" method="post">
					<div class="row mb-3">
						<label for="id">아이디</label>
						<div class="input-group">
							<input type="text" class="form-control" name="mem_id" id="id">
							<span class="input-group-btn">
								<button class="btn btn-primary" type="button" id="id_check_pwa">아이디
									확인</button> <span id="message_id"></span>
							</span>
						</div>
					</div>
					<div class="row mb-3">
						<label for="pwa">비밀번호 질문</label>
						<div class="col-md-15">
							<span id="qna_content"></span>
						</div>
					</div>
					<div class="row mb-3">
						<label for="pwa">비밀번호 답변</label>
						<div class="col-md-15">
							<input type="text" class="form-control" id="mem_pwa"
								name="mem_pwa">
						</div>
					</div>
					<button type="button" class="btn btn-primary" id="check" style="margin-bottom:10px;">답변 인증</button>
					<!-- 비밀번호 변경 -->
					<div style="display:none;" id="changePw">
						<div class="row mb-3">
							<label for="new_pw">새 비밀번호</label>
							<div class="col-md-15">
								<input type="password" class="form-control" id="new_pw" name="new_pw">
							</div>
						</div>
						<div class="row mb-3">
							<label for="new_pw_c">새 비밀번호 확인</label>
							<div class="col-md-15">
								<input type="password" class="form-control" id="new_pw_c" name="new_pw_c">
							</div>
						</div>
						<button type="submit" class="btn btn-primary">비밀번호 변경하기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

