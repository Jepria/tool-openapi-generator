package org.jepria.tools.openapi.generator.languages.jersey.models;

import io.swagger.v3.oas.models.OpenAPI;
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

  public static ApplicationConfigModel getFromSpec( OpenAPI spec, String mainPackage) {

    ApplicationConfigModel model = new ApplicationConfigModel();

    List<BaseJaxrsModel> adapters = BaseJaxrsModel.getFromSpec(spec);
    for (BaseJaxrsModel adapter : adapters) {
      adapter.setApiPackage(mainPackage + "." + adapter.getClassName().toLowerCase() + ".rest");
    }

    model.setAdapters(adapters);
    model.setMainPackage(mainPackage);

    return model;
  }

}
