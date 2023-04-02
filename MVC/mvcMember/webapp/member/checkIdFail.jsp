<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CheckIdFail here</title>
</head>
<body>


<form action="/mvcMember/member/checkId.do">

	${param.id } 사용 불가능<br>
	<!-- CheckIdService()의 return "/member/checkIdFail.jsp?id="+id; 사용 -->
	<!-- 
	request.setAttribute("변수", 값);
	${requestScope.id } > jsp는 servlet과 forward되었으므로 request, response 공유 가능
	${id }: pageScope에 id variable이 없으므로 자동으로 requestScope로 넘어가서 변수를 탐색하므로 requestScope 생략가능
	param은 생략 불가
	 -->
	아이디
	<input type="text" name="id">
	<input type="submit" value="중복체크">

</form>


</body>
</html>