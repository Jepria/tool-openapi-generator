package org.jepria.tools.openapi.generator.languages.jersey.models.entity;

import org.jepria.tools.openapi.generator.languages.jersey.models.BaseDtoImpl;

public class ServerFactoryModel extends BaseDtoImpl {

  private static final String TEMPLATE_FILE_NAME = "/mustache-templates/service-rest/src/main/java/entity/ServerFactory.mustache";

  private String apiPackage;
  private String className;

  public ServerFactoryModel() {
    this.setTemplate(TEMPLATE_FILE_NAME);
  }

  public String getApiPackage() {
    return apiPackage;
  }

  public void setApiPackage(String apiPackage) {
    this.apiPackage = apiPackage;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }
}
