$(function(){
	let currentPage;//현재 페이지 번호
	let count;//총 댓글수
	let rowCount;//한 페이지에 있는 페이지 번호 개수 
	
	//댓글 목록
	function selectList(pageNum){
		
	}
	
	//페이지 처리 이벤트 연결(다음 댓글 보기 클릭시 데이터 추가) 
	
	//댓글 등록
	$('#com_form').submit(function(event){
		event.preventDefault();
		
		if($('#com_content').val().trim()==''){
			alert('내용을 입력하세요');
			$('#com_content').val().focus();
			return false;
		}
		//form 이하에 입력한 데이터를 모두 읽어옴
		let form_data = $(this).serialize();
		
		//댓글 등록
		$.ajax({
			url:'writeComment.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result=='logout'){
					alert('로그인해야 작성할 수 있습니다');
				}else if(param.result=='success'){
					//폼 초기화
					initForm();
					//댓글 작성을 성공하면 새로 삽입한 댓글을 포함해서
					//첫번째 페이지의 댓글을 다시 호출함
				}else{
					alert('댓글 등록 오류 발생');
				}
			},
			error(){
				alert('네트워크 오류');
			}
		});
	});
	
	//댓글 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#com_first').text('300 / 300');
	}
	
	//textarea에 내용 입력시 글자수 태그
	//미래에 생길 수정 textarea 태그에는 $(document).on(); 사용
	$(document).on('keyup','textarea',function(){
		//입력한 글자수 구함
		let inputLength = $(this).val().length;
		if(inputLength > 300){//300자 이상인 경우
			//넘는 글자를 강제로 자름
			$(this).val($(this).val().substring(0,300))
		}else{//300자 이하인 경우
			let remain = 300 - inputLength;	
			remain += '/ 300';
			//이벤트가 발생한 곳이 등록폼인지 수정폼인지 체크
			//              문자열로 표기
			if($(this).attr('id')=='com_content'){
				//등록폼 글자수
				$('#com_first').text(remain);
			}else{
				$('#mcom_first').text(remain);
			}
		}
	});
	
	
	//댓글 수정 버튼 클릭시 수정폼 초기화


	//수정폼에서 취소 버튼 클릭시 수정폼 초기화

	//댓글 수정폼 초기화
	
	//댓글 수정
	
	//댓글 삭제
	
	//초기 데이터(목록) 호출
	selectList(1);
});