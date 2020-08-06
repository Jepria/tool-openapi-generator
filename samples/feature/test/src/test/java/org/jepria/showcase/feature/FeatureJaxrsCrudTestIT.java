package org.jepria.showcase.feature;

import org.jepria.showcase.feature.dto.*;

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


public class FeatureJaxrsAdapterCrudTestIT {

  private static final String ENTITY_URL = "http://localhost:8080/";
  private static final String OPERATOR_NAME = "name";
  private static final String OPERATOR_PASSWORD = "password";


  @Test
  public void recordCrudTest() {
    FeatureDto dto = createRandomMinimalFeatureDto();
    String location = createFeature(dto);
    FeatureDto createdDto = setMinimalFeatureDto(getFeature(location));
    Assertions.assertTrue(equalsEntities(dto, createdDto), "New dto and created dto are different");

    FeatureDto newDto = createRandomMinimalFeatureDto();
    updateFeature(newDto, location);
    FeatureDto updatedDto = setMinimalFeatureDto(getFeature(location));
    Assertions.assertTrue(equalsEntities(newDto, updatedDto), "New dto for updating and updated dto are different");

    deleteFeature(location);
  }

  private String createFeature(FeatureDto testDto) {
    Response postResponse = RestAssured.given()
            .auth()
            .basic(OPERATOR_NAME, OPERATOR_PASSWORD)
            .header("Content-Type", "application/json")
            .body(testDto)
            .post(ENTITY_URL);
    Assertions.assertEquals(201, postResponse.statusCode());
    return postResponse.getHeader("Location");
  }

  private FeatureDto getFeature(String location) {
    Response response = RestAssured.given()
            .auth()
            .basic(OPERATOR_NAME, OPERATOR_PASSWORD)
            .when()
            .get(location);
    Assertions.assertEquals(200, response.statusCode());
    Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
    return gson.fromJson(response.asString(), FeatureDto.class);
  }

  private void updateFeature(FeatureDto testDto, String location) {
    Response putResponse = RestAssured.given()
            .auth()
            .basic(OPERATOR_NAME, OPERATOR_PASSWORD)
            .header("Content-Type", "application/json")
            .body(testDto)
            .put(location);
    int statusCode = putResponse.statusCode();
    Assertions.assertEquals(200, statusCode);
  }

  private void deleteFeature(String location) {
    RequestSpecification requestDelete = RestAssured.given()
            .auth()
            .basic(OPERATOR_NAME, OPERATOR_PASSWORD);                
    Response deleteResponse = requestDelete.delete(location);

    Assertions.assertEquals(200, deleteResponse.statusCode()); //TODO: maybe should be 204
  }

  private FeatureDto createRandomMinimalFeatureDto() {
    FeatureDto dto = new FeatureDto();
    //TODO

    return dto;
  }

  private FeatureDto setMinimalFeatureDto(FeatureDto dto) {
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
