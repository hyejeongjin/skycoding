<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>공지사항 작성</title>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
 
<!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/course-style.css" >
  <script type="text/javascript">	$(function(){
		$('#write_form').submit(function(){
			if($('#title').val().trim()==''){
				alert('제목을 입력하세요!');
				$('#title').val('').focus();
				return false;
			}
			if($('#content').val().trim()==''){
				alert('내용을 입력하세요!');
				$('#content').val('').focus();
				return false;
			}
		});
	});
</script>
</head>

<body style="text-align:center">
<div class="page-main"> <jsp:include page="/WEB-INF/views/common/header.jsp"/>
<!-- Start #main -->
  <main id="register-main" class="register-main">
  
    <div class="pagetitle" style="margin-top:150px;">
    <h3>공지사항 작성</h3>
    </div><!-- End Page Title -->
    <section class="section">
      <div class="row" >
        <div class="col-lg-12">

          <div class="card" style="width: 1000px; margin: auto;">
            <div class="card-body">
        
   
              <form action="admin_write.do" method="post" id="write_form" 
		                       enctype="multipart/form-data">
		    	
			
                <div class="row mb-3">
                  <div class="col-sm-12" >
                    <input type="text" name="news_title" id="news_title" class="form-control" placeholder="제목을 입력해주세요">
                  </div>
                </div>
                <div class="row mb-3" >
                  <div class="col-sm-12">
                    <input class="form-control" name="news_photo" id="news_photo" type="file" id="formFile">
                  </div>
                </div>
                <div class="row mb-3">
                  <div class="col-sm-12">
                    <textarea class="form-control" name="news_content" id="news_content" style="height: 300px" placeholder="공지사항 내용을 입력해주세요"></textarea>
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
</div>
</body>
</html>