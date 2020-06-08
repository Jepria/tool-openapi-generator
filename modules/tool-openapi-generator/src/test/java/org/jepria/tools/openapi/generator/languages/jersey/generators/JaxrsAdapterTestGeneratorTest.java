package org.jepria.tools.openapi.generator.languages.jersey.generators;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class JaxrsAdapterTestGeneratorTest {

  @Test
  void createTest() throws IOException {
    String specShowcase = "d:\\work\\jepria-showcase\\module\\JepRiaShowcase\\App\\service-rest\\src\\api-spec\\feature\\swagger.json";
    String specBroker = "d:\\work\\tool-openapi-generator\\modules\\tool-openapi-generator\\src\\main\\resources\\spec\\broker\\swagger.json";
    String specTest = "d:\\work\\tool-openapi-generator\\modules\\tool-openapi-generator\\src\\main\\resources\\spec\\swagger.json";
    String specLocation = specBroker;

    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\temp\\JaxrsAdapterGeneratorTest\\";

    JaxrsAdapterTestGenerator generator = new JaxrsAdapterTestGenerator(specLocation);
    generator.create();
    generator.saveToFiles(outputFolder);
  }

  @Test
  void featureTest() throws IOException {
    String specLocation = new File(getClass().getClassLoader().getResource("spec/feature/swagger.json").getPath()).getCanonicalPath();
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\temp\\JaxrsAdapterGeneratorTest\\";

    JaxrsAdapterTestGenerator generator = new JaxrsAdapterTestGenerator(specLocation);
    generator.create();
    generator.saveToFiles(outputFolder);

  }
}