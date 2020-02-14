package org.jepria.tools.openapi.generator.languages.jersey.dtos.rest.operations;

import io.swagger.v3.oas.models.Operation;
import java.util.HashMap;

public class CrudJaxrsOperation extends JaxrsOperation {
  // TODO: need refactoring!
  boolean isCreate, isRead, isUpdate, isDelete;

  static HashMap<String, String> crudMap = new HashMap<>();

  static {
    crudMap.put("isCreate", "create");
    crudMap.put("isRead", "getRecordById");
    crudMap.put("isUpdate", "update");
    crudMap.put("isDelete", "deleteRecordById");
  }

  public CrudJaxrsOperation(String nickname, String httpMethod, String path, Operation oas3Operation) {
    super(nickname, httpMethod, path, oas3Operation);
    String operationId = crudMap.entrySet().stream().filter(opId -> oas3Operation.getOperationId().startsWith(opId.getValue() + "-")).findFirst().get().getValue();
    switch (operationId) {
      case "create":
        isCreate = true;
        break;
      case "getRecordById":
        isRead = true;
        break;
      case "update":
        isUpdate = true;
        break;
      case "deleteRecordById":
        isDelete = true;
        break;
      default:
    }
  }

  public static boolean isCrud(Operation oas3Operation) {
    //TODO: заменить на
    return crudMap.entrySet().stream().anyMatch(opId -> oas3Operation.getOperationId().startsWith(opId.getValue() + "-"));
  }
}
