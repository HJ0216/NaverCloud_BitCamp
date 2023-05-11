// 목록 로드
$(function(){
	$.ajax({
		type: 'post',
		url: '/user/list',
		dataType: 'json', // 생략가능, @ResponseBody가 DTO ▶ JSON으로 변경
		success: function(data){
			console.log(JSON.stringify(data));
			
			/* 표현법_1
			
			$.each(data, function(index, item){ // ajax each 반복문
			// data 자체가 list인 경우, 자료값에 list를 따로 기재하지 않음
			// data = [{"name":"홍길동","id":"hong","pwd":"111"}]

			var result = `<tr>`
					 + `<td>` + item.name + `</td>`
					 + `<td>` + item.id + `</td>`
					 + `<td>` + item.pwd + `</td>`
					 + `</tr>`;
			
			$('#listFormTable').append(result);
				
			}); // each
			*/
			
						
			for(var i=0; i<data.length; i++){
				var result = 
				`
				<tr>
					<td align="center">${data[i].name}</td>
					<td align="center">${data[i].id}</td>
					<td align="center">${data[i].pwd}</td>
				</tr>
				`;
				
				$('#listFormTable').append(result);
				// append: 'listFormTable'에 마지막 자식으로 새로운 요소(<tr>, <td>)를 추가
			}
			
			
		},
		error: function(error){
			console.log(error);
		}		
		
	}); // ajax
	
}); // onload



// 검색
$('#searchBtn').click(function(){
		$('#listForm #keywordDiv').remove(); // 검색어가 입력되면 요소를 삭제

	if($('#keyword').val()==''){
		$('#searchForm').append('<div id="keywordDiv">검색어를 입력해주세요.</div>');
		// alert("검색어를 입력해주세요.");
	} else {
		$.ajax({
			type: 'post',
			url: '/user/search',
			data: {'searchOpt': $('#searchOpt').val(),
				   'keyword': $('#keyword').val()},
			// data: $('#searchForm').serialize(),
			dataType: 'json',
			success: function(data){
				console.log(JSON.stringify(data));
				
				$('#listFormTable tr:gt(0)').remove();
				// listFormTable에서 첫번째 행을 제외한 모든 행 삭제
				
				$.each(data, function(index, item){ // ajax each 반복문
					var result = `<tr>`
							 + `<td align="center">` + item.name + `</td>`
							 + `<td align="center">` + item.id + `</td>`
							 + `<td align="center">` + item.pwd + `</td>`
							 + `</tr>`;
					
					$('#listFormTable').append(result);
				
				}); // each	
				
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});