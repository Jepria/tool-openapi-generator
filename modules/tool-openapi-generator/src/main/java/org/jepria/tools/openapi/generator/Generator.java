package org.jepria.tools.openapi.generator;

import java.io.IOException;

public interface Generator {
  void create();
  void saveToFiles(String rootLocation) throws IOException;
}
