<%-- 
    Document   : OrganizationDetailsInRegistration
    Created on : 9 Aug, 2016, 2:36:37 PM
    Author     : cjp
--%>



<%-- RegisterForm jsp for registering user  --%>

<link href="css/all.css" rel="stylesheet">
<link href="css/Login.css" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Organization Details</title>
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
                    <c:set var="pageTitle" scope="request" value="Registration For Organization"/>
                    <jsp:include page="Header.jsp" flush="true" />
                </td>
            </tr>
            <tr height="78%">
                <td id="middle-wrap" valign="middle">
                    <%-- The Following Input Fields to gathering details of user--%>
                    <%--This Form redirect t Registered --%>
                    <form:form method="POST" action="OrganizationDetails" modelAttribute="registrationForm">
                        <table>

                            <tr>
                                <td><form:label path="organizationFirst">Which Organization you want to do assesment? *</form:label></td>
                                <td><form:input path="organizationFirst" />
                                    <form:hidden path="userId" value="${user}"></form:hidden>
                                        <form:hidden path="pageTitle" value="${pageTitle}"></form:hidden>
                                </td> 
                                    <td><font color="red"> <form:errors path="organizationFirst"></form:errors></font></td>
                                </tr>

                                <tr>
                                    <td><form:label path="organizationEmployees">Enter Number of employees in Organization?*</form:label></td>
                               
                                     <td><form:select path="organizationEmployees" >
                                        <form:option selected="selected"  value="" label="-- Choose one--"/>
                                        <form:options  items="${employee}"/>

                                    </form:select></td> 
                                    
                                    
                                    
                              
                                
                                <td><font color="red"> <form:errors path="organizationEmployees"></form:errors></font></td>
                                </tr>
                                <tr>
                                    <td><form:label path="revenue">What is the Annual revenue?*</form:label></td>
                                 <td><form:select path="revenue" >
                                        <form:option selected="selected"  value="" label="-- Choose one--"/>
                                        <form:options  items="${annualIncome}"/>

                                    </form:select></td> 
                                <td><font color="red"> <form:errors path="revenue"></form:errors></font></td>
                                </tr>
                                <tr>
                                    <td><form:label path="organizationEmailId">Enter Organization Email Id?*</form:label></td>
                                <td><form:input path="organizationEmailId" /></td> 
                                <td><font color="red"> <form:errors path="organizationEmailId"></form:errors></font></td>
                                </tr>

                                <tr>
                                    <td><form:label path="location">Enter Organization Location ? *</form:label></td>
                                <td><form:input path="location" /></td>
                                <td><font color="red"> <form:errors path="location"></form:errors></font></td>
                                </tr>

                            <%--The Following buttons are  for displaying submitting user details --%>
                            <tr>     
                                <td colspan="2"><input type="submit" value="Submit Details" ></td>
                                <td><input type="button" value="Cancel" onclick="location = ''"></td>

                            </tr>
                            <%-- Back to Login page link id for redirecting welcome page --%>
                            <tr>
                                <td></td>
                                <td><ul><a href="Registration" >  Previous Page</a></ul></td>
                            </tr>

                        </table>

                    </form:form>

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