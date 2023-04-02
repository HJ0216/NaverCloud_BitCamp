<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
include 예정인 jsp 파일 작성 시, 유의사항
1. html의 기본 구조가 겹치므로 삭제
2. css를 전역적으로 설정했을 경우, include 후 전체 tag가 영향을 받는 문제 발생
▶ css style 지정 시, id 속성 등을 지정해서 구체적으로 기재
--%>


<div>
	<c:if test="${sessionScope.memId == null }">
	<%-- session에 저장된 값이 없다면 --%>
		<input type="button" value="로그인" onclick="location.href='/miniProject_jQuery/member/loginForm.do'"/>
		<input type="button" value="회원가입" onclick="location.href='/miniProject_jQuery/member/writeForm.do'"/>
	</c:if>
	
	<c:if test="${sessionScope.memId !=null }">
		<h3>${memId }님 로그인</h3>
		<%-- scope 생략 시, page > request > session(LoginService.java) > application 순으로 탐색 --%>
		<input type="button" value="로그아웃" id="logoutBtn">
	</c:if>
</div>

<%-- jQuery 사용에 대한 안내: 서버에 직접 접근(CDM 방식) --%>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
$('#logoutBtn').click(function(){
	$.ajax({
		type: 'get',
		url: '/miniProject_jQuery/member/logout.do',
		// controlServlet > LogoutService.java > logout.jsp > $.ajax()
		success: function() {
			alert("Logout Complete");
			location.href='/miniProject_jQuery/index.jsp';
			// 상대번지: include된 파일 기준으로 작성
			// 그러므로 현재 위치는 index가 됨
		},
		error: function(err) {
			console.log(err);
		}
	});
}); // ; 먼저 작성
</script>


<%--
탐색 순서
pageScope ▶ requestScope ▶ SessionScope ▶ ApplicationScope
Scope 생략 가능
--%>