<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>	
<meta charset="UTF-8">
<title>Board</title>
</head>

<body>
<table>
	<thead class="text-center">
		<tr>
			<td>번호</td>
			<td>글제목</td>
			<td>글쓴이</td>
			<td>작성일</td>
		</tr>
	</thead>
	<tbody class="text-center">
		<c:forEach items="${boardList}" var="boardList">
			<tr>
				<td><c:out value="${boardList.boardno}"/></td>
				<td class="text-center"><c:out value="${boardList.title}"/></td>
				<td><c:out value="${boardList.writer}"/></td>
				<td><c:out value="${boardList.createdate}"/></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>