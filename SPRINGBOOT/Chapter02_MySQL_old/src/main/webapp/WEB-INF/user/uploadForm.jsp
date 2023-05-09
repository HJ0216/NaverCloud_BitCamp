<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadForm.jsp here</title>
</head>
<body>

<!-- 단순 submit, 반드시 post 방식, 반드시 enctype="multipart/form-data" 선언 -->
<form method="post" enctype="multipart/form-data" action="/user/upload">
	<table border="1">
		<tr>
			<td>Item</td>
			<td><input type="text" name="imgName" size="45" /></td>
			<!-- name은 class field명과 동일해햐함 ModelAttribute를 거쳐서 userImgDTO로 이동 -->
		</tr>

		<tr>
			<td colspan="2">
				<textarea name="imgContent" rows="15" cols="50"></textarea>
			</td>
		</tr>

<%--				
		<tr>
			<td colspan="2">
				<input type="file" name="img" />
				<!--
					img만 name은 class field명과 달라야함 ModelAttribute를 거쳐서 userImgDTO로 이동X
					@RequestParam에 의해 UserController에 머무름
			 	-->
			 
			</td>
		</tr>

		다중 파일 업로드 시에는 name 속성에 같은 이름을 지정
		<input>의 name이 동일 > controller에서 []로 인식

		<tr>
			<td colspan="2">
				<input type="file" name="img" />			 	
			</td>
		</tr>


--%>

		<tr>
			<td colspan="2">
				<img id="showImg" width="70" height="70" />
				<img id="uploadImg" src="/img/uploadImg.png" alt="uploadImg" width="23.6" height="23.6" />
				<input type="file" name="img[]" id="imgUpload" multiple="multiple" 
					   style="visibility: hidden;"/>
			</td>
		</tr>
		


		<tr>
			<td colspan="2" align="center">
				<input type="submit" id="uploadBtn" value="Submit Img" />
			</td>
		</tr>
	</table>
</form>



<!-- jQuery, CDM -->
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
$('#uploadImg').click(function(){
	$('#imgUpload').trigger('click'); // imgUpload click과 동일한 효과를 갖도록 강제 이벤트 실행
});

<%-- hidden 어떤게 선택되었는지 알 수 없는 문제 발생 > 어떤 이미지를 선택했는지 보여주기 --%>
$('#imgUpload').change(function(){
	readURL(this); // this = 업로드한 이미지 객체
});

function readURL(input){
	var reader = new FileReader(); // FileReader(): js code
	reader.onload = function(e){
		$('#showImg').attr('src', e.target.result); // e.target: 이벤트가 발생하는 요소 반환
	}
	
	reader.readAsDataURL(input.files[0]);
}

</script>

</body>
</html>

<!-- 
	FileReader
	FileReader는 type이 file인 input 태그 또는 API 요청과 같은 인터페이스를 통해  File 또는 Blob 객체를 편리하게 처리할수있는 방법을 제공하는 객체이며
	abort, load, error와 같은 이벤트에서 발생한 프로세스를 처리하는데 주로 사용되며, File 또는 Blob 객체를 읽어서 result 속성에 저장한다.
	
	FileReader.onload()
	load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료되었을 때마다 발생한다.
 -->