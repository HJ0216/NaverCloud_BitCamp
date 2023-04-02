<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String clickCheck = request.getParameter("clickCheck");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

div {
	color: red;
	font-size: 8pt;
	font-weight: bold;
}

</style>

</head>
<body>

<form name="writeForm" method="post" action="http://localhost:8080/miniProject_JSP/member/write.jsp">

	<table border="1" cellpadding="5" cellspacing="0">
	
	<tr>
		<th width="70">이름</th>
		<td><input type="text" name="name" id="name" placeholder="이름 입력">
		<div id="nameDiv"></div>
		<!-- 유효성검사 시, 입력 요청 문구 위치 지정 -->
		</td>
	</tr>


	<tr>
		<th width="70">아이디</th>
		<td><input type="text" name="id" id="id" placeholder="아이디 입력">
		<input type="button" value="중복체크" onclick="checkId()">
		<!-- <input type="hidden" name="idDuplication" id="idDuplication" value="idUncheck" > -->
		<!-- <input type="text" name="idDuplication" value="" style="display: none"> -->
		<input type="text" name="idDuplication" value="">
		<!-- 중복확인용 id를 pk를 활용해서 미리 DB에 설정해놓기
			 -> value를 ""로 하여 유효성 검사에서 넘어갈 수 없게 한 후 중복검사에 함께 활용 -->
		<div id="idDiv"></div>
		</td>
	</tr>
	

	<tr>
		<th width="70">비밀번호</th>
		<td><input type="password" name="pwd" id="pwd">
		<div id="pwdDiv"></div>
	</tr>


	<tr>
		<th width="70">재확인</th>
		<td><input type="password" name="repwd" id="repwd">
		<div id="repwdDiv"></div>
		</td>
	</tr>


	<tr>
		<th width="70">성별</th>
		<td><input type="radio" name="gender" id="gender_m" value="0" checked="checked">
		<label for="gender_m">남자</label>
		
		&nbsp; &nbsp; &nbsp; <!-- 띄어쓰기 -->
						
		<input type="radio" name="gender" id="gender_w" value="1">
		<label for="gender_w">여자</label>
		</td>
	</tr>


	<tr>
		<th width="70">이메일</th>
			<td><input type="text" name="email1" id="email1" style="width: 120px;">
			@ <!-- http 줄바꿈: 문자와 입력칸간 공백 부여 -->
			<input type="text" name="email2" id="email2" style="width: 120px;">
			<select name="email3" id="email3" style="width: 120px;" onChange="javascript:select()">
			<!-- input type="button": onClick / "select": onChange / "submit": action -->
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
			<option value="010" selected>010</option>
			<option value="011">011</option>
			<option value="019">019</option>
			<option value="070">070</option>
			</select>
			- <!-- 줄바꿈: text 박스와 문자 간격 부여 -->
			<input type="text" name="tel2" style="width: 60px;">
			-
			<input type="text" name="tel3" style="width: 60px;">
			<div id="telDiv"></div>
		</td>
	</tr>


	<tr>
		<th width="70">주소</th>
		<td><input type="text" name="zipcode" id="zipcode" size="5" placeholder="우편번호" readonly>
			<input type="button" value="우편번호검색" onclick="execDaumPostcode()"><br/>
			<input type="text" name="addr1" id="addr1" placeholder="주소" style="width: 400px;" readonly><br/>
			<input type="text" name="addr2" id="addr2" placeholder="상세주소" style="width: 400px;">
			<div id="addressDiv"></div>
		</td>
	</tr>


	<tr>
		<td colspan="2" align="center">
			<input type="button" value="회원가입" onclick="checkWrite()">
			<!-- button ~ onclick: js를 사용하므로 'javascript:' 생략 가능 -->
			<input type="reset" value="다시작성">
		</td>
	</tr>


	</table>
</form>


<script>

// var: 전역 변수
// const, let: 지역 변수

function checkId() {
	var idValue = document.writeForm.id.value;
	// idValue를 function 밖에서 선언할 경우
	// 1. writeForm.jsp 파일이 로드 될 때, 입력되지 않은 상태에서 값이 인식됨
	// 2. checkId()이 호출될 때, 입력된 idValue 값을 저장하고 함수 내부에서 새로이 입력된 값이 인식되지 않음
	// 3. idValue=="" 유효성 검사를 넘어가지 못함
	// 4. 이에 따라 idValue를 지역변수로 선언
	document.getElementById("idDiv").innerText="";
	
	if(idValue=="") {
		document.getElementById("idDiv").innerHTML="<font color='magenta'>아이디 입력 필수</font>";
		// innerText: text 인식, innerHTML: HTML 인식
	} else {
		window.open("./checkId.jsp?id="+idValue,"checkId","width=500 height=300 left=900 top=200")
		// 이름을 부여하지 않을 경우, 창이 중복 체크 버튼을 누를 때마다 중복해서 나타남
		// 그러므로 2번째 요소에 이름을 부여함으로써 1개의 창만 팝업되도록 함
		// 새로운 창인 checkId는 writeForm.jsp가 아닌 checkId.jsp가 관리
		
	}
}


</script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="http://localhost:8080/miniProject_JSP/js/member.js"></script>

</body>
</html>