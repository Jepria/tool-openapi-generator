package org.jepria.showcase.featureprocess.dto;

import org.jepria.server.data.OptionDto;

import java.util.Date;
import java.util.List;

public class FeatureProcessCreateDto {

  private String featureStatusCode;

  public void setFeatureStatusCode(String featureStatusCode){
    this.featureStatusCode = featureStatusCode;
  }
  
  public String getFeatureStatusCode(){
    return this.featureStatusCode;
  }

}