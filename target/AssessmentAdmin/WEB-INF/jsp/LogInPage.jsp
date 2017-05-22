<%-- 
    Document   : welcome
    Created on : 10 May, 2016, 5:23:26 PM
    Author     : cjp
--%>
<%-- LogIn page is for user authentication to enter to survey  --%>



<link href="css/all.css" rel="stylesheet">
<link href="css/Login.css" rel="stylesheet">
<%@page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <%-- The Following script for preventing back button in survey after login --%>
        <script>
            function preventBack() {
                window.history.forward();
            }
            setTimeout("preventBack()", 0);
            window.onunload = function () {
                null;
            };

            function openWindow1()
            {
                var URL = "help";
                window.open(URL, "RecoverPassword", "width=700,height=450");
            }
        </script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign-in Page</title>
    </head>
    <body>
        <table  width="100%" height="100%" >
            <tr height="17%">
                <td  valign="top">
                    <c:set var="pageTitle" scope="request"  value="Sign-In"/>
                    <c:set var="hidden" scope="request"  value="hidden"/>
            <jsp:include page="Header.jsp" flush="true" />
        </td>
    </tr>
    <tr height="78%">
        <td id="middle-wrap" valign="middle">
            <table id="sidebar">  
                <tr>
                    <td> <u id="heading">Advice</h1></td>
                </tr>


                <%-- form:form tag is for posting userInoput and validating user and displaying Validating messages --%>
                <form:form method="POST" action="SubmittedLogIn" modelAttribute="userLogInForm">
                    <%--Checking errorMessage for session if logout then error message will displayed--%>
                    <tr>
                        <td>
                    <c:if test="${errorMessage!=null}">
                        <font color="red"> ${errorMessage}</font>
                    </c:if>
            </td>
        </tr>
        <tr>
            <td><font color="red"> <form:errors path="activate"></form:errors></font>
            <font color="red"> <form:errors path="authorMsg"></form:errors>${authorMsg}</font>
            </td>
        </tr>
        <tr>                
            <td><form:label path="userId">User Id*</form:label></td>
            <td><form:input path="userId" /></td>
            <%--form:errors for displaying validation errors of user --%>
            <td><font color="red"> <form:errors path="userId"></form:errors></font></td>
            </tr>
            <tr>
                <td><form:label path="password">PASSWORD*</form:label></td>

                <td><form:password path="password" /></td> 

            <%--form:errors for displaying validation errors of password --%>
            <td><font color="red"> <form:errors path="password"></form:errors></font></td>
            </tr>                     

            <tr>
                <td>
                    Enter text as shown in the Picture*
                </td>
            </tr>
            <tr>
            <%-- This code is for displaying recaptcha--%>
            <td>
                <form:label path="captchaImage"><img src="${pageContext.request.contextPath}/CaptchaServlet"> </form:label>
             
            </td> 
                
                <td>
                  
                <form:input  path="captchas" />
            </td>   
            </td>
          
            <td><font color="red"> <form:errors path="captchas"></form:errors>${captchasMsg}</font></td>
            </tr>
        <%--This code is for displying recaptcha error message --%>
        <tr>
            <td colspan="2"><input type="submit"value="LOGIN" ></td>
            <td>  <input type="button" value="CLEAR" onclick="this.form.reset()">
            </td>           
        </tr>
        
        <%--The Registration and recovery are redirect to Registration page and recovery --%>
        
    </form:form>
</table>                         



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

