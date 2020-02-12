package org.jepria.tools.openapi.generator.languages.jersey.dtos;

import java.util.ArrayList;
import java.util.List;
import org.jepria.tools.openapi.generator.languages.jersey.dtos.rest.operations.OtherJaxrsOperation;

public class ServiceDto extends BaseDtoImpl {

  private static final String TEMPLATE_FILE_NAME = "/mustache-templates/service-rest/src/main/java/entity/Service.mustache";

  private String apiPackage;
  private String className;

  private List<OtherJaxrsOperation> operations = new ArrayList<>();

  public ServiceDto() {
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
}
