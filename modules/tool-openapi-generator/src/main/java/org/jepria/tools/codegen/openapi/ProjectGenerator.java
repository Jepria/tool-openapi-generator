package org.jepria.tools.codegen.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jepria.tools.codegen.openapi.languages.jersey.JaxrsAdapterGenerator;

public class ProjectGenerator {

  public static void main(String[] args) throws IOException {

  }

  public static void createProject(OpenAPI openAPI) throws FileNotFoundException {

  }

  public List<String> createAdapters(OpenAPI openAPI) throws IOException {
    JaxrsAdapterGenerator jaxrsAdapterGen = new JaxrsAdapterGenerator(openAPI);
    jaxrsAdapterGen.create();
    return new ArrayList<>(jaxrsAdapterGen.getValues().values());
  }


}
