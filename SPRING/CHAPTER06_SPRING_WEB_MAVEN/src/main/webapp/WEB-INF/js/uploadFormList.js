// 파일을 읽을 경우, 데이터가 출력되어야하므로 onload function 사용
$(function(){
	$.ajax({
		type: 'post',
		url: '/chapter06_SpringWebMaven/user/getUploadFormAjaxList',
	 	dataType: 'json',
	 	success: function(data){
	 		console.log(JSON.stringify(data));
	 		
	 		$.each(data, function(index, items){
	 			$('<tr/>').append($('<td/>', {
					align: 'center',
					text: items.seq
				})).append($('<td/>', {
					align: 'center',
					// text: items.image
					}).append($('<img>', {
						src: '../storage/' + items.image,
						// 현재 위치 = 현재 파일 위치 = uploadFormList.js
				 		// 이미지는 실제 폴더에 들어있지만, 이미지의 url 작성 시, 가상폴더의 위치를 기준으로 작성
				 		style: 'width: 30px; height: 30px'
					}))
				).append($('<td/>', {
					align: 'left',
					text: items.imageName
				})).appendTo($('#userImgListTable'));
	 			
	 		}); // $.each
	
	 		
	 		/*
	 		1. $('<tr/>').append($('<td/>',{})).append($('<td/>',{})).append($('<td/>',{})).appendTo($('#userImgListTable'));
	 		2. {} 해체
	 		3. 이미지 불러오기 		
	 		<td>
	 			<img src="">
	 		</td>
	 		
	 		*/
	 		
	 		
	 	},
	 	error: function(err){
	 		console.log(err);
	 	}
	 	

	}); // ajax
}); // function