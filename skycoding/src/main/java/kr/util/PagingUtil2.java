package kr.util;

public class PagingUtil2 {
	private int startRow;	 // 한 페이지에서 보여줄 게시글의 시작 번호
	private int endRow;	 // 한 페이지에서 보여줄 게시글의 끝 번호
	private StringBuffer page;// 페이지 표시 문자열

	/**
	 * currentPage : 현재페이지 
	 * count : 전체 게시물 수
	 * rowCount : 한 페이지의  게시물의 수
	 * pageCount : 한 화면에 보여줄 페이지 수
	 * pageUrl : 호출 페이지 url
	 * addKey : 부가적인 key 없을 때는 null 처리 (&num=23형식으로 전달할 것)
	 * */
	public PagingUtil2(int currentPage, int count, int rowCount,
			int pageCount, String pageUrl) {
		this(null,null,currentPage,count,rowCount,pageCount,pageUrl,null);
	}
	public PagingUtil2(int currentPage, int count, int rowCount,
			int pageCount, String pageUrl, String addKey) {
		this(null,null,currentPage,count,rowCount,pageCount,pageUrl,addKey);
	}
	public PagingUtil2(String keyfield, String keyword, int currentPage, int count, int rowCount,
			int pageCount,String pageUrl) {
		this(keyfield,keyword,currentPage,count,rowCount,pageCount,pageUrl,null);
	}
	public PagingUtil2(String keyfield, String keyword, int currentPage, int count, int rowCount,
			int pageCount,String pageUrl,String addKey) {
		
		String sub_url = "";//uri 링크 뒤에 붙이는 파라미터
		if(keyword != null) sub_url = "&keyfield="+keyfield+"&keyword="+keyword;
		if(addKey != null) sub_url += addKey;
		
		// 전체 페이지 수
		int totalPage = (int) Math.ceil((double) count / rowCount);//페이지 개수를 반올림
		
		if (totalPage == 0) {
			totalPage = 1;
		}
		// 현재 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		// 현재 페이지는 request에서 값을 가져옴
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		// DAO객체에 전달할 현재 페이지의 처음과 마지막 글의 번호 가져오기.
		startRow = (currentPage - 1) * rowCount + 1;
		endRow = currentPage * rowCount;
		// 시작 페이지와 마지막 페이지 값 구하기.
		int startPage = (int) ((currentPage - 1) / pageCount) * pageCount + 1;
		int endPage = startPage + pageCount - 1;
		// 마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		
		
		
		page = new StringBuffer();
		page.append("<nav aria-label='Page navigation example'><ul class='pagination justify-content-center'>");
			//현재페이지가 페이지의 개수보다 클 때, 이전 block 페이지 표시
			if (currentPage > pageCount) {
				page.append("<li class='page-item'>"
							+ "<a href="+pageUrl+"?pageNum="+ (startPage - 1) + sub_url +" "
									+ "class='page-link' aria-label='Previous' style='border: 0px; color: black;'>");
				page.append("<span aria-hidden='true'>");
				page.append("&laquo;");
				page.append("</span></a></li>");
			}
			//페이지 번호.현재 페이지 링크를 제거.
			for (int i = startPage; i <= endPage; i++) {//1~2
				if (i > totalPage) {
					break;
				}
				if (i == currentPage) {
					page.append("<li class='page-item'><a class='page-link' style='border: 0px; color: #0d6efd;'>");
					page.append(i);
					page.append("</a></li>");
				} else {
					page.append("<li class='page-item'>");
					page.append("<a class='page-link' style='border: 0px; color: black;' href='"+pageUrl+"?pageNum=");
					page.append(i);
					page.append(sub_url+"'>");
					page.append(i);
					page.append("</a></li>");
				}
			}
			// 다음 block 페이지
			if (totalPage - startPage >= pageCount) {
				page.append("<li class='page-item'>");
				page.append("<a href='"+pageUrl+"?pageNum="+ (endPage + 1) + sub_url +"' class='page-link' "
								+"aria-label='Next' style='border: 0px; color: black;'>");
				page.append("&raquo;");
				page.append("</a>");
			}
		page.append("</ul></nav>");
	}

	
	
	
	
	public StringBuffer getPage() {
		return page;
	}
	public int getStartRow() {
		return startRow;
	}
	public int getEndRow() {
		return endRow;
	}
}
