$(function(){
	let currentPage; //현재 보고 있는 페이지
	let count; //총 카운트
	let rowCount; //한 페이지에 몇 개씩 보여지는지
	
	//댓글 목록
	function selectList(pageNum){
		currentPage = pageNum; //pageNum을 넘겨주면 페이지에 맞게 데이터를 읽어옴
		
		//로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			url:'listReply.do',
			type:'post',
			data:{pageNum:pageNum,qna_id:$('#qna_id').val()}, //ajax로 읽어올 정보(pageNum값, board_num값)
			dataType:'json',
			success:function(param){
				//로딩 이미지 감추기
				$('#loading').hide();
				count = param.count;
				rowCount = param.rowCount;
				
				if(pageNum == 1){
					//처음 호출시(1페이지)에는 목록을 표시하는 div의 내부 내용물 제거
					//1페이지가 아니면 전페이지 뒤에 붙여서 보이게
					$('#output').empty();
				}
				
				$(param.list).each(function(index,item){
					let output = '<div class="item">';
					output += '<h4>' + item.mem_id + '</h4>';
					output += '<div class="sub-item">';
					output += '<p>' + item.qnaComm_content + '</p>';
					
					if(item.qnaComm_modify_date){ //수정한 경우
						output += '<span class="modify-date">최근 수정일 : ' + item.qnaComm_modify_date + '</span>';
					}else{ //수정 안 한 경우
						output += '<span class="modify-date">등록일 : ' + item.qnaComm_reg_date + '</span>';
					}
					
					//로그인한 회원번호와 작성자의 회원번호 일치 여부 체크
					if(param.user_num == item.mem_num){
						//로그인한 회원번호와 작성자의 회원번호 일치(어차피 일치할 때만 보여지게 설정할 거라 else는 작성 안 함)
						output += ' <input type="button" data-renum="'+item.qnaComm_id+'" value="수정" class="modify-btn">';
						output += ' <input type="button" data-renum="'+item.qnaComm_id+'" value="삭제" class="delete-btn">';
					}
					
					output += '<hr size="1" noshade width="100%">';
					output += '</div>';
					output += '</div>';
					
					//문서 객체에 추가
					$('#output').append(output);
				});//end of each
				
				//page button 처리
				if(currentPage >= Math.ceil(count/rowCount)){
					//다음 페이지가 없음
					$('.paging-button').hide();
				}else{
					//다음 페이지가 존재
					$('.paging-button').show();
				}
			},
			error:function(){
				$('#loading').hide(); //오류가 나면 로딩이미지 감추기
				alert('네트워크 오류 발생');	
			}
		});
	}
	
	
	//페이지 처리 이벤트 연결(다음 댓글 보기 버튼 클릭시 데이터 추가)
	$('.paging-button input').click(function(){
		selectList(currentPage + 1); //페이지만 하나 증가해서 다음페이지 보여지게 하면 됨
	});
	
	//댓글 등록
	$('#re_form').submit(function(event){
		//주소(detail.do) 바뀌면 안 돼서 기본 이벤트 제거
		event.preventDefault();
		
		if($('#qnaComm_content').val().trim()==''){
			alert('내용을 입력하세요!');
			$('#qnaComm_content').val('').focus();
			return false;
		}
		
		//form 이하의 태그에 입력한 데이터를 모두 읽어 옴
		//this는 textarea를 감싸고 있는 form
		//serialize는 parameter name과 value의 쌍으로 모두 읽어옴
		let form_data = $(this).serialize();
		
		//댓글 등록
		$.ajax({
			url:'writeReply.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(param.result == 'success'){
					//폼 초기화
					initForm();
					//댓글 작성이 성공하면 새로 삽입한 글을 포함해서 첫번째 페이지의 게시글을 다시 호출
					selectList(1);
				}else{
					alert('댓글 등록 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류');
			}
		});
	});
	
	//댓글 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#re_first .letter-count').text('300/300');
	}	

	//textarea에 내용 입력시 글자수 체크
	$(document).on('keyup','textarea',function(){ //미래의 태그는 $(document).on(이벤트,태그,이벤트내용순) 명시
		//입력한 글자수 구함
		let inputLength = $(this).val().length;
		
		if(inputLength > 300){ //300를 넘어선 경우
			$(this).val($(this).val().substring(0,300)); //300자까지만 남기고 나머지는 잘라냄. 남긴 건 다시 textarea에 셋팅
		}else{ //300자 이하인 경우
			let remain = 300 - inputLength;
			remain += '/300';
			if($(this).attr('id') == 'qnaComm_content'){
				//등록폼 글자수
				$('#re_first .letter-count').text(remain);
			}else{
				//수정폼 글자수
				$('#mre_first .letter-count').text(remain);
			}
			
		}
	});
	
	//댓글 수정 버튼 클릭시 수정폼 노출(미래의 태그)
	$(document).on('click','.modify-btn',function(){
		//댓글 번호
		let qnaComm_id = $(this).attr('data-renum');
		//댓글 내용		this는 수정버튼						//g:지정문자열 모두, i:대소문자 구별없이 검색
		let qnaComm_content = $(this).parent().find('p').html().replace(/<br>/gi,'\n');
		
		//댓글 수정폼 UI(동적으로 만듦) //아래에서 .item에 붙일 건데 이게 있어야 할 자리에 수정폼을 보여지게 할 거라서 이건 숨김처리 할 거다
		let modifyUI = '<form id="mre_form">';
			modifyUI += '<input type="hidden" name="qnaComm_id" id="qnaComm_id" value="'+qnaComm_id+'">';
			modifyUI += '<textarea rows="3" cols="50" name="qnaComm_content" id="mre_content" class="rep-content">'+qnaComm_content+'</textarea>';
			modifyUI += '<div id="mre_first"><span class="letter-count">300/300</span></div>';
			modifyUI += '<div id="mre_second" class="align-right">';
			modifyUI += ' <input type="submit" value="수정">';
			modifyUI += ' <input type="button" value="취소" class="re-reset">';
			modifyUI += '</div>';
			modifyUI += '<hr size="1" noshade width="96%">';
			modifyUI += '</form>';
			
			//이전에 이미 수정 중인(수정폼이 활성화된) 댓글이 있을 경우 수정버튼을 클릭하면 숨긴 sub-item(153라인 참고)을 환원시키고 수정폼을 초기화함
			initModifyForm();
			
			//지금 클릭해서 수정하고자 하는 데이터는 감추기
			//수정버튼(this(직계부모))을 감싸고 있는 div
			$(this).parent().hide();
			
			//수정폼을 수정하고자 하는 데이터가 있는 div에 노출
				//부모들 중에서라서 parents. 29라인 보면 item 이랑 sub-item이 있는데 얘네가 부모들이다
			$(this).parents('.item').append(modifyUI);
			
			//입력한 글자수 셋팅
			let inputLength = $('#mre_content').val().length;
			let remain = 300 - inputLength;
			remain += '/300';
			
				//문서 객체에 반영
			$('#mre_first .letter-count').text(remain);
	});
	
	//수정폼에서 취소 버튼 클릭시 수정폼 초기화
	$(document).on('click','.re-reset',function(){
		initModifyForm();
	});
	
	//댓글 수정폼 초기화
	function initModifyForm(){
		$('.sub-item').show();
		$('#mre_form').remove();
	}
	
	//댓글 수정(미래의 태그)
	$(document).on('submit','#mre_form',function(event){
		//기본 이벤트 제거
		event.preventDefault();
		
		if($('#mre_content').val().trim()==''){
			alert('내용을 입력하세요');
			$('#mre_content').val('').focus();
			return false;
		}
		
		//폼에 입력한 데이터 반환
		let form_data = $(this).serialize();
		
		//서버와 통신
		$.ajax({
			url:'updateReply.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 수정할 수 있습니다.');
				}else if(param.result == 'success'){ //수정폼에 작성한 내용을 그대로 읽어오기(굳이 데이터에서 읽어올 필요 없음)					
																		//태그 무력화시키고 \n을 <br>로 바꾸기 위해
					$('#mre_form').parent().find('p').html($('#mre_content').val().replace(/</g,'&lt;')
																				  .replace(/>/g,'&gt')
																				  .replace(/\n/g,'<br>'));
					//댓글은 수정일 안 쓰고 등록일만 쓰기로 해서 주석처리
					//$('#mre_form').parent().find('.reg-date').text('최근 수정일 : 5초 미만');
					
					//수정폼 삭제 및 초기화
					initModifyForm();
				}else if(param.result == 'wrongAccess'){
					alert('타인의 글을 수정할 수 없습니다');
				}else{
					alert('댓글 수정 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생')
			}
		});
	});
	
	//댓글 삭제
	$(document).on('click','.delete-btn',function(){
		//댓글 번호
		let qnaComm_id = $(this).attr('data-renum');
		
		//서버와 통신
		$.ajax({
			url:'deleteReply.do',
			type:'post',
			data:{qnaComm_id:qnaComm_id},
			dataType:'json',
			success:function(param){
				if(param.result=='logout'){
					alert('로그인해야 삭제할 수 있습니다.');
				}else if(param.result=='success'){
					alert('삭제 완료!!');
					selectList(1); //1페이지 읽어옴
				}else if(param.result=='wrongAccess'){
					alert('타인의 글을 삭제할 수 없습니다.');
				}else{
					alert('댓글 삭제 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		
	});
	
	
	
	//초기 데이터(목록) 호출
	selectList(1); //처음에는 첫페이지가 보여져야 하니까 1이라고 명시
});