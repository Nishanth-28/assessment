/**
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.dos;

import java.util.Date;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author cjp
 */
@Document
public class SurveyDO {
    @Id
    private String id;
    private String userId;
    private String organization;
    private String application;
    private Map<String, String> questionnaire;
    
    private String creationDate;
    private String modificationDate;
    private String reportGeneratedDate;

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getReportGeneratedDate() {
        return reportGeneratedDate;
    }

    public void setReportGeneratedDate(String reportGeneratedDate) {
        this.reportGeneratedDate = reportGeneratedDate;
    }

    
    
//    private Date creationDate;
//    private Date modificationDate;
//    private Date reportGeneratedDate;
//
//    public Date getReportGeneratedDate() {
//        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
//        return reportGeneratedDate;
//    }
//
//    public void setReportGeneratedDate(Date reportGeneratedDate) {
//        this.reportGeneratedDate = reportGeneratedDate;
//    }
//    
//    public Date getCreationDate() {
//        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return creationDate;
//    }
//
//    public void setCreationDate(Date creationDate) {
//        this.creationDate = creationDate;
//    }
//
//    public Date getModificationDate() {
//        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return modificationDate;
//    }
//
//    public void setModificationDate(Date modificationDate) {
//        this.modificationDate = modificationDate;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Map<String, String> getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Map<String, String> questionnaire) {
        this.questionnaire = questionnaire;
    }
   
   
   

}
