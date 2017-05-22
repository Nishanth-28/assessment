<%-- 
    Document   : RegisterForm
    Created on : 13 May, 2016, 12:17:32 PM
    Author     : cjp
--%>

<%-- RegisterForm jsp for registering user  --%>
<link href="css/all.css" rel="stylesheet">
<link href="css/Login.css" rel="stylesheet">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/countries.js"></script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" >

            function submitForm(action) {

                document.getElementById("qtta").action = action; //Setting form action to "success.php" page
                document.getElementById("qtta").submit(); // Submitting form

                return true;

            }
        </script>
        <script type="text/javascript">

            $(function () {
                var $select = $('select');
                $select.on('change', function () {
                    var output = 0;
                    for (var i = 0, len = $select.length; i < len; i++) {
                        output += parseInt($select.eq(i).val());
                    }
                    //the `output` variable now contains the addition of all the values from the `acabados[]` select elements
                });
            });
        </script>
        <script type="text/javascript">
            function openWindow1()
            {
                var URL = "help";
                window.open(URL, "RecoverPassword", "width=700,height=450");
            }
        </script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Form</title>
    </head>
    
    <body onsubmit="populateCountries('countries', 'states')">
   
       
    </div>
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
                                   <%-- The Following Input Fields to gathering details of user--%>
                    <%--This Form redirect t Registered --%>
                    <form:form method="POST" action="AdminRegistered" modelAttribute="adminRegistrationForm" onsubmit="return populateCountries()">
                         <table  height="100%" width="50%" align="left" rowspan="2">     

                            <tr>
                                <td><form:label path="firstName">First Name*</form:label></td>
                                <td><form:input path="firstName" /></td> 
                                <td><form:hidden path="pageTitle" value="${pageTitle}"></form:hidden>
                                    <font color="red"> <form:errors path="firstName"></form:errors></font></td>
                                </tr>
                                <tr>
                                    <td><form:label path="middleName">Middle Name</form:label></td>
                                <td><form:input path="middleName" /></td> 
                            </tr>
                            <tr>
                                <td><form:label path="lastName">Last Name*</form:label></td>
                                <td><form:input path="lastName" /></td>
                                <td><font color="red"> <form:errors path="lastName"></form:errors></font></td>
                                </tr>
                                <tr>
                                    <td><form:label path="emailId">Official Email id*</form:label></td>
                                <td><form:input path="emailId" /></td> 
                                <td><font color="red"> <form:errors path="emailId"></form:errors></font>
                               </td>
                                
                            </tr>
                               <tr>
                                 <td><form:label path="address">Address*</form:label></td>
                                 <td><form:input path="address" /></td> 
                                 <td><font color="address"> <form:errors path="address"></form:errors></font></td>
                                </tr>
                                <tr>
                                    <td><form:label path="designations">Designation*</form:label></td>
                                <td><form:select path="designations" >
                                        <form:option selected="selected"  value="" label="-- Choose one--"/> 
                                        <form:options  items="${designation}"/>
                                    </form:select></td> 
                                <td><font color="red"> <form:errors path="designations"></form:errors></font></td>
                                </tr>
                  </table>  

                <table  height="100%" width="50%" align="right" rowspan="2">   
                    <tr>
                        <td><form:label path="phone">Phone Number*</form:label></td>
                    <td><form:input path="phone" /></td>
                    <td><font color="red"> <form:errors path="phone"></form:errors></font></td>
                    </tr>
                 
                <tr>
                    <td><form:label path="userId">Create User Id*</form:label></td>
                    <td><form:input path="userId" /></td> 
                    <td><font color="red"> <form:errors path="userId"></form:errors></font></td>
                    </tr>
                    <tr>
                        <td><form:label path="pass1">Password*</form:label></td>
                    <td><form:password path="pass1" /></td> 
                    <td><font color="red"> <form:errors path="pass1"></form:errors></font>
                   </td>    
                    </tr>
                    <tr>
                        <td><form:label path="pass2">Conform Password*</form:label></td>
                    <td><form:password path="pass2" /></td> 
                    <%--form:errors is for displaying errors --%>
                    <td><font color="red"> <form:errors path="pass2"></form:errors></font></td>
                    </tr>
                <%--The Following buttons are  for displaying submitting user details --%>
                <tr>     
                    <td colspan="2"><input type="submit" value="Create Account" ></td>
                    <td><input type="button" value="Cancel" onclick="this.form.reset()"></td>
               </tr>
                </form:form>
                <%-- Back to Login page link id for redirecting welcome page --%>
                 <tr>
                    <td></td>
                    <td align="left">
                         <form:form id="qtta" method="POST" >  
                             <a href="javascript:submitForm('Admin')"><ul>Previous</ul></a>    
                         </form:form> 
                    </td>
                </tr>
        </table>

          
                   
                     <script language="javascript">
            window.onload = function () { populateCountries("countries", "states"); }
           
        </script>
                </td>
                
            </tr>
            <tr height="5%">
                <td  valign="bottom">
                    <jsp:include page="Footer.jsp" flush="true" />
                </td>

            </tr>
        </table>
    </body>
</html>
