<%-- 
    Document   : Page
    Created on : 20 Jun, 2016, 2:17:55 PM
    Author     : cjp
--%>
<%-- Blocks jsp for Adding,Editing ,updating and deleting blocks in admin User Interface --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<title>Popup Login and Register</title>

<script src="js/jquery-3.0.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="css/elements.css" />

<link href="css/all.css" rel="stylesheet">
<link href="css/collaboration1.css" rel="stylesheet">
<link href="css/collaboration.css" rel="stylesheet">
<link href="css/Admin.css" rel="stylesheet">
<script src="js/datasorter.js" type="text/javascript" ></script>
<script type="text/javascript" src="js/page.js"></script>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" >

 $(document).ready(function () {
                $('#myTable').DataTable( {
        "lengthMenu": [[-1,10, 25, 50 ], ["All",10, 25, 50]]
    } );
    });

            $(document).ready(function () {
                $('#myTable').DataTable();
                var pageId = 0;

                $('a').click(function () {
                    pageId = $(this).attr('id');
                    var a = $(this).parent().parent().parent().parent().parent().parent();

                    var data;
                    var table = $('#myTable').DataTable();
                    
                    data = table.row(a).data();
                    if (pageId === '0' || pageId === 'undefined') {


                        $("#sub").attr("formaction", "${pageContext.request.contextPath}/savePage");
                        var s = document.getElementById('lastpageId').value;

                        $("#pageIdlbl").attr("value", parseInt(s) + 1);
                         $("#pageTitle").attr("value", "");

                    } else {
                        

                        $("#sub").attr("formaction", "${pageContext.request.contextPath}/updatePage?pageId=" + pageId);
                        $("#pageIdlbl").attr("value", data[0]);

                        $("#pageTitle").attr("value", data[1]);
                        var res = data[2].split(" ");
                        for (i = 0; i < res.length; i++) {
                            $("#blockSet").attr("value", res[0]);

                        }
                    }
                    
               
});
                

                $("a[rel*=leanModal]").leanModal({top: 200, overlay: 0.6, closeButton: ".modal_close"});
               
            });
            function submitForm(action) {

                document.getElementById("qtta").action = action; //Setting form action to "success.php" page
                document.getElementById("qtta").submit(); // Submitting form

                return true;

            }


            function openWindow1()
            {
                var URL = "help";
                window.open(URL, "RecoverPassword", "width=700,height=450");
            }
        </script>



        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Advice Report</title>
    </head>
    <body>
        <table  width="100%" height="100%">
            <tr height="17%">
                <td  valign="top">
                    <c:set var="pageTitle" scope="request" value="Pages Configured"/>
                    <jsp:include page="Header.jsp" flush="true" />
                </td>
            </tr>
            <tr height="78%">
                <td id="middle-wrap" valign="middle">
                    <table>
                        <thead>
                            <tr>
                                <td>
                                    <table>
                                        
                                        <tr>
                                            <td align="left">
                                                <form:form id="qtta" method="POST" >  
                                                    <a href="javascript:submitForm('Admin')"><font color="red">Previous</font></a>    
                                                    </form:form> 
                                            </td>
                                            <td align="right">
                                                <a rel="leanModal" href="#modal" id="0" class="btn">Add Page</a>
                                            </td>
                                            
                              <td><a href="GetBlock">Blocks</a></td>
                             <td><a href="PostQuestions">Questions</a></td>
                             <td><a href="GetRules">Rules</a></td>     
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>



                                    <div>
                                        <form:form id="qtta" method="POST" >


                                            <table id="myTable">

                                                <thead>

                                                    <tr>
                                                        <td>Page Id</td>
                                                        <td>Page Title</td>
                                                        <td>Block</td>
                                                        <td>Action</td>
                                                    </tr>
                                                </thead>
                                                <tbody>

                                                    <c:forEach var="listVal" items="${pagesList}">
                                                        <tr > 
                                                            <td>${listVal.pageId}</td>
                                                            <td>${listVal.pageTitle}</td>
                                                            <td>
                                                                <c:forEach var="questionId" items="${listVal.blockSetMap}">
                                                                    ${questionId.value},
                                                                </c:forEach>
                                                            </td>
                                                            <td>
                                                                <table id="actionTable" border="0">
                                                                    <tr>
                                                                        <td align="left">
                                                                            <a rel="leanModal" href="#modal" class="btn" id="${listVal.pageId}">update</a>
                                                                        </td>
                                                                        <td align="right">
                                                                            <input type="submit"value="Delete" formaction="deletePage?pageId=${listVal.pageId}"  onclick="return confirm('Are you want to delete?');">

                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </td>

                                                        </tr>       
                                                    </c:forEach>

                                                </tbody>
                                            </table>
                                        </form:form>
                                    </div>


                                </td>
                            </tr>
                        </tbody>
                    </table>



                    <div id="modal" class="popupContainer" style="display:none;">
                        <header class="popupHeader">
                            <span class="header_title">Page</span>
                            <span class="modal_close"><i class="fa fa-times"></i></span>
                        </header>

                        <section class="popupBody">
                            <div class="social_login">
                                <form:form id="fm" method="POST"  modelAttribute="pageForm" >
                                    <table>
                                        <tr>
                                            <td><form:hidden path="lastpageId"  id="lastpageId" value="${lastPageId}"></form:hidden>
                                                <form:label path="pageId" >PageId*</form:label></td>
                                            <td><form:hidden path="pageId" value="${lastPageId+1}" />
                                                <input id="pageIdlbl" type="text" disabled="true"/>
                                            </td> 
                                        </tr>
                                        <tr>
                                            <td><form:label path="pageTitle" >PageTitle*</form:label></td>
                                            <td><form:input path="pageTitle" /></td> 
                                        </tr>                   

                                        <tr>
                                            <td><form:label path="blockSet"> Block*</form:label></td> 
                                            <td><form:select multiple="true" path="blockSet">
                                                    <c:forEach var="block" items="${blockMap}">
                                                    
                                                         <form:option path="" label="${block.value} - ${block.key}" value="${block.key}" />
                                                    </c:forEach>
                                                   
                                                </form:select></td>

                                            <!--                                                <td id="items"><input type="text" name="blockSet" ></td>-->
                                        </tr>
                                        <%-- The Following buttons for adding and deleting blocks--%>
                                        <!--                                        <tr>
                                                                                    <td><input id="add"  type="button" value="Add Block" /></td>                            
                                                                                    <td><input id="delete"  type="button" value="Delete Block" /></td>
                                                                                </tr>-->
                                        <tr>
                                            <td></td>

                                            <td>  
                                                <input type="submit" id="sub" value="Submit" formmethod="post" formaction=""/></td>
                                        </tr>

                                    </table>
                                </form:form>

                            </div>



                        </section>
                    </div>



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

