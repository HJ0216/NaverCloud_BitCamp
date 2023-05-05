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
		// e.target: 이벤트가 발생한 요소, result: FileReader 객체가 성공적으로 읽은 후의 결과 데이터를 의미
		// URL은 이미지 파일의 경우에는 이미지 데이터를 포함하며, e.target.result를 통해 접근할 수 있음
		// e.target.result에 저장된 데이터 URL을 사용하여 이미지 요소의 src 속성에 할당함으로써, 해당 이미지를 화면에 표시
	}
	
	reader.readAsDataURL(input.files[0]);
	// 파일을 읽어서 해당 파일의 데이터를 URL 형식으로 변환
	// input.files[0]: input type이 file인 태그에서 첫번째로 선택받은 파일을 의미
	// 해당 파일의 데이터를 URL 형식으로 변환하여 이미지로 표시하고자 함
}

</script>

</body>
</html>