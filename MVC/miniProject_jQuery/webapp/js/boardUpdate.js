// boardView.js 참조
$(document).ready(function(){
	$.ajax({
		type: 'post',
		url: '/miniProject_jQuery/board/boardUpdate.do',
		data: 'seq=' + $('#seq').val(),		
		dataType: 'json',
		success: function(data){
			console.log(JSON.stringify(data));
			
			$('#subject').val(data.subject);
			// input type=text: value
			$('#content').val(data.content);
		},
		error: function(err){
			console.log(err);
		}
	});
});


// 글 수정
$('#boardReWriteBtn').click(function(){
	$('#subjectDiv').empty();
	// document.getElementById("subjectDiv").innerText = "";
	$('#contentDiv').empty();
	// document.getElementById("contentDiv").innerText = "";

	if($('#subject').val() == ''){
	// if(document.boardWriteForm.subject.value=="")
		$('#subjectDiv').text('제목 입력');
		// document.getElementById("subjectDiv").innerText="Enter the Subject";
		$('#subject').focus();
		// document.boardWriteForm.subject.focus();
	} else if($('#content').val() == ''){
		$('#contentDiv').text('내용 입력');
		$('#content').focus();
	} else{		
		$.ajax({
		// document.boardWriteForm.submit();
		
			type: 'post',

			url: '/miniProject_jQuery/board/boardReWrite.do',
			
			data: $('#boardUpdateForm').serialize(),					
			// subject, content, seq, pg
			
			/* 
			// serialize 방식
			data: $('#boardWriteForm').serialize(),
			
			// 문자열 입력 방식
			data: 'subject=' + $('#subject').val() + '&content=' + $('#content').val(),

			// JSON 방식
			data: {'subject': $('#subject').val(), 'content': $('#content').val()},
			*/
			
			// dataType: 'text', 받는 데이터가 없을 때는 dataType 삭제
			
			success: function() {
				alert('글수정 완료');
				location.href='/miniProject_jQuery/board/boardList.do?pg='+$('#pg').val();
				// From BoardUpdateFormService.java setAttrbute
				// location.href='/miniProject_jQuery/board/boardList.do?pg='+${requestScope.pg};
				// location.href='/miniProject_jQuery/board/boardList.do?pg='+${pg};
				// location.href='/miniProject_jQuery/board/boardList.do?pg=${pg}'; ◀ el tag '' 안으로 가능
				// el tag: jsp 파일에서만 가능(jsp 파일 내 script 선언 구간도 가능)
				// 단, 순수 .js 파일에서는 불가능

			},
			
			error: function(err) {
				console.log(err);
			}
		});
	}

});


// 글 다시 작성
$('#boardResetBtn').click(function(){
	$.ajax({
		type: 'post',
		url: '/miniProject_jQuery/board/boardUpdate.do',
		data: 'seq=' + $('#seq').val(),		
		// .jsp에서 직접 작성 시, 'seq=' + ${seq} el tag 사용 가능
		dataType: 'json',
		success: function(data){
			console.log(JSON.stringify(data));
			
			$('#subject').val(data.subject);
			// input type=text: value
			$('#content').val(data.content);
			// textarea: value
			// span#content: span tag에는 value attr은 있지만, text attr은 없으므로 작동하지 X
		},
		error: function(err){
			console.log(err);
		}
	});	

});
