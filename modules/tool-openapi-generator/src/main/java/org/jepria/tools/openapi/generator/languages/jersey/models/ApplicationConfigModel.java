package org.jepria.tools.openapi.generator.languages.jersey.models;

import java.util.List;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.BaseJaxrsModel;

public class ApplicationConfigModel {
  private String mainPackage;

  public void setAdapters(List<BaseJaxrsModel> adapters) {
    this.adapters = adapters;
  }

  List<BaseJaxrsModel> adapters;

  public void setMainPackage(String mainPackage) {
    this.mainPackage = mainPackage;
  }

}
