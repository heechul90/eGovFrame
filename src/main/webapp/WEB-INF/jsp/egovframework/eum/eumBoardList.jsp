<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="title.sample" /></title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
    <script type="text/javaScript" language="javascript" defer="defer">
        <!--
        /* 글 수정 화면 function */
        function fn_egov_select(id) {
        	document.listForm.selectedId.value = id;
           	document.listForm.action = "<c:url value='/updateSampleView.do'/>";
           	document.listForm.submit();
        }
        
        /* 글 등록 화면 function */
        function fn_egov_addView() {
           	document.listForm.action = "<c:url value='/addSample.do'/>";
           	document.listForm.submit();
        }
        
        /* 글 목록 화면 function */
        function fn_egov_selectList() {
        	document.listForm.action = "<c:url value='/egovSampleList.do'/>";
           	document.listForm.submit();
        }
        
        /* pagination 페이지 링크 function */
        function fn_egov_link_page(pageNo){
        	document.listForm.pageIndex.value = pageNo;
        	document.listForm.action = "<c:url value='/egovSampleList.do'/>";
           	document.listForm.submit();
        }
        
        //-->
    </script>
</head>

<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<h1><a href="eumHome.jsp">Eum Board</a></h1>
<!-- List -->
<div class="table">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="카테고리ID, 케테고리명, 사용여부, Description, 등록자 표시하는 테이블">
		<caption style="visibility:hidden">카테고리ID, 케테고리명, 사용여부, Description, 등록자 표시하는 테이블</caption>
		<colgroup>
			<col width="40"/>
			<col width="100"/>
			<col width="150"/>
			<col width="?"/>
			<col width="60"/>
		</colgroup>
		<tr>
			<th align="center">NO</th>
			<th align="center">NAME</th>
			<th align="center">DESCRIPTION</th>
			<th align="center">USE_YN</th>
			<th align="center">REG_USER</th>
		</tr>
		<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td align="center" class="listtd"><c:out value="${result.id}"/></td>
				<td align="center" class="listtd"><c:out value="${result.name}"/>&nbsp;</td>
				<td align="left" class="listtd"><c:out value="${result.description}"/>&nbsp;</td>
				<td align="center" class="listtd"><c:out value="${result.useYn}"/>&nbsp;</td>
				<td align="center" class="listtd"><c:out value="${result.regUser}"/>&nbsp;</td>
			</tr>
		</c:forEach>	
	</table>
</div>
</body>
</html>