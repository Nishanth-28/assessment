/**
 * The LogInValidator class is for validating loginForm
 *
 * @author  CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.validators;

import com.advice.forms.UserLogInForm;
import com.advice.services.LoginService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @component auto-detection when using annotation-based configuration and class
 * path scanning.
 * @Autowired Marks a constructor, field, setter method or configuration method
 * as to be auto wired by Spring 's dependency injection facilities.
 * @author cjp
 */
@Component
public class LogInValidator implements Validator {

    @Autowired
    LoginService loginService;

    /**
     *
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return UserLogInForm.class.equals(clazz);
    }

    /**
     *
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        try {
            UserLogInForm userLogInForm = (UserLogInForm) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "NotEmpty.userLogInForm.userId");
            boolean admin = loginService.getAdminRole(userLogInForm);
                        
            try {
                if (!(userLogInForm.getUserId() != null && userLogInForm.getUserId().isEmpty())) {
                    if (!loginService.isValidUser(userLogInForm.getUserId())) {
                        errors.rejectValue("userId", "Check.userLogInForm.userId");
                    } else {
                        if (!admin) {
                            errors.rejectValue("authorMsg", "Check.userLogInForm.authorMsg");
                        }
                    }

                } else {
                    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userLogInForm.password");
                    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "captchas", "NotEmpty.userLogInForm.captchas");

                }
                if (!loginService.authenticateActivation(userLogInForm)) {
                    if (loginService.isValidUser(userLogInForm.getUserId())) {
                        errors.rejectValue("activate", "Check.userLogInForm.active");
                    }
                } else {
                    if (admin) {
                    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userLogInForm.password");
                    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "captchas", "NotEmpty.userLogInForm.captchas");
                    if (!(userLogInForm.getPassword() != null && userLogInForm.getPassword().isEmpty())) {
                        if (!loginService.authenticateUser(userLogInForm)) {
                            errors.rejectValue("password", "errors.required");
                        }
                    }
                }}
            } catch (Exception ex) {
                Logger.getLogger(LogInValidator.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception ex) {
            Logger.getLogger(LogInValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
