<%-- 
    Document   : Adminpage
    Created on : 14 May, 2016, 10:45:33 AM
    Author     : cjp
--%>
<%--  AdminPage is mainPage for Admin UserInterface  --%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link href="css/all.css" rel="stylesheet">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Advice Main Page</title>
        <script type="text/javascript">
            function openWindow1()
            {
                var URL = "help";
                window.open(URL, "RecoverPassword", "width=700,height=450");
            }
        </script>

    </head>
    <body>    <table  width="100%" height="100%">
            <tr height="17%">
                <td  valign="top">
                    <c:set var="pageTitle" scope="request" value="Admin Dashboard"/>
                    <jsp:include page="Header.jsp" flush="true" />
                </td>
            </tr>
            <tr height="78%">
                <td id="middle-wrap" valign="middle">
                    <div id="leftmenu_main">

                        <h3>Manage</h3>

                        <ul>
                            <%-- Adding links to AdminScreen--%>
                            <li><a href="PostQuestions">Manage Questionnaire</a></li>
                            <li><a href="GetBlock">Manage Blocks</a></li>
                            <li><a href="GetPage">Manage Pages</a></li>
                            <li> <a href="GetRules">Rules Engine</a></li>                       
                            <li> <a href="Registration">Registration - New User</a></li>
<!--                            <li> <a href="AdminRegistration">Registration</a></li>-->
                        </ul>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <jsp:include page="Footer.jsp" flush="true" />
                </td>
            </tr>



    </body>
</html>