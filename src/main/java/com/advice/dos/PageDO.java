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
public class PageDO {
    
     @Id
    private String id;
    private int pageId;
    private String pageTitle;
    private Map<Integer, Integer> blockSet;
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
    public int getPageId() {
        return pageId;
    }

    /**
     *
     * @param pageId
     */
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    /**
     *
     * @return
     */
    public Map<Integer, Integer> getBlockSet() {
        return blockSet;
    }

    /**
     *
     * @param blockSet
     */
    public void setBlockSet(Map<Integer, Integer> blockSet) {
        this.blockSet = blockSet;
    }

    /**
     *
     * @return
     */
    public String getPageTitle() {
        return pageTitle;
    }

    /**
     *
     * @param pageTitle
     */
    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
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

}
