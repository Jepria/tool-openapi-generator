package org.jepria.tools.codegen.openapi;

import java.io.IOException;

public interface Generator {
  void create();
  void saveToFiles(String rootLocation) throws IOException;
}
