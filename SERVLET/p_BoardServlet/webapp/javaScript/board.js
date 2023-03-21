const form_write = document.boardWriteForm;
// document.사용될_Form_name

function checkWrite() {
	document.getElementById("nameDiv").innerText="";
	document.getElementById("emailDiv").innerText="";
	document.getElementById("subjectDiv").innerText="";
	document.getElementById("contentDiv").innerText="";
	
	
	if(form_write.name_Subject.value==""){
	// name, id 관계없이 사용 가능
		alert("제목 입력")
		return;
	}
	
	else if(form_write.name_Content.value=="") {
		document.getElementById("contentDiv").innerText="내용 입력";
		return;
	}
		else {form_write.submit();}
	
}
