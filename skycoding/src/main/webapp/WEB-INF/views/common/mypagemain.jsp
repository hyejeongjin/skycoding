<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <jsp:include page="/WEB-INF/views/common/header.jsp"/>

  <!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">
      <li class="nav-item">
        <a class="nav-link " href="${pageContext.request.contextPath}/mypage/profilePage.do">
          <i class="bi bi-person-circle"></i>
          <span>프로필</span>
        </a>
      </li>

      <li class="nav-item">
        <a class="nav-link collapsed" href="${pageContext.request.contextPath}/mypage/myStudy.do">
          <i class="bi bi-book-half"></i>
          <span>내 학습</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link collapsed" href="${pageContext.request.contextPath}/mypage/myWriting.do">
          <i class="bi bi-pencil-square"></i>
          <span>작성 게시글</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link collapsed" href="${pageContext.request.contextPath}/mypage/myBookmark.do">
          <i class="bi bi-bookmark-heart-fill"></i>
          <span>관심 강좌</span>
        </a>
      </li>
    </ul>
  </aside><!-- End Sidebar-->