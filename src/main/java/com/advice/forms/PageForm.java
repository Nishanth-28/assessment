/**
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.forms;

import java.util.Map;

/**
 *
 * @author cjp
 */
public class PageForm {

    private int pageId;
    private String pageTitle;
    private int[] blockSet;
     private int lastpageId;

    public int getLastpageId() {
        return lastpageId;
    }

    public void setLastpageId(int lastpageId) {
        this.lastpageId = lastpageId;
    }
    private Map<Integer, Integer> blockSetMap;

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
    public int[] getBlockSet() {
        return blockSet;
    }

    /**
     *
     * @param blockSet
     */
    public void setBlockSet(int[] blockSet) {
        this.blockSet = blockSet;
    }

    /**
     *
     * @return
     */
    public Map<Integer, Integer> getBlockSetMap() {
        return blockSetMap;
    }

    /**
     *
     * @param blockSetMap
     */
    public void setBlockSetMap(Map<Integer, Integer> blockSetMap) {
        this.blockSetMap = blockSetMap;
    }

    

}
