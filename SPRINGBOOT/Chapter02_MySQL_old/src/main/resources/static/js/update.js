// 화면 숨기기
$(document).ready(function(){
	$('#updateDiv').hide();
});


// 검색 대상 유무 확인
$('#searchBtn').click(function(){
// 어떤 버튼이 눌려서 동작하는지 확인
	$('#resultDiv').empty();

	$.ajax({
	type: 'post',
	url: '/user/getUser',
	data: 'id='+$('#searchId').val(),
	success: function(data){
				if(data == ''){
					$('#resultDiv').text('검색 대상 없음');
					$('#resultDiv').css('color', 'red')
								   .css('font-size', '9pt');
					$('#updateDiv').hide();
					
				} else {
					console.log(JSON.stringify(data));
				
					$('#updateDiv').show();
					$('#name').val(data.name);
					// tag 별 속성을 모를 때는 text or val 입력
					$('#id').val(data.id);
					$('#pwd').val(data.pwd);
					
				}
			},
			error: function(err){
				console.log(err);
			}	 
	
	});
});


// 수정버튼
$('#updateBtn').click(function(){
	$('#nameDiv').empty();
	$('#pwdDiv').empty();
	
	if($('#name').val==''){
		$('#nameDiv').text('이름 입력');
		$('#name').focus();
	} else if($('#pwd').val()=='') {
		$('#pwdDiv').text('비밀번호 입력');
		$('#pwd').focus();		
	}

	$.ajax({
		type: 'post',
		url: '/user/update',
		data: $('#updateForm').serialize(),
		success: function(){
			alert("수정 완료");
			location.href="/user/list";						
		},
		error: function(err){
			console.log(err);
		}
		
	});
});



// 리셋 버튼
/*
$('#resetBtn').click(function(){
	$.ajax({
	type: 'post',
	url: '/user/getUser',
	data: 'id='+$('#searchId').val(),
	success: function(data){
				if(data == ''){
					$('#resultDiv').text('검색 대상 없음');
					$('#resultDiv').css('color', 'red')
								   .css('font-size', '9pt');
					$('#updateDiv').hide();
					
				} else {
					console.log(JSON.stringify(data));
				
					$('#updateDiv').show();
					$('#name').val(data.name);
					$('#id').val(data.id);
					$('#pwd').val(data.pwd);
					
				}
			},
			error: function(err){
				console.log(err);
			}	 
	
	});

});
*/


// Trigger
// 검색 버튼을 누른것과 취소 버튼을 누른 것이 같은 효과가 나타나도록 조정
$('#resetBtn').click(function(){
	$('#searchBtn').trigger('click'); // searchBtn 클릭으로 연결
});