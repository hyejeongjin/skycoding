<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>이벤트 글 수정 폼</title>
<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/course-style.css" >
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$('#update_form').submit(function(){
			if($('input[type=radio]:checked').length < 0){
				alert('이벤트 진행 여부를 지정하세요!');
				return false;
			}
			let deadline = document.getElementById('deadline').value.trim();
			if(deadline == ''){
				alert('이벤트 마감일을 입력하세요!');
				$('#deadline').val('').focus();
				return false;
			}else if(/^0/.test(deadline)){					
				//마감일 정규식 검사
				//0으로 시작하는지 check
				alert('마감일 년도는 0으로 시작할 수 없습니다.');
				$('#deadline').val('').focus();
				return false;
			}else if(!/\d{4}-\d{2}-\d{2}/.test(deadline)){
				//4자리-2자리-2자리인지 check
				alert('마감일을 yyyy-MM-dd 형식으로 입력해주세요');
				return false;
			}
			if($('#content').val().trim() == ''){
				alert('이벤트 요약내용을 입력하세요!');
				$('#content').val('').focus();
				return false;
			}
			if($('#detail_content').val().trim() == ''){
				alert('이벤트 세부내용을 입력하세요!');
				$('#detail_content').val('').focus();
				return false;
			}
		})
	});
	
	//파일 삭제 여부 확인
	$(function(){
		$('#file_del').click(function(){
			let choice = confirm('삭제하시겠습니까?');
			if(choice){
				$.ajax({
					url:'deleteEventFile.do',
					type:'post',
					data:{event_id : ${event.event_id}},
					dataType:'json',
					success:function(param){
						if(param.result == 'success'){
							alert('파일이 삭제되었습니다.');
							$('#file_delete_check').hide();
						}else{
							alert('파일 삭제 오류 발생');
						}
					},
					error:function(){
						alert('네트워크 오류 발생');
					}
				});
			}
		});
	});
</script>
</head>
<body>
<!-- 수정폼 메인 시작 -->
<div class="page-main">
	<div class="content-main">
	<main id="register-main" class="register-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	
  		<div class="pagetitle">
    		<h1>이벤트 수정하기</h1>
  		</div><!-- 타이틀끝 -->
  		
  <section class="section">
    <div class="row">
      <div class="col-lg-12">
        <div class="card-write">
          <div class="card-body">
          <h5 class="card-title"></h5>
          
            <form action="updateEvent.do" method="post" id="update_form" enctype="multipart/form-data">
                <input type="hidden" name="event_id" value="${event.event_id}">
	            <div class="row mb-3 float-start">
	               <div class="col-sm-12">
	                 <h3>[ ${event.event_course_name} ]</h3>
	               </div>
	            </div>
				<div class="form-check form-check-inline float-end" style="margin-bottom:10px">
					<input class="form-check-input" type="radio" name="attr" id="attr0" value="0">
					<label class="form-check-label" for="attr0">종료</label>
				</div>
				<div class="form-check form-check-inline float-end" style="margin-bottom:10px">
					<input class="form-check-input" type="radio" name="attr" id="attr1" value="1" checked>
					<label class="form-check-label" for="attr1">진행중</label>
				</div>
				<div class="row mb-2" style="clear:both;"></div>
              
              <div class="row mb-3">
                <div class="col-sm-12">
                
                  <input class="col- form-control" name="photo" id="photo" 
                  		 type="file" id="formFile" accept="image/gif,image/png,image/jpeg">
                <c:if test="${!empty event.event_photo}">
                	<div id="file_delete_check">
                		[${event.event_photo}]파일이 등록되어 있습니다.
                		<input type="button" value="파일삭제" id="file_del">
                	</div>
                </c:if>
                </div>
              </div>
              
              <div class="row mb-3">
                <div class="col-sm-12">
                  <input type="text" name="deadline" id="deadline" class="form-control" 
                  		 placeholder="마감일을 입력해주세요   ex) yyyy-MM-dd" value="${event.event_deadline}">
                </div>
              </div>
              <div class="row mb-3">
                <div class="col-sm-12">
                  <textarea class="form-control" name="content" id="content" style="height: 70px" 
                  			placeholder="이벤트 요약내용을 입력해주세요">${event.event_content}</textarea>
                </div>
              </div>
              <div class="row mb-3">
                <div class="col-sm-12">
                  <textarea class="form-control" name="detail_content" id="detail_content" style="height: 200px" 
                  			placeholder="이벤트 상세내용을 입력해주세요">${event.event_detail_content}</textarea>
                </div>
              </div>
              
              <div class="text-end">
                <button type="button" class="btn btn-secondary" onclick='history.go(-1)'>취소</button>
                <button type="submit" class="btn btn-primary">수정</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
</main><!-- 메인 끝 -->
</div><!-- .content-main 끝 -->
</div><!-- .page-main 끝 -->
</body>
</html>