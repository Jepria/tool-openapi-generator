package org.jepria.showcase.feature.dto;

import org.jepria.server.data.OptionDto;

import java.util.Date;
import java.util.List;

public class FeatureDto {

  private Integer featureId;

  private String featureName;

  private String featureNameEn;

  private String description;

  private OptionDto<String> featureStatus;

  private DateTime dateIns;

  private OptionDto<Integer> author;

  private OptionDto<Integer> responsible;

  public void setFeatureId(Integer featureId){
    this.featureId = featureId;
  }
  
  public Integer getFeatureId(){
    return this.featureId;
  }

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

  public void setFeatureStatus(OptionDto<String> featureStatus){
    this.featureStatus = featureStatus;
  }
  
  public OptionDto<String> getFeatureStatus(){
    return this.featureStatus;
  }

  public void setDateIns(DateTime dateIns){
    this.dateIns = dateIns;
  }
  
  public DateTime getDateIns(){
    return this.dateIns;
  }

  public void setAuthor(OptionDto<Integer> author){
    this.author = author;
  }
  
  public OptionDto<Integer> getAuthor(){
    return this.author;
  }

  public void setResponsible(OptionDto<Integer> responsible){
    this.responsible = responsible;
  }
  
  public OptionDto<Integer> getResponsible(){
    return this.responsible;
  }

}