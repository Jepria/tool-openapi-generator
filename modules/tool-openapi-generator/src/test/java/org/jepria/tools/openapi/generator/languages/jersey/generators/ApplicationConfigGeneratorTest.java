package org.jepria.tools.openapi.generator.languages.jersey.generators;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

class ApplicationConfigGeneratorTest {

  @Test
  void createTest() throws IOException {
    String specLocation = new File(getClass().getClassLoader().getResource("spec/swagger.json").getPath()).getCanonicalPath();
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\temp\\ApplicationConfigGeneratorTest\\";

    if (new File(outputFolder).exists()) {
      FileUtils.cleanDirectory(new File(outputFolder));
    }

    ApplicationConfigGenerator generator = new ApplicationConfigGenerator(specLocation);
    generator.setMainPackage("org.jepria.rfi");
    generator.create();
    generator.saveToFiles(outputFolder);
  }
}