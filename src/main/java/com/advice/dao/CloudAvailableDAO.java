/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advice.dao;

import com.advice.dos.CloudAvailableDO;
import com.advice.encryptionAlgorithams.EncryptionDecryption;
import com.mongodb.MongoException;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Viswa
 */
@Repository
public class CloudAvailableDAO {
    
    private static final String COLLECTION_NAME = "availableClouds";
    private static final Logger logger = Logger.getLogger(CloudAvailableDAO.class);
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EncryptionDecryption encryptionDecryption;
    
    public List<CloudAvailableDO> getAvailableClouds() throws Exception {
        logger.info("Start in the method getAvailableClouds of object CloudAvailableDAO");
        try {
            return mongoTemplate.findAll(CloudAvailableDO.class, COLLECTION_NAME);
            
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
    }
    
    public CloudAvailableDO getAvailableCloud(int cloudId) throws Exception {
        logger.info("Start in the method getAvailableCloud of object CloudAvailableDAO");
        CloudAvailableDO cloudAvailable = null;
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("cloudId").is(cloudId));
            cloudAvailable = mongoTemplate.findOne(query, CloudAvailableDO.class, COLLECTION_NAME);
            
        } catch (DataAccessException dae) {
            logger.error("Data access exception ", dae);
            throw dae;
        } catch (MongoException me) {
            logger.error("Mongo Exception ", me);
            throw me;
        } catch (Exception e) {
            logger.error("Exception ", e);
            throw e;
        }
        logger.info("End in the method getAvailableCloud of object CloudAvailableDAO");
        return cloudAvailable;
    }
    
    
}
