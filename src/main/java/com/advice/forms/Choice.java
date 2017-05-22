/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advice.forms;

import com.advice.dos.CloudScore;
import java.util.List;

/**
 *
 * @author cjp
 */
public class Choice {
    private String choiceName;
     private List<CloudScore> scoring;
     private String remarks;

    public String getChoiceName() {
        return choiceName;
    }

    public void setChoiceName(String choiceName) {
        this.choiceName = choiceName;
    }

    public List<CloudScore> getScoring() {
        return scoring;
    }

    public void setScoring(List<CloudScore> scoring) {
        this.scoring = scoring;
    }
     public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
   
  
    
}
