/**
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.forms;

import com.advice.dos.OptionsDO;
import java.util.Map;

/**
 *
 * @author cjp
 */
public class AdminQuestionForm {

    private String id;
    private int questionId;
    private String question;
    private String UIItem;
    private String mandatory;
    private String ruleRequired;
    private OptionsDO[] options;
    private Map<String,String> optionsMap;
    private String questionType;
    private String lastquestionId;
    private String questionSet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUIItem() {
        return UIItem;
    }

    public void setUIItem(String UIItem) {
        this.UIItem = UIItem;
    }

    public String getMandatory() {
        return mandatory;
    }

    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }

    public String getRuleRequired() {
        return ruleRequired;
    }

    public void setRuleRequired(String ruleRequired) {
        this.ruleRequired = ruleRequired;
    }

    public OptionsDO[] getOptions() {
        return options;
    }

    public void setOptions(OptionsDO[] options) {
        this.options = options;
    }

    public Map<String,String> getOptionsMap() {
        return optionsMap;
    }

    public void setOptionsMap(Map<String,String> optionsMap) {
        this.optionsMap = optionsMap;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getLastquestionId() {
        return lastquestionId;
    }

    public void setLastquestionId(String lastquestionId) {
        this.lastquestionId = lastquestionId;
    }

    public String getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(String questionSet) {
        this.questionSet = questionSet;
    }

    
    
}
