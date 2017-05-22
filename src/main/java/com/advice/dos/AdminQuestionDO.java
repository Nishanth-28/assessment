/**
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.dos;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author cjp
 */
@Document
public class AdminQuestionDO {
    @Id
    private String id;
    private int questionId;
    private String question;
    private String UIItem;
    private String mandatory;
    private OptionsDO[] options;
     private String questionType;
        private String ruleRequired;
    private Date creationDate;
    private Date modificationDate;

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

    public OptionsDO[] getOptions() {
        return options;
    }

    public void setOptions(OptionsDO[] options) {
        this.options = options;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getRuleRequired() {
        return ruleRequired;
    }

    public void setRuleRequired(String ruleRequired) {
        this.ruleRequired = ruleRequired;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

   
}
