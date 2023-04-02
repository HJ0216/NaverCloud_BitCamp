// boardView.js 참조
$('#boardWriteBtn').click(function(){
	$('#subjectDiv').empty();
	// document.getElementById("subjectDiv").innerText = "";
	$('#contentDiv').empty();
	// document.getElementById("contentDiv").innerText = "";

	if($('#subject').val() == ''){
	// if(document.boardWriteForm.subject.value=="") {
		$('#subjectDiv').text('제목 입력');
		// document.getElementById("subjectDiv").innerText="Enter the Subject";
		$('#subject').focus();
		// document.boardWriteForm.subject.focus();
	} else if($('#content').val() == ''){
		$('#contentDiv').text('내용 입력');
		$('#content').focus();
	} else{
		console.log($('#subject').val());
		console.log($('#content').val());
		
		$.ajax({ // ajax를 사용하여 화면이동 없이 해당 페이지 내에서만 이동
		// document.boardWriteForm.submit();
		
			type: 'post',

			url: '/miniProject_jQuery/board/write.do',
			
			data: {'subject': $('#subject').val(),
				   'content': $('#content').val()},
					
			/* 
			// serialize 방식
			data: $('#boardWriteForm').serialize(),
			
			// 문자열 입력 방식
			data: 'subject=' + $('#subject').val() + '&content=' + $('#content').val()
				+ '&name=' + $('#name').val() + '&id=' + $('#id').val() + '&email=' + $('#email').val(),

			// JSON 방식
			data: {'subject': $('#subject').val(), 'content': $('#content').val()},

			*/
			
			// dataType: 'text', 받는 데이터가 없을 때는 dataType 삭제
			
			success: function() {
				alert('글작성 완료');
				location.href='/miniProject_jQuery/board/boardList.do?pg=1';
			},
			
			error: function(err) {
				console.log(err);
			}
		});
	}
});

