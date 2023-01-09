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
</head>


<body>
  <!-- ======= Header ======= -->
  
    <header id="header" class="header fixed-top d-flex align-items-center">
  
    <div class="d-flex align-items-center justify-content-between">
      <a href="index.html" class="logo d-flex align-items-center">
        <span class="d-none d-lg-block">강의상세</span>
      </a>
    </div>

  </header>
 

  <!-- ======= Sidebar ======= -->
  
  
  <aside id="sidebar" class="sidebar">
  
    <ul class="sidebar-nav" id="sidebar-nav">
      <li class="nav-item">
        <a class="nav-link " href="index.html">
          <i class="bi bi-grid"></i>
          <span>전체</span>
        </a>
      </li>

      <li class="nav-item">
        <a class="nav-link collapsed" href="#">
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
      
       <c:if test="${!empty user_num && user_auth == 9}">
        <li class="nav-item">
        <a class="nav-link collapsed" href="/ch19MvcPage/course/writeForm.do">
          <span>강의 등록</span>
        </a>
      </li>
    </c:if>
      
      
    </ul>
  </aside><!-- End Sidebar-->

  <main id="main" class="main">

    <section class="section contact">
      <div class="row gy-4">
        <div class="col-xl-6">
          <img src="https://ap-northeast-2-02870039-view.menlosecurity.com/c/0/i/aHR0cHM6Ly9jcGhpbmYucHN0YXRpYy5uZXQvbW9vYy8yMDIyMTEwOV8xOTQvMTY2Nzk3NDY3Nzk0MGZpYnBDX0pQRUcvVFVyWUQ1a2FXeERpeFhnaFNjbmcuanBnP3R5cGU9dzc2MA~~" style="width: 100%;">
        </div>
        <div class="col-xl-6">
          <div class="card p-4" style="height: 80%;">
            test
            test
            test
            test
          </div>
          
          <div class="text-end">
            <button type="button" class="btn btn-primary">추천</button>
            <button type="submit" class="btn btn-secondary">수강신청</button>
          </div>
        </div>
      </div>

      
      <div class="col-xl-12" style="margin-top: 20px;">
        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist" style="border-bottom: 1px solid #6c757d;">
          <li class="nav-item" role="presentation">
            <button class="nav-link active" id="교육개요-tab" data-bs-toggle="pill" data-bs-target="#교육개요" type="button" role="tab" aria-controls="교육개요" aria-selected="true">교육개요</button>
          </li>
          <li class="nav-item" role="presentation">
            <button class="nav-link" id="교육일정-tab" data-bs-toggle="pill" data-bs-target="#교육일정" type="button" role="tab" aria-controls="교육일정" aria-selected="false">교육일정</button>
          </li>
        </ul>
        <div class="tab-content pt-2" id="myTabContent">
          <div class="tab-pane fade show active" id="교육개요" role="tabpanel" aria-labelledby="home-tab">
            <h6>모집일정</h6>
            <div class="card p-4" >
              test
              test
              test
              test
            </div>
            <h6>모집일정</h6>
            <div class="card p-4" >
              test
              test
              test
              test
            </div>
          </div>
          <div class="tab-pane fade" id="교육일정" role="tabpanel" aria-labelledby="profile-tab">
            
            <h6>모집일정</h6>
              <div class="card p-4" >
                test1
                test2
                test34
                test
              </div>
              <h6>모집일정</h6>
              <div class="card p-4" >
                test
                test
                test
                test
              </div>
          </div>
        </div>
      </div>
    </section>

  </main><!-- End #main -->
  
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>