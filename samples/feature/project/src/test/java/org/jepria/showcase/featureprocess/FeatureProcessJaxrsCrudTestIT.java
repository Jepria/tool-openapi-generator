package org.jepria.showcase.featureprocess;

import org.jepria.showcase.featureprocess.dto.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;


public class FeatureProcessJaxrsAdapterCrudTestIT {

  private static final String ENTITY_URL = "http://localhost:8080/";
  private static final String OPERATOR_NAME = "name";
  private static final String OPERATOR_PASSWORD = "password";


  @Test
  public void recordCrudTest() {
    FeatureProcessDto dto = createRandomMinimalFeatureProcessDto();
    String location = createFeatureProcess(dto);
    FeatureProcessDto createdDto = setMinimalFeatureProcessDto(getFeatureProcess(location));
    Assertions.assertTrue(equalsEntities(dto, createdDto), "New dto and created dto are different");

    FeatureProcessDto newDto = createRandomMinimalFeatureProcessDto();
    updateFeatureProcess(newDto, location);
    FeatureProcessDto updatedDto = setMinimalFeatureProcessDto(getFeatureProcess(location));
    Assertions.assertTrue(equalsEntities(newDto, updatedDto), "New dto for updating and updated dto are different");

    deleteFeatureProcess(location);
  }

  private String createFeatureProcess(FeatureProcessDto testDto) {
    Response postResponse = RestAssured.given()
            .auth()
            .basic(OPERATOR_NAME, OPERATOR_PASSWORD)
            .header("Content-Type", "application/json")
            .body(testDto)
            .post(ENTITY_URL);
    Assertions.assertEquals(201, postResponse.statusCode());
    return postResponse.getHeader("Location");
  }

  private FeatureProcessDto getFeatureProcess(String location) {
    Response response = RestAssured.given()
            .auth()
            .basic(OPERATOR_NAME, OPERATOR_PASSWORD)
            .when()
            .get(location);
    Assertions.assertEquals(200, response.statusCode());
    Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
    return gson.fromJson(response.asString(), FeatureProcessDto.class);
  }

  private void updateFeatureProcess(FeatureProcessDto testDto, String location) {
    Response putResponse = RestAssured.given()
            .auth()
            .basic(OPERATOR_NAME, OPERATOR_PASSWORD)
            .header("Content-Type", "application/json")
            .body(testDto)
            .put(location);
    int statusCode = putResponse.statusCode();
    Assertions.assertEquals(200, statusCode);
  }

  private void deleteFeatureProcess(String location) {
    RequestSpecification requestDelete = RestAssured.given()
            .auth()
            .basic(OPERATOR_NAME, OPERATOR_PASSWORD);                
    Response deleteResponse = requestDelete.delete(location);

    Assertions.assertEquals(200, deleteResponse.statusCode()); //TODO: maybe should be 204
  }

  private FeatureProcessDto createRandomMinimalFeatureProcessDto() {
    FeatureProcessDto dto = new FeatureProcessDto();
    //TODO

    return dto;
  }

  private FeatureProcessDto setMinimalFeatureProcessDto(FeatureProcessDto dto) {
    //TODO

    return dto;
  }

  private boolean equalsEntities(Object obj1, Object obj2) {
    if (obj1 == obj2) {
      return true;
    }
    
    //TODO

    return false;
  }

}
