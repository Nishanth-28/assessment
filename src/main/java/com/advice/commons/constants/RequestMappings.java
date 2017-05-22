/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advice.commons.constants;

/**
 *
 * @author cjp
 */
public class RequestMappings {
   
    
    //AdminController
    public static final String ADMIN = "/Admin";
    public static final String POSTQUESTIONS = "/PostQuestions";
    public static final String SAVEQUESTIONS = "/saveQuestion";
    public static final String DELETEQUESTION = "/deleteQuestion";
    public static final String LISTOPTIONS = "/listOptions";
    
    public static final String UPDATEQUESTION = "/updateQuestion";
    public static final String GETPAGE = "/GetPage";
    public static final String SAVEPAGE = "/savePage";
    public static final String DELETEPAGE = "/deletePage";
    public static final String UPDATEPAGE = "/updatePage";
    public static final String GETBLOCK = "/GetBlock";
    public static final String SAVEBLOCK = "/saveBlock";
    public static final String DELETEBLOCK = "/deleteBlock";
    public static final String UPDATEBLOCK = "/updateBlock";
    public static final String GETRULE = "/GetRules";
    public static final String SAVERULE = "/saveRule";
    public static final String DELETERULE = "/deleteRule";
    public static final String UPDATERULE = "/updateRule";

   
    //Common Controller
    //LogIn Controller
    public static final String WELCOME = "/welcome";
    public static final String LOGOUT = "/logout";
    public static final String LOGOUTSESSION = "/logOutSession";
    public static final String SUBMITTEDLOGIN = "/SubmittedLogIn";

    //Question Controller
    //Registration Controller
    public static final String REGISTRATION = "/Registration";
    public static final String REGISTERED = "/Registered";
    
    public static final String ADMINREGISTRATION = "/AdminRegistration";
    public static final String ADMININREGISTERED = "/AdminRegistered";
    
    public static final String ORGANIZATIONDETAILS = "/OrganizationDetails";
    public static final String HELP = "/help";
    public static final String ACTIVATIONUSER = "/activation";
    
    //SubmittedLogIn Controller
    public static final String ADVISE = "/Advise";
    public static final String STARTADVISE = "/StartAdvice";
    public static final String ADVICEREPORT = "/AdviceReport";
}
