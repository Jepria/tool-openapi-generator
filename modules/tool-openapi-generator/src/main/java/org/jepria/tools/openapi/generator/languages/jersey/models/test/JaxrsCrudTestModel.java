package org.jepria.tools.openapi.generator.languages.jersey.models.test;

public class JaxrsCrudTestModel {

  private static final String TEMPLATE_FILE_NAME = "/mustache-templates/service-rest/src/test/java/JaxrsAdapterCrudTest.mustache";

  private String apiPackage;
  private String modelPackage;
  private String entityName;

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

  public String getEntityName() {
    return entityName;
  }

  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }
}
