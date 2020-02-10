package org.jepria.tools.openapi.generator.languages.jersey;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jepria.tools.openapi.generator.languages.jersey.generators.DtoGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.generators.JaxrsAdapterGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.generators.JaxrsAdapterTestGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.generators.WebGenerator;

class ApplicationStructureCreator {

  private String apiSpecFolder = "api-spec";
  private String outputFolderName;

  List<String> folders = new ArrayList<>();


  public ApplicationStructureCreator(String outputFolderName) {
    this.outputFolderName = outputFolderName;
    folders.add(outputFolderName);
    folders.add(outputFolderName + "/" + apiSpecFolder);
  }

  public boolean create(OpenAPI openAPI) throws IOException {
    if (null == openAPI) {
      return false;
    }

    File file = new File(outputFolderName);

    Boolean check = file.mkdir();
    createAdapters(openAPI, this.outputFolderName + "rest\\");
    createAdapterTests(openAPI, this.outputFolderName + "test\\");
    createDtos(openAPI, this.outputFolderName + "dto\\");

    return false;
  }

  public void create(String specFileLocation) throws IOException {
    OpenAPI openAPI = new OpenAPIV3Parser().read(specFileLocation);
    create(openAPI);
  }

  private void createAdapters(OpenAPI spec, String outputFolder) throws IOException {
    JaxrsAdapterGenerator generator = new JaxrsAdapterGenerator(spec);
//    generator.setMainPackage();
    generator.getValues();
    generator.create();

    generator.saveToFiles(outputFolder);
  }

  private void createAdapterTests(OpenAPI spec, String outputFolder) throws IOException {
    JaxrsAdapterTestGenerator generator = new JaxrsAdapterTestGenerator(spec);
    generator.create();
    generator.saveToFiles(outputFolder);
  }

  private void createDtos(OpenAPI spec, String outputFolder) throws IOException {
    DtoGenerator generator = new DtoGenerator(spec);
    generator.create();
    generator.saveToFiles(outputFolder);
  }

  private void createWeb(String outputFolder) throws IOException {
    WebGenerator generator = new WebGenerator();
    generator.setMainPackage("org.jepria.rfi");
    generator.create();
    generator.saveToFiles(outputFolder);
  }


}
