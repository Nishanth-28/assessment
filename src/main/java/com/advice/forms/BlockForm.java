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
public class BlockForm {

    private int blockId;
    private String blockTitle;
    private int[] questionSet;
    private Map<Integer, Integer> questionSetMap;
    private int lastblockId;

    public String getBlockTitle() {
        return blockTitle;
    }

    public void setBlockTitle(String blockTitle) {
        this.blockTitle = blockTitle;
    }

    public int getLastblockId() {
        return lastblockId;
    }

    public void setLastblockId(int lastblockId) {
        this.lastblockId = lastblockId;
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
    public int[] getQuestionSet() {
        return questionSet;
    }

    /**
     *
     * @param questionSet
     */
    public void setQuestionSet(int[] questionSet) {
        this.questionSet = questionSet;
    }

    /**
     *
     * @return
     */
    public Map<Integer, Integer> getQuestionSetMap() {
        return questionSetMap;
    }

    /**
     *
     * @param questionSetMap
     */
    public void setQuestionSetMap(Map<Integer, Integer> questionSetMap) {
        this.questionSetMap = questionSetMap;
    }

    /**
     *
     * @return
     */
}
