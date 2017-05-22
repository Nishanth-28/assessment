<%-- 
    Document   : Blocks
    Created on : 22 Jun, 2016, 12:47:59 PM
    Author     : cjp
--%>
<%-- Blocks jsp for Adding,Editing ,updating and deleting blocks in admin User Interface --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--This Page is for Redirect to pages like cloud compatability and Technology mapping Report --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<title>Popup Login and Register</title>
<link href="css/collaboration1.css" rel="stylesheet">
<script src="js/jquery-3.0.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="css/elements.css" />

<link href="css/all.css" rel="stylesheet">

<link href="css/collaboration.css" rel="stylesheet">
<link href="css/Admin.css" rel="stylesheet">

<script src="js/datasorter.js" type="text/javascript" ></script>
<script type="text/javascript" src="js/block.js"></script>

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
             $(document).ready(function () {
                $('#myTable').DataTable( {
         "lengthMenu": [[-1,10, 25, 50 ], ["All",10, 25, 50]]
    } );
    });

        
            $(document).ready(function () {
                 $('#myTable').DataTable();
                 $("a[rel*=leanModal]").leanModal({top: 200, overlay: 0.6, closeButton: ".modal_close"});
                var blocksId = 0;
                $('a').click(function () {
                   var a = $(this).parent().parent().parent().parent().parent().parent();
               
                    var data;
                    var table = $('#myTable').DataTable();

                    data = table.row(a).data();

                    blocksId = $(this).attr('id');
                   

                    if (blocksId === '0' || blocksId === 'undefined') {
                        $("#sub").attr("formaction", "${pageContext.request.contextPath}/saveBlock");
                          var s = document.getElementById('lastblockId').value;

                        $("#blockIdlbl").attr("value", parseInt(s) + 1);
                        $("#blockId").attr("value", parseInt(s) + 1);
                         $("#blockTitle").attr("value","");
                    } else {
                         $("#sub").attr("formaction", "${pageContext.request.contextPath}/updateBlock?blocksId=" + blocksId);
                        $("#blockIdlbl").attr("value", data[0]);
                         $("#blockId").attr("value",data[0]);
                         $("#blockTitle").attr("value",data[1]);
                        var res = data[1].split(" ");
                        for (i = 0; i < res.length; i++) {
                            $("#questionSet").attr("value", res[0]);

                        }

                    }
                    $("#questionSet").click(function () {
                       
                        var data1 = table.rows().data();
                        
                        data1.each(function (value, index) {
                            
                             var selectedValues = [];
                        $("#questionSet :selected").each(function () {
                            selectedValues.push($(this).val());
                            if(value!==selectedValues){
                                 
                             }
                        });
                            
                        });
                    });

                });
                  $("a[rel*=leanModal]").leanModal({top: 200, overlay: 0.6, closeButton: ".modal_close"});
               
    
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
        <title>Advice Report</title>
    </head>
    <body>
        <table  width="100%" height="100%">
            <tr height="17%">
                <td  valign="top">
                    <c:set var="pageTitle" scope="request" value="Blocks Configured"/>
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
                                                <a rel="leanModal" id="0" href="#modal" class="btn">Add</a>

                                            </td>
                                            <td><a href="GetPage">Pages</a></td>
                             
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
                                                        <td>Block Id</td>
                                                        <td>Title</td>
                                                        <td>Question Id</td>                                                        
                                                        <td>Action</td>
                                                    </tr>
                                                </thead>
                                                <tbody>

                                                    <c:forEach var="listVal" items="${blocksList}">
                                                        <c:set var="actions" value="${listVal.blockId}"/>
                                                        <tr>
                                                                <td>${listVal.blockId}</td>  
                                                                <td>${listVal.blockTitle}</td>
                                                                <td> ${listVal.questionSetMap.keySet()}</td>
                                                               <td>
                                                                    <table id="actionTable" border="0">
                                                                        <tr>
                                                                            <td align="left">
                                                                                <a rel="leanModal" href="#modal" class="btn" id="${listVal.blockId}">update</a>
                                                                            </td>
                                                                            <td align="right">
                                                                                <input type="submit"value="Delete" formaction="deleteBlock?blockId=${listVal.blockId}"  onclick="return confirm('Are you want to delete?');">

                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                </td>

                                                            </tr>  
                                                        <c:forEach var="questionId" items="${listVal.questionSetMap}">

                                                           
                                                        </c:forEach>

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
                                <form:form id="fm" method="POST"  modelAttribute="blockForm" >
                                    <table>
                                        
                                         <tr>
                                            <td><form:hidden path="lastblockId"  id="lastblockId" value="${lastBlockId}"></form:hidden>                                         
                                            <form:label path="blockId"  >blockId*</form:label></td>
                                            <td><form:hidden path="blockId" value="${lastblockId+1}" />
                                                <input id="blockIdlbl" type="text" disabled="true"/>
                                            </td> 
                                          
                                        </tr>
                                        <tr>
                                            <td>
                                            <form:label path="blockTitle"  >blockTitle*</form:label></td>
                                            <td>
                                                <input id="blockTitle" name="blockTitle" type="text" value="" />
                                            </td> 
                                          
                                        </tr>

                                        <tr>
                                            <td><form:label path="questionSet"> questionId*</form:label></td>  
                                            <td><form:select path="questionSet" multiple="true" >
                                                     <form:options items="${questionList}" />
                                                </form:select></td>
                                           
                                                
                                            </tr>
                                            
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














