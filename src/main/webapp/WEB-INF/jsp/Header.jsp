<%-- 
    Document   : Header
    Created on : 19 Aug, 2016, 3:57:57 PM
    Author     : cjp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link href="css/Header.css" rel="stylesheet">

<table id="maintbl" border="0" width="100%" color="red">
    <tr>
        <td width="20%" align="left" rowspan="2"> <img src="css/images/cloudjournee2.png" alt="" />  </td>

        <td align="center" valign="middle" id="mainHead">Assessment Administration</td>
        <td width="20%" valign="bottom" rowspan="2">
            <table border="0" width="100%">
               
                <tr> <td align="right" valign="bottom" style="visibility: ${hidden}"><a href="logout">Logout</a></td></tr>
            </table>
        </td>
    </tr>
    <tr>
        <td align="center" valign="bottom" id="dynamicHead">${pageTitle}</td>

    </tr>
</table>

