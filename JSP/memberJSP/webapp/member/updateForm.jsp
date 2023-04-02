<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="member.dao.MemberDAO"
		 import="member.bean.MemberDTO"
%>

<%
	request.setCharacterEncoding("UTF-8");

// 	String id = request.getParameter("id");
	// Session으로부터 id 값을 얻어오기	
	String id = (String) session.getAttribute("memId");	

	MemberDAO memberDAO = MemberDAO.getInstance();
	MemberDTO memberDTO = memberDAO.memberCall(id);

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Form</title>

<style type="text/css">
div {
	color: red;
	font-size: 8pt;
	font-weight: bold;
}

</style>

</head>
<body>


<form name="updateForm" method="post" action="http://localhost:8080/memberJSP/member/update.jsp">
<!-- http://localhost:8080/memberJSP/member/ 생략 가능 -->

	<table border="1" cellpadding="5" cellspacing="0">
	
	<tr>
		<th width="70">이름</th>
		<td><input type="text" name="name" id="name" value="<%=memberDTO.getName() %>" placeholder="이름 입력">
		<!-- value=""로 확실히 묶어주기
			 String value 비교: equals, addr 비교: ==-->		
		<div id="nameDiv"></div>
		</td>
	</tr>


	<tr>
		<th width="70">아이디</th>
		<td><input type="text" name="id" id="id" value="<%=memberDTO.getId() %>" placeholder="아이디 입력" readonly>
		<!-- id는 param으로 직접 넘겨받았으므로 memberDTO.getId() 대신 id만 입력 가능 -->
		<div id="idDiv"></div>
		</td>
	</tr>
	

	<tr>
		<th width="70">비밀번호</th>
		<td><input type="password" name="pwd" id="pwd" value="<%=memberDTO.getPwd() %>">
		<!-- 비밀번호 및 비밀번호 확인은 값을 불러오지 않아도 됨 value="" -->
		<div id="pwdDiv"></div>
	</tr>


	<tr>
		<th width="70">재확인</th>
		<td><input type="password" name="repwd" id="repwd" value="<%=memberDTO.getPwd() %>">
		<div id="repwdDiv"></div>
		</td>
	</tr>


	<tr>
		<th width="70">성별</th>
		<td><input type="radio" name="gender" id="gender_m" value="0"
		<% if(memberDTO.getGender()!=null && memberDTO.getGender().equals("0")){ %>
		checked="checked"
		<% } %>
		><label for="gender_m">남자</label>
		
		&nbsp; &nbsp; &nbsp; <!-- 띄어쓰기 -->
						
		<input type="radio" name="gender" id="gender_w" value="1"
		<% if(memberDTO.getGender()!=null && memberDTO.getGender().equals("1")){ %>
		checked="checked"
		<% } %>
		><label for="gender_w">여자</label>
		</td>
	</tr>


	<tr>
		<th width="70">이메일</th>
			<td><input type="text" name="email1" value="<%=memberDTO.getEmail1() %>" style="width: 120px;">
			@ <!-- http 줄바꿈: 문자와 입력칸간 공백 부여 -->
			<input type="text" name="email2" value="<%=memberDTO.getEmail2() %>" style="width: 120px;">
 			<select name="email3" style="width: 120px;" onChange="javascript:select()">
			<option value="" <% if(memberDTO.getEmail2()!=null && !memberDTO.getEmail2().equals("naver.com")
			&& !memberDTO.getEmail2().equals("gmail.com") && !memberDTO.getEmail2().equals("nate.com")) {%>selected<%} %>>직접입력</option>
			<option value="naver.com" <% if(memberDTO.getEmail2()!=null && memberDTO.getEmail2().equals("naver.com")) {%>selected<%} %>>naver.com</option>
            <!-- <option value="naver.com" <%=memberDTO.getEmail2().equals("naver.com") ? "selected" : "" %>>naver.com</option>  -->
			<!-- memberDTO.getEmail2().equals() ? "" : selected -->
			<!-- web일 때는 js 위주로 사용해보기 -->

			<option value="gmail.com" <% if(memberDTO.getEmail2()!=null && memberDTO.getEmail2().equals("gmail.com")) {%>selected<%} %>>gmail.com</option>
			<option value="nate.com" <% if(memberDTO.getEmail2()!=null && memberDTO.getEmail2().equals("nate.com")) {%>selected<%} %>>nate.com</option>
		</select>
		<div id="emailDiv"></div>
		</td>
	</tr>


	<tr>
		<th width="70">휴대폰</th>
		<td><select name="tel1" style="width: 60px;">
			<option value="010" <% if(memberDTO.getTel1()!=null && memberDTO.getTel1().equals("010")) {%>selected <%} %>>010</option> 
			<option value="011" <% if(memberDTO.getTel1()!=null && memberDTO.getTel1().equals("011")) {%>selected <%} %>>011</option> 
			<option value="019" <% if(memberDTO.getTel1()!=null && memberDTO.getTel1().equals("019")) {%>selected <%} %>>019</option>
			<option value="070" <% if(memberDTO.getTel1()!=null && memberDTO.getTel1().equals("070")) {%>selected <%} %>>070</option>
			</select>
			
			<!-- <option value="070" < %if(memberDTO.getTel1()!=null && memberDTO.getTel1().equals("070")) {% >
			selected>070</option> < %} % >
			상기 코드에서 다음 -가 사라지는 이유:
			</option>가 마무리되지 않아서 인식이 제대로 되지 않음 -->
			
			- <input type="text" name="tel2" value="<%=memberDTO.getTel2() %>" style="width: 60px;"> -
			<input type="text" name="tel3" value="<%=memberDTO.getTel3() %>" style="width: 60px;">
			<div id="telDiv"></div>
		</td>
	</tr>


	<tr>
		<th width="70">주소</th>
		<td><input type="text" name="zipcode" id="zipcode" value=<%=memberDTO.getZipcode() %> size="5" placeholder="우편번호" readonly>
			<input type="button" value="우편번호검색" onclick="execDaumPostcode()"><br/>
			<input type="text" name="addr1" id="addr1" value="<%=memberDTO.getAddr1() %>" placeholder="주소" style="width: 400px;" readonly><br/>
			<input type="text" name="addr2" id="addr2" value="<%=memberDTO.getAddr2() %>" placeholder="상세주소" style="width: 400px;">
			<!-- 문자열 전체를 받기 위해서 value=""로 받기 -->
			<div id="addressDiv"></div>
		</td>
	</tr>


	<tr>
		<td colspan="2" align="center">
			<input type="button" value="회원 정보 수정" onclick="checkUpdate()">
			<!-- button ~ onclick: js를 사용하므로 'javascript:' 생략 가능 -->
			<input type="button" value="뒤로" onclick="history.go(-1)">
		</td>
	</tr>


	</table>
</form>


<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="http://localhost:8080/memberJSP/js/member.js">
// type = "text/javascript" 생략 가능
// script src: html 파일로 불러오기 O, 해당 파일로 이동하기 X

</script>

</body>
</html>