$(function(){
	$.ajax({
		type: 'post',
		url: '/person/getPersonList',
		dataType: 'json', // 생략가능
		success: function(data){
			console.log(JSON.stringify(data));
			
		/*
			$.each(data, function(index, item){ // ajax each 반복문
			// data가 list인 경우, 자료값에 list를 따로 기재하지 않음
			
				$('<tr/>').append($('<td/>', {
					align: 'center',
					text: items.name,
					width: 150
				})).append($('<td/>', {
					align: 'center',
					text: items.age,
					width: 150
				})).append($('<td/>', {
					align: 'left',
					text: items.photo,
					width: 150
				})).appendTo($('#listTable'));
			*/
			
		/*	
			$.each(data, function(index, item){ // ajax each 반복문
			// data가 list인 경우, 자료값에 list를 따로 기재하지 않음
			var html = `<tr>`
					 + `<td>` + item.photo + `</td>`
					 + `<td>` + item.name + `</td>`
					 + `<td>` + item.age + `</td>`
					 + `</tr>`;
			
			$('#listTable').append(html);
				
			}); // each

		*/
		
		for(var i=0; i<data.length; i++){
			var html = `<tr>
							<td align="center">
							<img src="/storage/${data[i].photo}" width="100" height="100">
							</td>
							<td align="center">${data[i].name}</td>
							<td align="center">${data[i].age}</td>
						</tr>`;
						
			$('#listTable').append(html);
		} // for

		},
		error: function(err){
			console.log(err);
		}		

	}); // ajax
	
}); // function