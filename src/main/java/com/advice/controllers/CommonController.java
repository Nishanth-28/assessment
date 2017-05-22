/**
 * The commoncontroller is for validating sessions.
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.controllers;

import com.advice.commons.constants.ModelAttributesConst;
import com.advice.commons.exceptions.InvalidSessionException;
import com.advice.forms.RegistrationForm;
import com.advice.forms.UserLogInForm;
import com.advice.services.RegistrationService;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpSessionRequiredException;

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
 * and Portlet environments.
 * @SessionAttributes Annotation that indicates the session attributes that a
 * specific handler uses. This will typically list the names of model
 * attributes. which should be transparently stored in the session or some
 * conversational storage, serving as form-backing beans.
 * @Autowired Marks a constructor, field, setter method or configurations method
 * as to be autowired by Spring ' s dependency injection facilities.
 * @author cjp
 */

public abstract class CommonController {

    @Autowired
    RegistrationService registrationServices;
    
    static final Logger logger = Logger.getLogger(CommonController.class);

    public static RegistrationService staticRegistrationService;

//    @Autowired
//   CommonController(RegistrationService registrationService){
//      CommonController.staticRegistrationService = registrationService;
    // }
    @PostConstruct
    void init() {

        CommonController.staticRegistrationService = registrationServices;
    }
    
    public static RegistrationForm initializeRegistration() {  
        RegistrationForm defaultValuesForRegistration=null;
        
        try {

          //  defaultValuesForRegistration = staticRegistrationService.defaultValuesForRegistration();
           logger.info("publicities of registration" + defaultValuesForRegistration.getPublicity());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CommonController.class.getName()).log(Level.SEVERE, null, ex);
        }
       return  defaultValuesForRegistration;
    }

    /**
     *
     * @Request receive the request
     * @throw an exception if userId is null
     * @param request
     * @throws InvalidSessionException
     * @throws HttpSessionRequiredException
     */
    protected void validateSession(HttpServletRequest request) throws InvalidSessionException, HttpSessionRequiredException {

        logger.info("Going to run CommonController validateSession method");
        //create usr variable and that contains userdetails untill the the session valid   
        UserLogInForm userLogInForm = (UserLogInForm) request.getSession().getAttribute(ModelAttributesConst.USERLOGINFORM);
        logger.info("Going to run CommonController validateSession method" + userLogInForm);
        if (userLogInForm == null || userLogInForm.getUserId() == null) {

            throw new InvalidSessionException();

        } else {
            logger.info("Going to run CommonController validateSession method, Session is valid");

        }
        logger.info("CommonController validateSession End of the method");
    }

}
