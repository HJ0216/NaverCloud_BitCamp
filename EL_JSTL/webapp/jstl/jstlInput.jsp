<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL input here</title>
</head>
<body>

<form method="post" action="jstlResult.jsp"> <!-- 입력상자에 입력된 데이터를 들고 이동 -->
<!-- post 방식: 한글 인코딩 추가 필요 -->
<table border="1" cellpadding="5" cellspacing="0">

	<tr>
		<th width="70">이름</th>
		<td><input type="text" name="name" id="name" placeholder="이름 입력">
		</td>
	</tr>


	<tr>
		<th width="70">나이</th>
		<td><input type="text" name="age" id="age" placeholder="나이 입력">
		</td>
	</tr>
	

	<tr>
		<th width="70">색깔</th>
		<td>
			<select name="color" style="width: 100px;">
				<optgroup label="색깔">
					<option value="red" selected>빨강</option>
					<option value="green">초록</option>
					<option value="blue">파랑</option>
					<option value="magenta">보라</option>
					<option value="cyan">하늘</option>
				</optgroup>
			</select>
		</td>
	</tr>


	<tr>
		<th>취미</th>
		<td>
			<input type="checkbox" name="hobby" id="hobby1" value="독서"/>
			<label for="hobby1">독서</label>
			<input type="checkbox" name="hobby" id="hobby2" value="영화"/>
			<label for="hobby2">영화</label>
			<input type="checkbox" name="hobby" id="hobby3" value="음악"/>
			<label for="hobby3">음악</label>
			<input type="checkbox" name="hobby" id="hobby4" value="게임"/>
			<label for="hobby4">게임</label>
			<input type="checkbox" name="hobby" id="hobby5" value="쇼핑"/>
			<label for="hobby5">쇼핑</label>
		</td>
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="SEND"/>
			<input type="reset" value="CANCEL"/>
		</td>
	</tr>


</table>

</form>

</body>
</html>