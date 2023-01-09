<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>내학습</title>

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/css/style.css" rel="stylesheet">
</head>


<body>
  <!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
      <a href="index.html" class="logo d-flex align-items-center">
        <span class="d-none d-lg-block">내학습</span>
      </a>
    </div>
    
  </header>

  <!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">
      <li class="nav-item">
        <a class="nav-link collapsed" href="index.html">
          <i class="bi bi-person-circle"></i>
          <span>프로필</span>
        </a>
      </li>

      <li class="nav-item">
        <a class="nav-link " href="#">
          <i class="bi bi-book-half"></i>
          <span>내 학습</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link collapsed" href="#">
          <i class="bi bi-pencil-square"></i>
          <span>작성 게시글</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link collapsed" href="#">
          <i class="bi bi-bookmark-heart-fill"></i>
          <span>관심 강좌</span>
        </a>
      </li>
    </ul>
  </aside><!-- End Sidebar-->

  <main id="main" class="main">    


		
	<section class="section">
		<h6>정렬기준</h6>
	    <select class="form-select" style="width:160px; float:left;">
	      <option selected value="1">최근신청순</option>
	      <option value="2">가나다순</option>
	    </select>	
	    <form class="search-form d-flex align-items-center" method="POST" action="#" style="float:right;">
	        <input type="text" name="query" style="width:200px;" placeholder="강의명 검색">
	        <button type="submit" title="Search"><i class="bi bi-search"></i></button>
	    </form> <!-- End Search Bar -->    
	</section><br><br><br>

    
    <section class="section clear:both"> 
      <div class="row align-items-top">
        <div class="col-lg-4">
          <div class="card">
            <img src="assets/img/product-1.jpg" class="card-img-top" style="height:300px;">
          </div>
        </div>
        <div class="col-lg-4">
          <div class="card">
            <img src="assets/img/product-2.jpg" class="card-img-top" style="height:300px;">
          </div>
        </div>
        <div class="col-lg-4">
          <div class="card">
            <img src="assets/img/product-3.jpg" class="card-img-top" style="height:300px;">
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
</body>

</html>