$('#writeBtn').click(function(){
	$('#nameDiv').empty();
	$('#idDiv').empty();
	$('#pwdDiv').empty();

	if($('#name').val() == ''){
		$('#nameDiv').text('Enter the name'); // 문자 입력 시, "" or '' 유의
		$('#nameDiv').css('color', 'dodgerblue');
		return false;
	}

	if($('#id').val() == ''){
		$('#idDiv').text('Enter the id'); // 문자 입력 시, "" or '' 유의
		$('#idDiv').css('color', 'dodgerblue');
		return false;
	}

	if($('#pwd').val() == ''){
		$('#pwdDiv').text('Enter the Pwd'); // 문자 입력 시, "" or '' 유의
		$('#pwdDiv').css('color', 'dodgerblue');
		return false;
	}
	
});