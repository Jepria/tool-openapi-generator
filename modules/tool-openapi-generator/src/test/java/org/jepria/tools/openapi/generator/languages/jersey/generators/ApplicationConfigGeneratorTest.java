package org.jepria.tools.openapi.generator.languages.jersey.generators;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class ApplicationConfigGeneratorTest {
  String specShowcase = "d:\\work\\jepria-showcase\\module\\JepRiaShowcase\\App\\service-rest\\src\\api-spec\\feature\\swagger.json";
  String specBroker   = "d:\\work\\tool-openapi-generator\\modules\\tool-openapi-generator\\src\\main\\resources\\spec\\broker\\swagger.json";
  String specTest     = "d:\\work\\tool-openapi-generator\\modules\\tool-openapi-generator\\src\\main\\resources\\spec\\swagger.json";

  @Test
  void createTest() throws IOException {
    String specLocation = specShowcase;
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\temp\\ApplicationConfigGeneratorTest\\";

    ApplicationConfigGenerator generator = new ApplicationConfigGenerator(specLocation);
    generator.setMainPackage("org.jepria.rfi");
    generator.create();
    generator.saveToFiles(outputFolder);
  }
}