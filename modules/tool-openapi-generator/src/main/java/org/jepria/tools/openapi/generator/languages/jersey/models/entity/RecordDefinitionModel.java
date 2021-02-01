package org.jepria.tools.openapi.generator.languages.jersey.models.entity;

public class RecordDefinitionModel {

  private static final String TEMPLATE_FILE_NAME = "/mustache-templates/service-rest/src/main/java/entity/RecordDefinition.mustache";

  private String apiPackage;
  private String entityName;

  public String getApiPackage() {
    return apiPackage;
  }

  public void setApiPackage(String apiPackage) {
    this.apiPackage = apiPackage;
  }

  public String getEntityName() {
    return entityName;
  }

  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }
}
