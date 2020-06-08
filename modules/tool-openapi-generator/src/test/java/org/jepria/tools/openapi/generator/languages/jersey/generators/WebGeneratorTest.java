package org.jepria.tools.openapi.generator.languages.jersey.generators;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

class WebGeneratorTest {

  @Test
  void createTest() throws IOException {
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\temp\\WebGeneratorTest\\";

    if (new File(outputFolder).exists()) {
      FileUtils.cleanDirectory(new File(outputFolder));
    }

    WebGenerator generator = new WebGenerator();
    generator.setMainPackage("org.jepria.rfi");
    generator.create();
    generator.saveToFiles(outputFolder);
  }
}