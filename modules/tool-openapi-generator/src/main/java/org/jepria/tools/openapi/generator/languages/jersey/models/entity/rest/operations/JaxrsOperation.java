package org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.operations;

import static org.jepria.tools.openapi.generator.utils.SchemaUtils.getSchemaType;
import static org.jepria.tools.openapi.generator.utils.StringUtils.sanitizeName;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.jepria.tools.openapi.generator.utils.SchemaUtils;

public class JaxrsOperation {

  String operation;
  String httpMethod;
  String nickname;
  String path;
  String returnType;

  SubResourceOperation subresourceOperation = null;

  private List<Parameter> allParams = new ArrayList<>();

  private class SubResourceOperation {

    private String path;

    SubResourceOperation(String path) {
      this.path = path;
    }
  }

  private class Parameter {

    String name;
    String type;

    private List<Parameter.Annotation> allAnnotations = new ArrayList<>();

    private Parameter(String name, String type, String annotation, String annotationVal) {
      name      = sanitizeName(name);
      this.name = name;
      this.setType(type);
      this.allAnnotations.add(new Parameter.Annotation(annotation, annotationVal));
    }

    private Parameter(String name, String type) {
      name      = sanitizeName(name);
      this.name = name;
      this.setType(type);
    }

    private void setType(String type) {
      switch (type) {
        case "integer":
          this.type = "Integer";
          break;
        case "string":
          this.type = "String";
          break;
        default:
          this.type = type;
      }
    }

    private class Annotation {

      String annotation;
      String annotationVal;

      public Annotation(String annotation, String annotationVal) {
        this.annotation    = annotation;
        this.annotationVal = annotationVal;
      }

    }
  }

  public JaxrsOperation(String nickname, String httpMethod, String path,
      Operation operation
  ) {
    this.nickname   = nickname;
    this.httpMethod = httpMethod;
    this.operation  = httpMethod;
    this.path       = path;

    if ("GET".equals(httpMethod)) {
      this.returnType = getReturnType(operation);
    }

    if (null != path) {
      subresourceOperation = new SubResourceOperation(path);
    }

    if (null != operation.getParameters()) {
      for (io.swagger.v3.oas.models.parameters.Parameter parameter : operation
          .getParameters()) {
        this.allParams.add(new Parameter(parameter.getName(), getSchemaType(parameter.getSchema()), inToJerseyAnnotation(parameter.getIn()), parameter.getName()));
      }
    }
    Parameter parameter = this.getBodyParams(operation);
    if (null != parameter) {
      this.allParams.add(parameter);
    }
  }

  static String inToJerseyAnnotation(String in) {
    switch (in) {
      case "path":
        in = "PathParam";
        break;
      case "query":
        in = "QueryParam";
        break;
      case "header":
        in = "HeaderParam";
        break;
      default:
        break;
    }
    return in;
  }

  private Parameter getBodyParams(Operation operation) {

    Parameter parameter = null;

    if (null != operation.getRequestBody()) {
      if (null != operation.getRequestBody().getContent()) {

        for (MediaType type : operation.getRequestBody().getContent().values()) {
          String paramName = SchemaUtils.refToName(type.getSchema().get$ref());//paramNameFromRef(type.getSchema().get$ref());
          if (null != paramName) {
            parameter = new Parameter(paramName, paramName);
          }
        }
      }
    }

    return parameter;
  }

  private String paramNameFromRef(String ref) {
    String[] arr = ref.split("/");
    return arr[arr.length - 1];
  }

  private String getReturnType(Operation operation) {
    String returnType = null;

    if (null != operation.getResponses().get("200") &&
        null != operation.getResponses().get("200").getContent()) {
      Schema schema = operation.getResponses().get("200").getContent().values().iterator().next().getSchema();
      returnType = getSchemaType(schema);
    }

    return returnType;
  }


}
