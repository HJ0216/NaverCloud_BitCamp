// boardList.js 참조
$(document).ready(function(){
	$.ajax({
		type: 'post',
		url: '/miniProject_jQuery/board/getBoard.do',
		data: 'seq=' + $('#seq').val(),		
			   // 글 조회에 pg값 필요 X > seq만 보내면 됨
			   // {'seq': $('#seq').val(),
			   //    pg': $('#pg').val()},
		dataType: 'json',
		success: function(data){
			console.log(JSON.stringify(data));

				$('#subjectSpan').text(data.subject);
				$('#seqSpan').text(data.seq);
				$('#idSpan').text(data.id);
				$('#hitSpan').text(data.hit);
				$('#contentSpan').text(data.content);
				// span 속성은 value가 없으므로 text 사용

				// 본인 글 확인
				if($('#memId').val()==data.id){
					$('#boardViewSpan').show();
				} else {
					$('#boardViewSpan').hide();
				}
						
		},
		error: function(err){
			console.log(err);
		}
	});
});




// 글 수정 폼
$('#boardUpdateFormBtn').click(function(){
	$('#boardViewForm').attr('action', '/miniProject_jQuery/board/boardUpdateForm.do');
	// seq, pg 전달
	$('#boardViewForm').submit();
	// submit: id 속성은 가져갈 수 없고 name 속성만 form을 통해 데이터 이동 가능

});


// 글 삭제 폼
$('#boardDeleteFormBtn').click(function(){
	$('#boardViewForm').attr('action', '/miniProject_jQuery/board/boardDeleteForm.do');
	$('#boardViewForm').submit();
	// submit: id 속성은 가져갈 수 없고 name 속성만 form을 통해 데이터 이동 가능

});



// 답글 작성 폼
$('#boardReplyFormBtn').click(function(){
	$('#boardViewForm').attr('action', '/miniProject_jQuery/board/boardReplyForm.do');
	// action이 채워져 있지 않으므로 동적으로 채워줄 수 있음
	$('#boardViewForm').submit();
	// submit: id 속성은 가져갈 수 없고 name 속성만 form을 통해 데이터 이동 가능
	// seq, pg 전달
});




