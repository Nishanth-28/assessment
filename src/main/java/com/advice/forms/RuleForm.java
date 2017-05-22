/**
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.forms;

import com.advice.dos.CloudScore;
import java.util.List;

/**
 *
 * @author cjp
 */
public class RuleForm {

    private String id;
    private String questionId;
    private String question;
    private String choiceName;
    private String remarks;
    //private List<Choice> choice;
    private List<CloudScore> scoring;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChoiceName() {
        return choiceName;
    }

    public void setChoiceName(String choiceName) {
        this.choiceName = choiceName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<CloudScore> getScoring() {
        return scoring;
    }

    public void setScoring(List<CloudScore> scoring) {
        this.scoring = scoring;
    }


}
