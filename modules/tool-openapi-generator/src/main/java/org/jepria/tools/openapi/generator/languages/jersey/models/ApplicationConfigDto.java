package org.jepria.tools.openapi.generator.languages.jersey.models;

import java.util.List;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.BaseJaxrsDto;

public class ApplicationConfigDto {
  private String mainPackage;

  public void setAdapters(List<BaseJaxrsDto> adapters) {
    this.adapters = adapters;
  }

  List<BaseJaxrsDto> adapters;

  public void setMainPackage(String mainPackage) {
    this.mainPackage = mainPackage;
  }

}
