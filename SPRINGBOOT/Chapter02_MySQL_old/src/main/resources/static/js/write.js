$(function(){
	$('#writeBtn').click(function(){
			var formData = new FormData($('#writeForm')[0]);
			// FormData 객체를 생성하고, #writeForm인 폼 요소를 선택하여 FormData 객체에 데이터를 추가
			// FormData 객체를 사용하여 폼 데이터를 구성하면, 파일 업로드와 같은 이진 데이터를 포함한 다양한 유형의 데이터를 전송할 수 있음
			
		$.ajax({
			type: 'post',
			url: '/person/write',
			data: formData,
			enctype: 'multipart/form-data',
			// 폼 데이터가 파일 업로드를 포함하는 경우에 사용되는 인코딩 방식을 설정
			// 파일 업로드와 함께 폼 데이터를 전송하는 데 사용
			processData: false,
			// ajax는 일반적인 데이터 형식을 처리하기 때문에 FormData 객체를 사용하는 경우에는 데이터를 직접 처리해야 함
			// 이 옵션을 설정하여 데이터를 자동으로 처리하지 않도록 지정
			contentType: false,
			// ajax는 컨텐츠 유형을 자동으로 설정하려고 시도하지만, FormData 객체를 사용하는 경우에는 multipart/form-data를 제대로 처리하기 위해 false로 설정해야 함
			success: function(){
				alert('등록 완료');
				location.href='/person/list';
			},
			error: function(err){
				console.log(err);
			}

		}); // ajax

	}); // click

}); // function
