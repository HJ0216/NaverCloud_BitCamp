<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardReplyForm here</title>
<style type="text/css">
#boardReplyForm div{
	color: red;
	font-size: 8px;
	font-weight: bold;	
}
</style>

</head>
<body>

<form id="boardReplyForm">

<input type="text" name="seq" value="${seq}" />
<input type="text" name="pg" value="${pg}" />

<h3>Reply FORM</h3>

<table border="1" cellpadding="5" cellspacing="0">

	<tr>
		<th width="70">Subject</th>
		<td><input type="text" name="subject" id="subject" placeholder="Ener the subject">
		<div id="subjectDiv"></div>
		</td>
	</tr>
	

	<tr>
		<th width="70">Contents</th>
		<td><textarea name="content" id="content" cols="50" rows="10"  placeholder="Enter the Content"></textarea>
		<!-- <textarea></textarea>는 태그 사이의 띄어쓰기 or 개행을 입력값으로 받으므로 붙여쓰기 -->
		<div id="contentDiv"></div>
		</td>
	</tr>
	

	<tr>
		<td colspan="2" align="center">
			<input type="button" value="Complete" id="boardReplyBtn">
			<!-- onclick: js() 사용 시, submit에 따른 action을 false 처리해줘야 하므로 id 지정 후 ajax 처리 -->
			<input type="reset" value="ReWrite">
		</td>
	</tr>

	
</table>

</form>

<%-- jQuery 사용에 대한 안내: 서버에 직접 접근(CDM 방식) --%>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<script type="text/javascript">
$('#boardReplyBtn').click(function(){
	$('#subjectDiv').empty();
	// document.getElementById("subjectDiv").innerText = "";
	$('#contentDiv').empty();
	// document.getElementById("contentDiv").innerText = "";

	if($('#subject').val() == ''){
		$('#subjectDiv').text('제목 입력');
		$('#subject').focus();
	} else if($('#content').val() == ''){
		$('#contentDiv').text('내용 입력');
		$('#content').focus();
	} else{
		
		console.log($('#subject').val());
		console.log($('#content').val());
		
		$.ajax({ // ajax를 사용하여 화면이동 없이 해당 페이지 내에서만 이동
			type: 'post',
			
			url: '/miniProject_jQuery/board/boardReply.do',
			
			data: $('#boardReplyForm').serialize(), // seq, pg, subject, content
			
			/* 
			// serialize 방식
			data: $('#boardReplyForm').serialize(),
			
			// 문자열 입력 방식
			data: 'subject=' + $('#subject').val() + '&content=' + $('#content').val()
				+ '&seq=' + $('#seq').val() + '&pg=' + $('#pg').val(),

			// JSON 방식
			data: {'subject': $('#subject').val(), 
				   'content': $('#content').val(), 
				   'seq': $('#seq').val(), 
				   'pg': $('#pg').val()},
			*/
			
			// dataType: 'text', 받는 데이터가 없을 때는 dataType 삭제
			
			success: function() {
				alert('답글작성 완료');
				location.href='/miniProject_jQuery/board/boardList.do?pg='+${pg};
				// pg=1이 아닌 전달받은 pg로 이동
			},
			
			error: function(err) {
				console.log(err);
			}
		});
	}
});

</script>




<script type="text/javascript">

	function checkBoardReply() {
		document.getElementById("subjectDiv").innerText = "";
		document.getElementById("contentDiv").innerText = "";
		
		if(document.boardReplyForm.subject.value=="") {
			document.getElementById("subjectDiv").innerText="Enter the Subject";
			document.boardReplyForm.subject.focus();
			// focus를 맞춰서 커서를 입력칸에 두어 입력이 바로 가능하도록 함
			return;} // return: 함수 종료

		else if(document.boardReplyForm.content.value=="") {
			document.getElementById("contentDiv").innerText="Enter the Content";
			document.getElementById("content").focus();
			// contentDiv focus가 아니라 content에 focus
			return;
		}
	 	
	 	else {document.boardReplyForm.submit();} // submit -> action
	 	
	} // check()
	
</script>


</body>
</html>