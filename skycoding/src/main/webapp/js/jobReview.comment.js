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
						output += '<div class="comment-btn">';
						output += ' <input type="button" data-comnum="'+item.com_id+'" value="수정" class="modify-btn align-right btn btn-secondary btn-sm">';
						output += ' <input type="button" data-comnum="'+item.com_id+'" value="삭제" class="delete-btn align-right btn btn-secondary btn-sm" id="delete_btn">';
						output += '</div>';
					}
					output += '</div></div>';
					output += '<hr size="1" noshade width="100%">';
				
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
		});//end of ajax
	}
	
	//페이지 처리 이벤트 연결(다음 댓글 보기 클릭시 데이터 추가) 
	$('.paging-button').click(function(){
		selectList(currentPage + 1);
	});
	
	
	
	
	//댓글 등록
	$('#com_form').submit(function(event){
		//기본이벤트 제거
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
					selectList(1);
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
	
								
	
	//댓글 수정 버튼 클릭시 수정폼 노출
	$(document).on('click','.modify-btn',function(){
		//댓글번호
		let com_id = $(this).attr('data-comnum');	
		//댓글 내용
		let com_content = $(this).parents('.sub-item').find('p').html().replace(/<br>/gi,'\n');	
		                                                                      //g:지정문자열 모두, i:대소문자 무시
		//댓글 수정폼 UI
		let modifyUI = '<form id="mcom_form">';
		modifyUI += '<div id="com_title">';
		modifyUI += '<span class="letter-count" id="mcom_first">300 / 300</span>';
		modifyUI += '<input type="hidden" name="com_id" id="com_id" value="'+com_id+'">';
		modifyUI += '</div>';//end of com_title
		modifyUI += '<div class="inner-text">';
		modifyUI += '<textarea rows="3" cols="50" name="com_content" id="mcom_content" class="com-content form-control inner-text">'+com_content+'</textarea>';
		modifyUI += '<div class="comment-btn">';
		modifyUI += ' <input type="submit" value="수정" class="btn btn-secondary btn-sm align-right">';
		modifyUI += ' <input type="button" value="취소" class="btn btn-secondary btn-sm align-right com-reset">';
		modifyUI += '</div>';//end of comment-btn
		modifyUI += '</div>';//end of inner-test
		modifyUI += '</form>';
		
		//이전에 이미 수정한 댓글이 있을 경우 수정버튼을 클릭하면 숨김. sub-item버튼을 환원시키고 수정폼을 초기화함
		initModifyForm();
		
		//지금 수정해서 클릭하고자 하는 데이터는 감추기
		//수정버튼을 감싸고 있는 div
		$(this).parents('.sub-item').hide();//잠깐 안보이게 숨기기
		//수정폼을 수정하고자 하는 데이터가 있는 div에 노출  
		$(this).parents('.item').append(modifyUI); 
		
		//수정폼을 가져올 때 입력한 글자수 셋팅
		let inputLength = $('#mcom_content').val().length;
		let remain = 300 - inputLength;
		remain += ' / 300';
		//문서 객체에 반영
		$('#mcom_first').text(remain);                                                      
	});


	//수정폼에서 취소 버튼 클릭시 수정폼 초기화
	$(document).on('click','.com-reset',function(){
		initModifyForm();
	});
	
	//댓글 수정폼 초기화
	function initModifyForm(){
		$('.sub-item').show();
		$('#mcom_form').remove();		
	}
	
	//댓글 수정
	$(document).on('submit','#mcom_form',function(event){
		//기본이벤트 제거
		event.preventDefault();
		
		if($('#mcom_content').val().trim()==''){
			alert('내용을 입력하세요');
			$('#mcom_content').val('').focus();
			return false;
		}
		//폼 이하의 데이터를 반환
		let form_data = $(this).serialize();
		
		//서버와 통신
		$.ajax({
			url:'updateComment.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 수정할 수 있습니다.');
				}else if(param.result == 'success'){
					$('#mcom_form').parent().find('p').html($('#mcom_content').val()
								.replace(/</g,'&lt;').replace(/>/g,'&gt;')
								.replace(/\n/g,'<br>'));
							
					
					//수정폼 삭제 및 초기화
					initModifyForm();
				}else if(param.result == 'wrongAccess'){
					alert('타인의 글을 수정할 수 없습니다.');
				}else{
					alert('댓글 수정 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	
	//댓글 삭제
	$(document).on('click','#delete_btn',function(){
		//댓글 번호
		let com_id = $(this).attr('data-comnum');
		
		//서버와 통신
		$.ajax({
			url:'deleteComment.do',
			type:'post',
			data:{com_id:com_id},
			dataType:'json',
			success:function(param){
				if(param.result=='logout'){
					alert('로그인해야 삭제할 수 있습니다');
				}else if(param.result=='success'){
					alert('삭제 완료');
					selectList(1);
				}else if(param.result=='wrongAccess'){
					alert('타인의 글을 삭제할 수 없습니다.');
				}else{
					alert('댓글 삭제 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 통신 오류');
			}
		});
		
	});


	
	//초기 데이터(목록) 호출
	selectList(1);
});