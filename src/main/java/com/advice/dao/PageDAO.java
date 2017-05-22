/**
 *The BlockDAO used to create collection of   block .
 * here we also do crud operations.
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.dao;

import com.advice.dos.AdminQuestionDO;
import com.advice.dos.PageDO;
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
 * annotated class is a "Repository" (or "DAO").
 *
 * A class thus annotated is eligible for Spring DataAccessException
 * translation. The annotated class is also clarified as to its role in the
 * overall application architecture for the purpose of tools, aspects, etc.
 *
 * this annotation also serves as a specialization of @Component, allowing for
 * implementation classes to be autodetected through classpath scanning
 * @Autowired Marks a constructor, field, setter method or config method as to
 * be autowired by Spring's dependency injection facilities.
 * @author cjp
 */
@Repository
public class PageDAO {

   @Autowired
    private MongoTemplate mongoTemplate;
   
   @Autowired
    private EncryptionDecryption encryptionDecryption;

    
    public static final String COLLECTION_NAME = "page";

    private static final Logger pageDAOLogger = Logger.getLogger(PageDAO.class);

    /**
     * getPages is retrieving all pages data and pages collection
     *
     *
     * @param pageId
     * @return
     * @throws Exception
     */
    public PageDO getPageByPageId(int pageId) throws Exception {
         pageDAOLogger.info("Going to run  PageDAO getPages method");
        PageDO pageDO = null;
        try {
            Query query = new Query();

            query.addCriteria(Criteria.where("pageId").is(pageId));
            pageDO = mongoTemplate.findOne(query, PageDO.class, "page");

        } catch (DataAccessException dae) {
            pageDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            pageDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            pageDAOLogger.error("This is Error message", e);
            throw e;
        }
        pageDAOLogger.info("PageDAO End of getPages method");
        return pageDO;
    }
     /**
     * retrievePagesData method for finding All pages and returning PageData
     *
     *
     * @return @throws Exception
     */
    public List<PageDO> findAllPages() throws Exception {
        pageDAOLogger.info("Going to run  AdminDAO retrievePagesData method");

        List<PageDO> pageDOList = null;
        try {
            pageDOList = mongoTemplate.findAll(PageDO.class, "page");

        } catch (DataAccessException dae) {
            pageDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            pageDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            pageDAOLogger.error("This is Error message", e);
            throw e;
        }
        pageDAOLogger.info(" AdminDAO End of retrievePagesData method");
        return pageDOList;
    }
     /**
     *
     * @param blockId
     * @return
     */
    public int getPageByBlockId(int blockId) {
          pageDAOLogger.info("Going to run  AdminDAO updatePage method");
       
        int pageId = 0;
        try {
            if (pageDAOLogger.isDebugEnabled()) {
                pageDAOLogger.debug("Start debug");
            }
            //Query query = new Query();

              //query.addCriteria(Criteria.where("pageId").is(pageDO.getPageId()));
              List<PageDO> pageDOList = mongoTemplate.findAll(PageDO.class, COLLECTION_NAME);
            for (PageDO pageDO : pageDOList) {
                if (pageDO.getBlockSet().containsKey(blockId)) {
                    pageId = pageDO.getPageId();
                } else {

                }
            }
        } catch (DataAccessException dae) {
            pageDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            pageDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            pageDAOLogger.error("This is Error message", e);
            throw e;
        }
        pageDAOLogger.info(" AdminDAO End of updatePage method");
        return pageId;
    }

    /**
     * lastPage method is for finding last pageId
     *
     *
     * @return @throws Exception
     */
    public PageDO findLastPage() throws Exception {
         pageDAOLogger.info("Going to run  PageDAO lastPage method");
        PageDO pageDO = null;
        try {
            Query query = new Query();
            query.limit(2);
            query.with(new Sort(Sort.Direction.DESC, "pageId"));
            pageDO = mongoTemplate.findOne(query, PageDO.class, "page");

        } catch (DataAccessException dae) {
            pageDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            pageDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            pageDAOLogger.error("This is Error message", e);
            throw e;
        }
        pageDAOLogger.info(" PageDAO End of lastPage method");
        return pageDO;
    }

    /**
     *
     * savePage method is for creating collections , inserting and updating
     * collections. check collection is Exist or not. If collection not exists
     * create new Collection of pageDO Generate dynamic Id with create
     * collection object Insert data adminQuestionDO with "pages collection"
     * updateQuestion method is for updating data of pages create new Query
     * object with name query In query add criteria check pageId is equal to
     * admin pageId value To findOne method with collection object that matched
     * to query.and assign to question create another query object with name as
     * queryForUpdate In query add criteria check _id is equal to question
     * object questionId value create new update object using Update class set
     * update method with String name as "pageTitle " and value as pageDO
     * pageTitle set update method with String name as "blockSet " and value as
     * pageDO blockSet updateFirst method to update new data based on
     * queryForUpdate and update object.
     *
     *
     * @param pageDO
     * @throws Exception
     */
    public void savePage(PageDO pageDO) throws Exception {
        pageDAOLogger.info("Going to run  AdminDAO savePage method");
        try {

            if (!mongoTemplate.collectionExists(PageDO.class)) {
                mongoTemplate.createCollection(PageDO.class);
            }
            Query query = new Query();

            query.addCriteria(Criteria.where("pageId").is(pageDO.getPageId()));

            PageDO question = mongoTemplate.findOne(query, PageDO.class, "page");

            if (question == null) {
                pageDO.setId(UUID.randomUUID().toString());
                mongoTemplate.insert(pageDO, "page");

            } else {

                Query queryForUpdate = new Query();
                queryForUpdate.addCriteria(Criteria.where("_id").is(question.getId()));

                Update update = new Update();
                update.set("pageTitle", pageDO.getPageTitle());
                update.set("blockSet", pageDO.getBlockSet());
                update.set("modificationDate", pageDO.getModificationDate());


                mongoTemplate.updateFirst(queryForUpdate, update, PageDO.class, "page");

            }
        } catch (DataAccessException dae) {
            pageDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            pageDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            pageDAOLogger.error("This is Error message", e);
            throw e;
        }
        pageDAOLogger.info(" AdminDAO End of savePage method");

    }

    /**
     * updatePage method is for updating data of pages. create new Query object
     * with name query In query add criteria check pageId is equal to PageDO
     * pageId value To findOne method with collection object that matched to
     * query.and assign to question create another query object with name as
     * queryForUpdate In query add criteria check _id is equal to question
     * object questionId value create new update object using Update class set
     * update method with String name as "pageTitle " and value as RulesEngineDO
     * pageTitle set update method with String name as "blockSet " and value as
     * RulesEngineDO blockSet updateFirst method to update new data based on
     * queryForUpdate and update object.
     *
     *
     * @param pageDO
     * @throws Exception
     */
    public void updatePage(PageDO pageDO) throws Exception {
            pageDAOLogger.info("Going to run  AdminDAO updatePage method");
        try {

            Query query = new Query();

            query.addCriteria(Criteria.where("pageId").is(pageDO.getPageId()));

            PageDO question = mongoTemplate.findOne(query, PageDO.class, "page");
            Query queryForUpdate = new Query();
            queryForUpdate.addCriteria(Criteria.where("_id").is(question.getId()));

            Update update = new Update();
            update.set("pageTitle", pageDO.getPageTitle());
            update.set("blockSet", pageDO.getBlockSet());
            update.set("modificationDate", pageDO.getModificationDate());

            mongoTemplate.updateFirst(queryForUpdate, update, PageDO.class, "page");

        } catch (DataAccessException dae) {
            pageDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            pageDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            pageDAOLogger.error("This is Error message", e);
            throw e;
        }
        pageDAOLogger.info("AdminDAO End of updatePage method");
    }

    /*
    *
    * addData method is for creating collections , inserting and updating collections.
    * check collecion is Exist or not. If collection not exists create new Collection of adminQuestionDo
    * Generate dynamic Id with create collection object
    * Insert data adminQuestionDO with "questionnaire collection"    
    * updateQuestion method is for updating data of questionnaire
    * create new Query object  with name query
    * In query  add criteria  check questionId is equal to admin questionId value
    * To findOne method with collection objecte that matched to query.and assign to question
    * create another query object with name as queryForUpdate
    * In query  add criteria  check _id is equal to question object questionId value
    * create new update object using Update class
    * set update method with String name as "questionId " and value as adminDO questionId 
    * set update method with String name as "question " and value as adminDO question
    * set update method with String name as "UIItem " and value as adminDO UIItem 
    * set update method with String name as "options " and value as adminDo options 
    * updateFirst method to update new data based on queryForUpdate and update object.
     */
   

    /**
     * deletePage method deleting objects in page collection based on pageId
     *
     *
     * @param pageId
     * @throws Exception
     */
    public void deletePage(int pageId) throws Exception {
        
            pageDAOLogger.info("Going to run  AdminDAO deletePage method");
        try {

            //PageDO pageDO = new PageDO();
            Query query = new Query();
            query.addCriteria(Criteria.where("pageId").is(pageId));
            mongoTemplate.remove(query, PageDO.class, "page");
        } catch (DataAccessException dae) {
            pageDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            pageDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            pageDAOLogger.error("This is Error message", e);
            throw e;
        }
        pageDAOLogger.info(" AdminDAO End of deletePage method");
    }

    /**
     * deleteQuestion method deleting objects in questionnaire collection based
     * on questionId
     *
     *
     * @param questionId
     * @throws Exception
     */
    public void deleteQuestion(int questionId) throws Exception {
         pageDAOLogger.info("Going to run  AdminDAO deleteQuestion method");
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("questionId").is(questionId));
            mongoTemplate.remove(query, AdminQuestionDO.class, "questionnaire");
        } catch (DataAccessException dae) {
            pageDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            pageDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            pageDAOLogger.error("This is Error message", e);
            throw e;
        }
        pageDAOLogger.info(" AdminDAO End of  deleteQuestion method");
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
//        PageDAO pageDAO=new PageDAO();
//        pageDAO.deletePage(0);
//        pageDAO.deleteQuestion(0);
//        pageDAO.getPages(0);
//        pageDAO.lastPage();
//        pageDAO.retrievePagesData();
//        pageDAO.savePage(pageDO);
//        pageDAO.updatePage(pageDO);
    }

   

}
