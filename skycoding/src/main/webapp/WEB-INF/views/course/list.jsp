<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>강의전체</title>

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/course-style.css" >
</head>



<body>
<!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">
    
    <div class="d-flex align-items-center justify-content-between">
      <a href="/myUITest/course/list.do?cate=1" class="logo d-flex align-items-center">
        <span class="d-none d-lg-block">강의</span>
      </a>
    </div>
  </header>
    <!-- ======= Header ======= -->
  
   

  <!-- ======= Sidebar ======= -->
  
  <aside id="sidebar" class="sidebar">
    <ul class="sidebar-nav" id="sidebar-nav">
      <li class="nav-item">
                            <!-- 변경 -->
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
    <c:if test="${!empty user_num && user_auth == 9}">
        <li class="nav-item">         <!-- 변경 -->
        <a class="nav-link collapsed" href="/skycoding/course/writeForm.do">
          <span>강의 등록</span>
        </a>
      </li>
    </c:if>
      
    </ul>
  </aside><!-- End Sidebar-->

<!-- Start Search Bar -->
  <main id="main" class="main">
  <section class="section">
    <div class="search-bar">
      <form class="search-form d-flex align-items-center"  id="search-form" action="list.do" method="get" action="#">
        <input type="text" name="query" value="${param.keyword}"> 
        <button type="submit" title="Search"><i class="bi bi-search"></i></button>
      </form>
    </div><!-- End Search Bar -->
</section>

    <select class="form-select" style="width:120px; float:right;">
      <option selected>Dropdown</option>
      <option value="1">추천순</option>
      <option value="2">조회순</option>
    </select>

    <h5 style="clear:right">전체 N개</h5>

    <c:if test="${count > 0}">
    <section class="section">
      <div class="row align-items-top">
        <div class="col-lg-3">
        <c:forEach var="course" items="${list}">
          <div class="card">
            <img src="${pageContext.request.contextPath}/upload/${course.course_photo}" class="card-img-top">
            <div class="card-body text-center">
              <p class="card-text" style="margin: 10px 0px;">${course.course_name}</p>
              <p class="card-text" style="margin: 10px 0px;">${course.course_tr}</p>
              <i class="bi bi-heart-fill" style="padding-right:10px;"></i>0
              <i class="bi bi-person-fill" style="padding-right:10px; padding-left:10px;"></i>0
            </div>
          </div>
          </c:forEach>
        
         </div>
          </div>
    </section>
     </c:if>

    <div class="text-center">
      <!-- Pagination with icons -->
      <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous" style="border:0px; color:black;">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#" style="border:0px; color:black;">1</a></li>
                  <li class="page-item"><a class="page-link" href="#" style="border:0px; color:black;">2</a></li>
                  <li class="page-item"><a class="page-link" href="#" style="border:0px; color:black;">3</a></li>
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next" style="border:0px; color:black;">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                </ul>
              </nav><!-- End Pagination with icons -->
    </div>
 
    

    
  </main><!-- End #main -->

</body>

</html>