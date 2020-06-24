package org.jepria.tools.openapi.generator.languages.jersey.models.test;

public class JaxrsCrudTestModel {

  private static final String TEMPLATE_FILE_NAME = "/mustache-templates/service-rest/src/test/java/JaxrsAdapterCrudTest.mustache";

  private String apiPackage;
  private String modelPackage;
  private String className;

  public String getApiPackage() {
    return apiPackage;
  }

  public void setApiPackage(String apiPackage) {
    this.apiPackage = apiPackage;
  }

  public String getModelPackage() {
    return modelPackage;
  }

  public void setModelPackage(String modelPackage) {
    this.modelPackage = modelPackage;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }
}
