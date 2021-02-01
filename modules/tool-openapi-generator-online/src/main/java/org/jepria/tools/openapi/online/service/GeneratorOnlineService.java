package org.jepria.tools.openapi.online.service;

import org.jepria.tools.openapi.generator.languages.jersey.ApplicationStructureCreator;
import org.jepria.tools.openapi.online.dto.GenConfig;
import org.jepria.tools.openapi.online.dto.LangOptionsDto;
import org.springframework.stereotype.Service;

@Service
public class GeneratorOnlineService {


  public void generateProject(GenConfig config) throws Exception {
    switch (config.getLang()) {
      case "JEPRIA_JAVA":
        ApplicationStructureCreator creator = new ApplicationStructureCreator("/");
        creator.setBasePackage(mainPackage);
        creator.create(specContent);
        break;
      default:
        throw new Exception("Unknown language");
    }
  }

  public LangOptionsDto getLangOptions(String lang) {
    return new LangOptionsDto();
  }
}
