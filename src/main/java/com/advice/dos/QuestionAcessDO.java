/**
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.dos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author cjp
 */
@Document
public class QuestionAcessDO {

    @Id
    private String id;
    private int questionId;
    private String question;
    private String UIItem;
    private OptionsDO[] options;

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
    public OptionsDO[] getOptions() {
        return options;
    }

    /**
     *
     * @param options
     */
    public void setOptions(OptionsDO[] options) {
        this.options = options;
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

}
