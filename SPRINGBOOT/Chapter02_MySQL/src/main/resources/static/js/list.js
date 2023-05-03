// 파일을 읽을 경우, 데이터가 출력되어야하므로 onload function 사용
$(function(){
	$.ajax({
		type: 'post',
		url: '/user/getUserList',
		// '/' → DispatcherServlet → UserController
		// 시작할 때, "/" 작성 필수, dir 안내
		data: 'pg='+$('#pg').val(),
		// pg값을 보내주므로 userController에서는 해당 data를 받아줄 준비가 되어야 함
		
		dataType: 'json', // 객체는 데이터로 주고받지 못함
		success: function(data){
			console.log(JSON.stringify(data));
			console.log(data.list[0].name);
			/*
			data
			[{"name":"김둘리","id":"둘리","pwd":"123"},
			 {"name":"200","id":"100","pwd":"200"},
			 {"name":"name","id":"id","pwd":"123"},
			 {"name":"네오","id":"neo","pwd":"111"},
			 {"name":"스프링","id":"spring","pwd":"111"},
			 {"name":"12","id":"12","pwd":"12"},
			 {"name":"300","id":"3","pwd":"300"},
			 {"name":"123","id":"123","pwd":"123"}]
			*/
			
			$.each(data.list, function(index, items){ // ajax each 반복문
			/*
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			*/
				$('<tr/>').append($('<td/>', {
					align: 'center',
					text: items.name
				})).append($('<td/>', {
					align: 'center',
					text: items.id
				})).append($('<td/>', {
					align: 'center',
					text: items.pwd
				})).appendTo($('#userListTable'));
				
			}); // each

			// 페이징 처리
			$('#userPagingDiv').html(data.userPaging.pagingHTML);

		},
		
		error: function(err){
			console.log(err);
		}
		
	}); //ajax

});