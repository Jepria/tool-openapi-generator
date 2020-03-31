package org.jepria.tools.openapi.generator.languages.jersey.dtos;

public class PomDto extends BaseDtoImpl {

  private static final String TEMPLATE_FILE_NAME = "/mustache-templates/service-rest/pom.mustache";

  private String basePackage;
  private String applicationName;

  public PomDto() {
    this.setTemplate(TEMPLATE_FILE_NAME);
  }

  public void setBasePackage(String basePackage) {
    this.basePackage = basePackage;
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }


}
