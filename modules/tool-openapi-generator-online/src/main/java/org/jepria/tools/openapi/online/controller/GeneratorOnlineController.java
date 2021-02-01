package org.jepria.tools.openapi.online.controller;

import org.jepria.tools.openapi.online.dto.GenConfig;
import org.jepria.tools.openapi.online.dto.LangOptionsDto;
import org.jepria.tools.openapi.online.service.GeneratorOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class GeneratorOnlineController {

  private GeneratorOnlineService service;

  @Autowired
  public GeneratorOnlineController(GeneratorOnlineService service) {
    this.service = service;
  }

  @GetMapping("/{lang}")
  public ResponseEntity<LangOptionsDto> getOptions(@PathVariable String lang) {
    return new ResponseEntity<LangOptionsDto>(HttpStatus.OK);
  }
  @PostMapping("/gen")

  public ResponseEntity generate (@RequestBody GenConfig config) {
    Integer fileId = null;

    HttpHeaders headers = new HttpHeaders();
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fileId).toUri();
    headers.setLocation(location);

    return new ResponseEntity(headers, HttpStatus.CREATED);
  }

}
