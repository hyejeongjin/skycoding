<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>강의등록</title>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
 
<!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/course-style.css" >
  <script type="text/javascript">
    //강의 정보 등록 유효성 체크 
	$(function(){
		$('#write_form').submit(function(){
			if($('input[type=radio]:checked').length<=0){
				alert('강의표시여부를 지정하세요!');
				return false;
			}
			if($('#course_name').val()==''){
				alert('강의명을 입력하세요');
				$('#course_name').focus();
				return false;
			}
			if($('#course_tr').val()==''){
				alert('강의자명을 입력하세요');
				$('#course_tr').focus();
				return false;
			}
			if($('#course_photo').val().trim()==''){
				alert('파일을 업로드하세요');
				$('#course_photo').val('').focus();
				return false;
			}
			if($('#course_content').val()==''){
				alert('강의내용을 입력하세요');
				$('#course_content').focus();
				return false;
			}
			
		});
	});
</script>
</head>

<body>
<!-- Start #main -->
  <main id="register-main" class="register-main">
 
    <div class="pagetitle">
      <h1>강의 등록하기</h1>
    </div><!-- End Page Title -->
    
  
    <section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card-write">
            <div class="card-body">
              <h5 class="card-title"></h5>
   
              <form action="write.do" method="post" id="write_form" 
		                       enctype="multipart/form-data">
		      
		      <div class="form-check form-check-inline" style="margin-bottom:10px">
  					<input class="form-check-input" type="radio" name="course_cate" id="course_cate1" value="1">
  					<label class="form-check-label" for="course_cate1">HTML</label>
				</div>
				<div class="form-check form-check-inline" style="margin-bottom:10px">
  					<input class="form-check-input" type="radio" name="course_cate" id="course_cate2" value="2">
  					<label class="form-check-label" for="course_cate2">CSS</label>
				</div>
				<div class="form-check form-check-inline" style="margin-bottom:10px">
  					<input class="form-check-input" type="radio" name="course_cate" id="course_cate3" value="3">
  					<label class="form-check-label" for="course_cate3">JAVA</label>
				</div>
				<div class="form-check form-check-inline" style="margin-bottom:10px">
  					<input class="form-check-input" type="radio" name="course_cate" id="course_cate4" value="4">
  					<label class="form-check-label" for="course_cate4">DB</label>
				</div>
		    	
			
                <div class="row mb-3">
                  <div class="col-sm-12">
                    <input type="text" name="course_name" id="course_name" class="form-control" placeholder="강의명을 입력해주세요">
                  </div>
                </div>
                
                
                <div class="row mb-3">
                  <div class="col-sm-12">
                    <input type="text" name="course_tr" id="course_tr" class="form-control" placeholder="강의자명을 입력해주세요">
                  </div>
                </div>
                <div class="row mb-3">
                  <div class="col-sm-12">
                    <input class="form-control" name="course_photo" id="course_photo" type="file" id="formFile">
                  </div>
                </div>
                <div class="row mb-3">
                  <div class="col-sm-12">
                    <textarea class="form-control" name="course_content" id="course_content" style="height: 300px" placeholder="강의 내용을 입력해주세요"></textarea>
                  </div>
                </div>
                
                <div class="text-end">
                  <button type="button" class="btn btn-secondary">취소</button>
                  <button type="submit" class="btn btn-primary" >등록</button>
                </div>

              </form>

            </div>
          </div>

        </div>

      </div>
      
    </section>

  </main><!-- End #main -->

</body>
</html>