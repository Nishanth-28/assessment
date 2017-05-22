/**
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.dos;

import static jdk.nashorn.internal.runtime.Debug.id;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cjp
 */

public class OptionsDO {
     
   
    private String option;
    private int questionId;

    /**
     *
     * @return
     */
    public String getOption() {
        return option;
    }

    /**
     *
     * @param option
     */
    public void setOption(String option) {
        this.option = option;
    }

    /**
     *
     * @return
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     *
     * @param questionId
     */
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

   

}
