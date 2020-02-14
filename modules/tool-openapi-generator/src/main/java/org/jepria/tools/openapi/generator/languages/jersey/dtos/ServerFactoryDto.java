package org.jepria.tools.openapi.generator.languages.jersey.dtos;

public class ServerFactoryDto extends BaseDtoImpl {

  private static final String TEMPLATE_FILE_NAME = "/mustache-templates/service-rest/src/main/java/entity/ServerFactory.mustache";

  private String apiPackage;
  private String className;

  public ServerFactoryDto() {
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
