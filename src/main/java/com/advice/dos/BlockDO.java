/**
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.dos;

import java.util.Date;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author cjp
 */
@Document
public class BlockDO {
     @Id
    private String id;
    private int blockId;
    private Map<Integer, Integer> questionSet;
    private String blockTitle;
    private Date creationDate;
    private Date modificationDate;

    public Date getCreationDate() {
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    /**
     *
     * @return
     */
    public int getBlockId() {
        return blockId;
    }

    /**
     *
     * @param blockId
     */
    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    /**
     *
     * @return
     */
    public Map<Integer, Integer> getQuestionSet() {
        return questionSet;
    }

    /**
     *
     * @param questionSet
     */
    public void setQuestionSet(Map<Integer, Integer> questionSet) {
        this.questionSet = questionSet;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getBlockTitle() {
        return blockTitle;
    }

    public void setBlockTitle(String blockTitle) {
        this.blockTitle = blockTitle;
    }

}
