<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="member.dao.MemberDAO"
		 import="member.bean.MemberDTO"
%>

<%
	request.setCharacterEncoding("UTF-8");

//	String id = request.getParameter("id");
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
<body onload="pick()">
<!-- body tag를 읽었을 때, onload=js 처리 -->

<form name="updateForm" method="post" action="update.jsp">

	<table border="1" cellpadding="5" cellspacing="0">
	
	<tr>
		<th width="70">이름</th>
		<td><input type="text" name="name" id="name" value="<%=memberDTO.getName() %>" placeholder="이름 입력">
		<!-- value=""로 확실히 묶어주기
			 String value 비교: equals, addr 비교: == -->
		<div id="nameDiv"></div>
		</td>
	</tr>


	<tr>
		<th width="70">아이디</th>
		<td><input type="text" name="id" id="id" value="<%=memberDTO.getId() %>" placeholder="아이디 입력" readonly>
		<div id="idDiv"></div>
		</td>
	</tr>
	

	<tr>
		<th width="70">비밀번호</th>
		<td><input type="password" name="pwd" id="pwd" value="<%=memberDTO.getPwd() %>">
		<div id="pwdDiv"></div>
	</tr>


	<tr>
		<th width="70">재확인</th>
		<td><input type="password" name="repwd" id="repwd" value="<%=memberDTO.getPwd() %>">
		<div id="repwdDiv"></div>
		</td>
	</tr>


	<!-- load를 통한 select 진행 -->
	<tr>
		<th width="70">성별</th>
		<td><input type="radio" name="gender" id="gender_m" value="0">
		<label for="gender_m">남자</label>
		
		&nbsp; &nbsp; &nbsp; <!-- 띄어쓰기 -->
						
		<input type="radio" name="gender" id="gender_w" value="1">
		<label for="gender_w">여자</label>
		</td>
	</tr>


	<!-- js function을 통한 select -->
	<tr>
		<th width="70">이메일</th>
			<td><input type="text" name="email1" id="email1" value="<%=memberDTO.getEmail1() %>" style="width: 120px;">
			@ <!-- http 줄바꿈: 문자와 입력칸간 공백 부여 -->
			<input type="text" name="email2" id="email2" value="<%=memberDTO.getEmail2() %>" style="width: 120px;">
			<!-- js() 사용을 위한 id value 부여 -->
 			<select name="email3" id="email3" style="width: 120px;" onChange="javascript:select()">
			<option value="" selected>직접입력</option>
			<option value="naver.com">naver.com</option>
			<option value="gmail.com">gmail.com</option>
			<option value="nate.com">nate.com</option>
		</select>
		<div id="emailDiv"></div>
		</td>
	</tr>


	<tr>
		<th width="70">휴대폰</th>
		<td><select name="tel1" style="width: 60px;">
			<option value="010" >010</option> 
			<option value="011" >011</option> 
			<option value="019" >019</option>
			<option value="070" >070</option>
			</select>
			
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
<script src="http://localhost:8080/miniProject_JSP/js/member.js"></script>
<!-- type = "text/javascript" 생략 가능 -->

<script type="text/javascript">
function pick(){
	document.updateForm.gender[<%=memberDTO.getGender() %>].checked = true;
	// name value가 동일할 경우, 값들이 배열화 됨
	// gender[0] = man, gender[1] = woman
	// memberDTO.getGender()로 0 or 1을 DB에서 반환받아 button-checked 상태 불러오기

	document.updateForm.tel1.value = '<%=memberDTO.getTel1() %>'
	// js 문자열 표시: '', '' 표시 안할 경우, 숫자로 인식되어 010 선택이 되지 않을 수 있음
}

</script>

</body>
</html>