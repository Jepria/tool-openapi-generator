package org.jepria.tools.openapi.generator.languages.jersey.dtos.rest.operations;

import io.swagger.v3.oas.models.Operation;

public class OtherJaxrsOperation extends JaxrsOperation {

  boolean isPost, isPut, isGet, isDelete;

  public OtherJaxrsOperation(String nickname, String httpMethod, String path, Operation operation) {
    super(nickname, httpMethod, path, operation);
    setType(httpMethod);
  }

  void setType(String httpMethod) {
    switch (httpMethod) {
      case "GET":
        isGet = true;
        break;
      case "POST":
        isPost = true;
        break;
      case "PUT":
        isPut = true;
        break;
      case "DELETE":
        isDelete = true;
        break;
      default:
    }
  }

}
