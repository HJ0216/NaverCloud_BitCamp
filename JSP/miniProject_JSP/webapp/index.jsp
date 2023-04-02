<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index page here</title>


<style>

a:link    {color: red;    text-decoaration: none;} /* 한 번도 클릭하지 않았을 경우 */
a:visited {color: black;  text-decoaration: none;} /* 마우스 클릭 후 */
a:hover   {color: yellow; text-decoaration: none;} /* 마우스를 올렸을 때 */
a:active  {color: green;  text-decoaration: none;} /* 마우스를 누르고 있을 때 */

/*
html 주석: <!-- -->
<style> 내부 주석: / * * /
*/

</style>


</head>
<body>

<div style="text-align: center;">
<h2> *** MAIN *** </h2>
<h3> 

<!-- 로그인 성공 시, session 발생
	 목록 제외 로그인 여부에 따라 보여지는 화면을 구분 -->
<% if(session.getAttribute("memId")==null) { %>
	<!-- 현재 위치한 index.html 기준으로 상대경로 작성 -->
	<a href="./member/writeForm.jsp">Sign In</a> <br/>
	<a href="./member/loginForm.jsp">Log in</a> <br/>
	<% } else { %>
	<a href="./member/logout.jsp">Log out</a> <br/>
	<a href="./member/updateForm.jsp">Modify Info</a> <br/>
	<a href="./member/deleteFormT.jsp">Delete account</a> <br/>
	<a href="./board/boardWriteForm.jsp">Write</a> <br/>
<% } %>
	<a href="./board/boardListT.jsp?pg=1">List</a> <br/>
	<!-- list 이동 시, pg param을 넘겨서 1pg에서 시작 -->

</h3>
</div>

</body>
</html>