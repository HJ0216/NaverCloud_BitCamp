// 검색 대상 유무 확인
/*
$('#deleteBtn').click(function(){
// 어떤 버튼이 눌려서 동작하는지 확인
	$('#resultDiv').empty();

	var result = confirm("정말 탈퇴하시겠습니까?");
	if(result){

	$.ajax({
	type: 'post',
	url: '/chapter06_SpringWebMaven/user/delete',
	data: 'id='+$('#searchId').val(),
	// dataType: 'json',
	// null값을 반환할 가능성이 있는 경우, JSON 변환이 불안정해짐
	// dataType을 생략하여 자료에 맞게 자동으로 형식 지정
	success: function(data){
				if(data != 1){
				// null값은 JSON으로 변경 불가능
				// null값을 consolelog로 찍어봐서 같은 값인지 확인
					console.log(JSON.stringify(data));
					$('#resultDiv').text('검색 대상 없음');
					$('#resultDiv').css('color', 'red')
								   .css('font-size', '9pt');
					
				} else {				
				// 아이디가 있을 경우, UserDTO가 JSON으로 바뀌어서 들어옴
					console.log(JSON.stringify(data));
					alert("탈퇴 완료");
					location.href="/chapter06_SpringWebMaven/";			
				}
			},
			error: function(err){
				console.log(err);
			}	 
	
	}); // ajax
	
	} else {return}
	
});
*/


$('#searchBtn').click(function(){
// 어떤 버튼이 눌려서 동작하는지 확인
	$('#resultDiv').empty();

	var result = confirm("정말 탈퇴하시겠습니까?");
	if(result){

	// ajax 내부에 ajax 중첩 사용 가능
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
					console.log(JSON.stringify(data));
					$('#resultDiv').text('검색 대상 없음');
					$('#resultDiv').css('color', 'red')
								   .css('font-size', '9pt');
					
				} else {		
					if(confirm('삭제하시겠습니까?')){
						$.ajax({
							type: 'post',
							url: '/chapter06_SpringWebMaven/user/delete',
							data: 'id='+$('#searchId').val(),
							success: function(){
								alert("회원 탈퇴 완료");
								location.href='/chapter06_SpringWevMaven/user/list';
							},
							error: function(err){
								console.log(err);
							}
						}); // inner ajax
					}
				}
			},
			error: function(err){
				console.log(err);
			}	 
	
	}); // ajax
	
	} else {return}
	
});
