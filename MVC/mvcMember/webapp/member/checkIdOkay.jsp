<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CheckIdOkay here</title>
</head>
<body>

${param.id } 사용 가능<br>
<!-- checkIdService에서 loginOkay를 return받으면서 id도 query로 넘겨받음 -->
<input type="button" value="사용하기" onclick="checkIdClose('${param.id }')">
<!-- ${id }: pageScope에 id variable이 없으므로 자동으로 requestScope로 넘어가서 변수를 탐색하므로 requestScope 생략가능 -->

<script type="text/javascript">
function checkIdClose(id) {
	// id == '${param.id }'
	// 문자 입력 시, "" 사용 필수
	// checkId() 매개변수에서 이미 문자 처리했으므로 js에서는 따로 처리 X
	
	opener.writeForm.id.value = id;
	// document: 해당 .jsp 파일을 의미하므로 document.writeForm은 checkId.jsp에서만 form을 찾게됨
	// opener: 해당 .jsp 파일을 불러온 부모 document를 추가 참조
	opener.writeForm.idDuplication.value = id;
	// idDup value도 전달하여 중복 확인 검사
	window.close();
	opener.writeForm.pwd.focus();
	
}
</script>


</body>
</html>