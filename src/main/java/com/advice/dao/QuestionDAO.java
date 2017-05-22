/**
 *The QuestionDAO used to create collection of survey .
 * here we also do crud operations.
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.dao;

import com.advice.dos.AdminQuestionDO;
import com.advice.dos.BlockDO;
import com.advice.dos.OptionsDO;
import com.advice.dos.QuestionAcessDO;

import com.advice.dos.QuestionDO;
import com.advice.encryptionAlgorithams.EncryptionDecryption;
import com.mongodb.MongoException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
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
public class QuestionDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EncryptionDecryption encryptionDecryption;

    public static final String COLLECTION_NAME = "questionnaire";

    private static final Logger questionDAOLogger = Logger.getLogger(QuestionDAO.class);

    /**
     * retrieveData method for finding All Questionnaire and returning
     * questionnaireData
     *
     * @return
     * @throws java.lang.Exception
     */
    public List<AdminQuestionDO> findAllQuestions() throws Exception {

        questionDAOLogger.info("Going to run  AdminDAO retrieveData method");
        List<AdminQuestionDO> adminQuestionDOList = null;
        try {

            adminQuestionDOList = mongoTemplate.findAll(AdminQuestionDO.class, "questionnaire");

        } catch (DataAccessException dae) {
            questionDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            questionDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            questionDAOLogger.error("This is Error message", e);
            throw e;
        }
        questionDAOLogger.info("AdminDAO End of retrieveData method");
        return adminQuestionDOList;
    }

    /**
     *
     * @return @throws java.lang.Exception
     */
    public List<QuestionAcessDO> getQuestions() throws Exception {
        questionDAOLogger.info("Going to run  QuestionDAO getQuestions method");
        List<QuestionAcessDO> questionAcessDOList = null;
        try {
            questionAcessDOList = mongoTemplate.findAll(QuestionAcessDO.class, "questionnaire");

        } catch (DataAccessException dae) {
            questionDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            questionDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            questionDAOLogger.error("This is Error message", e);
            throw e;
        }
        questionDAOLogger.info("QuestionDAO End of getQuestions method");
        return questionAcessDOList;
    }

    /**
     * findQuestion method is data in questionnaire based questionnaire. and
     * return questionAccessDO object.
     *
     * @param questionId
     * @return
     * @throws java.lang.Exception
     */
    public QuestionAcessDO findQuestion(int questionId) throws Exception {
        questionDAOLogger.info("Going to run  QuestionDAO findQuestion method");
        QuestionAcessDO questionAcessDO = null;
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("questionId").is(questionId));
            questionAcessDO = mongoTemplate.findOne(query, QuestionAcessDO.class, "questionnaire");

        } catch (DataAccessException dae) {
            questionDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            questionDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            questionDAOLogger.error("This is Error message", e);
            throw e;
        }
        questionDAOLogger.info(" QuestionDAO End of findQuestion method");
        return questionAcessDO;

    }

    /**
     *
     * @param questionId
     * @return
     * @throws java.lang.Exception
     */
    public QuestionDO findQuestionnaire(int questionId) throws Exception {
        questionDAOLogger.info("Going to run  QuestionDAO findQuestionnaire method");
        QuestionDO questionDO = null;
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("questionId").is(questionId));

            questionDO = mongoTemplate.findOne(query, QuestionDO.class, COLLECTION_NAME);

        } catch (DataAccessException dae) {
            questionDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            questionDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            questionDAOLogger.error("This is Error message", e);
            throw e;
        }
        questionDAOLogger.info("QuestionDAO End of findQuestionnaire method");
        return questionDO;
    }

    /**
     * addData method is for creating collections , inserting and updating
     * collections. check collection is Exist or not. If collection not exists
     * create new Collection of adminQuestionDo Generate dynamic Id with create
     * collection object Insert data adminQuestionDO with "questionnaire
     * collection"
     *
     * @param adminQuestionDo
     * @throws CloneNotSupportedException
     */
    public void saveQuestion(AdminQuestionDO adminQuestionDo) throws CloneNotSupportedException, Exception {
        questionDAOLogger.info("Going to run  AdminDAO addData method");
        try {
            if (!mongoTemplate.collectionExists(AdminQuestionDO.class)) {
                mongoTemplate.createCollection(AdminQuestionDO.class);
            }
            adminQuestionDo.setId(UUID.randomUUID().toString());
            System.out.println("The adminQuestionDo is"+adminQuestionDo.getRuleRequired());
            mongoTemplate.insert(adminQuestionDo, COLLECTION_NAME);

        } catch (DataAccessException dae) {
            questionDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            questionDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            questionDAOLogger.error("This is Error message", e);
            throw e;
        }
    }

    /**
     * updateQuestion method is for updating data of questionnaire create new
     * Query object with name query In query add criteria check questionId is
     * equal to Admin questionId value To findOne method with collection object
     * that matched to query.and assign to question create another query object
     * with name as queryForUpdate In query add criteria check _id is equal to
     * question object questionId value create new update object using Update
     * class set update method with String name as "questionId " and value as
     * adminDO questionId set update method with String name as "question " and
     * value as adminDO question set update method with String name as "UIItem "
     * and value as adminDO UIItem set update method with String name as
     * "options " and value as adminDo options updateFirst method to update new
     * data based on queryForUpdate and update object.
     *
     * @param adminQuestionDo
     * @throws java.lang.Exception
     */
    public void updateQuestion(AdminQuestionDO adminQuestionDo) throws Exception {
        questionDAOLogger.info("Going to run  AdminDAO updateQuestion method");
        try {

            Query query = new Query();

            query.addCriteria(Criteria.where("questionId").is(adminQuestionDo.getQuestionId()));

            AdminQuestionDO question = mongoTemplate.findOne(query, AdminQuestionDO.class, "questionnaire");

            Query queryForUpdate = new Query();
            queryForUpdate.addCriteria(Criteria.where("_id").is(question.getId()));

            Update update = new Update();

            update.set("questionId", adminQuestionDo.getQuestionId());
            update.set("question", adminQuestionDo.getQuestion());
            update.set("UIItem", adminQuestionDo.getUIItem());
            update.set("mandatory", adminQuestionDo.getMandatory());
            update.set("questionType", adminQuestionDo.getQuestionType());
            update.set("options", adminQuestionDo.getOptions());
             update.set("ruleRequired",adminQuestionDo.getRuleRequired());
            update.set("modificationDate", adminQuestionDo.getModificationDate());
            //System.out.println("The query is" + queryForUpdate + update);

            mongoTemplate.updateFirst(queryForUpdate, update, "questionnaire");

        } catch (DataAccessException dae) {
            questionDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            questionDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            questionDAOLogger.error("This is Error message", e);
            throw e;
        }
        questionDAOLogger.info(" AdminDAO End of updateQuestion method");
    }

    /**
     *
     * deleteQuestion method deleting objects in questionnaire collection based
     * on questionId
     *
     * @param questionId
     * @throws java.lang.Exception
     */
    public void deleteQuestion(int questionId) throws Exception {

        questionDAOLogger.info("Going to run  AdminDAO deleteQuestion method");
        try {
            //PageDO pageDO = new PageDO();

            Query query = new Query();
            query.addCriteria(Criteria.where("questionId").is(questionId));
            mongoTemplate.remove(query, AdminQuestionDO.class, "questionnaire");
        } catch (DataAccessException dae) {
            questionDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            questionDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            questionDAOLogger.error("This is Error message", e);
            throw e;
        }
        questionDAOLogger.info(" AdminDAO End of deleteQuestion method");
    }

    /**
     * findQuestionId method for finding lastquestionId and returning questionId
     * increase by one create new query object for Query class and set limit to
     * one and sorting question id in decreasing order find lastquestionId using
     * query adminQuestionDo class and questionnaire collection. check
     * questionId is null or not . If questionId is null set questionIdInc is
     * one. otherwise assign questionId from AdminauestionDo class and increment
     * questionIdInc by 1 Finally return questionIdInc value .
     *
     * @return
     * @throws java.lang.Exception
     */
    public int findQuestionId() throws Exception {
        questionDAOLogger.info("Going to run  AdminDAO findQuestionId method");
        int questionIdInc = 0;
        try {
            Query query = new Query();
            query.limit(1);
            query.with(new Sort(Sort.Direction.DESC, "questionId"));
            AdminQuestionDO questionId = mongoTemplate.findOne(query, AdminQuestionDO.class, "questionnaire");
            if (questionId == null) {
                questionIdInc = 1;

            } else {
                questionIdInc = questionId.getQuestionId();

                questionIdInc = questionIdInc + 1;
            }

        } catch (DataAccessException dae) {
            questionDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            questionDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            questionDAOLogger.error("This is Error message", e);
            throw e;
        }
        questionDAOLogger.info(" AdminDAO End of findQuestionId method");
        return questionIdInc;

    }

    /**
     * getNewBlock method for finding questions based on selcted options and
     * questionId and create hash map and that new question added to questionset
     * map object and return questionset.
     *
     * @param selectedOption
     * @param questionSet1
     * @return
     * @throws java.lang.Exception
     */
    public Map<Integer, Integer> getNewBlock(Map<Integer, String> selectedOption, Map<Integer, Integer> questionSet1) throws Exception {

        questionDAOLogger.info("Going to run  QuestionDAO getNewBlock method");

        Map<Integer, Integer> questionSet = null;
        try {
            questionSet = new HashMap<>();

            for (Integer questionId : selectedOption.keySet()) {

                if (questionSet1.get(questionId) != null) {
                    if (selectedOption.get(questionId) != null && !selectedOption.get(questionId).equals("")) {

                        Query query = new Query();
                        query.addCriteria(Criteria.where("questionId").is(questionId));
                        query.addCriteria(Criteria.where("options.option").is(selectedOption.get(questionId)));

                        QuestionAcessDO questionAcessDO = mongoTemplate.findOne(query, QuestionAcessDO.class, "questionnaire");

                        for (OptionsDO optionList : questionAcessDO.getOptions()) {
                            if (optionList.getOption().equals(selectedOption.get(questionId))) {

                                questionSet.put(optionList.getQuestionId(), optionList.getQuestionId());
                            }
                        }

                    }
                }
            }

        } catch (DataAccessException dae) {
            questionDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            questionDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            questionDAOLogger.error("This is Error message", e);
            throw e;
        }
        questionDAOLogger.info(" QuestionDAO End of getNewBlock method");
        return questionSet;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
//        QuestionDAO questionDAO=new QuestionDAO();
//        questionDAO.addData(adminQuestionDo);
//        questionDAO.deleteQuestion(0);
//        questionDAO.findQuestion(0);
//        questionDAO.findQuestionId();
//        questionDAO.findQuestionnaire(0);
//        questionDAO.getNewBlock(selectedOption, questionSet1);
//        questionDAO.getQuestions();
//        questionDAO.retrieveData();
//        questionDAO.updateQuestion(adminQuestionDo);
    }

    public QuestionDO findLastQuestion() throws Exception {
         questionDAOLogger.info("Going to run  QuestionAO lastPage method");
        QuestionDO questionDO = null;
        try {
            Query query = new Query();
            query.limit(2);
            query.with(new Sort(Sort.Direction.DESC, "questionId"));
            questionDO = mongoTemplate.findOne(query, QuestionDO.class, "questionnaire");

        } catch (DataAccessException dae) {
            questionDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            questionDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            questionDAOLogger.error("This is Error message", e);
            throw e;
        }
        questionDAOLogger.info(" PageDAO End of lastPage method");
        return questionDO;
    }

    public BlockDO findLastBlock() {
         questionDAOLogger.info("Going to run  BlockDAO lastPage method");
        BlockDO blockDO = null;
        try {
            Query query = new Query();
            query.limit(2);
            query.with(new Sort(Sort.Direction.DESC, "blockId"));
            blockDO = mongoTemplate.findOne(query, BlockDO.class, "block");

        } catch (DataAccessException dae) {
            questionDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            questionDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            questionDAOLogger.error("This is Error message", e);
            throw e;
        }
        questionDAOLogger.info(" PageDAO End of lastPage method");
        return blockDO;
    }

    public AdminQuestionDO findAllOptions(int questionId) {
          questionDAOLogger.info("Going to run  AdminDAO retrieveData method");
        AdminQuestionDO adminQuestionDOList = null;
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("questionId").is(questionId));
            adminQuestionDOList = mongoTemplate.findOne(query, AdminQuestionDO.class, "questionnaire");
            for(OptionsDO ad:adminQuestionDOList.getOptions()){
               // System.out.println("lkjasdf : "+ad.getOption());
            }
        } catch (DataAccessException dae) {
            questionDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            questionDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            questionDAOLogger.error("This is Error message", e);
            throw e;
        }
        questionDAOLogger.info("AdminDAO End of retrieveData method");
        return adminQuestionDOList;
    }


}
