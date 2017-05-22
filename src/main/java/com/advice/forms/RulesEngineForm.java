/**
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.forms;

import java.util.List;

/**
 *
 * @author cjp
 */
public class RulesEngineForm {
    private String questionId;
   private List<RuleForm> ruleForm;

    public List<RuleForm> getRuleForm() {
        return ruleForm;
    }

    public void setRuleForm(List<RuleForm> ruleForm) {
        this.ruleForm = ruleForm;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

}
