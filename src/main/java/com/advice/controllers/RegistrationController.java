/**
 * The Registration controller class for autherizations to different organizations and  users
 *
 * @author  CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.controllers;

import com.advice.commons.constants.ModelAttributesConst;
import com.advice.commons.constants.RequestMappings;
import com.advice.commons.constants.ReturnConst;
import com.advice.commons.exceptions.ApplicationException;
import com.advice.commons.exceptions.ConnectivityException;
import com.advice.validators.RegistrationValidator;
import com.advice.commons.exceptions.InvalidSessionException;
import com.advice.forms.RegistrationForm;
import com.advice.forms.UserLogInForm;
import com.advice.services.IntializationService;
import com.advice.services.RegistrationService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @InitBinder Annotation that identifies methods which initialize the
 * WebDataBinder which will be used for populating command and form object
 * arguments of annotated handler methods.
 * @ModelAttribute Annotation that binds a method parameter or method return
 * value to a named model attribute, exposed to a web view. Supported for
 * controller classes with '@RequestMapping methods '.
 * @RequestMapping Annotation for mapping web requests onto specific handler
 * classes and/or handler methods. Provides a consistent style between Servlet
 * and Port let environments.
 * @SessionAttributes Annotation that indicates the session attributes that a
 * specific handler uses. This will typically list the names of model
 * attributes. which should be transparently stored in the session or some
 * conversational storage, serving as form-backing beans.
 * @Autowired Marks a constructor, field, setter method or configuration method
 * as to be auto wired by Spring 's dependency injection facilities.
 * @author cjp
 */
@Controller
public class RegistrationController extends CommonController {

    private static final Logger registerLogger = Logger.getLogger(RegistrationController.class);
    @Autowired(required = false)
    private RegistrationService registrationService;

    @Autowired(required = false)
    RegistrationValidator registrationValidator;
//
// @Autowired(required = false)
//  IntializationService intializationService;

    RegistrationForm registrationFormObject = null;
    RegistrationForm registrationForm = null;

    /**
     *
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {

        registerLogger.info("Going to run RegistrationController initBinder method");

        binder.setValidator(registrationValidator);
    }

    // setRegistration method for adding some values to modelAttributes to view registrationform.jsp
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = RequestMappings.REGISTRATION, method = RequestMethod.GET)
    public String setRegistrationForm(Model model) {
        registerLogger.info("Going to run public class RegistrationController  setRegistrationForm method");

        try {

            registrationForm = new RegistrationForm();

            model.addAttribute("pageTitle", IntializationService.getAnnualIncome());
            model.addAttribute("cloudAdaption", IntializationService.getCloudAdaption());
            model.addAttribute("designation", IntializationService.getDesignation());
            model.addAttribute("employee", IntializationService.getEmployee());
            model.addAttribute("publicity", IntializationService.getPublicity());
            model.addAttribute(ModelAttributesConst.REGISTRATIONFORM, registrationForm);

        } catch (Exception e) {
            registerLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        registerLogger.info("Exiting RegistrationController  setform method");
        return ReturnConst.REGISTERFORM;
    }
//registrationFormSubmit for getting POST response from RegistrationForm

    /**
     *
     * @param registrationForm
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = RequestMappings.REGISTERED, method = RequestMethod.POST)
    public String registrationFormSubmit(@ModelAttribute("registrationForm") @Validated RegistrationForm registrationForm, BindingResult result, Model model, HttpServletRequest request, HttpSession session) throws InvalidSessionException, HttpSessionRequiredException {
        registerLogger.info("Going to run public class RegistrationController  registrationFormSubmit method");
        validateSession(request);

        String uri = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort();  

        //          or
        //String uri = request.getRequestURL()+"?"+request.getQueryString();
         
        UserLogInForm userLogInForm = (UserLogInForm) session.getAttribute(ModelAttributesConst.USERLOGINFORM);
         
        try {

            // check resulBinder has errors or not
            //if it has errors again send initial form with validation messages.
            if (result.hasErrors()) {

                model.addAttribute(ModelAttributesConst.REGISTRATIONFORM, registrationForm);
                model.addAttribute("pageTitle", IntializationService.getAnnualIncome());
                model.addAttribute("cloudAdaption", IntializationService.getCloudAdaption());
                model.addAttribute("designation", IntializationService.getDesignation());
                model.addAttribute("employee", IntializationService.getEmployee());
                model.addAttribute("publicity", IntializationService.getPublicity());

                return ReturnConst.REGISTERFORM;
            }

            registrationService.createUser(registrationForm, userLogInForm, uri);

            model.addAttribute("annualIncome", IntializationService.getAnnualIncome());
            model.addAttribute("employee", IntializationService.getEmployee());
            model.addAttribute("pageTitle", IntializationService.getAnnualIncome());
            model.addAttribute(ModelAttributesConst.REGISTRATIONFORM, registrationForm);
            model.addAttribute(ModelAttributesConst.USERID, registrationForm.getUserId());
        } catch (InvalidSessionException ise) {
            registerLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;

        } catch (ConnectivityException ce) {
            registerLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            registerLogger.error("This is Error message", ae);

            return ReturnConst.WELCOME;
        } catch (Exception e) {
            registerLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        registerLogger.info("Exiting RegistrationController  submit method");
        registerLogger.info("RegistrationController  End of registrationFormSubmit method");
        // return view as index.jsp
        return ReturnConst.ORGANIZATIONDETAILS;
    }

    /**
     *
     * @param registrationForm
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = RequestMappings.ORGANIZATIONDETAILS, method = RequestMethod.POST)
    public String organazationDetailsSubmit(@ModelAttribute("registrationForm") @Validated RegistrationForm registrationForm, BindingResult result, Model model) {
        registerLogger.info("Going to run public class RegistrationController  registrationFormSubmit method");
        try {

            // check resulBinder has errors or not
            //if it has errors again send initial form with validation messages.
            if (result.hasErrors()) {

                model.addAttribute("registrationForm", registrationForm);
                model.addAttribute("annualIncome", IntializationService.getAnnualIncome());
                model.addAttribute("employee", IntializationService.getEmployee());
                model.addAttribute("pageTitle", IntializationService.getAnnualIncome());
                model.addAttribute(ModelAttributesConst.REGISTRATIONFORM, registrationForm);
                model.addAttribute(ModelAttributesConst.USERID, registrationForm.getUserId());

                return ReturnConst.ORGANIZATIONDETAILS;
            }
            // passing registrationForm object to userRegistration class with userInsert method.
            registrationService.updateUser(registrationForm);

        } catch (InvalidSessionException ise) {
            registerLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;

        } catch (ConnectivityException ce) {
            registerLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            registerLogger.error("This is Error message", ae);

            return ReturnConst.WELCOME;
        } catch (Exception e) {
            registerLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        registerLogger.info("Exiting RegistrationController  submit method");
        // return view as index.jsp
        return ReturnConst.REGISTSUCES;
    }

    // help method is for getting request from help and redturn view as help.jsp
    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(RequestMappings.HELP)
    public String setHelpPage(HttpServletRequest request) {
        registerLogger.info("Going to run public class RegistrationController  help method");
        try {
            //here validating session
            validateSession(request);
        } catch (InvalidSessionException | HttpSessionRequiredException e) {
            registerLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        registerLogger.info("Exiting RegistrationController  help method");

        // return Help.jsp has a view.
        return ReturnConst.HELP;
    }

    // The activation for user link registration.
    // userRegistrationService method for displaying link for user activation.
    /**
     *
     * @param active
     * @return
     */
    @RequestMapping(value = RequestMappings.ACTIVATIONUSER, method = RequestMethod.GET)
    public String activateLoginPage(@RequestParam(value = "active", required = true) String active) {
        registerLogger.info("Going to run public class RegistrationController  activateLogin method");
        String returnValue;
        try {

            registrationService.activateLogin(active);
            return ReturnConst.WELCOME;
        } catch (InvalidSessionException ise) {
            registerLogger.error("This is Error message", ise);
            returnValue = ReturnConst.WELCOME;

        } catch (ConnectivityException ce) {
            registerLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            registerLogger.error("This is Error message", ae);

            return ReturnConst.WELCOME;
        } catch (Exception e) {
            registerLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        registerLogger.info("RegistrationController End of activateLogin method");
        return returnValue;
    }
    
    
    
    
    
    
    
    
    
    
    @RequestMapping(value = RequestMappings.ADMINREGISTRATION, method = RequestMethod.GET)
    public String setAdminRegistrationForm(Model model) {
        registerLogger.info("Going to run public class RegistrationController  setAdminRegistrationForm method");

        try {

            registrationForm = new RegistrationForm();

//            model.addAttribute("pageTitle", IntializationService.getAnnualIncome());
//            model.addAttribute("cloudAdaption", IntializationService.getCloudAdaption());
//            model.addAttribute("designation", IntializationService.getDesignation());
//            model.addAttribute("employee", IntializationService.getEmployee());
//            model.addAttribute("publicity", IntializationService.getPublicity());
            model.addAttribute(ModelAttributesConst.ADMINREGISTRATIONFORM, registrationForm);

        } catch (Exception e) {
            registerLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        registerLogger.info("Exiting RegistrationController  setform method");
        return ReturnConst.ADMINREGISTERFORM;
    }
//registrationFormSubmit for getting POST response from RegistrationForm

    /**
     *
     * @param registrationForm
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = RequestMappings.ADMININREGISTERED, method = RequestMethod.POST)
    public String adminRegistrationFormSubmit(@ModelAttribute("adminRegistrationForm") @Validated RegistrationForm adminRegistrationForm, BindingResult result, Model model, HttpServletRequest request, HttpSession session) throws InvalidSessionException, HttpSessionRequiredException {
        registerLogger.info("Going to run public class RegistrationController  registrationFormSubmit method");
        validateSession(request);

        String uri = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort();  

        //          or
        //String uri = request.getRequestURL()+"?"+request.getQueryString();
         
        UserLogInForm userLogInForm = (UserLogInForm) session.getAttribute(ModelAttributesConst.USERLOGINFORM);
         
        try {

            // check resulBinder has errors or not
            //if it has errors again send initial form with validation messages.
            if (result.hasErrors()) {

                model.addAttribute(ModelAttributesConst.ADMINREGISTRATIONFORM, adminRegistrationForm);
//                model.addAttribute("pageTitle", IntializationService.getAnnualIncome());
//                model.addAttribute("cloudAdaption", IntializationService.getCloudAdaption());
//                model.addAttribute("designation", IntializationService.getDesignation());
//                model.addAttribute("employee", IntializationService.getEmployee());
//                model.addAttribute("publicity", IntializationService.getPublicity());

                return ReturnConst.ADMINREGISTERFORM;
            }

            registrationService.createUser(adminRegistrationForm, userLogInForm, uri);

//            model.addAttribute("annualIncome", IntializationService.getAnnualIncome());
//            model.addAttribute("employee", IntializationService.getEmployee());
//            model.addAttribute("pageTitle", IntializationService.getAnnualIncome());
            model.addAttribute(ModelAttributesConst.ADMINREGISTRATIONFORM, adminRegistrationForm);
            model.addAttribute(ModelAttributesConst.USERID, adminRegistrationForm.getUserId());
        } catch (InvalidSessionException ise) {
            registerLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;

        } catch (ConnectivityException ce) {
            registerLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            registerLogger.error("This is Error message", ae);

            return ReturnConst.WELCOME;
        } catch (Exception e) {
            registerLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        registerLogger.info("Exiting RegistrationController  submit method");
        registerLogger.info("RegistrationController  End of registrationFormSubmit method");
        // return view as index.jsp
        return ReturnConst.ORGANIZATIONDETAILS;
    }

}
