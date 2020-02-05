package org.jepria.tools.codegen;

import static org.jepria.tools.codegen.JepriaOpenAPIGenerator.GEN_OPT;
import static org.jepria.tools.codegen.JepriaOpenAPIGenerator.GEN_REST;
import static org.jepria.tools.codegen.JepriaOpenAPIGenerator.GEN_TESTS;
import static org.jepria.tools.codegen.JepriaOpenAPIGenerator.OUTPUT_OPT;
import static org.jepria.tools.codegen.JepriaOpenAPIGenerator.SPEC_OPT;
import static org.jepria.tools.codegen.JepriaOpenAPIGenerator.generate;

import java.io.File;
import java.io.IOException;
import org.apache.commons.cli.ParseException;
import org.junit.Test;

public class JepriaOpenAPIGeneratorTest {


  @Test
  public void generateTest() throws ParseException, IOException {

    String specLocation = new File(getClass().getClassLoader().getResource("feature/swagger.json").getPath()).getCanonicalPath();

    String[] argv = {"-" + SPEC_OPT, specLocation, "-" + OUTPUT_OPT, "out/", "-" + GEN_OPT, GEN_TESTS};

    generate(argv);

  }

  @Test
  public void generateRestTest() throws ParseException, IOException {

    String specLocation = new File(getClass().getClassLoader().getResource("feature/swagger.json").getPath()).getCanonicalPath();

    String[] argv = {"-" + SPEC_OPT, specLocation, "-" + OUTPUT_OPT, "out/", "-" + GEN_OPT, GEN_REST};

    generate(argv);

  }

  @Test
  public void test() throws IOException, ParseException {
    String[] argv = {""};

    generate(argv);
  }

}