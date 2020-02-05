package org.jepria.tools.codegen.openapi.languages.jersey;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class DtoGeneratorTest {

  @Test
  void createTest() throws IOException {
    String specShowcase = "d:\\work\\jepria-showcase\\module\\JepRiaShowcase\\App\\service-rest\\src\\api-spec\\feature\\swagger.json";
    String specBroker   = "d:\\work\\tool-openapi-generator\\modules\\tool-openapi-generator\\src\\main\\resources\\spec\\broker\\swagger.json";
    String specTest     = "d:\\work\\tool-openapi-generator\\modules\\tool-openapi-generator\\src\\main\\resources\\spec\\swagger.json";
    String specLocation = specTest;

    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\temp\\DtoGeneratorTest\\";

    DtoGenerator generator = new DtoGenerator(specLocation);
    generator.create();
    generator.saveToFiles(outputFolder);

  }

}