// 회원가입 유효성 검사
$('#writeBtn').click(function(){
	$('#nameDiv').empty();
	$('#idDiv').empty();
	$('#pwdDiv').empty();

	if($('#name').val() == ''){
		$('#nameDiv').text('Enter the name'); // 문자 입력 시, "" or '' 유의
		$('#nameDiv').css('color', 'dodgerblue');
		$('#name').focus();
		return false;
	}

	if($('#id').val() == ''){
		$('#idDiv').text('Enter the id'); // 문자 입력 시, "" or '' 유의
		$('#idDiv').css('color', 'dodgerblue');
		$('#id').focus();
		return false;
	}

	if($('#pwd').val() == ''){
		$('#pwdDiv').text('Enter the Pwd'); // 문자 입력 시, "" or '' 유의
		$('#pwdDiv').css('color', 'dodgerblue');
		$('#pwd').focus();
		return false;
	}
	
	else {
		$.ajax({
			type: 'post',
			url: '/user/write',
			data: $('#writeForm').serialize(),
			success: function(){
				alert("회원가입을 축하합니다!");
				location.href="/user/list";
			},
			error: function(err){
				console.log(err);
			}
		});
	}
	
});


// 아이디 중복체크
$('#id').focusout(function(){
	if($('#id').val() == ''){
		$('#idDiv').text('Enter the name'); // 문자 입력 시, "" or '' 유의
		$('#idDiv').css('color', 'dodgerblue');
		$('#id').focus();
		return false;
	}

	else {
		$.ajax({
			type: 'post',
			url: '/user/isExistId',
			data: 'id='+$('#id').val(),
			dataType: "text",
			// 서버로부터 받는 데이터(text, html, xml, json) 자료형
			success: function(data){
				data = data.trim();
				
				if(data == 'Exist'){
					$('#idDiv').text('사용 불가능');
					$('#idDiv').css('color', 'red');
					
				} else if(data == 'Non_Exist') {
					$('#idDiv').text('사용 가능');
					$('#idDiv').css('color', 'blue');
					$('#idDuplication').val($('#id').val());
					$('#pwd').focus();
				}
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});
