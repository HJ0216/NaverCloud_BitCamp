<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

/*

원하는 쿠키만 얻을 수는 없으며 모든 쿠키를 한 번에 다 받아야 함

Cookie[] ar = request.getCookies();	

if(ar != null) { // null일 경우는 이미 쿠키 세션 만료
	for(int i=0; i<ar.length; i++) {
		if(ar[i].getName().equals("memName")){
			ar[i].setMaxAge(0);
			response.addCookie(ar[i]);
			// client에게 쿠키 만료 전달
		}
		
		if(ar[i].getName().equals("memId")){
			ar[i].setMaxAge(0);
			response.addCookie(ar[i]);
		}

	}
}

*/


// Session
session.removeAttribute("memName");
session.removeAttribute("memId");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>LOGOUT</h3>

</body>
</html>