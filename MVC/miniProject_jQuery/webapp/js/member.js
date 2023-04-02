const form = document.writeForm;
// 중복되는 선언부 변수 활용
// file load 시, 전체 내용이 한 번에 읽힘

$('#writeBtn').click(function(){
	$('#nameDiv').empty();
	$('#idDiv').empty();
	$('#pwdDiv').empty();
	$('#repwdDiv').empty();
	$('#emailDiv').empty();
	$('#telDiv').empty();
	$('#addressDiv').empty();

	if($('#name').val() == '') {
		$('#nameDiv').html('이름 입력');
		$('#name').focus();
	} else if($('#id').val() == ''){
		$('#idDiv').html('아이디 입력');
		$('#id').focus();
	} else if(pwd == ''){
		$('#pwdDiv').html('비밀번호 입력');
		$('#pwd').focus();
	} else if(repwd == ''){
		$('#repwdDiv').html('비밀번호 확인 입력');
		$('#repwd').focus();
	} else if($('#pwd').val() != $('#repwd').val()){
		$('#pwdDiv').text('비밀번호 불일치');
		$('#pwd').focus();
	} else if($('#id').val() != $('#idDuplication').val()){
		$('#idDiv').html('중복 체크 필요');
		$('#id').focus();		
	} else {
		// 새로운 페이지로 이동(submit > action)
		// $('#writeForm').submit();
		// 페이지 이동 없이 회원가입 처리
		
		// <form> 안의 데이터 값을 문자열 형식으로 가져옴
		// console.log($('#writeForm').serialize());
		
		
		// 페이지 이동 없이 회원가입 처리 완료
		
		// submit을 할 경우,
		// $('#writeForm').submit();
		// write.do
		
		$.ajax({
			type: 'post',
			url: '/miniProject_jQuery/member/write.do',
			data: $('#writeForm').serialize(),
			dataType: 'text', // 숫자 불가능
			success: function(data){
				data = data.trim();
				
				if(data == "WriteOkay"){
					alert('회원가입 완성');
					location.href='../index.jsp';
					// 현재 위치가 writeForm.jsp
				}
				else {alert('회원 재가입 필요');}
			},
			error: function(err){
				console.log(err);
			}
		}); // $.ajax

				
	}

});






	// 회원가입 버튼 클릭 시
	function checkWrite() {
		// 초기화
		document.getElementById("nameDiv").innerText = "";
		document.getElementById("idDiv").innerText = "";
		document.getElementById("pwdDiv").innerText = "";
		document.getElementById("repwdDiv").innerText = "";
		document.getElementById("emailDiv").innerText = "";
		document.getElementById("telDiv").innerText = "";
		document.getElementById("addressDiv").innerText = "";
		
		
		if(form.name.value=="") // name attribute 활용
		// if (document.getElementByID("name").value=="") // id attribute 활용	
			{
				alert("이름 입력");
				// document.getElementByID("nameDiv").innerText=="이름 입력";
				return;} // return: 함수 종료


		else if(form.id.value=="") {
				// alert("아이디 입력");
				document.getElementById("idDiv").innerText="아이디 입력";
				return;}

		else if(form.idDuplication.value=="idUncheck"){
                alert("아이디 중복체크를 해주세요.");
                return;}
		
		else if(form.idDuplication.value != form.id.value){
                alert("아이디 중복체크를 해주세요.");
                return;}

		else if(form.pwd.value=="")	{
				// alert("비밀번호 입력");
				document.getElementById("pwdDiv").innerText="비밀번호 입력";
				return;}


		else if(form.repwd.value=="") {
				// alert("재확인 입력");
				document.getElementById("repwdDiv").innerText="비밀번호 확인 입력";
				return;}
				

		else if(document.getElementById("pwd").value != document.getElementById("repwd").value) {
			// getElementById: html에서 id_value 확인
			alert("비밀번호 불일치");
			//document.getElementById("repwdDiv").innerText="비밀번호 불일치";	
		}

			
		else if(form.email1.value=="" || document.writeForm.email2.value=="") {
				// alert("이메일 입력");
				document.getElementById("emailDiv").innerText="이메일 입력";
				return;}
						
						
		else if(form.tel2.value=="" || document.writeForm.tel3.value=="") {
				// alert("휴대폰 입력");
				document.getElementById("telDiv").innerText="휴대폰 입력";
				return;}


		else if(document.writeForm.zipcode.value=="") {
				alert("우편번호 입력");
				// document.getElementById("addressDiv").innerText="우편번호 입력";
				return;}


		else if(document.writeForm.addr2.value=="") {
				alert("주소 입력");
				// document.getElementById("addressDiv").innerText="주소 입력";				
				return;}

	
	 	else {document.writeForm.submit();}
	 	// submit: writeForm data를 query에 담아 action으로 이동
	 	
	} // checkWrite()



	// 이메일 주소 클릭 시
	function select() {
//			document.writeForm.email2.value = document.writeForm.email3.value;
			document.getElementById("email2").value = document.getElementById("email3").value;
			// name 속성은 폼 이름이 필요하지만 id 속성은 폼 이름이 필요 X
			// writeForm, updateForm 2번 기재하는 것은 비효율적이므로 get
	} // select()


	
	// 우편변호 검색 클릭 시: 다음 우편번호api
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
//                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

				// 미사용 항목: 참고항목 주석 처리
                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
//                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
//                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
//                        extraAddr += data.bname;
//                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
//                    if(data.buildingName !== '' && data.apartment === 'Y'){
//                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
//                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
//                    if(extraAddr !== ''){
//                        extraAddr = ' (' + extraAddr + ')';
//                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
//                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
//                } else {
//                    document.getElementById("sample6_extraAddress").value = '';
//                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("zipcode").value = data.zonecode;
                document.getElementById("addr1").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("addr2").focus();
            }
        }).open();
    }

    
    
  
const formUpdate = document.updateForm;
// 중복되는 선언부 변수 활용
    
    	// 회원 정보 수정 버튼 클릭 시
	function checkUpdate() {
		// 초기화
		document.getElementById("nameDiv").innerText = "";
		document.getElementById("pwdDiv").innerText = "";
		document.getElementById("repwdDiv").innerText = "";
		document.getElementById("emailDiv").innerText = "";
		document.getElementById("telDiv").innerText = "";
		document.getElementById("addressDiv").innerText = "";
		
		
		
		if(formUpdate.name.value=="") // name attribute 활용
		// if (document.getElementByID("name").value=="") // id attribute 활용	
			{
				alert("이름 입력");
				// document.getElementByID("nameDiv").innerText=="이름 입력";
				return;} // return: 함수 종료


		else if(formUpdate.pwd.value=="")	{
				// alert("비밀번호 입력");
				document.getElementById("pwdDiv").innerText="비밀번호 입력";
				return;}



		else if(formUpdate.repwd.value=="") {
				// alert("재확인 입력");
				document.getElementById("repwdDiv").innerText="비밀번호 확인 입력";
				return;}
				

		else if(document.getElementById("pwd").value != document.getElementById("repwd").value) {
			// getElementById: html에서 id_value 확인
			alert("비밀번호 불일치");
			//document.getElementById("repwdDiv").innerText="비밀번호 불일치";	
		}

			
		else if(formUpdate.email1.value=="" || formUpdate.email2.value=="") {
				// alert("이메일 입력");
				document.getElementById("emailDiv").innerText="이메일 입력";
				return;}
						
						
		else if(formUpdate.tel2.value=="" || formUpdate.tel3.value=="") {
				// alert("휴대폰 입력");
				document.getElementById("telDiv").innerText="휴대폰 입력";
				return;}


		else if(formUpdate.zipcode.value=="") {
				alert("우편번호 입력");
				// document.getElementById("addressDiv").innerText="우편번호 입력";
				return;}


		else if(formUpdate.addr2.value=="") {
				alert("주소 입력");
				// document.getElementById("addressDiv").innerText="주소 입력";				
				return;}

	
	 	else {document.updateForm.submit();}
	 	// submit: writeForm data를 query에 담아 action으로 이동
	 	
	} // checkUpdate()

