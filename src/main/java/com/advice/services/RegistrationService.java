/**
 * The Registration Service for sending email link for registration
 * passing registration controller objects to registrationDAo
 *
 * @author  CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.services;

import com.advice.dao.RegistrationDAO;
import com.advice.dos.RegistrationDO;
import com.advice.forms.RegistrationForm;
import com.advice.forms.UserLogInForm;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author cjp
 */
@Service
public class RegistrationService {

    @Autowired
    RegistrationDAO registrationDAO;

    @Autowired
    private JavaMailSender mailSender;

    private static final Logger registrationServiceLogger = Logger.getLogger(RegistrationService.class);
    /**
     * userInsert method is get registrationForm from registration's controller.
     * set registration form to registrationDO object and send to
     * registrationDAo addData method. send dynamically generated link to user
     * email id.
     *
     * @author cjp
     * @param registrationForm
     * @throws java.lang.Exception
     */
    public void createUser(RegistrationForm registrationForm, UserLogInForm userLogInForm, String uri) throws Exception {
        registrationServiceLogger.info("Start running userInsert method in RegistrationService");
        try {
            RegistrationDO registrationDO = new RegistrationDO();
            registrationDO.setCountry(registrationForm.getCountry());
            registrationDO.setDesignation(registrationForm.getDesignation());
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(registrationForm.getEmailId());
            //generating random unique key
            String uniqueKey = UUID.randomUUID().toString();
            registrationForm.setId(uniqueKey);

            //recipent address
            registrationDO.setEmailId(registrationForm.getEmailId());

            //setting subject for email.
            String subject = "Hi " + registrationForm.getFirstName() + ", Your Request for CloudJournee LogIn Page";

            // here create automatic generated link wirh random unique key.
            String url = "http://localhost:8084/CloudJournee/activation?active=" + uniqueKey;

            //here we create message txt
            String message = "Hi " + registrationForm.getFirstName() + ",  You have sucessfully registered.please check below link to activate your account and Enter into survey"
                    + "Your LoginId : " + registrationForm.getUserId() + ", Password : " + registrationForm.getPass1() + ""
                    + " Click on Link: " + url;
            email.setSubject(subject);
            email.setText(message);

            mailSender.send(email);

            registrationDO.setEmployee(registrationForm.getEmployee());
            registrationDO.setFirstName(registrationForm.getFirstName());
            registrationDO.setId(registrationForm.getId());
            registrationDO.setLastName(registrationForm.getLastName());
            registrationDO.setMiddleName(registrationForm.getMiddleName());
            registrationDO.setOrganazationName(registrationForm.getOrganazationName());
            registrationDO.setOther(registrationForm.getOther());
            registrationDO.setPass1(registrationForm.getPass1());
            registrationDO.setPass2(registrationForm.getPass2());
            registrationDO.setPhone(registrationForm.getPhone());
            registrationDO.setPublicities(registrationForm.getPublicities());
            registrationDO.setStates(registrationForm.getStates());
            registrationDO.setCountries(registrationForm.getCountries());

            registrationDO.setDesignations(registrationForm.getDesignations());
            registrationDO.setEmployees(registrationForm.getEmployees());
            registrationDO.setCloudAdaptions(registrationForm.getCloudAdaptions());
            registrationDO.setUserId(registrationForm.getUserId());
            registrationDO.setActive("0");
            registrationDO.setAdminId(userLogInForm.getUserId());
           
            java.util.Date date = new java.util.Date();
            registrationDO.setCreationDate(date);
            registrationDO.setModificationDate(date);
            //here passing registrationDO object registrationDAO through addData method
            registrationDAO.insertUser(registrationDO);

//            ActivationDO activeDO = new ActivationDO();
//
//            activeDO.setUserId(registrationForm.getUserId());
//            activeDO.setActive(uniqueKey);
//            // here passing activeDO object to registrtionDAO addactiveData method
//            registrationDAO.updateActivate(activeDO);
        } catch (MailException me) {
            registrationServiceLogger.error("MailException in userInsert method of RegistrationService", me);
            throw me;
        } catch (Exception e) {
            registrationServiceLogger.error("Exception in userInsert method of RegistrationService", e);
            throw e;

        }
        registrationServiceLogger.info("End running userInsert method in RegistrationService");
    }

    /**
     * activateLogin method for activate user when click on link their email.
     *
     * @param id
     * @throws java.lang.Exception
     */
    public void activateLogin(String id) throws Exception {
        registrationServiceLogger.info("Going to run  RegistrationService activateLogin method");
        RegistrationDO registrationDO = new RegistrationDO();
        try {
            java.util.Date date = new java.util.Date();
            registrationDO.setModificationDate(date);
            registrationDAO.activateRegistraionById(id);
            

        } catch (Exception e) {
            registrationServiceLogger.error("This is Error message", e);

        }
        registrationServiceLogger.info("RegistrationService End of activateLogin method");

    }

    /**
     *
     * @param recoveryForm
     * @throws Exception
     */
    /**
     *
     * @param registrationForm
     * @throws Exception
     */
    public void updateUser(RegistrationForm registrationForm) throws Exception {
        registrationServiceLogger.info("Going to run  RegistrationService updateUser method");
        try {
            RegistrationDO registrationDO = new RegistrationDO();
            registrationDO.setUserId(registrationForm.getUserId());
            registrationDO.setOrganizationFirst(registrationForm.getOrganizationFirst());
            registrationDO.setOrganizationEmailId(registrationForm.getOrganizationEmailId());
            registrationDO.setLocation(registrationForm.getLocation());
            registrationDO.setRevenue(registrationForm.getRevenue());
            registrationDO.setOrganizationEmployees(registrationForm.getOrganizationEmployees());
            java.util.Date date = new java.util.Date();
            registrationDO.setModificationDate(date);
            registrationDAO.updateOrganizationDetails(registrationDO);
            //To change body of generated methods, choose Tools | Templates.
        } catch (Exception e) {

        }
        registrationServiceLogger.info(" RegistrationService End of updateUser method");
    }

    /**
     *
     * @param userId
     * @return
     */
    public String findOrganization(String userId) {
        registrationServiceLogger.info("Going to run  RegistrationService findOrganization method");
        String organizationName = null;
        try {
            organizationName = registrationDAO.findOrganizationDetails(userId);
        } catch (Exception e) {

        }
        registrationServiceLogger.info(" RegistrationService End of findOrganization method");
        return organizationName;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
//        RegistrationService registrationService=new RegistrationService();
//        registrationService.activateLogin(active);
//        registrationService.userInsert(registrationForm);
        RegistrationDO rdo = new RegistrationDO();
        java.util.Date date = new java.util.Date();
        rdo.setCreationDate(date);
        System.out.println(rdo.getCreationDate());
        rdo.setModificationDate(date);
        System.out.println(rdo.getModificationDate());
    }

}
