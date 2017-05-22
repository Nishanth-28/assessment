/**
 *LoginSerrvice is for getting  authenticating user and getting roles from  registrationDAO
 *
 *
 * @author  CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.services;

import com.advice.dao.RegistrationDAO;
import com.advice.dos.RegistrationDO;
import com.advice.forms.UserLogInForm;
import java.util.Random;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cjp
 */
@Service
public class LoginService {

    @Autowired
    RegistrationDAO registrationDAO;
    private static final Logger collaborationServiceLogger = Logger.getLogger(LoginService.class);

    /**
     * AuthenticateUser for finding authorized user Extract registrationDOList
     * and check user is authorized user or not.
     *
     * @param userLogInForm
     * @return true or false
     * @throws java.lang.Exception
     */
    public boolean authenticateUser(UserLogInForm userLogInForm) throws Exception {
        collaborationServiceLogger.info("Going to run  LoginService AuthenticateUser method");
        boolean returnValue = false;
        try {

            String userId = userLogInForm.getUserId();
            String password = userLogInForm.getPassword();
            RegistrationDO registrationDO = registrationDAO.getUser(userId, password);
            //System.out.println("authenticateUser1 : "+!registrationDO.getPass2().equals(userLogInForm.getPassword()));
            if (registrationDO.getUserId().equals(userLogInForm.getUserId()) && registrationDO.getPass2() != userLogInForm.getPassword() && !registrationDO.getUserId().equals(null) && !registrationDO.getPass2().equals(null)) {
                //if (registrationDO.getActive().equals("1")) {
                returnValue = true;
                // }

            }

        } catch (Exception e) {
            collaborationServiceLogger.error("This is Error message", e);

        }
        collaborationServiceLogger.info(" LoginService End of AuthenticateUser method");
        return returnValue;
    }
    
    public boolean isValidUser(String userId) {
        
         collaborationServiceLogger.info("Going to run  LoginService AuthenticateUser method");
        boolean returnValue = false;
        try {

            RegistrationDO registrationDO = registrationDAO.getUser(userId);
            if(registrationDO != null)
                return true;
        } catch (Exception e) {
            collaborationServiceLogger.error("This is Error message", e);

        }
        collaborationServiceLogger.info(" LoginService End of AuthenticateUser method");
        return false;
        
    }

    public boolean authenticateActivation(UserLogInForm userLogInForm) throws Exception {
        collaborationServiceLogger.info("Going to run  LoginService AuthenticateUser method");
        boolean returnValue = false;
        try {

            String userId = userLogInForm.getUserId();
            String password = userLogInForm.getPassword();
            RegistrationDO registrationDO = registrationDAO.getUser(userId);
            //  System.out.println("authenticateUser1 : "+!registrationDO.getPass2().equals(userLogInForm.getPassword()));
            //if (registrationDO.getPass2().equals(userLogInForm.getPassword())) {
                if (registrationDO.getActive().equals("1")) {
                    returnValue = true;
                }

            //}

        } catch (Exception e) {
            collaborationServiceLogger.error("This is Error message", e);

        }
        collaborationServiceLogger.info(" LoginService End of AuthenticateUser method");
        return returnValue;
    }

    /**
     * Admin is for finding admin role
     *
     * @param userLogInForm
     * @return true or false
     * @throws java.lang.Exception
     */
    public boolean getAdminRole(UserLogInForm userLogInForm) throws Exception {
        collaborationServiceLogger.info("Going to run  LoginService AuthenticateUser method");
        System.out.println("$$$getAdminRole");
        boolean returnValue = false;
        try {

            RegistrationDO registrationDO = registrationDAO.admin(userLogInForm.getUserId());
            if (registrationDO == null || registrationDO.getRole() == null) {
                returnValue = false;
            } else if (registrationDO.getRole().equals("administrator")) {
                returnValue = true;
            } else {
                returnValue = false;
            }

        } catch (Exception e) {
            collaborationServiceLogger.error("This is Error message", new Exception("NullPointerException"));
        }
        collaborationServiceLogger.info("LoginService End of AuthenticateUser method");
        return returnValue;

    }

     /**
     * Generate a CAPTCHA String consisting of random lowercase & uppercase
     * letters, and numbers.
     *
     * @return
     */
    public String generateCaptcha() {
        
        String CaptchaText = null;
        try {
            Random random = new Random();
            int length = 5;
            StringBuilder captchaStringBuffer = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int captchaNumber = Math.abs(random.nextInt()) % 60;
                
                int charNumber = 0;
                if (captchaNumber < 26) {
                    charNumber = 65 + captchaNumber;
                } else if (captchaNumber < 52) {
                    charNumber = 65 + (captchaNumber - 26);
                } else {
                    charNumber = 48 + (captchaNumber - 52);
                }
                captchaStringBuffer.append((char) charNumber);
            }
            CaptchaText = captchaStringBuffer.toString();
        } catch (Exception e) {

        }
        return CaptchaText;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
//        LoginService loginService=new LoginService();
//        loginService.Admin(userLogInForm);
//        loginService.AuthenticateUser(userLogInForm);
    }

}
