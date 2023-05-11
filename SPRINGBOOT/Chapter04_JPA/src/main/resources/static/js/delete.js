// 삭제 아이디 조회 후 창 숨기기
$(function(){
	$('#deleteForm').hide();
});


// 리셋버튼 클릭
$('#cancelBtn').click(function(){
	$('#searchBtn').trigger('click');
}); // click



// 삭제할 아이디 검색
$('#searchBtn').click(function(){
	// 초기화
	$('#resultDiv').empty();
	$('#deleteForm').hide();	
	
	$.ajax({
		type: 'post',
		url: '/user/getUserInfo',
		data: 'searchId=' + $('#searchId').val(),
		// Controller에서 받는 변수명과 일치
		// dataType: 'json',
		// dataType 종류: 'text', 'html', 'xml', 'json'
		// getUserInfo에서 JSON 반환 유무가 불확실할 때, dataType을 생략해서 자동으로 자료에 맞는 형식으로 지정
		success: function(data){
			console.log(JSON.stringify(data));
			if(data==null){
			// Optional<UserDTO> 값이 있으면 JSON이 return되고 없으면 null이 반환됨
				/*$('#resultDiv').text('검색 결과가 없습니다.')
							   .css('color', 'tomato')
							   .css('font-weight', 'bold');*/
				$('#resultDiv').text('검색 결과가 없습니다.')
							   .css({
								  'color': 'tomato',
								  'font-weight': 'bold'
								});
							   
							   
			} else {
				$('#deleteForm').show();
				$('#name').val(data.name);
				$('#id').val(data.id);
				// $('#pwd').val(data.pwd);
				// 삭제 전 비밀번호 재확인
								
				$('#deleteBtn').click(function(){
					if(confirm('정말로 삭제하시겠습니까?')){

					$.ajax({
						type: 'post',
						url: '/user/delete',
						data: 'id=' + $('#id').val(),
						success: function(){
							alert("삭제 완료");
							location.href="/user/listForm"
						},
						error: function(err){
							console.log(err);
						}

					});
					
					}

				}); // click
					
			}
			
			
		},
		error: function(err){
			console.log(err);
		}


	}); // ajax
	
	
}); // click 이벤트