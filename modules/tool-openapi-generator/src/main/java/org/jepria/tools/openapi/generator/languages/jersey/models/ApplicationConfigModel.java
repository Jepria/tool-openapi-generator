package org.jepria.tools.openapi.generator.languages.jersey.models;

import io.swagger.v3.oas.models.OpenAPI;
import java.util.List;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.JaxrsAdapterModel;

public class ApplicationConfigModel {
  private String mainPackage;

  public void setAdapters(List<JaxrsAdapterModel> adapters) {
    this.adapters = adapters;
  }

  List<JaxrsAdapterModel> adapters;

  public void setMainPackage(String mainPackage) {
    this.mainPackage = mainPackage;
  }

  public static ApplicationConfigModel getFromSpec( OpenAPI spec, String mainPackage) {

    ApplicationConfigModel model = new ApplicationConfigModel();

    List<JaxrsAdapterModel> adapters = JaxrsAdapterModel.getFromSpec(spec);
    for (JaxrsAdapterModel adapter : adapters) {
      adapter.setApiPackage(mainPackage + "." + adapter.getClassName().toLowerCase() + ".rest");
    }

    model.setAdapters(adapters);
    model.setMainPackage(mainPackage);

    return model;
  }

}
