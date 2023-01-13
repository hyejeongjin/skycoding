<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>강의상세</title>

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link
    href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
    rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link rel="stylesheet"  href= "${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" >
  <link rel="stylesheet"  href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/course-style.css" >
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/course.fav.js"></script>
  <script type="text/javascript">
  //서버와 통신
 $(function(){
	 let course_idChecked = 0;
	 $('#cart_check').click(function(){
		 $.ajax({
				url : '../cart/checkApplication.do',
				type : 'post',
				data : {course_id:$('#course_id').val()
				},
				dataType : 'json',
				success : function(param) {
					if (param.result == 'courseNotFound') {
						course_idChecked = 1;
							$('#message_id').css('color', '#000000').text(
								'수강신청 가능');
							location.href='application.do?course_id=${course.course_id}';
					} else if (param.result == 'courseDuplicated') {
						idChecked = 0;
						$('#message_id').css('color', 'red').text(
						'수강신청 중복');
						$('#id').val('').focus();
					} else {
						idChecked = 0;
						alert('수강신청 오류 발생');
					}
				},
				error : function() {
					idChecked = 0;
					alert('네트워크 오류 발생');
				}
			});
	});
 });
  </script>
</head>


<body>
  <!-- 헤더  -->
   <jsp:include page="/WEB-INF/views/common/header.jsp"/>
  <!--  사이드바  -->
  
    <aside id="sidebar" class="sidebar">
    <ul class="sidebar-nav" id="sidebar-nav">
      <li class="nav-item">
                             
        <a class="nav-link " href="/skycoding/course/list.do?course_cate=1">
          <span>HTML</span>
        </a>
      </li>

      <li class="nav-item">           
        <a class="nav-link collapsed" href="/skycoding/course/list.do?course_cate=2">
          <span>CSS</span>
        </a>
      </li>
      
      <li class="nav-item">                        
        <a class="nav-link collapsed" href="/skycoding/course/list.do?course_cate=3">
          <span>JAVA</span>
        </a>
      </li>
      
      <li class="nav-item">           
        <a class="nav-link collapsed" href="/skycoding/course/list.do?course_cate=4">
          <span>DB</span>
        </a>
      </li>
      
         <!-- 관리자 등급만 버튼 보이게 생성 -->
    <c:if test="${!empty mem_num && mem_auth == 9}">
        <li class="nav-item">         <!-- 주소 skycoding으로 변경 -->
        <a class="nav-link collapsed" href="/skycoding/course/writeForm.do">
          <span>강의 등록</span>
        </a>
      </li>
    </c:if>
      
    </ul>
  </aside><!-- 사이드바 끝-->
  
  <!-- 메인 시작 -->
  <!-- 카드 시작 : 강의 -->
  <main id="main" class="main">
 
    <section class="section contact">
      <div class="row gy-4">
        <div class="col-xl-6">
          <img src="${pageContext.request.contextPath}/upload/${course.course_photo}" style="width: 100%;">
        </div>
        <div class="col-xl-6">
          <div class="card-write p-4" style="height: 80%;">
            <p class="card-text" style="margin: 10px 0px;">강의명 : ${course.course_name}</p>
             <p class="card-text" style="margin: 10px 0px;">강의자 : ${course.course_tr}</p>
          </div>
           <%-- 좋아요 --%>
           <input type="hidden" id="course_id" value="${course.course_id}">
          <img id="output_fav" src="${pageContext.request.contextPath}/images/like01.png" width="50"> 	
          <div class="text-end">
             
            <input type="button" id="cart_check" value="수강신청" class="btn btn-primary" >
            <span id="message_id"></span>
          </div>
        </div>
      </div>
  <!-- 카드 끝 : 강의 -->
      
        <!-- 카드 시작 : 강의 설명글 -->
      <div class="col-xl-12" style="margin-top: 20px;">
        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist" style="border-bottom: 1px solid #6c757d;">
          <li class="nav-item" role="presentation">
            <button class="nav-link active" id="교육개요-tab" data-bs-toggle="pill" data-bs-target="#교육개요" type="button" role="tab" aria-controls="교육개요" aria-selected="true">교육개요</button>
          </li>
        </ul>
        <div class="tab-content pt-2" id="myTabContent">
          <div class="tab-pane fade show active" id="교육개요" role="tabpanel" aria-labelledby="home-tab">
            <h6>교육개요</h6>
            <div class="card-write p-4" >
              ${course.course_content}
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- 카드 끝 : 강의 설명글  -->

  </main>
  <!-- 메인 끝  -->
  <jsp:include page="/WEB-INF/views/common/footer1.jsp" />
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>