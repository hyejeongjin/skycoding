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

  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/course.fav.js"></script>
  <script type="text/javascript">
  	$(function(){
  		$('#list_sort').change(function(){
  			location.href='list.do?course_cate=${param.course_cate}&sort='+$(this).val();
  		});
  	});
  </script>
</head>



<body>
<!-- 헤더 -->
  <header id="header" class="header fixed-top d-flex align-items-center">
    
    <div class="d-flex align-items-center justify-content-between">
      <a href="/myUITest/course/list.do?cate=1" class="logo d-flex align-items-center">
        <span class="d-none d-lg-block">강의</span>
      </a>
    </div>
  </header>
    <!-- 헤더 끝 -->
  
   

  <!-- 사이드바 -->
  
  <aside id="sidebar" class="sidebar">
    <ul class="sidebar-nav" id="sidebar-nav">
      <li class="nav-item">
                            <!-- 주소 skycoding으로 변경 -->
        <a class="nav-link " href="/skycoding/course/list.do?course_cate=1">
          <span>HTML</span>
        </a>
      </li>

      <li class="nav-item">          <!-- 주소 skycoding으로 변경 -->
        <a class="nav-link collapsed" href="/skycoding/course/list.do?course_cate=2">
          <span>CSS</span>
        </a>
      </li>
      
      <li class="nav-item">           <!-- 주소 skycoding으로 변경 -->
        <a class="nav-link collapsed" href="/skycoding/course/list.do?course_cate=3">
          <span>JAVA</span>
        </a>
      </li>
      
      <li class="nav-item">           <!-- 주소 skycoding으로 변경 -->
        <a class="nav-link collapsed" href="/skycoding/course/list.do?course_cate=4">
          <span>DB</span>
        </a>
      </li>
      
         <!-- 관리자 등급만 버튼 보이게 생성 -->
    <c:if test="${!empty mem_num && mem_auth == 9}">
        <li class="nav-item">         <!-- 변경 -->
        <a class="nav-link collapsed" href="/skycoding/course/writeForm.do">
          <span>강의 등록</span>
        </a>
      </li>
    </c:if>
      
    </ul>
  </aside><!-- 사이드바 끝-->

<!-- 검색폼 -->
  <main id="main" class="main">
  <section class="section">
    <div class="search-bar">
      <form class="search-form d-flex align-items-center"  id="search-form" action="list.do" method="get" action="#">
        <input type="hidden" name="course_cate" value="${param.course_cate}">
        <input type="text" name="keyword" value="${param.keyword}" placeholder="검색어를 입력하세요"> 
        <button type="submit" title="Search"><i class="bi bi-search"></i></button>
      </form>

    </div><!-- 검색폼 끝 -->
</section>
     
     <!-- dropdwon 시작 -->
    <select class="form-select" name="sort" id="list_sort" style="width:120px; float:right;">
      <option value="1" <c:if test="${param.sort == 1}">selected</c:if>>최신순</option>
      <option value="2" <c:if test="${param.sort == 2}">selected</c:if>>조회순</option>
      <option value="3" <c:if test="${param.sort == 3}">selected</c:if>>추천순</option>
    </select> 
     <!-- dropdwon 끝 -->

    <!-- 강의 메인 시작 -->
    <h5 style="clear:right">전체${count}개</h5>

    <c:if test="${count > 0}">
    <section class="section">
      <div class="row align-items-top">
        <div class="col-lg-6">
        <c:forEach var="course" items="${list}">
          <div class="card" onclick="location.href='detail.do?course_id=${course.course_id}'">
          	
            	<img src="${pageContext.request.contextPath}/upload/${course.course_photo}" class="card-img-top">
            	<div class="card-body text-center">
              	<p class="card-text" style="margin: 10px 0px;">${course.course_name}</p>
              	<p class="card-text" style="margin: 10px 0px;">${course.course_tr}</p>
                <%-- 좋아요 --%>
                 <input type="hidden" id="course_id" value="${course.course_id}">
              	<i class="bi bi-heart-fill" style="padding-right:20px;" ></i>${course.like_count}
              	<i class="bi bi-person-fill" style="padding-right:10px; padding-left:10px;"></i>${course.course_hit}
            
            </div>
          </div>
          </c:forEach>
        
         </div>
          </div>
    </section>
     </c:if>
    <!-- 강의 메인 끝  -->
    
    <div class="text-center">
  
      <!-- 페이지 처리 시작 -->
      <c:if test="${count>0}">
		<div class="align-center">${page}</div>
		</c:if>
              <!-- 페이지 처리 끝 -->
    </div>
   

    
  </main>

</body>

</html>