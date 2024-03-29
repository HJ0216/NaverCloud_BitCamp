<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeForm.jsp here</title>
</head>
<body>

<form id="writeForm">
	<table border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th style="width: 80px;">이름</th>
			<td>
				<input type="text" name="name" id="name" />
				<%-- 
					serialize 대상 data: name
					유효성 검사 대상 data: id
				 --%>
				<div id="nameDiv"></div>
			</td>
		</tr>

		<tr>
			<th>나이</th>
			<td>
				<input type="text" name="age" id="age" />
				<div id="ageDiv"></div>
			</td>
		</tr>


		<tr>
			<td colspan="2">
				<img id="showImg" width="70" height="70" />
				<img id="uploadImg" src="/img/uploadImg.png" alt="uploadImg" width="23.6" height="23.6" />
				<%-- 
					Spring: mvc:resources에서 mapping, location을 통해 servlet으로 넘어가지 않도록 처리
					SpringBoot: src/main/resources/static에 위치시켜서 servlet으로 넘어가는 것을 방지
				 --%>
				 
				<%--
					<input type="file" name="img[]" id="imgUpload" multiple="multiple" style="visibility: hidden;"/>
 				--%>
 				<input type="file" name="img" id="imgUpload" style="visibility: hidden;"/>
				<%-- 1개만: name="img" / 2개 이상: name="img[]" multiple="multiple" --%>
				<%-- iput type:file > hidden 선택된 이미지를 알 수 없으므로 img#showImg를 통해 load --%>
			</td>
		</tr>

		
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="submit" id="writeBtn" />
				<input type="reset" value="cancel" />
			</td>
		</tr>
		
	</table>
</form>


<!-- jQuery, CDM -->
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="/js/write.js"></script>
<!-- 별도로 mvc:resources를 선언하지 않아도 src/main/resources/static으로 넘어감
<script> 태그의 src 속성을 사용하여 불러온 JavaScript 파일은 클라이언트 측에서 실행되는 코드
브라우저는 HTML 파일을 로드하면서 JavaScript 파일도 함께 다운로드하여 실행합니다.
이때 JavaScript 파일은 브라우저에서 동작하므로 서버 측에서는 별도의 처리가 필요하지 않습니다.
따라서 /js/write.js와 같은 정적 파일 경로는 클라이언트 측에서 불러오는 정적 파일을 찾기 위한 경로로 사용됩니다.

반면, <a> 태그를 사용하여 생성한 링크는 클라이언트 측에서 서버로 요청을 보내고, 서버는 요청을 받아 해당하는 컨트롤러에서 처리합니다.
이때 서버는 요청을 처리하고 응답을 생성해 클라이언트에게 반환합니다.
따라서 /person/writeForm과 같은 URL은 서버에서 동작하는 컨트롤러를 찾기 위한 경로로 사용됩니다.
-->

<script type="text/javascript">
$('#uploadImg').click(function(){
	$('#imgUpload').trigger('click'); // imgUpload click과 동일한 효과를 갖도록 강제 이벤트 실행
});

<%-- iput type:file > hidden 선택된 이미지를 알 수 없으므로 img#showImg를 통해 load --%>
$('#imgUpload').change(function(){
	readURL(this); // this = 업로드한 이미지 객체
});

function readURL(input){
	var reader = new FileReader();
	// FileReader(): 웹 페이지에서 파일을 읽을 수 있는 js 내장 객체
	reader.onload = function(e){
	// onload: 파일이 성공적으로 로드되었을 때 발생하는 이벤트로 파일을 읽어서 이미지를 화면에 표시하는 역할을 수행
		$('#showImg').attr('src', e.target.result);
		// e.target: 이벤트가 발생한 태그(input, file)
		// ㄷ.target.result: FileReader 객체(input 태그에서 선택한 파일의 내용을 읽어옴)가 성공적으로 읽은 후의 결과 데이터(이미지 주소)
		// e.target.result에 저장된 데이터 URL을 사용하여 이미지 요소의 src 속성에 할당함으로써, 해당 이미지를 화면에 표시
	}
	
	reader.readAsDataURL(input.files[0]);
	// 파일을 읽어서 해당 파일의 데이터를 URL 형식으로 변환
	// input.files[0]: input type이 file인 태그에서 첫번째로 선택받은 파일을 의미
	// 해당 파일의 데이터를 URL 형식으로 변환하여 이미지로 표시하고자 함
	/*
		reader.readAsDataURL(input.files[0]) 코드를 실행하면, 해당 코드의 실행 결과로 파일의 내용이 URL 형식으로 변환되고, 변환된 URL은 reader 객체의 result 속성에 저장됩니다.
		onload 이벤트 핸들러 함수 내부에서는 e.target.result 속성을 통해 reader 객체의 result 속성값, 즉 파일 내용이 URL 형식으로 변환된 값을 가져와서 사용합니다.
	*/
}

</script>

</body>
</html>