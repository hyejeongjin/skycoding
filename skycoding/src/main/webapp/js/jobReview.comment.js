$(function(){
	let currentPage;//현재 페이지 번호
	let count;//총 댓글수
	let rowCount;//한 페이지에 있는 페이지 번호 개수 
	
	//댓글 목록
	function selectList(pageNum){
		currentPage = pageNum;
		
		//로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			url:'listComment.do',
			type:'post',
			data:{pageNum:pageNum,rev_id:$('#rev_id').val()},
			dataType:'json',
			success:function(param){
				$('#loading').hide();
				count = param.count;
				rowCount = param.rowCount;
				
				if(pageNum==1){
					//처음 호출시는 목록을 표시하는 div에 내용물 제거
					$('#output').empty();
				}
				$(param.list).each(function(index,item){
					let output = '<div class="item">';
					output += '<h4>' + item.mem_id + '</h4>';
					output += '<div class="sub-item">';
					output += '<p>' + item.com_content + '</p>';
					
					if(item.com_modify_date){
						output += '<span class="modify-date">최근 수정일 : ' + item.com_modify_date + '</span>';
					}else{
						output += '<span class="modify-date">등록일 : ' + item.com_reg_date + '</span>';
					}
					//로그인한 회원번호와 작성자의 회원번호 일치 여부 체크
					if(param.mem_num==item.mem_num){
						                               //커스텀 data- 속성으로 속성 만들기 가능
						                               //data-comnum은 댓글 번호 표시(수정, 삭제시 댓글번호를 쉽게 읽어옴)
						output += ' <input type="button" data-comnum="'+item.com_id+'" value="수정" class="modify-btn">';
						output += ' <input type="button" data-comnum="'+item.com_id+'" value="삭제" class="delete-btn">';
					}
					output += '<hr size="1" noshade width="100%">';
					output += '</div></div>';
				
					$('#output').append(output);
				});//end of each
				
				//페이지버튼 처리
				if(currentPage >= Math.ceil(count/rowCount)){//총 페이지 개수
					//다음 페이지가 없음
					$('.paging-button').hide();
				}else{
					//다음페이지가 존재
					$('.paging-button').show();
				}
				
			},
			error(){
				$('#loading').hide();
				alert('네트워크 오류 발생');
			}
		});
	}
	
	//페이지 처리 이벤트 연결(다음 댓글 보기 클릭시 데이터 추가) 
	$('.paging-button').click(function(){
		selectList(currentPage + 1);
	});
	
	
	
	
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