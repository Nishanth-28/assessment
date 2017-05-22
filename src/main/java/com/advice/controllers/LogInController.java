/**
 * LoginController is for user authentication either client or Admin.
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.controllers;

import com.advice.commons.constants.ModelAttributesConst;
import com.advice.commons.constants.RequestMappings;
import com.advice.commons.constants.ReturnConst;
import com.advice.commons.exceptions.ApplicationException;
import com.advice.commons.exceptions.ConnectivityException;
import com.advice.validators.LogInValidator;
import com.advice.dos.UserLogInDO;
import com.advice.commons.exceptions.InvalidSessionException;
import com.advice.encryptionAlgorithams.EncryptionDecryption;
import com.advice.forms.UserLogInForm;
import com.advice.services.LoginService;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.apache.log4j.Logger;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

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
 * as to be autowired by Spring 's dependency injection facilities.
 * @author cjp
 */
@Controller
@SessionAttributes(value = ModelAttributesConst.USERLOGINFORM, types = {UserLogInForm.class})
public class LogInController extends CommonController {

    private static final Logger logger = Logger.getLogger(LogInController.class);
   @Autowired
    private LoginService loginService;
   @Autowired
    LogInValidator logInValidator;

    /**
     *
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {

        logger.info("Going to run LogInController initBinder method");

        binder.setValidator(logInValidator);

    }
    UserLogInDO userLogInDo;

    /**
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(RequestMappings.WELCOME)
    public String loginForm(Model model, HttpSession session) {
        logger.info("Going to run Loggincontroller setform method");
        try {
            EncryptionDecryption.setEncryptionEnable(false);
            model.addAttribute(ModelAttributesConst.USERLOGINFORM, new UserLogInForm());

        } catch (Exception e) {
            logger.error("This is Error message", new Exception("Exception"));
        }
        logger.info("Exiting Loggincontroller setform method");
        //return view as LogInPage.jsp
        return ReturnConst.LOGINPAGE;
    }

    /**
     *
     * @param request
     * @param sessionStatus
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = RequestMappings.LOGOUT, method = RequestMethod.GET)
    public String logOut(HttpServletRequest request, SessionStatus sessionStatus, HttpSession session, Model model) {
        logger.info("Going to run LogOutController logout method");
        try {

            //invalidating session
            session.invalidate();//to make session close 

            sessionStatus.setComplete();//to make session close

        } catch (Exception e) {
            logger.error("This is Error message in logout Method", new Exception("Exception"));

            // e.printStackTrace();
        }
        logger.info(" LogOutController end of  logout method");
        // redirect to logOutsession
        return ReturnConst.REDIRECTLOGOUTSESSION;
    }

    /**
     *
     * @param request
     * @param sessionStatus
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = RequestMappings.LOGOUTSESSION, method = RequestMethod.GET)
    public String logOutSession(HttpServletRequest request, SessionStatus sessionStatus, HttpSession session, Model model) {
        logger.info("Going to run LogOutController logoutSess method");
        try {

            // add errorMessage of string  and new UserLoginForm  to modelAttriibutes.
            model.addAttribute("errorMessage", "You are successfully logedout");
            model.addAttribute(ModelAttributesConst.USERLOGINFORM, new UserLogInForm());
            // return view as LogInPage 

        } catch (Exception e) {
            logger.error("This is Error message", new Exception("Exception"));
            return ReturnConst.LOGINPAGE;
        }
        logger.info(" LogOutController End of logoutSess method");
        return ReturnConst.LOGINPAGE;//return welcome page 
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(RequestMappings.SUBMITTEDLOGIN)
    public String landLogin(HttpServletRequest request) {
        logger.info("Going to run Loggincontroller setfo method");
        try {
            validateSession(request);

        } catch (InvalidSessionException | HttpSessionRequiredException e) {
            logger.error("Error message in LogInController setQuestionnaire method",e);
            return ReturnConst.LOGINPAGE;
        }

        // return view as LandLogIn.jsp
        logger.info("Loggincontroller End of setfo method");
        return ReturnConst.LANDLOGIN;
    }

    /**
     *
     * @param request
     * @param session
     * @param userLogInForm
     * @param result
     * @param model
     * @param status
     * @return
     */
    @RequestMapping(value = RequestMappings.SUBMITTEDLOGIN, method = RequestMethod.POST)
    public String authenticateLogin(HttpServletRequest request, HttpSession session, @ModelAttribute(ModelAttributesConst.USERLOGINFORM) @Validated UserLogInForm userLogInForm, BindingResult result, Model model, SessionStatus status) {
        logger.info("Going to run Loggincontroller authLogin method");
        boolean admin;
        String returnValue;
        try {  
            // userLogInForm.setCaptchasValue(captcha);
            //System.out.println("the captch value is"+captcha);
            // check bindingResult has any errors or not
            //if it has error return view as LogInPage
            if (result.hasErrors()) {
                 logger.error(userLogInForm.getUserId()+":"+request.getRemoteAddr()+" "+"AuthenticationFailed");
                model.addAttribute(ModelAttributesConst.USERLOGINFORM, userLogInForm);
                return ReturnConst.LOGINPAGE;
            }
            //validating Session
            validateSession(request);

            // String challenge = request.getParameter("recaptcha_challenge_field");
            //  String uresponse = request.getParameter("recaptcha_response_field");
            // ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
            // check recaptchaResponse is valid or not
            // if it is valid get authenticateUser method from loginservice and assign variable as  success
            // check success is true or not . if sucess is null return view as LoginPage.jsp 
            // otherwise add user from session and recaptcha variable to model and return view as LogInPage.jsp
            // if (reCaptchaResponse.isValid()) {
            boolean success = loginService.authenticateUser(userLogInForm);
            if (!success) {
                  logger.error(userLogInForm.getUserId()+":"+request.getRemoteAddr()+" "+"AuthenticationFailed");
                returnValue = ReturnConst.LOGINPAGE;
            } else {
                
                String captcha = (String) session.getAttribute("captcha");

                if (captcha.equals(userLogInForm.getCaptchas())) {
                     logger.info(userLogInForm.getUserId()+":"+request.getRemoteAddr()+" "+"AuthenticationSucesss");
                    returnValue = ReturnConst.REDIRECTADMIN;
                } else {
                    logger.error(userLogInForm.getUserId()+":"+request.getRemoteAddr()+" "+"AuthenticationFailed");
                    // model.addAttribute("captchas", captcha);
                    model.addAttribute("captchasMsg", "pleas enter correct captch");
                    model.addAttribute(ModelAttributesConst.USERLOGINFORM, userLogInForm);
                    return ReturnConst.LOGINPAGE;
                }
            }
            // get Admin method from loginService assign variable as admin
          //  admin = loginService.getAdminRole(userLogInForm);
            // check admin is true or not . if admin is true. redirct to Admin
            // otherwise redirect to submittedLogin
//            if (admin) {           
//                returnValue = ReturnConst.REDIRECTADMIN;
//            } 
//            else {
//                model.addAttribute("authorMsg", "Invalid User");
//                model.addAttribute(ModelAttributesConst.USERLOGINFORM, userLogInForm);
//                return ReturnConst.LOGINPAGE;
//            }
        } catch (InvalidSessionException ise) {
            logger.error("Error message in LogInController setQuestionnaire method",ise);
            return ReturnConst.LOGINPAGE;
        } catch (ConnectivityException ce) {
            logger.error("Error message in LogInController setQuestionnaire method",ce);
            return ReturnConst.LOGINPAGE;
        } catch (ApplicationException ae) {
            logger.error("Error message in LogInController setQuestionnaire method",ae);
            return ReturnConst.LOGINPAGE;
        } catch (Exception e) {
            logger.error("Error message in LogInController setQuestionnaire method",e);
            return ReturnConst.LOGINPAGE;
        }
        logger.info("Loggincontroller End of authLogin  method");
        return returnValue;

    }

     @RequestMapping("/CaptchaServlet")
    protected void captchProcessRequest(HttpServletRequest request,
            HttpServletResponse response, Model model)
            throws ServletException, IOException {
        logger.info("Going to run Loggincontroller captchProcessRequest method");
        try {

            int width = 170;
            int height = 80;

           

            BufferedImage bufferedImage = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);

            Graphics2D g2d = bufferedImage.createGraphics();

            Font font = new Font("Courier New", Font.BOLD, 24);
            g2d.setFont(font);

            RenderingHints rh = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            rh.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);

            g2d.setRenderingHints(rh);

            GradientPaint gp = new GradientPaint(0, 0,
                    new Color(53,140,206), 0, height / 2, Color.BLUE, true);

            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);

           g2d.setColor(new Color(235, 238, 238));

            Random r = new Random();
           
            String str = loginService.generateCaptcha();
            String captcha = String.copyValueOf(str.toCharArray());
            request.getSession().setAttribute("captcha", captcha);
            //model.addAttribute("captcha", captcha );
           int x = 0;
            int y = 0;

            for (int i = 0; i < str.toCharArray().length; i++) {
                x += 10 + (Math.abs(r.nextInt()) % 15);
                y = 20 + Math.abs(r.nextInt()) % 20;
                g2d.drawChars(str.toCharArray(), i, 1, x, y);
            }

            g2d.dispose();

            response.setContentType("image/png");
            try (OutputStream os = response.getOutputStream()) {
                ImageIO.write(bufferedImage, "png", os);
            }
           
        } catch (Exception e) {
            logger.error("Error message in LogInController setQuestionnaire method",e);

        }
        logger.info(" Loggincontroller End of captchProcessRequest method");
    }


   
}