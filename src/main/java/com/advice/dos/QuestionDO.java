/**
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.dos;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author cjp
 */
@Document
public class QuestionDO {

    @Id
    private String id;
    private int questionId;
    private String question;
    private String UIItem;
    private String mandatory;
    private String questionType;
    
    private List<OptionsDO> options;

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

    /**
     *
     * @return
     */
    public String getQuestion() {
        return question;
    }

    /**
     *
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     *
     * @return
     */
    public String getUIItem() {
        return UIItem;
    }

    /**
     *
     * @param UIItem
     */
    public void setUIItem(String UIItem) {
        this.UIItem = UIItem;
    }

    /**
     *
     * @return
     */
    public List<OptionsDO> getOptions() {
        return options;
    }

    /**
     *
     * @param options
     */
    public void setOptions(List<OptionsDO> options) {
        this.options = options;
    }

    /**
     *
     * @return
     */
    public String getMandatory() {
        return mandatory;
    }

    /**
     *
     * @param mandatory
     */
    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }

    /**
     *
     * @return
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     *
     * @param questionType
     */
    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

}
