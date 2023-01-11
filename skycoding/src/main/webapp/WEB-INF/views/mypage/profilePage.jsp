<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>프로필</title>

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/mypage-style.css" rel="stylesheet">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#photo_btn').click(function(){
			$('#photo_choice').show();
			$(this).hide();//수정 버튼 감추기
		});
		
		//이미지 미리 보기
		//처음 화면에 보여지는 이미지 읽기
		let photo_path = $('.my-photo').attr('src');
		let my_photo;
		$('#photo').change(function(){
			my_photo = this.files[0];
			if(!my_photo){
				$('.my-photo').attr('src',photo_path);
				return;
			}
			//파일 용량 체크
			if(my_photo.size > 1024*1024){
				alert(Math.round(my_photo.size/1024) 
						+ 'kbytes(1024kbytes까지만 업로드 가능)');
				$('.my-photo').attr('src',photo_path);
				$(this).val('');//선택한 파일 정보 지우기
				return;
			}
			
			let reader = new FileReader();
			reader.readAsDataURL(my_photo);
			
			reader.onload=function(){
				$('.my-photo').attr('src',reader.result);
			};
		});//end of change
		
		//이미지 전송
		$('#photo_submit').click(function(){
			if($('#photo').val()==''){
				alert('파일을 선택하세요!');
				$('#photo').focus();
				return;
			}
			
			//서버에 파일 전송
			let form_data = new FormData();
			form_data.append('photo',my_photo);
			$.ajax({
				url:'updateMyPhoto.do',
				type:'post',
				data:form_data,
				dataType:'json',
				contentType:false, //데이터 객체를 문자열로 바꿀지에 대한 설정,true면 일반 문자
				processData:false, //해당 타입을 true로 하면 일반 text로 구분
				enctype:'multipart/form-data',
				success:function(param){
					if(param.result == 'logout'){
						alert('로그인 후 사용하세요!');
					}else if(param.result == 'success'){
					    alert('프로필 사진이 수정되었습니다.');
					    photo_path = $('.my-photo').attr('src');
					    $('#photo').val('');
					    $('#photo_choice').hide();
					    $('#photo_btn').show();
					}else{
						alert('파일 전송 오류 발생');
					}
				},
				error:function(){
					alert('네트워크 오류 발생');
				}
			});
			
		});//end of click (파일 전송)
		
		//이미지 미리보기 취소
		$('#photo_reset').click(function(){
			//초기 이미지 표시
			$('.my-photo').attr('src',photo_path);
			$('#photo').val('');
			$('#photo_choice').hide();
			$('#photo_btn').show();
			
		});//end of click (이미지 미리보기 취소)
		
	});
</script>
</head>


<body>
  <jsp:include page="/WEB-INF/views/common/mypagemain.jsp"/>

  <main id="main" class="main">  
    <section class="section profile">
    
      <div class="row">
		<!-- 프로필 사진 -->      
        <div class="col-xl-6">    
          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">
			  <h2>프로필사진</h2><br>
              		<c:if test="${empty member.photo}">
					<img 
					src="${pageContext.request.contextPath}/images/face.png"
					class="rounded-circle my-photo">
					</c:if>
					<c:if test="${!empty member.photo}">
					<img 
					src="${pageContext.request.contextPath}/upload/${member.photo}"
					class="rounded-circle my-photo">
					</c:if>
					<br>               	
                <div class="text-center">
                	<button type="button" class="btn btn-primary" id="photo_btn">수정</button>
                </div>
                <div id="photo_choice" style="display:none;">
					<input type="file" id="photo" 
					 accept="image/gif,image/png,image/jpeg" class="mb-2"><br>
					<input type="button" value="전송" id="photo_submit" class="btn btn-primary">
					<input type="button" value="취소" id="photo_reset" class="btn btn-secondary">
				</div>         
          	</div>
	      </div>
	    </div>
	    
	    <!-- 회원정보 -->       
        <div class="col-xl-6">       
          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">
			  <h2>회원정보</h2><br>
			  <ul>
				<li>이름 : ${member.name}</li>
				<li>전화번호 : ${member.phone}</li>
				<li>이메일 : ${member.email}</li>
				<li>가입일 : ${member.reg_date}</li>
				<c:if test="${!empty member.modify_date}">
				<li>최근 정보 수정일 : ${member.modify_date}</li>
				</c:if>
			  </ul>                 	
              <div class="text-center">
               	<input type="button" class="btn btn-primary"
               	onclick="location.href='modifyProfileForm.do'" value="회원정보 수정">
              </div>
            </div>                
          </div>
	    </div>
	    
	    <!-- 회원탈퇴 -->
        <div class="col-xl-6">       
          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">
			  <h2>회원탈퇴</h2><br>
              <form>             	
                <div class="text-center">
                	<button type="button" class="btn btn-primary"
                		onclick="location.href='deleteProfileForm.do'">회원탈퇴</button>
                </div>
              </form><!-- End Profile Edit Form -->
            </div>                
          </div>
	    </div>
	    
	    <!-- 비밀번호 수정 -->
        <div class="col-xl-6">       
          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">
			  <h2>비밀번호 수정</h2><br>
              <form>             	
                <div class="text-center">
                	<button type="button" class="btn btn-primary" 
                		onclick="location.href='modifyPwForm.do'">비밀번호 수정</button>
                </div>
              </form><!-- End Profile Edit Form -->
            </div>                
          </div>
	    </div>	    	    

      </div>
    </section>
  </main><!-- End #main -->
</body>

</html>