<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadFormAjax here</title>
</head>
<body>

<form id="uploadForm">
	<table border="1">
		<tr>
			<td>Item</td>
			<td><input type="text" name="imageName" size="45" /></td>
			<!-- name은 class field명과 동일해햐함 ModelAttribute를 거쳐서 userImgDTO로 이동 -->
		</tr>

		<tr>
			<td colspan="2">
				<textarea name="imageContent" rows="15" cols="50"></textarea>
			</td>
		</tr>


		<tr>
			<td colspan="2">
				<img id="showImg" width="70" height="70" />
				<img id="uploadImg" src="../img/uploadImg.png" alt="uploadImg" width="23.6" height="23.6" />
				<input type="file" name="img[]" id="imgUpload" multiple="multiple" 
					   style="visibility: hidden;"/>
				<%-- hidden 어떤게 선택되었는지 알 수 없는 문제 발생 > 어떤 이미지를 선택했는지 보여주기 --%>
			</td>
		</tr>
		


		<tr>
			<td colspan="2" align="center">
				<input type="button" id="uploadBtn" value="Submit Img" />
				<!-- type="submit" 시 이동하는 것을 방지하기 위해 button 사용
					 action이 없을 경우, err가 발생해야하지만 기존에 submit으로 이동했던 캐시가 있을 경우, 작동하는 경우도 존재
				-->
			</td>
		</tr>
	</table>
</form>

<!-- jQuery, CDM -->
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="../js/upload.js"></script>


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