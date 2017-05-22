<%-- 
    Document   : Help
    Created on : 14 May, 2016, 9:47:00 AM
    Author     : cjp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%-- Help is For giving some instructions to user --%>
<link href="css/all.css" rel="stylesheet">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Help</title>
    </head>
    <body>
        <table  width="100%" height="100%">
            <tr height="17%">
                <td  valign="top">
            <c:set var="pageTitle" scope="request" value="Registration For Advice"/>
            <jsp:include page="Header.jsp" flush="true" />
        </td>
    </tr>
    <tr height="78%">
        <td id="middle-wrap" valign="middle">
    <c:set var="pageTitle" scope="request" value="Advice Main Page"/>
    
        <h1>Coming Soon!</h1>
</td>
</tr>
<tr>
    <td>
        <jsp:include page="Footer.jsp" flush="true" />  
    </td>
</tr>
</table>

</body>
</html>
