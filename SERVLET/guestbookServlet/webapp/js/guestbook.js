const form_write = document.guestbookWriteForm;
// 중복되는 선언부 변수 활용


	// 회원가입 버튼 클릭 시
	function checkWrite() {
		// 초기화
		document.getElementById("nameDiv").innerText = "";
		document.getElementById("emailDiv").innerText = "";
		document.getElementById("homepageDiv").innerText = "";
		document.getElementById("subjectDiv").innerText = "";
		document.getElementById("contentDiv").innerText = "";
		

		if(form_write.subject.value=="") {
			// alert("제목 입력");
			document.getElementById("subjectDiv").innerText="제목 입력";
			return;}
	
		
		else if(form_write.content.value=="") {
				// alert("내용 입력");
				document.getElementById("contentDiv").innerText="내용 입력";
				return;}
		
		else {form_write.submit();}
	
		
	}
	

	
function checkSearch() {
	// 초기화
	document.getElementById("seqDiv").innerText="";
	
		if(document.guestbookSearchForm.seq.value=="") {
			// document.form_name.variable.value
			// alert("제목 입력");
			document.getElementById("seqDiv").innerText="글번호 입력";
		return;}
		
		else {document.guestbookSearchForm.submit();}

}
