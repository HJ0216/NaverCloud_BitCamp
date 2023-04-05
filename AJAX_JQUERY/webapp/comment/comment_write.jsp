<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="result" value="true"></c:set>
<c:set var="message" value="comment saved" />

<%-- From exam07: $(this).serialize() --%>
<c:set var="num" value="${param.num}" />
<c:set var="datetime" value="${param.datetime}" />
<c:set var="writer" value="${param.writer}" />
<c:set var="content" value="${param.content}" />

<%--contentType="text/html: xml 파일 내용 보이지 X--%>
<?xml version="1.0" encoding="UTF-8"?>
<comment>
	<result>${result }</result>
	<message>${message }</message>
	<item>
		<num>${num }</num>
		<writer>${writer }</writer>
		<content>${content }</content>
		<datetime>${datetime }</datetime>
	</item>
</comment>