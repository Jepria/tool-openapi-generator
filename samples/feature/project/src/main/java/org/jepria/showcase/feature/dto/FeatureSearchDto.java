package org.jepria.showcase.feature.dto;

import org.jepria.server.data.OptionDto;

import java.util.Date;
import java.util.List;

public class FeatureSearchDto {

  private Integer featureId;

  private String featureNameTemplate;

  private String featureNameEnTemplate;

  private List<String> statusCodeList;

  private Integer authorId;

  private Integer responsibleId;

  private DateTime dateInsFrom;

  private DateTime dateInsTo;

  private Integer maxRowCount;

  public void setFeatureId(Integer featureId){
    this.featureId = featureId;
  }
  
  public Integer getFeatureId(){
    return this.featureId;
  }

  public void setFeatureNameTemplate(String featureNameTemplate){
    this.featureNameTemplate = featureNameTemplate;
  }
  
  public String getFeatureNameTemplate(){
    return this.featureNameTemplate;
  }

  public void setFeatureNameEnTemplate(String featureNameEnTemplate){
    this.featureNameEnTemplate = featureNameEnTemplate;
  }
  
  public String getFeatureNameEnTemplate(){
    return this.featureNameEnTemplate;
  }

  public void setStatusCodeList(List<String> statusCodeList){
    this.statusCodeList = statusCodeList;
  }
  
  public List<String> getStatusCodeList(){
    return this.statusCodeList;
  }

  public void setAuthorId(Integer authorId){
    this.authorId = authorId;
  }
  
  public Integer getAuthorId(){
    return this.authorId;
  }

  public void setResponsibleId(Integer responsibleId){
    this.responsibleId = responsibleId;
  }
  
  public Integer getResponsibleId(){
    return this.responsibleId;
  }

  public void setDateInsFrom(DateTime dateInsFrom){
    this.dateInsFrom = dateInsFrom;
  }
  
  public DateTime getDateInsFrom(){
    return this.dateInsFrom;
  }

  public void setDateInsTo(DateTime dateInsTo){
    this.dateInsTo = dateInsTo;
  }
  
  public DateTime getDateInsTo(){
    return this.dateInsTo;
  }

  public void setMaxRowCount(Integer maxRowCount){
    this.maxRowCount = maxRowCount;
  }
  
  public Integer getMaxRowCount(){
    return this.maxRowCount;
  }

}