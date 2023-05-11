$(function(){
	$('#updateForm').hide();
});



$('#searchBtn').click(function(){
	// 초기화
	$('#resultDiv').empty();
	$('#updateForm').hide();	
	
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
			// Optional<UserDTO> 값이 있으면 DTO 객체가 JSON으로 return되고
			// 값이 없으면, 즉 Optional.empty일 경우에는 JSON null이 반환됨
				/*$('#resultDiv').text('검색 결과가 없습니다.')
							   .css('color', 'tomato')
							   .css('font-weight', 'bold');*/
				$('#resultDiv').text('검색 결과가 없습니다.')
							   .css({
								  'color': 'tomato',
								  'font-weight': 'bold'
								});
							   
			} else {
				$('#updateForm').show();
				$('#name').val(data.name);
				$('#id').val(data.id);
				$('#pwd').val(data.pwd);
				
				
				// 버튼을 동적으로 생성할 경우, 2중 ajax 처리 또는 jQuery lib on() 사용
				// $('#button').on('click', function() {});
				$('#updateBtn').click(function(){
					$.ajax({
						type: 'post',
						url: '/user/write',
						// JpaRepository save(): insert 및 update 모두 진행하므로 메서드 재사용
						data: $('#updateForm').serialize(),
						success: function(){
							alert("수정 완료");
							location.href="/user/listForm"
						},
						error: function(err){
							console.log(err);
						}

					});
				}); // click
				
				
				// ajax 밖에서 따로 선언 가능
				$('#cancelBtn').click(function(){
					$('#searchBtn').trigger('click');
				}); // click
				
				
			}
			
			
		},
		error: function(err){
			console.log(err);
		}


	}); // ajax
	
	
}); // click 이벤트