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
	url: '/chapter06_SpringWebMaven/user/getUser',
	data: 'id='+$('#searchId').val(),
	// dataType: 'json',
	// null값을 반환할 가능성이 있는 경우, JSON 변환이 불안정해짐
	// dataType을 생략하여 자료에 맞게 자동으로 형식 지정
	success: function(data){
				if(data == ''){
				// null값은 JSON으로 변경 불가능
				// null값을 consolelog로 찍어봐서 같은 값인지 확인
					$('#resultDiv').text('검색 대상 없음');
					$('#resultDiv').css('color', 'red')
								   .css('font-size', '9pt');
					$('#updateDiv').hide();
					
				} else {
				// 아이디가 있을 경우, UserDTO가 JSON으로 바뀌어서 들어옴
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
	$.ajax({
		type: 'post',
		url: '/chapter06_SpringWebMaven/user/update',
		data: $('#updateForm').serialize(),
		success: function(){
			alert("수정 완료");
			location.href="/chapter06_SpringWebMaven/user/list";			
		},
		error: function(err){
			console.log(err);
		}
		
	});
});



// 리셋 버튼
$('#resetBtn').click(function(){
	$.ajax({
	type: 'post',
	url: '/chapter06_SpringWebMaven/user/getUser',
	data: 'id='+$('#searchId').val(),
	// dataType: 'json',
	// null값을 반환할 가능성이 있는 경우, JSON 변환이 불안정해짐
	// dataType을 생략하여 자료에 맞게 자동으로 형식 지정
	success: function(data){
				if(data == ''){
				// null값은 JSON으로 변경 불가능
				// null값을 consolelog로 찍어봐서 같은 값인지 확인
					$('#resultDiv').text('검색 대상 없음');
					$('#resultDiv').css('color', 'red')
								   .css('font-size', '9pt');
					$('#updateDiv').hide();
					
				} else {
				// 아이디가 있을 경우, UserDTO가 JSON으로 바뀌어서 들어옴
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