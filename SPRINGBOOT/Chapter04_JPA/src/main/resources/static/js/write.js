// 등록
$('#writeBtn').click(function(){
	$.ajax({
		type: 'post',
		url: '/user/write',
		data: $('#writeForm').serialize(),
		// Serialize 형태
		// 'name=홍길동&id=hong&pwd=111' ▶ Controller.java로 이동
		success: function(){
			alert("등록 완료");
		},
		error: function(error){
			console.log(error);
		}
		
	});
});


// 중복체크
$('#id').focusout(function(){
	$('#idDiv').empty();
	
	if($('#id').val()==''){
		$('#idDiv').text('아이디를 입력해주세요.');
		$('#id').focus();
	} else {
		$.ajax({
			type: 'post',
			url: '/user/isExistId',
			data: 'id=' + $('#id').val(),
			dataType: 'text',
			success: function(data){
				if(data == 'exist'){
					$('#idDiv').text('사용 불가');
					$('#idDiv').css('color', 'tomato');
				} else if(data == 'non_exist') {
					$('#idDiv').text('사용 가능');
					$('#idDiv').css('color', 'blue');	
				}
				
				
			},
			error: function(err){
				console.log(err);
			}
		});
	}
	
});