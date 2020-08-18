package org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.operations;

import io.swagger.v3.oas.models.Operation;
import java.util.HashMap;

public class SearchJaxrsOperation extends JaxrsOperation {
  // TODO: need refactoring!
  boolean isPostSearch, isGetSearchRequest, isGetSearchResultsetSize, isGetResultset, isGetResultsetPaged;

  static HashMap<String, String> searchMap = new HashMap<>();

  static {
    searchMap.put("postSearch", "postSearch");
    searchMap.put("getSearchRequest", "getSearchRequest");
    searchMap.put("getSearchResultsetSize", "getSearchResultsetSize");
    searchMap.put("getResultset", "getResultset");
    searchMap.put("getResultsetPaged", "getResultsetPaged");
  }

  public SearchJaxrsOperation(String nickname, String httpMethod, String path, Operation oas3Operation) {
    super(nickname, httpMethod, path, oas3Operation);
//    String operationId = searchMap.entrySet().stream().filter(opId -> oas3Operation.getOperationId().equals(opId.getValue())).findFirst().get().getValue(); //BAD code??
    String operationId = nickname; // OR NOT???
    switch (operationId) {
      case "postSearch":
        isPostSearch = true;
        break;
      case "getSearchRequest":
        isGetSearchRequest = true;
        break;
      case "getSearchResultsetSize":
        isGetSearchResultsetSize = true;
        break;
      case "getResultset":
        isGetResultset = true;
        break;
      case "getResultsetPaged":
        isGetResultsetPaged = true;
        break;
      default:
    }
  }

  public static boolean isSearch(Operation oas3Operation) {
    return searchMap.entrySet().stream().anyMatch(opId ->
        null != oas3Operation.getOperationId() && oas3Operation.getOperationId().equals(opId.getValue())
    );
  }

}
