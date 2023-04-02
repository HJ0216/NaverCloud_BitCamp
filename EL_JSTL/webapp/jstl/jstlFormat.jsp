<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Format here</title>
</head>
<body>

<!-- Decimal format과 유사 -->
<fmt:formatNumber type="number" value="123456789" /><br/>
<fmt:formatNumber type="number" value="123456789.88" /><br/>
<fmt:formatNumber type="number" value="123456789.88" maxFractionDigits="1" /><br/>
<!-- maxFractionDigits="1" 소수이하 1째자리까지 -->
<hr/>

<fmt:formatNumber type="number" value="123456.78" pattern="#,###" /><br/>
<fmt:formatNumber type="number" value="123456.789" pattern=".##" /><br/>
<fmt:formatNumber type="number" value="123456.789" pattern="#,###.##" /><br/>
<hr/>

<fmt:formatNumber type="number" value="0000.00" pattern="#,###.##" /><br/>
<fmt:formatNumber type="number" value="0000.00" pattern="0,000.00" /><br/>
<fmt:formatNumber type="number" value="12.3" pattern="000.00" /><br/>
<hr/>

<c:set var="now" value="<%=new java.util.Date() %>"/>
<!-- < %와 $ {를 함께 사용하면 오류가 발생할 수 있으므로 js로 변경 필요 -->
<c:out value="${now }"/><br/>
date: <fmt:formatDate value="${now }" type="date"/><br/>
time: <fmt:formatDate value="${now }" type="time"/><br/>
both: <fmt:formatDate value="${now }" type="both"/><br/>
<hr/>

<fmt:formatDate value="${now }" pattern="yyyy-MM-dd hh:mm" type="both"/><br/><!-- hh: 24시간제 -->
<fmt:formatDate value="${now }" pattern="yyyy-MM-dd" type="date"/><br/>
<fmt:formatDate value="${now }" pattern="yyyy-MM-dd E요일 a" type="date"/><br/><!-- a: 오전/오후 -->
<fmt:formatDate value="${now }" pattern="HH:mm:ss" type="time"/><br/><!-- HH: 12시간제 -->
<hr/>



</body>
</html>