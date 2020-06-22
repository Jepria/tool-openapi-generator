package org.jepria.tools.openapi.generator.languages.jersey.generators;

import org.apache.commons.io.FileUtils;
import org.jepria.tools.openapi.generator.languages.jersey.generators.entity.rest.JaxrsAdapterGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;

class JaxrsAdapterGeneratorTest {

  @Test
  void createTest() throws Exception {
    String specLocation = new File(getClass().getClassLoader().getResource("spec/swagger.json").getPath()).getCanonicalPath();
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\temp\\JaxrsAdapterGenerator\\";

    if (new File(outputFolder).exists()) {
      FileUtils.cleanDirectory(new File(outputFolder));
    }

    JaxrsAdapterGenerator generator = new JaxrsAdapterGenerator(specLocation);
    generator.create();
    generator.saveToFiles(outputFolder);
  }
}