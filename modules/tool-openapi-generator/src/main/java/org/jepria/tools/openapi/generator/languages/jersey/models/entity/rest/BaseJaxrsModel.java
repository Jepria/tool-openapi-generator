package org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import org.jepria.tools.openapi.generator.languages.jersey.models.BaseDtoImpl;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.operations.CrudJaxrsOperation;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.operations.OtherJaxrsOperation;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.operations.SearchJaxrsOperation;
import org.jepria.tools.openapi.generator.utils.StringUtils;

public class BaseJaxrsModel extends BaseDtoImpl {

  private String  apiPackage;
  private String  modelPackage;
  private String  mainPackage;
  private String  className;
  private String  rootPath;
  private boolean hasCrud   = false;
  private boolean hasSearch = false;

  private List<OtherJaxrsOperation>  operations       = new ArrayList<>();
  private List<CrudJaxrsOperation>   crudOperations   = new ArrayList<>();
  private List<SearchJaxrsOperation> searchOperations = new ArrayList<>();

  public BaseJaxrsModel(String rootPath) {
    this.rootPath  = rootPath;
    this.className = rootPath;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getClassName() {
    return className;
  }

  public void setApiPackage(String apiPackage) {
    this.apiPackage = apiPackage;
  }

  public String getApiPackage() {
    return apiPackage;
  }

  public String getRootPath() {
    return rootPath;
  }

  public void setModelPackage(String modelPackage) {
    this.modelPackage = modelPackage;
  }

  public String getModelPackage() {
    return modelPackage;
  }

  private static String getRootPath(String pathName) {
    String rootPath;
    rootPath = '/' + pathName.split("/")[1];
    return rootPath;
  }

  public static List<BaseJaxrsModel> getFromSpec(OpenAPI spec) {
    return getListFromPath(spec.getPaths());
  }

  private static List<BaseJaxrsModel> getListFromPath(Paths paths) {
    List<BaseJaxrsModel> resultDtosList = new ArrayList<>();

    Set<String> rootPaths = paths.entrySet().stream().map(p -> getRootPath(p.getKey())).collect(Collectors.toSet());
    for (String path : rootPaths) {
      Map<String, PathItem> children = paths.entrySet().stream().filter(
          (p) -> p.getKey().startsWith(path))
          .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

      resultDtosList.addAll(getAdapters(path, getApiName(path), children));
    }

    return resultDtosList;
  }

  private static List<BaseJaxrsModel> getAdapters(String rootPath, String rootApiName, Map<String, PathItem> paths) {
    List<BaseJaxrsModel> resultDtosList = new ArrayList<>();

    String childRootPath = rootPath + "/{" + rootApiName + "Id}/" + rootApiName + "-";

    Map<String, PathItem> current = paths.entrySet().stream().filter(
        (p) -> !p.getKey().startsWith(childRootPath))
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

    BaseJaxrsModel dto = pathToDto(rootPath, current);
    dto.setApiPackage("." + StringUtils.camelize(StringUtils.sanitizeName(rootPath), true));
    dto.setClassName(StringUtils.camelize(StringUtils.sanitizeName(rootApiName), false));
    resultDtosList.add(dto);

    Set<String> childNames = paths.keySet().stream().filter(
        pathItem -> pathItem.startsWith(childRootPath))
        .map(BaseJaxrsModel::getChildRootPath).collect(Collectors.toSet());

    for (String name : childNames) {
      Map<String, PathItem> children = paths.entrySet().stream().filter(
          (p) -> p.getKey().startsWith(name + "/") || p.getKey().equals(name))
          .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
      String apiName = getChildApiName(name, "/" + rootApiName + "/{" + rootApiName + "Id}");
      resultDtosList.addAll(getAdapters(name, apiName, children));
    }

    return resultDtosList;
  }

  private static BaseJaxrsModel pathToDto(String rootPath, Map<String, PathItem> paths) {
    BaseJaxrsModel dto = new BaseJaxrsModel(rootPath);//new T(rootPath);
    for (Entry<String, PathItem> pathname : paths.entrySet()) {
      if (null != pathname.getValue().getGet()) {
        dto.addOperation("GET", pathname.getKey(), pathname.getValue().getGet());
      }
      if (null != pathname.getValue().getPut()) {
        dto.addOperation("PUT", pathname.getKey(), pathname.getValue().getPut());
      }
      if (null != pathname.getValue().getPost()) {
        dto.addOperation("POST", pathname.getKey(), pathname.getValue().getPost());
      }
      if (null != pathname.getValue().getDelete()) {
        dto.addOperation("DELETE", pathname.getKey(), pathname.getValue().getDelete());
      }
    }

    return dto;
  }

  private static String getApiName(String pathName) {
    String apiName;
    apiName = pathName.split("/")[1];
    return apiName;
  }

  private static String getChildApiName(String pathName, String rootPath) {
    String apiName;
    apiName = pathName.replace(rootPath, "");
    apiName = apiName.split("/")[1];
    return apiName;
  }

  private static String getChildRootPath(String pathName) {
    String rootPath;
    rootPath = '/' + pathName.split("/")[1] + '/' + pathName.split("/")[2] + '/' + pathName.split("/")[3];
    return rootPath;
  }

  private void addOperation(String httpMethod, String path, Operation operation) {
    String operationName = operation.getOperationId();

    if (null == operationName) {
      System.out.println("Can't set operationName for operation: " + httpMethod + " " + path);
    }

    if (null != path) {
      path = path.replaceFirst("\\Q" + this.rootPath + "\\E", "");
    }
    operationName = StringUtils.sanitizeName(operationName);

    if (CrudJaxrsOperation.isCrud(operation)) {
      this.crudOperations.add(new CrudJaxrsOperation(operationName, httpMethod, path, operation));
      if (4 == this.crudOperations.size()) { // crud - 4 operations.
        hasCrud = true;
      }
    } else if (SearchJaxrsOperation.isSearch(operation)) {
      this.searchOperations.add(new SearchJaxrsOperation(operationName, httpMethod, path, operation));
      hasSearch = true;
    } else {
      this.operations.add(new OtherJaxrsOperation(operationName, httpMethod, path, operation));
    }
  }

  public void setMainPackage(String mainPackage) {
    this.mainPackage = mainPackage;
  }


  public List<OtherJaxrsOperation> getOperations() {
    return operations;
  }

}
