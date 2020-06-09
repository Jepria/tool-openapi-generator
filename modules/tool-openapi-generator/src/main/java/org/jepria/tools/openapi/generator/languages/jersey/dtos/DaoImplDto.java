package org.jepria.tools.openapi.generator.languages.jersey.dtos;

import java.util.ArrayList;
import java.util.List;
import org.jepria.tools.openapi.generator.languages.jersey.dtos.rest.operations.OtherJaxrsOperation;

public class DaoImplDto extends BaseDtoImpl {
  private static final String TEMPLATE_FILE_NAME = "/mustache-templates/service-rest/src/main/java/entity/dao/DaoImpl.mustache";

  private String apiPackage;
  private String className;
  private String modelPackage;

  private List<OtherJaxrsOperation> operations = new ArrayList<>();

  public DaoImplDto() {
    setTemplate(TEMPLATE_FILE_NAME);
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

  public List<OtherJaxrsOperation> getOperations() {
    return operations;
  }

  public void setOperations(List<OtherJaxrsOperation> operations) {
    this.operations = operations;
  }

  public String getModelPackage() {
    return modelPackage;
  }

  public void setModelPackage(String modelPackage) {
    this.modelPackage = modelPackage;
  }
}
