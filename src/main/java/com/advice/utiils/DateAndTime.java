/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advice.utiils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author cjptech
 */
public class DateAndTime {
    private Date creationDate;
    private Date modificationDate;
    private Date reportGeneratedDate;

    public String getCreationDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(creationDate);
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getModificationDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(modificationDate);
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }


    public String getReportGeneratedDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(reportGeneratedDate);
    }

    public void setReportGeneratedDate(Date reportGeneratedDate) {
        this.reportGeneratedDate = reportGeneratedDate;
    }
    
   
}
