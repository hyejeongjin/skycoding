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
      <a href="#" class="logo d-flex align-items-center">
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
        <a class="nav-link " href="/myUITest/course/htmlList.do">
          <span>HTML</span>
        </a>
      </li>

      <li class="nav-item">
        <a class="nav-link collapsed" href="#">
          <span>JS</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link collapsed" href="#">
          <span>JAVA</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link collapsed" href="#">
          <span>DB</span>
        </a>
      </li>
      
         <!-- 관리자 등급만 버튼 보이게 생성 -->
    <c:if test="${!empty user_num && user_auth == 9}">
        <li class="nav-item">         <!-- 변경 -->
        <a class="nav-link collapsed" href="/myUITest/course/writeForm.do">
          <span>강의 등록</span>
        </a>
      </li>
    </c:if>
      
    </ul>
  </aside><!-- End Sidebar-->


  <main id="main" class="main">
  <section class="section">
    <div class="search-bar">
      <form class="search-form d-flex align-items-center" method="POST" action="#">
        <input type="text" name="query">
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

    
    <section class="section">
      <div class="row align-items-top">
        <div class="col-lg-3">
          <div class="card">
            <img src="${pageContext.request.contextPath}/upload/${course.course_photo}" class="card-img-top">
            <div class="card-body text-center">
              <p class="card-text" style="margin: 10px 0px;">${course.course_name}</p>
              <p class="card-text" style="margin: 10px 0px;">${course.course_tr}</p>
              <i class="bi bi-heart-fill" style="padding-right:10px;"></i>0
              <i class="bi bi-person-fill" style="padding-right:10px; padding-left:10px;"></i>0
            </div>
          </div>
          <div class="card">
            <img src="https://ap-northeast-2-02870039-view.menlosecurity.com/c/0/i/aHR0cHM6Ly9jcGhpbmYucHN0YXRpYy5uZXQvbW9vYy8yMDIyMTAyOF8xODIvMTY2NjkxOTQyODA4Mlg3RlFBX0pQRUcvJUI1JUJGJUJGJUI1JUJCJUYzXyVCNCVFQiVDNyVBNSVDMCVDQyVCOSVDQyVDMSVGNl92MV8xLmpwZz90eXBlPWZmbjMwMF8xNjY~" class="card-img-top">
            <div class="card-body text-center">
              <p class="card-text" style="margin: 10px 0px;">강의이름</p>
              <p class="card-text" style="margin: 10px 0px;">강의자</p>
              <i class="bi bi-heart-fill" style="padding-right:10px;"></i>0
              <i class="bi bi-person-fill" style="padding-right:10px; padding-left:10px;"></i>0
            </div>
          </div>
        </div>

        
        <div class="col-lg-3">
          <div class="card">
            <img src="https://ap-northeast-2-02870039-view.menlosecurity.com/c/0/i/aHR0cHM6Ly9jcGhpbmYucHN0YXRpYy5uZXQvbW9vYy8yMDIyMTAyOF8xODIvMTY2NjkxOTQyODA4Mlg3RlFBX0pQRUcvJUI1JUJGJUJGJUI1JUJCJUYzXyVCNCVFQiVDNyVBNSVDMCVDQyVCOSVDQyVDMSVGNl92MV8xLmpwZz90eXBlPWZmbjMwMF8xNjY~" class="card-img-top">
            <div class="card-body text-center">
              <p class="card-text" style="margin: 10px 0px;">강의이름</p>
              <p class="card-text" style="margin: 10px 0px;">강의자</p>
              <i class="bi bi-heart-fill" style="padding-right:10px;"></i>0
              <i class="bi bi-person-fill" style="padding-right:10px; padding-left:10px;"></i>0
            </div>
          </div>
          <div class="card">
            <img src="https://ap-northeast-2-02870039-view.menlosecurity.com/c/0/i/aHR0cHM6Ly9jcGhpbmYucHN0YXRpYy5uZXQvbW9vYy8yMDIyMTAyOF8xODIvMTY2NjkxOTQyODA4Mlg3RlFBX0pQRUcvJUI1JUJGJUJGJUI1JUJCJUYzXyVCNCVFQiVDNyVBNSVDMCVDQyVCOSVDQyVDMSVGNl92MV8xLmpwZz90eXBlPWZmbjMwMF8xNjY~" class="card-img-top">
            <div class="card-body text-center">
              <p class="card-text" style="margin: 10px 0px;">강의이름</p>
              <p class="card-text" style="margin: 10px 0px;">강의자</p>
              <i class="bi bi-heart-fill" style="padding-right:10px;"></i>0
              <i class="bi bi-person-fill" style="padding-right:10px; padding-left:10px;"></i>0
            </div>
          </div>
        </div>

        
        <div class="col-lg-3">
          <div class="card">
            <img src="https://ap-northeast-2-02870039-view.menlosecurity.com/c/0/i/aHR0cHM6Ly9jcGhpbmYucHN0YXRpYy5uZXQvbW9vYy8yMDIyMTAyOF8xODIvMTY2NjkxOTQyODA4Mlg3RlFBX0pQRUcvJUI1JUJGJUJGJUI1JUJCJUYzXyVCNCVFQiVDNyVBNSVDMCVDQyVCOSVDQyVDMSVGNl92MV8xLmpwZz90eXBlPWZmbjMwMF8xNjY~" class="card-img-top">
            <div class="card-body text-center">
              <p class="card-text" style="margin: 10px 0px;">강의이름</p>
              <p class="card-text" style="margin: 10px 0px;">강의자</p>
              <i class="bi bi-heart-fill" style="padding-right:10px;"></i>0
              <i class="bi bi-person-fill" style="padding-right:10px; padding-left:10px;"></i>0
            </div>
          </div>
          <div class="card">
            <img src="https://ap-northeast-2-02870039-view.menlosecurity.com/c/0/i/aHR0cHM6Ly9jcGhpbmYucHN0YXRpYy5uZXQvbW9vYy8yMDIyMTAyOF8xODIvMTY2NjkxOTQyODA4Mlg3RlFBX0pQRUcvJUI1JUJGJUJGJUI1JUJCJUYzXyVCNCVFQiVDNyVBNSVDMCVDQyVCOSVDQyVDMSVGNl92MV8xLmpwZz90eXBlPWZmbjMwMF8xNjY~" class="card-img-top">
            <div class="card-body text-center">
              <p class="card-text" style="margin: 10px 0px;">강의이름</p>
              <p class="card-text" style="margin: 10px 0px;">강의자</p>
              <i class="bi bi-heart-fill" style="padding-right:10px;"></i>0
              <i class="bi bi-person-fill" style="padding-right:10px; padding-left:10px;"></i>0
            </div>
          </div>
        </div>
        
        <div class="col-lg-3">
          <div class="card">
            <img src="https://ap-northeast-2-02870039-view.menlosecurity.com/c/0/i/aHR0cHM6Ly9jcGhpbmYucHN0YXRpYy5uZXQvbW9vYy8yMDIyMTAyOF8xODIvMTY2NjkxOTQyODA4Mlg3RlFBX0pQRUcvJUI1JUJGJUJGJUI1JUJCJUYzXyVCNCVFQiVDNyVBNSVDMCVDQyVCOSVDQyVDMSVGNl92MV8xLmpwZz90eXBlPWZmbjMwMF8xNjY~" class="card-img-top">
            <div class="card-body text-center">
              <p class="card-text" style="margin: 10px 0px;">강의이름</p>
              <p class="card-text" style="margin: 10px 0px;">강의자</p>
              <i class="bi bi-heart-fill" style="padding-right:10px;"></i>0
              <i class="bi bi-person-fill" style="padding-right:10px; padding-left:10px;"></i>0
            </div>
          </div>
          <div class="card">
            <img src="https://ap-northeast-2-02870039-view.menlosecurity.com/c/0/i/aHR0cHM6Ly9jcGhpbmYucHN0YXRpYy5uZXQvbW9vYy8yMDIyMTAyOF8xODIvMTY2NjkxOTQyODA4Mlg3RlFBX0pQRUcvJUI1JUJGJUJGJUI1JUJCJUYzXyVCNCVFQiVDNyVBNSVDMCVDQyVCOSVDQyVDMSVGNl92MV8xLmpwZz90eXBlPWZmbjMwMF8xNjY~" class="card-img-top">
            <div class="card-body text-center">
              <p class="card-text" style="margin: 10px 0px;">강의이름</p>
              <p class="card-text" style="margin: 10px 0px;">강의자</p>
              <i class="bi bi-heart-fill" style="padding-right:10px;"></i>0
              <i class="bi bi-person-fill" style="padding-right:10px; padding-left:10px;"></i>0
            </div>
          </div>
        </div>
      </div>
    </section>

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