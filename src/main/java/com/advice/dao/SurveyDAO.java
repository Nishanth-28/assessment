/**
 * The StartAdviceDAO used to create collections of  survey.
 * here we also do crud operations.
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.dao;


import com.advice.dos.SurveyDO;
import com.advice.encryptionAlgorithams.EncryptionDecryption;
import com.mongodb.MongoException;
import static java.lang.String.format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
/**
 * @Repository annotation, importing the DAOs into the DI container and also
 * this annotation makes the unchecked exceptions (thrown from DAO methods)
 * eligible for translation into Spring DataAccessException. indicates that an
 * annotated class is a "Repository" (or "DAO").
 *
 * A class thus annotated is eligible for Spring DataAccessException
 * translation. The annotated class is also clarified as to its role in the
 * overall application architecture for the purpose of tools, aspects, etc.
 *
 * this annotation also serves as a specialization of @Component, allowing for
 * implementation classes to be auto detected through class path scanning
 * @Autowired Marks a constructor, field, setter method or configuration method
 * as to be auto wired by Spring 's dependency injection facilities.
 * @author cjp
 */
@Repository
public class SurveyDAO {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private EncryptionDecryption encryptionDecryption;

    public static final String COLLECTION_NAME = "survey";

    private static final Logger logger = Logger.getLogger(SurveyDO.class);

    /**
     * findSurvey method is finding survey based on userId appname and orgname
     * and return selected options
     *
     *
     *
     * @param surveyDo
     * @return
     * @throws Exception
     */
    public SurveyDO findSurvey(SurveyDO surveyDo) throws Exception {

        
        SurveyDO surveyDoObject = null;

        try {
            logger.info("Finding survey before encript: " + surveyDo.getUserId() + " - " + surveyDo.getApplication() + " - " + surveyDo.getOrganization());
            encryptionDecryption.encryptObject(surveyDo);
            logger.info("Going to run  QuestionDAO findSurvey method");

            Query query = new Query();
            query.addCriteria(Criteria.where("userId").is(surveyDo.getUserId()));
            query.addCriteria(Criteria.where("application").is(surveyDo.getApplication()));
            query.addCriteria(Criteria.where("organization").is(surveyDo.getOrganization()));
            logger.info("Finding survey after encript: " + surveyDo.getUserId() + " - " + surveyDo.getApplication() + " - " + surveyDo.getOrganization());
            surveyDoObject = mongoTemplate.findOne(query, SurveyDO.class, "survey");

            if (surveyDoObject == null) {
                logger.info("The quesstionnaire is" + surveyDo.getQuestionnaire());
                encryptionDecryption.decryptObject(surveyDo);

            }

            if (surveyDoObject != null) {

                encryptionDecryption.decryptObject(surveyDoObject);
                encryptionDecryption.decryptObject(surveyDo);
                logger.info("Finding survey after decript: " + surveyDoObject.getUserId() + " - " + surveyDoObject.getApplication() + " - " + surveyDoObject.getQuestionnaire());

            }

        } catch (DataAccessException dae) {
            logger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            logger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            logger.error("This is Error message", e);
            throw e;
        }

        return surveyDoObject;
    }
    
    public List<SurveyDO> getSurveyByQuestion(String questionId) {
        logger.info("Going to run  ReportDAO getOrganization method");
        List<SurveyDO> surveyLst = new ArrayList<SurveyDO>();
        try {
            encryptionDecryption.encrypt(questionId);
            
            Query query = new Query();
            query.addCriteria(Criteria.where("questionId").is(questionId));
            surveyLst = mongoTemplate.find(query, SurveyDO.class, COLLECTION_NAME);
            for (SurveyDO surveyDOObject : surveyLst) {
                encryptionDecryption.decryptObject(surveyDOObject);
                surveyLst.add(surveyDOObject);
            }
            encryptionDecryption.decrypt(questionId);
        } catch (DataAccessException dae) {
            logger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            logger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            logger.error("Exception while getting Suvery details by question", e);
        }
        logger.info("End of getOrganization method of SurveyDAO object");
        return surveyLst;
    }

    /**
     * getOrganization method is for getting organization details based on
     * userId and orgaName and return surveyDO object
     *
     * @param surveyDO
     * @return
     * @throws java.lang.Exception
     */
    public List<SurveyDO> getOrganization(SurveyDO surveyDO) throws Exception {

        logger.info("Going to run  ReportDAO getOrganization method");
        List<SurveyDO> surveyDOList = null;
        try {
            encryptionDecryption.encryptObject(surveyDO);
            Query query = new Query();
            query.addCriteria(Criteria.where("userId").is(surveyDO.getUserId()));
            query.addCriteria(Criteria.where("organization").is(surveyDO.getOrganization()));
           
            surveyDOList = mongoTemplate.find(query, SurveyDO.class, COLLECTION_NAME);
            encryptionDecryption.decryptObject(surveyDO);
            for (SurveyDO surveyDOObject : surveyDOList) {
                encryptionDecryption.decryptObject(surveyDOObject);
            }
        } catch (DataAccessException dae) {
            logger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            logger.error("This is Error message", me);
            throw me;
        }
        logger.info("ReportDAO End of getOrganization method");
        return surveyDOList;
    }

    /**
     * findApp method is find all application using userId
     *
     * @param userId
     * @param organizationName
     * @return
     * @throws java.lang.Exception
     */
    public List<SurveyDO> findApplication(String userId,String organizationName) throws Exception {
        logger.info("Going to run  StartAdviceDAO findApp method");
        List<SurveyDO> surveyDOhasedList = new ArrayList<>();

        try {

            userId = encryptionDecryption.encrypt(userId);

            Query query = new Query();

            query.addCriteria(Criteria.where("userId").is(userId));
            query.addCriteria(Criteria.where("application").not().regex("CommonForOrganization"));
            List<SurveyDO> surveyDOList = mongoTemplate.find(query, SurveyDO.class, COLLECTION_NAME);

            for (SurveyDO surveyDOObject : surveyDOList) {
                encryptionDecryption.decryptObject(surveyDOObject);

                surveyDOhasedList.add(surveyDOObject);
            }
            encryptionDecryption.decrypt(userId);
        } catch (DataAccessException dae) {
            logger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            logger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            logger.error("This is Error message", e);
            throw e;
        }
        logger.info(" StartAdviceDAO End of findApp method");

        return surveyDOhasedList;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            SurveyDAO surveyDAO = new SurveyDAO();
            SurveyDO surveyDo = new SurveyDO();
            surveyDo.setUserId("venkat");
            surveyDo.setOrganization("cjptech");
            surveyDo.setApplication("App1");
            //EncryptionDecryption encryptionDecryption = new EncryptionDecryption();
            //encryptionDecryption.encryptObject(surveyDo);

            //encryptionDecryption.decryptObject(surveyDo);
            //surveyDAO.addQuestion(surveyDo);
//        surveyDAO.findApp(user);
            //surveyDAO.findSurvey(surveyDo);
//        surveyDAO.findSurveys(surveyDo);
//        surveyDAO.getOrganization(surveyDO);
//        surveyDAO.insertApp(surveyDo);
//        surveyDAO.updateSurvey(findData, surveyDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param surveyDO
     * @return
     * @throws Exception
     */
    public List<SurveyDO> getOrganizationDetails(SurveyDO surveyDO) throws Exception {
        logger.info("Going to run  StartAdviceDAO getOrganizationDetails method");
        List<SurveyDO> surveyDOList = null;
        List<SurveyDO> surveyDOhasedList = new ArrayList<>();
        try {
            encryptionDecryption.encryptObject(surveyDO);

            Query query = new Query();
            query.addCriteria(Criteria.where("userId").is(surveyDO.getUserId()));           
            surveyDOList = mongoTemplate.find(query, SurveyDO.class, COLLECTION_NAME);
            encryptionDecryption.decryptObject(surveyDO);
            for (SurveyDO surveyDOObject : surveyDOList) {
                encryptionDecryption.decryptObject(surveyDOObject);
                surveyDOhasedList.add(surveyDOObject);

            }

        } catch (DataAccessException dae) {
            logger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            logger.error("This is Error message", me);
            throw me;
        }
        logger.info(" StartAdviceDAO End of getOrganizationDetails method");
        return surveyDOhasedList;

    }

//    public SurveyDO findSurveybyOrganization(SurveyDO surveyDo) {
//        
//    }

}
