package org.jepria.tools.openapi.generator.utils;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;

public class SchemaUtils {
  public static String refToName(String ref) {
    String refName = ref.split("/")[ref.split("/").length - 1];
    switch (refName) {
      case "OptionDtoInteger": refName = "OptionDto<Integer>"; break;
      case "OptionDtoString":  refName = "OptionDto<String>";  break;
    }
    return refName;
  }

  public static String getSchemaType(Schema schema){
    String returnType = null;
    if (schema instanceof ArraySchema) {
      returnType = "List<" + refToName(((ArraySchema) schema).getItems().get$ref()) + ">";
//      returnType = returnType + refToName(((ArraySchema) schema).getItems().get$ref());
    } else {
      returnType = schema.getType();
    }
    //TODO: another instances
    return returnType;
  }
}
