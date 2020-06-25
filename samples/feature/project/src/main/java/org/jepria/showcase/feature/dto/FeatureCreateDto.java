package org.jepria.showcase.feature.dto;

import org.jepria.server.data.OptionDto;

import java.util.Date;
import java.util.List;

public class FeatureCreateDto {

  private String featureName;

  private String featureNameEn;

  private String description;

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

}