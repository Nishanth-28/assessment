/**
 * The RegistrationValidator class is for validating RegistrationForm
 *
 * @author  CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.validators;

import com.advice.dao.RegistrationDAO;
import com.advice.forms.RegistrationForm;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @component auto-detection when using annotation-based configuration and class
 * path scanning.
 * @author cjp
 */
@Component
public class RegistrationValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_PATTERN
            = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&*()~]).{4,15})";

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    String MOBILE_PATTERN = "[0-9]{10,11}";
    String domains[] = {
        /* Default domains included */
        "aol.com", "att.net", "comcast.net", "facebook.com", "gmail.com", "gmx.com", "googlemail.com",
        "google.com", "hotmail.com", "hotmail.co.uk", "mac.com", "me.com", "mail.com", "msn.com",
        "live.com", "sbcglobal.net", "verizon.net", "yahoo.com", "yahoo.co.uk",
        /* Other global domains */
        "email.com", "games.com" /* AOL */, "gmx.net", "hush.com", "hushmail.com", "icloud.com", "inbox.com",
        "lavabit.com", "love.com" /* AOL */, "outlook.com", "pobox.com", "rocketmail.com" /* Yahoo */,
        "safe-mail.net", "wow.com" /* AOL */, "ygm.com" /* AOL */, "ymail.com" /* Yahoo */, "zoho.com", "fastmail.fm",
        "yandex.com",
        /* United States ISP domains */
        "bellsouth.net", "charter.net", "comcast.net", "cox.net", "earthlink.net", "juno.com",
        /* British ISP domains */
        "btinternet.com", "virginmedia.com", "blueyonder.co.uk", "freeserve.co.uk", "live.co.uk",
        "ntlworld.com", "o2.co.uk", "orange.net", "sky.com", "talktalk.co.uk", "tiscali.co.uk",
        "virgin.net", "wanadoo.co.uk", "bt.com",
        /* Domains used in Asia */
        "sina.com", "qq.com", "naver.com", "hanmail.net", "daum.net", "nate.com", "yahoo.co.jp", "yahoo.co.kr", "yahoo.co.id", "yahoo.co.in", "yahoo.com.sg", "yahoo.com.ph",
        /* French ISP domains */
        "hotmail.fr", "live.fr", "laposte.net", "yahoo.fr", "wanadoo.fr", "orange.fr", "gmx.fr", "sfr.fr", "neuf.fr", "free.fr",
        /* German ISP domains */
        "gmx.de", "hotmail.de", "live.de", "online.de", "t-online.de" /* T-Mobile */, "web.de", "yahoo.de",
        /* Russian ISP domains */
        "mail.ru", "rambler.ru", "yandex.ru", "ya.ru", "list.ru",
        /* Belgian ISP domains */
        "hotmail.be", "live.be", "skynet.be", "voo.be", "tvcablenet.be", "telenet.be",
        /* Argentinian ISP domains */
        "hotmail.com.ar", "live.com.ar", "yahoo.com.ar", "fibertel.com.ar", "speedy.com.ar", "arnet.com.ar",
        /* Domains used in Mexico */
        "hotmail.com", "gmail.com", "yahoo.com.mx", "live.com.mx", "yahoo.com", "hotmail.es", "live.com", "hotmail.com.mx", "prodigy.net.mx", "msn.com"
    };

    // String SPECIALCHAR_PATTERN= "[@#$%^&+=]";
   @Autowired
    RegistrationDAO registrationDAO;

    /**
     *
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationForm.class.equals(clazz);
    }

    /**
     *
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {

        try {
            RegistrationForm registrationForm = (RegistrationForm) target;
            if (registrationForm.getPageTitle().equals("Registration For Advice")) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.registrationForm.lana");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.registrationForm.fina");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "organazationName", "NotEmpty.registrationForm.orgna");

                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pass1", "NotEmpty.registrationForm.pass1");

                if (!(registrationForm.getPass1() != null && registrationForm.getPass1().isEmpty())) {
                    pattern = Pattern.compile(PASSWORD_PATTERN);
                    matcher = pattern.matcher(registrationForm.getPass1());
                    if (!matcher.matches()) {
                        errors.rejectValue("pass1", "PasswordPattern.registrationForm.pass1");
                    }
                }

                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pass2", "NotEmpty.registrationForm.pass2");

                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "NotEmpty.registrationForm.emid");
                for (String domain : domains) {

                    /*if (registrationForm.getEmailId().contains(domain)) {
            errors.rejectValue("emailId", "OfficialPattern.registrationForm.emid");
            } */
                }

                if (!(registrationForm.getEmailId() != null && registrationForm.getEmailId().isEmpty())) {
                    pattern = Pattern.compile(EMAIL_PATTERN);
                    matcher = pattern.matcher(registrationForm.getEmailId());
                    if (!matcher.matches()) {
                        errors.rejectValue("emailId", "Pattern.registrationForm.emid");
                    }
                }

                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "NotEmpty.registrationForm.userId");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employees", "NotEmpty.registrationForm.emp");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "designations", "NotEmpty.registrationForm.desg");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countries", "NotEmpty.registrationForm.country");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "states", "NotEmpty.registrationForm.state");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.registrationForm.phna");

                if (!(registrationForm.getPhone() != null && registrationForm.getPhone().isEmpty())) {
                    pattern = Pattern.compile(MOBILE_PATTERN);
                    matcher = pattern.matcher(registrationForm.getPhone());
                    if (!matcher.matches()) {
                        errors.rejectValue("phone", "Size.registrationForm.phna");
                    }
                }
                if (!(registrationForm.getPass1()).equals(registrationForm.getPass2())) {
                    errors.rejectValue("pass2", "password.required");
                }

                Boolean userId = registrationDAO.getUserId(registrationForm.getUserId());
                if (userId) {
                    errors.rejectValue("userId", "Check.registrationForm.userId");
                }
            }
            if (registrationForm.getPageTitle().equals("Registration For Organization")) {

                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "organizationFirst", "NotEmpty.registrationForm.organizationFirst");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "organizationEmployees", "NotEmpty.registrationForm.organizationEmployees");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "revenue", "NotEmpty.registrationForm.revenue");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "organizationEmailId", "NotEmpty.registrationForm.organizationEmailId");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "NotEmpty.registrationForm.location");
            }
        } catch (Exception ex) {
            Logger.getLogger(RegistrationValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
