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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/news_tyle.css" >
  <script type="text/javascript">	
  $(function(){
		$('#write_form').submit(function(){
			if($('input[type=radio]:checked').length<1){
				alert('필독 표시여부를 지정하세요!');
				return false;
			}
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
		                    <div class="form-check form-check-inline">
                     <input class="form-check-input" type="radio" id="inlineCheckbox1" name="news_attr" value="0">
                       <label class="form-check-label" for="inlineCheckbox1">필독표시</label>
                              </div>
                             <div class="form-check form-check-inline">
                                 <input class="form-check-input" type="radio" id="inlineCheckbox2" name="news_attr" value="1">
                                  <label class="form-check-label" for="inlineCheckbox2">필독 미표시</label>
                              </div>
		    	
			
                <div class="row mb-3">
                  <div class="col-sm-12" >
                    <input type="text" name="news_title" id="news_title" class="form-control" placeholder="제목을 입력해주세요">
                   
                  </div>
                </div>
                <div class="row mb-3" >
                  <div class="col-sm-12">
                    <input class="form-control" name="news_photo" id="news_photo" type="file" id="news_photo">
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
  	<jsp:include page="/WEB-INF/views/common/footer1.jsp" />
</div>
</body>
</html>