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
				// location.href='/miniProject_jQuery/board/boardList.do?pg='+${pg};

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
		dataType: 'json',
		success: function(data){
			console.log(JSON.stringify(data));
			
			$('#subject').val(data.subject);
			// input type=text: value
			$('#content').val(data.content);
			// textarea: value
			// text로 선언할 때 작동하지 않는 이유?????????????
		},
		error: function(err){
			console.log(err);
		}
	});	

});
