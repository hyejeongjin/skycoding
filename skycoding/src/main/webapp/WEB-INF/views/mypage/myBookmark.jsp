<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>관심강좌</title>

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/mypage-style.css" rel="stylesheet">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>

</head>

<script type="text/javascript">
	$(document).ready(function() {
		/*체크박스 전체선택 및 전체해제*/
		$("#allbtn").click(function() {
			var chks = document.getElementsByName("cbox");
			var chksChecked = 0;
			
			for(var i=0; i<chks.length; i++) {			
				if(chks[i].checked) {
					chksChecked++;
				}
			}
			
			if(chks.length == chksChecked){
				for(var j=0; j<chks.length; j++) {
					chks[j].checked=false;
				}
			}
			else{
				for(var j=0; j<chks.length; j++) {
					chks[j].checked=true;
				}
			}
		});
		
		/*삭제 버튼 눌렸을 때 선택된 체크박스가 없을 때의 처리*/
		$("#delbtn").click(function(){
			var chks = document.getElementsByName("cbox");
			var chksChecked = 0;
			for(var i=0; i<chks.length; i++) {			
				if(chks[i].checked) {
					chksChecked++;
				}
			}
			if(chksChecked==0) {
				alert("삭제할 항목이 없습니다.");
				return false;
			}
		});
		
	});
</script>

<body>
  <jsp:include page="/WEB-INF/views/common/mybookmarkmain.jsp"/>

  <main id="main" class="main">    
	<form action="deleteCourseLike.do">
	<c:if test="${not empty courselikeList}">	
	<section class="section">
	    <span class="likecourse-button">
          <button type="button" id="allbtn" class="btn btn-secondary" style="margin-right:0.5em;"><i class="bi bi-check-lg"></i>전체선택</button>
          <button type="submit" id="delbtn" class="btn btn-danger"><i class="bi bi-x-lg"></i>삭제</button>
        </span>
	</section><br>
	</c:if>	

    
    <!-- 여기서부터 본문 내용 시작 -->
    <section class="section clear:both">
      <div class="row align-items-top">
      	<!-- 관심 강좌 없을 때 처리 -->
      	<c:if test="${empty courselikeList}"><div style="height:350px;"><br><h4><b>관심 강좌가 없습니다.</b></h4></div></c:if>
      	<c:forEach var="courselike" items="${courselikeList}">
	      	<div class="col-lg-4">
	          <input type="checkbox" name="cbox" value="${courselike.course_id}" style="zoom:1.5;">
	          <div class="card">
	          	<a href="${pageContext.request.contextPath}/course/detail.do?course_id=${courselike.course_id}">
	          		<img src="${pageContext.request.contextPath}/upload/${courselike.course_photo}" class="card-img-top" style="height:300px;">
	          	</a>            
	          </div>      		
	      	</div>      		
      	</c:forEach>                                                  
      </div>
    </section>
    </form>

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
                  <li class="page-item"><a class="page-link" href="#" style="border:0px; color:black;">4</a></li>
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next" style="border:0px; color:black;">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                </ul>
              </nav><!-- End Pagination with icons -->
    </div>
  </main><!-- End #main -->
  <jsp:include page="/WEB-INF/views/common/footer1.jsp" />
</body>

</html>