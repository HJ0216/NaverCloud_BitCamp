<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	// Data
	String name = null;
	String id = null;
	

/*	

	// Cookie
	
	원하는 쿠키만 얻을 수는 없으며 모든 쿠키를 한 번에 다 받아야 함
	Cookie[] ar = request.getCookies();	
	ar[0] = response.addCookie(cookie)
	ar[1] = response.addCookie(cookie2)

	getCookies 대상 = login.jsp의 response.addCookie
	cookie를 특정 지어서 가져올 수 없으므로 설정된 전체 쿠키를 불러서 배열로 반환

	if(ar != null) { // 쿠키 유효시간이 경과하지 않을 경우에

		for(int i=0; i<ar.length; i++) {
			String cookieName = ar[i].getName(); -- memName
			String cookieValue = ar[i].getValue(); -- memName에 대한 sessionID
			
			System.out.println("쿠키명 = " + cookieName);
			System.out.println("쿠키값 = " + cookieValue);
			System.out.println();

			// Cookie Name(memName, memId)이 동일할 경우, 어떤 컴퓨터에서 로그인했는지 알 수 없으므로
			// PC당 고유한 Session ID(Cookie Value)를 부여해서 접속 컴퓨터를 구분
			
			if(cookieName.equals("memName")) {name = cookieValue;}
			// 쿠키 이름이 "memName"과 같다면, name에 memName 쿠키의 value 저장
			// cookie setMaxAge 초과 시 response cookie가 없으므로 request cookie가 null -> ar[i]==null -> CookieName==null
			if(cookieName.equals("memId")) {id = cookieValue;}
			
		}
	}
*/

	name = (String) session.getAttribute("memName");
	// session.getAttribute("memName"): return Object -> String으로 형변환
	id = (String) session.getAttribute("memId");
		
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<body>

<img src="../image/android.png" width="50" height="50" alt="안드로이드"
onclick="location.href='http://localhost:8080/miniProject_JSP/index.jsp'" style="cursor: pointer;">

<h3><%=name %>님 로그인</h3>

<br/>
	 
<!-- 
	<input type="button" value="회원정보 수정" onclick="location.href='updateForm.jsp?id=< %=id% >'">  -->

	 <!--
	 파일의 location에 대한 구분('') 필요
	 id value를 param으로 받아서 query로 넘기기
	 url을 수정해서 타인의 정보에 접근할 수 있는 문제 발생
	 : cookie or seesion 활용
	 -->

	 <input type="button" value="로그아웃" onclick="location.href='logout.jsp'">
	 <input type="button" value="회원정보 수정" onclick="location.href='updateFormT.jsp'">
	 <input type="button" value="회원 탈퇴" onclick="location.href='deleteFormT.jsp'">
	


</body>
</html>