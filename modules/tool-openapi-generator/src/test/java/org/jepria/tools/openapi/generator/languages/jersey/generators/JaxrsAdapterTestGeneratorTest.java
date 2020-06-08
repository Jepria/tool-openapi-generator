package org.jepria.tools.openapi.generator.languages.jersey.generators;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

class JaxrsAdapterTestGeneratorTest {

  @Test
  void createTest() throws IOException {
    String specLocation = new File(getClass().getClassLoader().getResource("spec/swagger.json").getPath()).getCanonicalPath();
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\temp\\JaxrsAdapterGeneratorTest\\";

    if (new File(outputFolder).exists()) {
      FileUtils.cleanDirectory(new File(outputFolder));
    }

    JaxrsAdapterTestGenerator generator = new JaxrsAdapterTestGenerator(specLocation);
    generator.create();
    generator.saveToFiles(outputFolder);
  }

  @Test
  void featureTest() throws IOException {
    String specLocation = new File(getClass().getClassLoader().getResource("spec/feature/swagger.json").getPath()).getCanonicalPath();
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\temp\\JaxrsAdapterGeneratorTestFeature\\";

    if (new File(outputFolder).exists()) {
      FileUtils.cleanDirectory(new File(outputFolder));
    }

    JaxrsAdapterTestGenerator generator = new JaxrsAdapterTestGenerator(specLocation);
    generator.create();
    generator.saveToFiles(outputFolder);

  }
}