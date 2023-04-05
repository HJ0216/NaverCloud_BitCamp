<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page trimDirectiveWhitespaces="true" %>
<%--
<?xml version="1.0" encoding="UTF-8"?> xml tag 시작 전 공백 제거

* html 주석 사용 시, web browser에는 표시됨
xml 시작 tag 위에 html 주석이 보이면서 xml이 작동하지 않게됨

web browser로 넘어가지 않도록 jsp 주석 사용
 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- el, jstl 사용 시, jstl, standard jar 파일 lib에 import --%>

<%-- using Java
// Data
String user_id = request.getParameter("user_id");
--%>

<%-- DB 연동
if(user_id.equals('hong')) return true;
DB에 user_id hong이 이미 저장된 경우, true 반환
 --%>

<c:set var="result" value="false" />
<c:if test="${param.user_id=='hong'}">
	<c:set var="result" value="true"/>
</c:if>


<%-- return dataType: html -> xml으로 변경
1번째 줄의
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
contentType="text/xml"로 변경
 --%>

<?xml version="1.0" encoding="UTF-8"?>
<check_id>
	<result>${result }</result>
</check_id>