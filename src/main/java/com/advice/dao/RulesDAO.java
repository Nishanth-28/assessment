/**
 *
 *The ReportDAO used to  retrieve data from rules engine and survey
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.dao;

import static com.advice.dao.PageDAO.COLLECTION_NAME;
import com.advice.dos.CloudScore;
import com.advice.dos.PageDO;
import com.advice.dos.RulesEngineDO;
import com.advice.encryptionAlgorithams.EncryptionDecryption;
import com.mongodb.MongoException;
import java.util.List;
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
 * annotated class is a "Repository" (or "DAO"). A class thus annotated is
 * eligible for Spring DataAccessException translation. The annotated class is
 * also clarified as to its role in the overall application architecture for the
 * purpose of tools, aspects, etc. this annotation also serves as a
 * specialization of @Component, allowing for implementation classes to be auto
 * detected through class path scanning
 * @Autowired Marks a constructor, field, setter method or configuration method
 * as to be auto wired by Spring 's dependency injection facilities.
 * @author cjp
 */
@Repository
public class RulesDAO {
    
    private static final String COLLECTION_NAME = "rules";

    private static final Logger logger = Logger.getLogger(RulesDAO.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EncryptionDecryption encryptionDecryption;
    
    @Autowired
    private CloudAvailableDAO couldAvailabeDAO;

    /**
     * getRulesEngine method for getting all rules from rules collection
     *
     * @return rulesList @throws java.lang.Exception
     * @throws java.lang.Exception
     */
    public List<RulesEngineDO> findAllrules() throws Exception {
        logger.info("Start in the getRulesEngine method of object RulesDAO");
        List<RulesEngineDO> rulesEngineDOList = null;
        try {
             
            rulesEngineDOList = mongoTemplate.findAll(RulesEngineDO.class, COLLECTION_NAME);
            
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
        logger.info("End in the getRulesEngine method of object RulesDAO");
        return rulesEngineDOList;
    }

    /**
     *
     * postRulesData is for creating collections , inserting and updating
     * collections. check collection is Exist or not. If collection not exists
     * create new Collection of adminQuestionDo Generate dynamic Id with create
     * collection object Insert data adminQuestionDO with "rules collection"
     * updateQuestion method is for updating data of rules create new Query
     * object with name query In query add criteria check questions is equal to
     * rulesEngine questionId value In query add criteria check choice is equal
     * to rulesEngine choice value To findOne method with collection object that
     * matched to query.and assign to question create another query object with
     * name as queryForUpdate In query add criteria check _id is equal to
     * question object questionId value create new update object using Update
     * class set update method with String name as "score " and value as
     * RulesEngineDO score set update method with String name as "cloud " and
     * value as RulesEngineDO cloud updateFirst method to update new data based
     * on queryForUpdate and update object.
     *
     *
     * @param rulesEngine
     * @throws java.lang.Exception
     */
    public void saveRule(RulesEngineDO rulesEngine) throws Exception {
        logger.info("Going to run  AdminDAO postRulesData method");
        try {
            if (!mongoTemplate.collectionExists(RulesEngineDO.class)) {
                mongoTemplate.createCollection(RulesEngineDO.class);
            }
            Query query = new Query();

            query.addCriteria(Criteria.where("questionId").is(rulesEngine.getQuestionId()));
            query.addCriteria(Criteria.where("choice").is(rulesEngine.getChoice()));
            RulesEngineDO question = mongoTemplate.findOne(query, RulesEngineDO.class, "rules");

            if (question == null) {
                rulesEngine.setId(UUID.randomUUID().toString());
                mongoTemplate.insert(rulesEngine, "rules");

            } else {

                Query queryForUpdate = new Query();
                queryForUpdate.addCriteria(Criteria.where("_id").is(question.getId()));

                Update update = new Update();

             update.set("remarks", rulesEngine.getRemarks());
              update.set("scoring", rulesEngine.getScoring());
                mongoTemplate.updateFirst(queryForUpdate, update, RulesEngineDO.class, "rules");

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
        logger.info(" AdminDAO End of postRulesData method");
    }

    /**
     * updateRulesData method is for updating data of rules. create new Query
     * object with name query In query add criteria check questions is equal to
     * RulesEngineDO questionId value In query add criteria check choice is
     * equal to RulesEngineDO Choice value To findOne method with collection
     * object that matched to query.and assign to question create another query
     * object with name as queryForUpdate In query add criteria check _id is
     * equal to question object questionId value create new update object using
     * Update class set update method with String name as "score " and value as
     * RulesEngineDO score set update method with String name as "cloud " and
     * value as RulesEngineDO cloud updateFirst method to update new data based
     * on queryForUpdate and update object.
     *
     *
     * @param rulesEngine
     * @throws java.lang.Exception
     */
//    public void updateRule(RulesEngineDO rulesEngine) throws Exception {
//        logger.info("Going to run  AdminDAO updateRulesData method");
//        try {
//
//            Query query = new Query();
//
//            query.addCriteria(Criteria.where("questionId").is(rulesEngine.getQuestionId()));
//            query.addCriteria(Criteria.where("choice").is(rulesEngine.getChoice()));
//            RulesEngineDO question = mongoTemplate.findOne(query, RulesEngineDO.class, "rules");
//
//            Query queryForUpdate = new Query();
//            queryForUpdate.addCriteria(Criteria.where("_id").is(question.getId()));
//
//            Update update = new Update();
//
//            //update.set("score", rulesEngine.getScore());
//            //update.set("cloud", rulesEngine.getCloud());
//            update.set("modificationDate", rulesEngine.getModificationDate());
//
//            mongoTemplate.updateFirst(queryForUpdate, update, RulesEngineDO.class, "rules");
//
//        } catch (DataAccessException dae) {
//            logger.error("This is Error message", dae);
//            throw dae;
//        } catch (MongoException me) {
//            logger.error("This is Error message", me);
//            throw me;
//        } catch (Exception e) {
//            logger.error("This is Error message", e);
//            throw e;
//        }
//        logger.info("AdminDAO End of updateRulesData method");
//    }

    /**
     * deleteRule method deleting objects in rules collection based on
     * questionId
     *
     *
     * @param questionId
     * @param choice
     * @throws java.lang.Exception
     */
    public void deleteRule(int questionId,String choice) throws Exception {
        logger.info("Going to run  AdminDAO deleteRule method");
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("questionId").is(questionId));
             query.addCriteria(Criteria.where("choice").is(choice));
            
            mongoTemplate.remove(query, RulesEngineDO.class, "rules");
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
        logger.info("AdminDAO End of deleteRule method");
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
//       RulesDAO rulesDAO=new RulesDAO(); 
//       rulesDAO.deleteRule(0);
//       rulesDAO.getRulesEngine();
//       rulesDAO.postRulesData(rulesEngine);
//       rulesDAO.retrieveRulesData();
//       rulesDAO.updateRulesData(rulesEngine);
    }

    public RulesEngineDO findLastRule() {
        logger.info("Going to run  RulesDAO lastPage method");
        RulesEngineDO rulesEngineDO = null;
        try {
            Query query = new Query();
            query.limit(2);
            query.with(new Sort(Sort.Direction.DESC, "questionId"));
            rulesEngineDO = mongoTemplate.findOne(query, RulesEngineDO.class, "rules");

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
        logger.info(" PageDAO End of lastPage method");
        return rulesEngineDO;
    }

}
