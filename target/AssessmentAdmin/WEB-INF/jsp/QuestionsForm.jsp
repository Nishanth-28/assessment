<%-- 
    Document   : QuestionsForm
    Created on : 6 Jul, 2016, 12:58:29 PM
    Author     : cjp
--%>
<%-- QuestionsForm jsp for Adding,Editing ,updating and deleting blocks in admin User Interface --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<title>Popup Login and Register</title>

<script src="js/jquery-3.0.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="css/elements.css" />

<link href="css/all.css" rel="stylesheet">

<link href="css/collaboration.css" rel="stylesheet">
<link href="css/collaboration1.css" rel="stylesheet">
<script src="js/datasorter.js" type="text/javascript" ></script>
<script type="text/javascript" src="js/question.js"></script>

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


                var questionId = 0;

                $('a').click(function () {
                    questionId = $(this).attr('id');
                    var a = $(this).parent().parent().parent().parent().parent().parent();
                    var data;
                    var table = $('#myTable').DataTable();
                    data = table.row(a).data();



                    if (questionId === '0' || questionId === 'undefined') {
                       
                        $("#sub").attr("formaction", "${pageContext.request.contextPath}/saveQuestion");

                    } else {
                        $("#sub").attr("formaction", "${pageContext.request.contextPath}/updateQuestion?questionId=" + questionId);
                        $("#question").attr("value", data[1]);
                        $("#mandatory option:selected").text(data[3]);
                        $("#UIItem option:selected").text(data[2]);



                    }

                    var ancherId = $(this).parent().attr('id')
                    if (ancherId === 'more') {
                        $("#moreoptions").children().remove();

                        var options = "optionText" + data[0];


                        $("#moreoptions").append('<tr><td>' + eval(options) + '</td></tr>');

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
                    <c:set var="pageTitle" scope="request" value="Questions Configured"/>
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
                                                        <td>QuestionId</td>
                                                        <td>Question</td>
                                                        <td>UIItem</td> 
                                                        <td>Mandatory</td>
                                                        <td>options</td>
                                                        <td>Action</td>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="listVal" items="${questionsList}">
                                                        <tr >  
                                                            <td >${listVal.questionId}</td>
                                                            <td >${listVal.question}</td>
                                                            <td>${listVal.UIItem}</td>
                                                            <td>${listVal.mandatory}</td>
                                                            <td>

                                                                <c:set var="optiontext" value=""/>
                                                                <c:set var="showtext" value="" />
                                                                <c:forEach var="options" items="${listVal.options}">
                                                                    <c:set var="optiontext" value="${optiontext}${options.option},"/>
                                                                </c:forEach>
                                                                <c:if test="${fn:length(optiontext)>=25}">
                                                                    <script>
                                                                        var optionText${listVal.questionId} = "${optiontext}";


                                                                    </script>
                                                                    <c:set var="showtext" value="${fn:substring(optiontext, 0, 25)}" />
                                                                    ${showtext}
                                                                    <table border="0">
                                                                        <tr>
                                                                            <td align="left" id="more" >
                                                                                <a  rel="leanModal" href="#modal1" >more...</a>
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                </c:if>
                                                                <c:if test="${fn:length(optiontext)<25}">
                                                                    ${optiontext}
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <table id="actionTable" border="0">
                                                                    <tr>
                                                                        <td align="left">
                                                                            <a rel="leanModal" href="#modal" class="btn" id="${listVal.questionId}">update</a>
                                                                        </td>
                                                                        <td align="right">
                                                                            <input type="submit"value="Delete" formaction="deleteQuestion?questionId=${listVal.questionId}" onclick="return confirm('Are you want to delete?');">

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

                        </tbody>
                    </table>

                    <div id="modal" class="popupContainer" style="display:none;">
                        <header class="popupHeader">
                            <span class="header_title">Page</span>
                            <span class="modal_close"><i class="fa fa-times"></i></span>
                        </header>
                        <section class="popupBody">
                            <form:form id="fm" method="POST"  modelAttribute="adminQuestionForm">
                                <table style="width:100%;table-layout:fixed;"  >

                                    <tr >

                                        <td><form:hidden path="questionId" value="${questionInput.questionId}"></form:hidden>

                                        </tr>


                                        <tr>
                                        <td><form:label path="question">Question</form:label></td>
                                        <td><form:input path="question" id="question" /></td> 

                                    </tr>

                                    <tr>
                                        <td ><form:label path="UIItem">UIItem</form:label></td>
                                        <td><form:select path="UIItem">
                                                 <form:option path="UIItem" selected="selected"  value="" label="-- Choose one--"/> 
                  
                                                <form:option path="UIItem" value="text"/>
                                                <form:option path="UIItem" value="select"/>
                                                <form:option path="UIItem" value="radio"/>
                                                <form:option path="UIItem" value="checkbox"/>
                                            </form:select>
                                        </td> 
                                    </tr>

                                    <tr>
                                        <td ><form:label path="mandatory">Mandatory</form:label></td>
                                        <td><form:select path="mandatory" >
                                                 <form:option path="mandatory" selected="selected"  value="" label="-- Choose one--"/> 
                                              
                                                <form:option path="mandatory" value="yes"/>
                                                <form:option path="mandatory" value="no"/>                      
                                            </form:select>

                                        </td> 
                                    </tr>
                                    <tr>
                                        <td ><form:label path="questionType">questionType</form:label></td>
                                        <td><form:select path="questionType" >
                                                <form:option path="questionType" selected="selected"  value="" label="-- Choose one--"/> 
                               
                                                <form:option path="questionType" value="common"/>
                                                <form:option path="questionType" value="specific"/>                      
                                            </form:select>

                                        </td> 
                                    </tr>
                                     <tr>
                                        <td ><form:label path="ruleRequired">Rule required</form:label></td>
                                        <td><form:select path="ruleRequired" >
                                                <form:option path="ruleRequired" selected="selected"  value="" label="-- Choose one--"/> 
                                                <form:option path="ruleRequired" value="yes"/>
                                                <form:option path="ruleRequired" value="no"/>                      
                                            </form:select>

                                        </td> 
                                    </tr>
                                    <tr>
                                        <td><form:label path="options"> options for Question </form:label></td>     
                                        <td><form:label path="options"> Generated Question Id </form:label></td>

                                        </tr>

                                        <tr width="100%">
                                            <td><input type="text" name="options[0].option" ></td>
                                            <td><input type="text" name="options[0].questionId" ></td>
                                        </tr>

                                        <tr id="items">

                                        </tr>

                                        <tr>
                                            <td><input id="add"  type="button" value="Add Option" /></td>
                                            <td><input id="delete"  type="button" value="Delete Option" /></td>
                                        </tr>

                                        <tr>
                                           
                                            <td></td>
                                            <td>  
                                                <input type="submit" id="sub" value="Submit" formmethod="post" formaction=""/></td>
                                        </tr>

                                    </table>
                            </form:form> 


                    </div>
                    <div id="modal1" class="popupContainer" style="display:none;">
                        <header class="popupHeader">
                            <span class="header_title">Options</span>
                            <span class="modal_close"><i class="fa fa-times"></i></span>
                        </header>
                        <section class="popupBody">
                            <table style="width:100%;table-layout:fixed;" id="moreoptions">

                         </table>


                    </div>

                    </section>

                    </div>
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


