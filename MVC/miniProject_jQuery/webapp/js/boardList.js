$(document).ready(function(){
// window.onload($(function(){}));
	$.ajax({
		type: 'post',
		url: '/miniProject_jQuery/board/getBoardList.do',
		// project명 앞에 '/'를 빠트린 경우, project 인식 X
		// GetBoardListService.java: return "/board/getBoardList.jsp";
		// ▶ ControllServlet.java: return받은 "/board/getBoardList.jsp"와 forwording
		// ▶ .jsp와 객체를 공유하며 view로 반환
		// .jsp의 ${requestScope.json } = service.java의 request.setAttribute("json", json)
		// list, pagingHTML: json에 저장되어 있음
		// forward 의미:
		// .java: servlet으로부터 request 객체를 전달받음(java 자체에서는 request 객체 없음)
		// 		: 해당 request 객체에 java 파일에서 데이터를 저장하고
		//		: return되는 .jsp와 해당 데이터를 공유하면서 servlet에 return
		// servlet이 갖고 있던 request 객체, 건네받은 java, return되는 jsp가 모두 객체를 공유 = forward
		
		
		data: {'pg': $('#pg').val()},
		// JSON: {'pg': $('#pg').val()},
		// 문자열: 'pg=' + $('#pg').val(),
		// jQuery: 혼자서 사용 가능 
		// jQuery: 서버와 연결할 때는 ajax()와 함께 사용
		
		dataType: 'json',
		// return dataType: text, html, xml, json
		// * 객체 type을 return 받을 수 없으므로 객체를 json으로 변환시켜 가져와야 함
		success: function(data){
			// console.log(JSON.stringify(data));
			// 주소값 대신 JSON type으로 변환하여 콘솔 출력

			// console.log(data.listT[0].seq);
			
			$.each(data.listT, function(index, items){
			// for(BoardDTO boardDTO(=items) : data.listT)
			// jQuery에서 값 입력은 왼쪽에서 오른쪽으로(java와 반대)
			// item: JSON 객체 하나하나(자바의 boardDTO)
			// index: listT의 방순서 or items의 저장 순서

				console.log(index + ", seq: " + items.seq + ", name: " + items.name);
				// 0, seq: 60, name: 홍길동1111
				
				$('<tr/>').append($('<td/>',{
					align: 'center', // <td> 속성값 부여
					text: items.seq
				}))		  .append($('<td/>',{
					// text: items.subject ▶ <a> 이동
				}).append($('<a/>', { // .append($('',{}).append($('',{})))
					href: '#',
					class: 'subjectA subjectA_'+items.seq,
					text: items.subject
				}))
				// result: <td><a href="#" class="subjectA subjectA_items.seq">items.subject</a></td>
				)		  .append($('<td/>',{
					align: 'center',
					text: items.id
				}))		  .append($('<td/>',{
					align: 'center',
					text: items.hit
				}))		  .append($('<td/>',{
					align: 'center',
					text: items.logtime
				})).appendTo($('#boardListTable'))
				// appendTo($('#boardListTable')): #boardListTable 테이블로 코드 추가
				// $('<tr/>').append($('<td/>',{})): tr 밑에 td를 붙임

				// A.append(B)
				// A
				// 	B
				// A.appendTo(B)
				// B
				//  A
				
				// 답글
				for(var i=1; i<items.lev; i++){
					$('.subjectA_'+items.seq).before('&emsp;');
				}
				if(items.pseq!=0){
					$('.subjectA_'+items.seq).before($('<img/>',{
						'src': '/miniProject_jQuery/img/reply.gif'
					}));
					// 모든 글 목록이 subjectA 속성에 속함-> 모든 글 목록에 답글 이미지가 붙여짐
					// .before(): 앞에
					// <table>
					//		<tr>
					// 			<td>
					// 			<td>
				}
				
			});// each
			
			// 페이징 처리
			$('#boardPagingDiv').html(data.pagingHTML);
			
			
			// 로그인 여부
			$('.subjectA').click(function(){
				// alert($('#memId').val());
				if($('#memId').val() == ''){
				// boardListT.jsp에서 ${memId}에 대한 값 설정이 이뤄지지 않았을 경우,
				// 해당 변수가 선언조차 되지 않은 상태에서 값 조화를 실행하게 되는 것이므로 undefined 반환
				
				// 만일 memId라는 변수는 선언되었는데, scope 겁색 후 해당 변수의 값이 조화가 안된다면
				// ${memId}, el 표현식 상 "" 빈값(empty)을 반환
					alert("로그인 필요");
					location.href="/miniProject_jQuery/member/loginForm.do";
				} else {
//					var seq = $(this); // <A>
					// $('.subjectA'): subjectA 5개 중에서 누구인지 알 수 X
					// $(this): 이벤트 발생 시, 태그를 Object 형태로 감싸서 보여줌
//					var seq = $(this).parent(); <TD>
//					var seq = $(this).parent().prev(); <TD>
					// prev: 형제들 중 this 직전
//					var seq = $(this).parent().next(); <TD>
					// next: 형제들 중 this 직후

					var seq = $(this).parent().prev().text(); // SEQ.val() 반환
					// 자신($(this)).부모(td).형제(tr).값(seq)

					var pg = $('#pg').val();
					// boardListService -(pg값 전달)-> BoardListT
					// boardListT는 pg값이 있으나 js파일로는 전달되지 않음
					// boardListT에서 <input type="text" id="pg" value="${pg }" > 통해 변수값 설정
					location.href='/miniProject_jQuery/board/boardView.do?seq=' + seq + '&pg=' + pg;
				}
				
			}); // subjectA 			
			
		}, // sucess
		error: function(err){
			console.log(err);
		}
	}); // $.ajax
	
});
// $(function(){});
