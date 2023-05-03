<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadFormAjaxList.jsp here</title>
</head>
<body>

<h3>
	<a href="/"><img src="/img/spongebob.png" width="100" height="100"></a>
	이미지 목록
</h3>

<form>
	<table id="userImgListTable" border="1" frame="hsides" rules="rows" cellspacing="0" cellpadding="5">
		<tr>
			<th width="100">번호</th>
			<th width="150">이미지</th>
			<th width="150">상품명</th>
		</tr>
	</table>
</form>


<!-- CDM 방식: js파일에 대한 서버로의 직접 접근 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="/js/uploadFormList.js"></script>


</body>
</html>