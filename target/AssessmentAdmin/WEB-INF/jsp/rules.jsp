<%-- 
    Document   : rules
    Created on : 20 Jun, 2016, 2:17:55 PM
    Author     : cjp
--%>
<%-- Rules page jsp for editing ,deleting and adding rules for questions--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--This Page is for Redirect to pages like cloud compatability and Technology mapping Report --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<title>Popup Login and Register</title>

<script src="js/jquery-3.0.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="css/elements1.css" />

<link href="css/all.css" rel="stylesheet">

<link href="css/collaboration1.css" rel="stylesheet">

<script src="js/datasorter.js" type="text/javascript" ></script>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" >
            $(document).ready(function () {
                $('#myTable').DataTable({
                    "lengthMenu": [[-1,10, 25, 50 ], ["All",10, 25, 50]]
                });

                $("#questionId").change(function () {

                    var questionId = $("#questionId").val();

                    $("#choices tr").each(function () {
                        this.parentNode.removeChild(this);
                    });

                    // $("#choices").find('input:text').last().closest('tr').remove();
                    //$("#tbApp").append('<tr><td><input type="text" name="applications[' + i++ + '].applicationName" id="applications[' + j++ + '].applicationName" class="form-control"/></td> </tr> ');
                    // $("#choices").children().remove();
                    var cloudList = availbleCloudList.split(",");
                    var cloudIdList = availbleCloudIdList.split(",");
                    var options = "optionText" + questionId;
                    var optionLst = eval(options);

                    var choicesOfQuestion = optionLst.split(",&=");

                    $("#ruletable thead").remove();
                    $("#ruletable tbody").remove();
                    $("#ruletable").append("<thead><tr><td colspan='2' >Choice</td></tr></thead>");
                    for (var j in cloudList) {
                        if (cloudList[j] !== '') {

                            $('#ruletable thead').find('tr:last').append('<td colspan="2">' + cloudList[j] + ' </td>');

                        }

                    }
                    $('#ruletable thead').find('tr:last').append('<td colspan="2">Remarks</td>');
                    $("#ruletable").append("<tbody></tbody>");
                    for (var i in choicesOfQuestion) {
                        if (choicesOfQuestion[i] !== '') {
                            $("#ruletable tbody").append('<tr><td colspan="2">' + choicesOfQuestion[i] + '<input type="hidden" name="ruleForm[' + i + '].choiceName" id="choiceName" class="form-control" value="' + choicesOfQuestion[i] + '"/></td></tr>');
                            for (var j in cloudList) {
                                if (cloudList[j] !== '') {

                                    $('#ruletable tbody').find('tr:last').append('<td colspan="2"><input type="text" name="ruleForm[' + i + '].scoring[' + j + '].score" id="ruleForm[' + i + '].scoring[' + j + '].score" class="form-control"/> <input type="hidden" name="ruleForm[' + i + '].scoring[' + j + '].cloudName" id=ruleForm[' + i + '].scoring[' + j + '].cloudName" class="form-control" value="' + cloudList[j] + '"/> <input type="hidden" name="ruleForm[' + i + '].scoring[' + j + '].cloudId" id="ruleForm[' + i + '].scoring[' + j + '].cloudId" class="form-control" value="' + cloudIdList[j] + '"/></td>');

                                }


                            }
                            $("#ruletable tbody").find('tr:last').append('<td colspan="2"><input type="text" name="ruleForm[' + i + '].remarks" id="ruleForm[' + i + '].remarks" class="form-control"/></td>');
                        }
                    }


                });

                $('#myTable').DataTable();

                $('a').click(function () {
                    

                    var selectedIdText = $(this).attr('id');
                    var obj = selectedIdText.split(",&=");

                    var questionId = obj[0];

                    var choice = obj[1];
                    var data;
                    var table = $('#myTable').DataTable();
                    var a = $(this).parent().parent().parent().parent().parent().parent();
                    data = table.row(a).data();
                    $("#sub").attr("formaction", "${pageContext.request.contextPath}/updateRule?questionId=" + questionId);
                    $("#questionId option:selected").each(function () {
                            $(this).removeAttr('selected');
                        });
                       
                    if (questionId === '0' || questionId === 'undefined') {

                        $("#ruletable thead").remove();
                        $("#ruletable tbody").remove();
                        $('#questionId').attr('disabled', false);
                       if( $('#questionId option:selected').text()!=='-- Choose one--'){
                            $('#questionId option:selected').text('-- Choose one--');
                       }
                        $("#sub").attr("formaction", "${pageContext.request.contextPath}/saveRule");
                    } else {

                        $("#ruletable thead").remove();
                        $("#ruletable tbody").remove();
                        $("#sub").attr("formaction", "${pageContext.request.contextPath}/updateRule?questionId=" + questionId + "&choice=" + choice);
                        $("#questionId option:selected").text(data[3]);
                        $('#questionId ').attr('disabled', true);
                        $("#choices tr").each(function () {
                            this.parentNode.removeChild(this);
                        });
                        // $("#choices").find('input:text').last().closest('tr').remove();
                        //$("#tbApp").append('<tr><td><input type="text" name="applications[' + i++ + '].applicationName" id="applications[' + j++ + '].applicationName" class="form-control"/></td> </tr> ');
                        // $("#choices").children().remove();
                        var cloudList = availbleCloudList.split(",");
                        var cloudIdList = availbleCloudIdList.split(",");
                        // var options = "optionText" + questionId;
                        // var optionLst = eval(options);

                        var choicesOfQuestion = data[1].split("Score:");
                        var choices = choicesOfQuestion[1].replace("<br>", "");
                        var newchoice = choices.split(",");


                        $("#ruletable thead").remove();
                        $("#ruletable tbody").remove();
                        $("#ruletable").append("<thead><tr><td>Choice</td></tr></thead>");
                        for (var j in cloudList) {
                            if (cloudList[j] !== '') {

                                $('#ruletable thead').find('tr:last').append('<td>' + cloudList[j] + ' </td>');

                            }

                        }
                        $('#ruletable thead').find('tr:last').append('<td>Remarks</td>');
                        $("#ruletable").append("<tbody></tbody>");

                        $("#ruletable tbody").append('<tr><td>' + choice + '<input type="hidden" name="ruleForm[' + 0 + '].choiceName" id="ruleForm[' + 0 + '].choiceName" class="form-control" value="' + choice + '"/><input type="hidden" name="ruleForm[' + 0 + '].questionId" id="choiceName" class="form-control" value="' + questionId + '"/></td></tr>');
                        for (var j in cloudList) {
                            if (cloudList[j] !== '') {

                                $('#ruletable tbody').find('tr:last').append('<td><input type="text" name="ruleForm[' + 0 + '].scoring[' + j + '].score" id="ruleForm[' + 0 + '].scoring[' + j + '].score" class="form-control" value="' + newchoice[j] + '"/> <input type="hidden" name="ruleForm[' + 0 + '].scoring[' + j + '].cloudName" id=ruleForm[' + 0 + '].scoring[' + j + '].cloudName" class="form-control" value="' + cloudList[j] + '"/> <input type="hidden" name="ruleForm[' + 0 + '].scoring[' + j + '].cloudId" id="ruleForm[' + 0 + '].scoring[' + j + '].cloudId" class="form-control" value="' + cloudIdList[j] + '"/></td>');

                            }
                        }
                        $("#ruletable tbody").find('tr:last').append('<td><input type="text" name="ruleForm[' + 0 + '].remarks" id="ruleForm[' + 0 + '].remarks" class="form-control" value="' + data[2] + '"/></td>');

                        $('#fm').submit(function () {
                            $('#questionId').attr('disabled', false);
                        });

                        
                    }

                });$("#questionId").attr('selectedIndex', '-1').find("option:selected").removeAttr("selected");
                
                $("a[rel*=leanModal]").leanModal({top: 200, overlay: 0.6, closeButton: ".modal_close"});

            });
            function openWindow1()
            {
                var URL = "help";
                window.open(URL, "RecoverPassword", "width=700,height=450");
            }

            function submitForm(action) {

                document.getElementById("qtta").action = action; //Setting form action to "success.php" page
                document.getElementById("qtta").submit(); // Submitting form

                return true;
            }

        </script>



        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Advice Report</title>
    </head>
    <body>
        <table  width="100%" height="100%">
            <tr height="17%">
                <td  valign="top">
                    <c:set var="pageTitle" scope="request" value="Rules Engine"/>
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
                                                <a rel="leanModal" href="#modal" id="0" class="btn">Add</a>
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

                                            <c:set var="availbleCloudsl" value=""/>
                                            <c:set var="availbleCloudslId" value=""/>
                                            <c:forEach var="availableCloud" items="${availbleClouds}">
                                                <c:set var="availbleCloudsl" value="${availbleCloudsl}${availableCloud.cloudName},"/>
                                                <c:set var="availbleCloudslId" value="${availbleCloudslId}${availableCloud.cloudId},"/>
                                            </c:forEach>
                                            <script>
                                                var availbleCloudList = "${availbleCloudsl}";
                                                var availbleCloudIdList = "${availbleCloudslId}";
                                            </script>

                                            <table id="myTable">

                                                <thead>
                                                    <tr>
                                                        <td>Choice</td>
                                                        <td>Scoring</td> 
                                                        <td>Remarks</td>
                                                        <td>Question Id </td>
                                                        <td>Action</td>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="ruleEngineForm" items="${rulesEngineFormList}">


                                                        <c:forEach var="ruleForm" items="${ruleEngineForm.ruleForm}">
                                                            <tr> 
                                                                <td>${ruleForm.choiceName}  </td>
                                                                <td>  Cloud:<c:forEach var="score" items="${ruleForm.scoring}"> ${score.cloudId},  </c:forEach>  <br>
                                                                    Cloud Name:<c:forEach var="score" items="${ruleForm.scoring}"> ${score.cloudName},  </c:forEach>  <br>
                                                                    Score:<c:forEach var="score" items="${ruleForm.scoring}"> ${score.score},</c:forEach>  <br>

                                                                    </td>

                                                                    <td>${ruleForm.remarks}</td>

                                                                <td>${ruleForm.questionId}</td>
                                                                <td>
                                                                    <table id="actionTable" border="0">
                                                                        <tr>
                                                                            <td align="left">
                                                                                <a rel="leanModal" href="#modal" class="btn" id="${ruleForm.questionId},&=${ruleForm.choiceName}">update</a>
                                                                            </td>
                                                                            <td align="right">
                                                                                <input type="submit"value="Delete" formaction="deleteRule?questionId=${ruleForm.questionId}&choice=${ruleForm.choiceName}" onclick="return confirm('Are you want to delete?');">

                                                                            </td>
                                                                        </tr>
                                                                    </table>

                                                                </td>

                                                            </tr> 


                                                        </c:forEach>

                                                    </c:forEach>


                                                    <c:forEach var="listVal" items="${questionsList}">
                                                        <c:set var="optiontext" value=""/>
                                                        <c:forEach var="options" items="${listVal.options}">                                                           
                                                            <c:set var="optiontext" value="${optiontext}${options.option},&="/>
                                                        </c:forEach>

                                                    <script>
                                                        var optionText${listVal.questionId} = "${optiontext}";
                                                    </script>
                                                </c:forEach>   

                                                </tbody>
                                            </table>
                                        </form:form>

                                    </div>
                        </tbody>
                    </table>


                    <div id="modal" class="popupContainer" style="display:none;">
                        <header class="popupHeader">
                            <span class="header_title">Manage Rules</span>
                            <span class="modal_close"><i class="fa fa-times"></i></span>
                        </header>

                        <section class="popupBody">
                            <div class="social_login">
                                <div>

                                    <form:form id="fm" method="POST"  modelAttribute="rulesEngineForm" >
                                        <table>
                                            <tr>
                                                <td><table>

                                                        <tr>
                                                            <td><form:label path="questionId" ><b>Question Id</b></form:label></td>
                                                            <td><form:select path="questionId">
                                                                    <form:option path="questionId" selected="selected"  value="" label="-- Choose one--"/> 
                                                                    <c:forEach var="question" items="${questionsList}">
                                                                        
                                                                             <form:option path="questionId" value="${question.questionId}" label="${question.questionId} - ${question.question}"></form:option> 
                                                                       
                                                                    </c:forEach>

                                                                </form:select></td>
                                                        </tr>        

                                                    </table>        
                                                </td>            

                                            </tr>
                                            <tr>

                                                <td><table id="ruletable" border="0">
                                                    </table>
                                                </td>
                                            </tr>


                                            <tr>

                                                <td align="center">  
                                                    <input type="submit" id="sub" value="Submit" formmethod="post" formaction=""/></td>
                                            </tr>

                                        </table>
                                    </form:form> 

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

