package org.jepria.tools.openapi.generator.languages.jersey.models.entity.dao;

import java.util.ArrayList;
import java.util.List;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.operations.OtherJaxrsOperation;

public class DaoImplModel {

  private String apiPackage;
  private String className;
  private String modelPackage;

  private List<OtherJaxrsOperation> operations = new ArrayList<>();

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
