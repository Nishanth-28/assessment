/**
 *   The BlockDAO used to create collection of   block .
 * here we also do crud operations.
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.dao;

import com.advice.dos.BlockDO;
import com.advice.encryptionAlgorithams.EncryptionDecryption;
import com.mongodb.MongoException;
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
public class BlockDAO {

   @Autowired
    private MongoTemplate mongoTemplate;
   
   @Autowired
    private EncryptionDecryption encryptionDecryption;
   
    public static final String COLLECTION_NAME = "block";

    private static final Logger blockDAOLogger = Logger.getLogger(BlockDAO.class);

    /**
     * getBlocks is retrieving all blocks data and blockd collection
     *
     *
     * @return
     * @throws java.lang.Exception
     */
    public List<BlockDO> getAllBlocks()throws Exception  {
        List<BlockDO> blockList = null;
        try {

            blockDAOLogger.info("Going to run  BlockDAO getBlocks method");

            blockList = mongoTemplate.findAll(BlockDO.class, COLLECTION_NAME);
            blockDAOLogger.info("BlockDAO End of getBlocks method");
        } catch (DataAccessException dae) {
            blockDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            blockDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            blockDAOLogger.error("This is Error message", e);
            throw e;
        }
        return blockList;
    }

    /**
     * findBlock method is retrieving particular block based on blockId
     *
     * @param block
     * @return
     * @throws java.lang.Exception
     */
    public BlockDO findBlockByBlockId(int block) throws Exception {
        blockDAOLogger.info("Going to run  BlockDAO findBlock method");
        BlockDO questionSet = null;
        try {

            Query query = new Query();
            query.addCriteria(Criteria.where("blockId").is(block));

            questionSet = mongoTemplate.findOne(query, BlockDO.class, COLLECTION_NAME);

        }catch (DataAccessException dae) {
            blockDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            blockDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            blockDAOLogger.error("This is Error message", e);
            throw e;
        }
        blockDAOLogger.info("BlockDAO End of findBlock method");
        return questionSet;

    }

    /**
     *
     * @param blockDO
     * @throws java.lang.Exception
     */
    public void saveBlock(BlockDO blockDO) throws Exception {
        blockDAOLogger.info("Going to run  AdminDAO saveBlock method");
        try {

            if (!mongoTemplate.collectionExists(BlockDO.class)) {
                mongoTemplate.createCollection(BlockDO.class);
            }
            Query query = new Query();

            query.addCriteria(Criteria.where("blockId").is(blockDO.getBlockId()));

            BlockDO question = mongoTemplate.findOne(query, BlockDO.class, "block");

            if (question == null) {
                blockDO.setId(UUID.randomUUID().toString());
                mongoTemplate.insert(blockDO, "block");

            } else {

                Query queryForUpdate = new Query();
                queryForUpdate.addCriteria(Criteria.where("_id").is(question.getId()));

                Update update = new Update();

                update.set("questionSet", blockDO.getQuestionSet());

                mongoTemplate.updateFirst(queryForUpdate, update, BlockDO.class, "block");

            }
        } catch (DataAccessException dae) {
            blockDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            blockDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            blockDAOLogger.error("This is Error message", e);
            throw e;
        }
        blockDAOLogger.info("AdminDAO End of saveBlock method");

    }

    /**
     * updateBlock method is for updating data of blocks. create new Query
     * object with name query In query add criteria check blockId is equal to
     * BlockDO blockId value To findOne method with collection objecte that
     * matched to query.and assign to question create another query object with
     * name as queryForUpdate In query add criteria check _id is equal to
     * question object questionId value create new update object using Update
     * class set update method with String name as "questionSet " and value as
     * RulesEngineDO questionSet updateFirst method to update new data based on
     * queryForUpdate and update object.
     *
     *
     * @param blockDO
     * @throws Exception
     */
    public void updateBlock(BlockDO blockDO) throws Exception {
        blockDAOLogger.info("Going to run  AdminDAO updateBlock method");
        try {

            if (!mongoTemplate.collectionExists(BlockDO.class)) {
                mongoTemplate.createCollection(BlockDO.class);
            }
            Query query = new Query();

            query.addCriteria(Criteria.where("blockId").is(blockDO.getBlockId()));

            BlockDO question = mongoTemplate.findOne(query, BlockDO.class, "block");

            Query queryForUpdate = new Query();
            queryForUpdate.addCriteria(Criteria.where("_id").is(question.getId()));

            Update update = new Update();

            update.set("questionSet", blockDO.getQuestionSet());
            update.set("modificationDate", blockDO.getModificationDate());

            mongoTemplate.updateFirst(queryForUpdate, update, BlockDO.class, "block");

        } catch (DataAccessException dae) {
            blockDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            blockDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            blockDAOLogger.error("This is Error message", e);
            throw e;
        }
        blockDAOLogger.info("AdminDAO End of updateBlock method");

    }
    

    /**
     * deleteBlock method deleting objects in blocks collection based on blockId
     *
     * @param blockId
     * @throws Exception
     */
    public void deleteBlockByBlockId(int blockId) throws Exception {
        blockDAOLogger.info("Going to run  AdminDAO deleteBlock method");
        try {

            Query query = new Query();
            query.addCriteria(Criteria.where("blockId").is(blockId));
            mongoTemplate.remove(query, BlockDO.class, "block");
        } catch (DataAccessException dae) {
            blockDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            blockDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            blockDAOLogger.error("This is Error message", e);
            throw e;
        }
        blockDAOLogger.info("AdminDAO End of  deleteBlock method");
    }

    /**
     *
     * @param questionId
     * @return
     * @throws Exception
     */
    public int findBlocksByQuestionId(Integer questionId) throws Exception {
        blockDAOLogger.info("Going to run  AdminDAO updatePage method");

        int blockId = 0;
        try {
            // Query query = new Query();

            //query.addCriteria(Criteria.where("pageId").is(pageDO.getPageId()));
            List<BlockDO> blockDOList = mongoTemplate.findAll(BlockDO.class, COLLECTION_NAME);
            for (BlockDO blockDO : blockDOList) {
                if (blockDO.getQuestionSet().containsKey(questionId)) {
                    blockId = blockDO.getBlockId();
                } else {

                }
            }
        }catch (DataAccessException dae) {
            blockDAOLogger.error("This is Error message", dae);
            throw dae;
        } catch (MongoException me) {
            blockDAOLogger.error("This is Error message", me);
            throw me;
        } catch (Exception e) {
            blockDAOLogger.error("This is Error message", e);
            throw e;
        }
        blockDAOLogger.info("AdminDAO End of updatePage method");

        return blockId;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
//        BlockDAO blockDAO=new BlockDAO();
//        blockDAO.deleteBlock(0);
//        blockDAO.findBlock(0);
//        blockDAO.getBlocks();
//        blockDAO.retrieveBlocksData();
//        blockDAO.saveBlock(blockDO);
//        blockDAO.updateBlock(blockDO);
    }
}
