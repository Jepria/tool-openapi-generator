package org.jepria.showcase.feature.dto;

import org.jepria.server.data.OptionDto;

import java.util.Date;
import java.util.List;

public class FeatureUpdateDto {

  private String featureName;

  private String featureNameEn;

  private String description;

  private Integer responsibleId;

  public void setFeatureName(String featureName){
    this.featureName = featureName;
  }
  
  public String getFeatureName(){
    return this.featureName;
  }

  public void setFeatureNameEn(String featureNameEn){
    this.featureNameEn = featureNameEn;
  }
  
  public String getFeatureNameEn(){
    return this.featureNameEn;
  }

  public void setDescription(String description){
    this.description = description;
  }
  
  public String getDescription(){
    return this.description;
  }

  public void setResponsibleId(Integer responsibleId){
    this.responsibleId = responsibleId;
  }
  
  public Integer getResponsibleId(){
    return this.responsibleId;
  }

}