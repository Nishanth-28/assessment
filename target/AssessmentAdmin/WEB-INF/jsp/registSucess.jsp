<%-- 
    Document   : index
    Created on : 10 May, 2016, 6:02:42 PM
    Author     : cjp
--%>
<%-- This page is for displaying after registration page --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="css/all.css" rel="stylesheet">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function openWindow1()
            {
                var URL = "help";
                window.open(URL, "RecoverPassword", "width=700,height=450");
            }
        </script>
    </head>
    <body>
        <table  width="100%" height="100%">
            <tr height="17%">
                <td  valign="top">
                    <c:set var="hidden" scope="request"  value="hidden"/>
                    <c:set var="pageTitle" scope="request" value="Registration For Advice"/>
                    <jsp:include page="Header.jsp" flush="true" />
                </td>
            </tr>
            <tr height="78%">
                <td id="middle-wrap" valign="middle">
                    Mr/Ms</br> ${registrationForm.firstName}<br>Your Details Posted Successfully</br>
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
