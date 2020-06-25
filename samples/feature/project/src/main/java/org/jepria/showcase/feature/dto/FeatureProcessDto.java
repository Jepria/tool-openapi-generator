package org.jepria.showcase.feature.dto;

import org.jepria.server.data.OptionDto;

import java.util.Date;
import java.util.List;

public class FeatureProcessDto {

  private Integer featureProcessId;

  private Integer featureId;

  private String featureStatusCode;

  private String featureStatusName;

  private DateTime dateIns;

  public void setFeatureProcessId(Integer featureProcessId){
    this.featureProcessId = featureProcessId;
  }
  
  public Integer getFeatureProcessId(){
    return this.featureProcessId;
  }

  public void setFeatureId(Integer featureId){
    this.featureId = featureId;
  }
  
  public Integer getFeatureId(){
    return this.featureId;
  }

  public void setFeatureStatusCode(String featureStatusCode){
    this.featureStatusCode = featureStatusCode;
  }
  
  public String getFeatureStatusCode(){
    return this.featureStatusCode;
  }

  public void setFeatureStatusName(String featureStatusName){
    this.featureStatusName = featureStatusName;
  }
  
  public String getFeatureStatusName(){
    return this.featureStatusName;
  }

  public void setDateIns(DateTime dateIns){
    this.dateIns = dateIns;
  }
  
  public DateTime getDateIns(){
    return this.dateIns;
  }

}