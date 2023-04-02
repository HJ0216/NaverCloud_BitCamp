<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardWrite here</title>
</head>
<body>

<script type="text/javascript">
window.onload=function(){
	alert("글작성 완료");
	location.href="/mvcBoard/board/boardList.do?pg=1";
	// *.do = ControlServlet으로 이동
	// command.properties의 도움을 받아 이동할 java 파일 탐색 후 servlet에 전달
	// 전달받은 java 파일로 이동
	// java: 1. 전달받은 데이터 처리 2. DB-5개의 레코드를 꺼내서 List에 담아오기 3. response-return: "*.jsp"
	// servlet에 jsp return
	// servlet에서의 jsp와의 forward 진행
}
</script>

</body>
</html>