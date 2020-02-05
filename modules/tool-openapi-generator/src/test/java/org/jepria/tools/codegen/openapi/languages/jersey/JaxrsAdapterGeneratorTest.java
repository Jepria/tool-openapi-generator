package org.jepria.tools.codegen.openapi.languages.jersey;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class JaxrsAdapterGeneratorTest {

  String specShowcase = "d:\\work\\jepria-showcase\\module\\JepRiaShowcase\\App\\service-rest\\src\\api-spec\\feature\\swagger.json";
  String specBroker   = "d:\\work\\tool-openapi-generator\\modules\\tool-openapi-generator\\src\\main\\resources\\spec\\broker\\swagger.json";
  String specTest     = "d:\\work\\tool-openapi-generator\\modules\\tool-openapi-generator\\src\\main\\resources\\spec\\swagger.json";

  @Test
  void createTest() throws IOException {
    String specLocation = specShowcase;
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\temp\\JaxrsAdapterGenerator\\";

    JaxrsAdapterGenerator generator = new JaxrsAdapterGenerator(specLocation);
    generator.create();
    generator.saveToFiles(outputFolder);
  }
}