package org.jepria.tools.openapi.online.controller;

import org.jepria.tools.openapi.online.service.GeneratorOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GeneratorOnlineController {

  private GeneratorOnlineService service;

  @Autowired
  public GeneratorOnlineController(GeneratorOnlineService service) {
    this.service = service;
  }


}
