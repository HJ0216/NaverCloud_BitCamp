<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String clickCheck = request.getParameter("clickCheck");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WriteForm here</title>

<style type="text/css">

#writeForm div {
	color: red;
	font-size: 8pt;
	font-weight: bold;
}

</style>

</head>
<body>

<form id="writeForm" method="post" action="/miniProject_jQuery/member/write.do">
<!--  submit > action에 따른 새로운 페이지 생성 -->

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
		<input type="button" value="중복체크"> <%-- 중복확인 체크를 새로운 창이 아닌 마우스 포인터를 통한 idDiv에서 안내 --%>
		<%--
		<input type="button" value="중복체크" id="checkIdBtn">
		<input type="hidden" name="idDuplication" id="idDuplication" value="idUncheck" >
		<input type="text" name="idDuplication" value="" style="display: none">
		--%>
		
		<input type="text" name="idDuplication" id="idDuplication" value="">
		<%--value를 ""로 하여 유효성 검사에서 넘어갈 수 없게 한 후 중복검사에 함께 활용--%>
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
		
		&nbsp; &nbsp; &nbsp; <%-- 띄어쓰기 --%>
						
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
			<input type="button" value="회원가입" id="writeBtn">
			<input type="reset" value="다시작성">
		</td>
	</tr>

	</table>
</form>


<!-- CDM 방식: js파일에 대한 서버로의 직접 접근 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="../js/member.js"></script>


<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="http://localhost:8080/miniProject_jQuery/js/member.js"></script>

<script type="text/javascript">
$('#id').focusout(function(){
	if($('#id').val() == ''){
		$('#idDiv').text('아이디 입력'); // 문자 입력 시, "" or '' 유의
		$('#idDiv').css('color', 'magenta');
	} else {
		// 서버 요청
		$.ajax({
			type: 'post',
			url: '/miniProject_jQuery/member/checkId.do',
			data: 'id=' + $('#id').val(), // 서버로 보내는 데이터
			dataType: "text", // 서버로부터 받는 데이터(text, html, xml, json)
			success: function(data){ // function(data) != 서버로 보내는 데이터
				data = data.trim();
				
				if(data == 'Exist'){
					$('#idDiv').text('사용 불가능');
					$('#idDiv').css('color', 'red');
					
				} else if(data == 'Non_Exist') {
					$('#idDiv').text('사용 가능');
					$('#idDiv').css('color', 'blue');
					$('#idDuplication').val($('#id').val());
					// content 입력: text, value 지정: val
					$('#pwd').focus();
				}
			},
			error: function(err){
				colsole.log(err);
			}
		});
	}
}); // 마우스 포인터 아웃 시, 중복 아이디 체크



$('#checkIdBtn').click(function(){
	$('#idDiv').empty();
	
	var id = $('#id').val();
	
	if(id == '') {
		$('#idDiv').html('<font color="magenta">아이디 입력</font>'); <%-- ''안에는 "" 입력 --%>
	}
	
	else {
		window.open("/miniProject_jQuery/member/checkId.do?id="+id,"checkId","width=500 height=300 left=900 top=200")
	$.ajax({
		type: 'post',
		url: '/miniProject_jQuery/member/checkId.do',
		data: 'id=' + $('#id').val(),
		dataType: 'text',
		success: function(result){
			result = result.trim();
			if(result == 'Exist'){
				$('#idDiv').text('사용 불가능');
				$('#idDiv').css('color', 'red');
				
			} else if(result == 'Non_Exist'){
				$('#idDiv').text('사용 가능');
				$('#idDiv').css('color', 'blue');
				$('#idDuplication').val($('#id').val());
				$('#pwd').focus();
			}
		},
		error: function(err){
			console.log(err);
		}
	}); /*$.ajax*/
	}
}); // 마침표 찍고 시작하기
</script>





</body>
</html>