/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advice.services;

import com.advice.dao.RegistrationDAO;
import com.advice.dos.RegistrationDO;
import java.util.Map;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cjp
 */
@Service
public class IntializationService {

    static final Logger logger = Logger.getLogger(IntializationService.class);
    private static IntializationService intializationService;
    @Autowired
    RegistrationDAO registrationDAO;
    private static  Map<String, String> employee;

    private static Map<String, String> designation;

    private static Map<String, String> publicity;

    private static Map<String, String> cloudAdaption;

    private static Map<String, String> annualIncome;

    public static Map<String, String> getEmployee() {
        return employee;
    }

    public static void setEmployee(Map<String, String> employee) {
        IntializationService.employee = employee;
    }

    public static Map<String, String> getDesignation() {
        return designation;
    }

    public static void setDesignation(Map<String, String> designation) {
        IntializationService.designation = designation;
    }

    public static Map<String, String> getPublicity() {
        return publicity;
    }

    public static void setPublicity(Map<String, String> publicity) {
        IntializationService.publicity = publicity;
    }

    public static Map<String, String> getCloudAdaption() {
        return cloudAdaption;
    }

    public static void setCloudAdaption(Map<String, String> cloudAdaption) {
        IntializationService.cloudAdaption = cloudAdaption;
    }

    public static Map<String, String> getAnnualIncome() {
        return annualIncome;
    }

    public static void setAnnualIncome(Map<String, String> annualIncome) {
        IntializationService.annualIncome = annualIncome;
    }

   
    public static RegistrationDO defaultValuesForRegistrations;

   


    /**
     * Create private constructor
     */
    private IntializationService() {

        logger.info("Going to run IntializationService Constructor");
    }

    /**
     * Create a static method to get instance.
     *
     * @return
     */
    public static IntializationService getInstance() {
        logger.info("Going to run IntializationService Constructor");
        if (intializationService == null) {
            intializationService = new IntializationService();

        }

        return intializationService;
    }

//    @Autowired
//   CommonController(RegistrationService registrationService){
//      CommonController.staticRegistrationService = registrationService;
    // }
    @PostConstruct
    void init() {

        // IntializationService.staticRegistrationService = registrationServices;
        //IntializationService.staticAdminService=adminService;
        try {
            if (defaultValuesForRegistrations == null) {
                defaultValuesForRegistrations = registrationDAO.userDefault();
                employee=defaultValuesForRegistrations.getEmployee();
                designation=defaultValuesForRegistrations.getDesignation();
                publicity=defaultValuesForRegistrations.getPublicity();
                cloudAdaption=defaultValuesForRegistrations.getCloudAdaption();
                 annualIncome=defaultValuesForRegistrations.getAnnualIncome();
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(IntializationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
