package org.jepria.tools.openapi.generator.languages.jersey.models.entity;

import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.operations.OtherJaxrsOperation;

import java.util.ArrayList;
import java.util.List;

public class ServiceImplModel {

  private String apiPackage;
  private String entityName;
  private String modelPackage;

  private List<OtherJaxrsOperation> operations = new ArrayList<>();

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
