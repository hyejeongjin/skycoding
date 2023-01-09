<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>프로필</title>

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
        <span class="d-none d-lg-block">프로필</span>
      </a>
    </div>
    
  </header>

  <!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">
      <li class="nav-item">
        <a class="nav-link " href="index.html">
          <i class="bi bi-person-circle"></i>
          <span>프로필</span>
        </a>
      </li>

      <li class="nav-item">
        <a class="nav-link collapsed" href="#">
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
    <section class="section profile">
    
      <div class="row">
      
        <div class="col-xl-6">    
          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">
			  <h2>프로필사진</h2><br>
              <img src="assets/img/profile-img.jpg" alt="Profile" class="rounded-circle"><br>             
              <form>             	
                <div class="text-center">
                	<button type="submit" class="btn btn-primary">수정</button>
                </div>
              </form><!-- End Profile Edit Form -->
            </div>                   
          </div>
	    </div>
	           
        <div class="col-xl-6">       
          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">
			  <h2>회원정보</h2><br>
			  <div class="text-left">
			  	이름 : <span class="profile_name">이하늘</span><br>
			  	전화번호 : <span class="profile_phone">010-1234-5678</span><br>
			  	가입일 : <span class="profile_name">2022-12-20</span><br>
			  	최근 정보 수정일 : <span class="profile_name">2022-12-28</span>
			  </div><br>        
              <form>             	
                <div class="text-center">
                	<button type="submit" class="btn btn-primary">회원정보 수정</button>
                </div>
              </form><!-- End Profile Edit Form -->
            </div>                
          </div>
	    </div>
	    
        <div class="col-xl-6">       
          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">
			  <h2>회원탈퇴</h2><br>
              <form>             	
                <div class="text-center">
                	<button type="submit" class="btn btn-primary">회원탈퇴</button>
                </div>
              </form><!-- End Profile Edit Form -->
            </div>                
          </div>
	    </div>
	    
        <div class="col-xl-6">       
          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">
			  <h2>비밀번호 수정</h2><br>
              <form>             	
                <div class="text-center">
                	<button type="submit" class="btn btn-primary">비밀번호 수정</button>
                </div>
              </form><!-- End Profile Edit Form -->
            </div>                
          </div>
	    </div>	    	    

      </div>
    </section>
  </main><!-- End #main -->
</body>

</html>