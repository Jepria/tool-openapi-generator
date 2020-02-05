package com.technology.jep.jepriashowcase.feature;
import com.technology.jep.jepriashowcase.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.technology.jep.jepriashowcase.feature.dto.FeatureDto;
import com.technology.jep.jepriashowcase.feature.dto.FeatureSearchDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import javax.ws.rs.core.MediaType;
import org.jepria.server.data.SearchRequestDto;
import org.junit.Assert;
import org.junit.Test;

public class FeatureJaxrsAdapterTest {

  private static final String baseUrl          = ""; // TODO
  private static final String operatorName     = ""; // TODO
  private static final String operatorPassword = ""; // TODO

  @Test
  public void setFeatureResponsible() {
    String testUrl = baseUrl + "/{featureId}/set-feature-responsible";

    RestAssured.given()
        .auth()
      .basic(operatorName, operatorPassword)
      .when()
      .get(testUrl)
      .then()
      .assertThat()
      .statusCode(200);
 }

  @Test
  public void getFeatureOperator() {
    String testUrl = baseUrl + "/option/feature-operator";

    RestAssured.given()
        .auth()
      .basic(operatorName, operatorPassword)
      .when()
      .get(testUrl)
      .then()
      .assertThat()
      .statusCode(200);
 }

  @Test
  public void getFeatureStatus() {
    String testUrl = baseUrl + "/option/feature-status";

    RestAssured.given()
        .auth()
      .basic(operatorName, operatorPassword)
      .when()
      .get(testUrl)
      .then()
      .assertThat()
      .statusCode(200);
 }

/*************************************************************/
  @Test
  public void getRecordByIdTest() {
    Integer recordId = 1; //TODO: set record ID for test

    String   testUrl  = baseUrl + "/" + recordId;
    Response response = RestAssured.given().auth().basic(operatorName, operatorPassword).when().get(testUrl);
    Assert.assertEquals(testUrl, 200, response.statusCode());
    //TODO: check response DTO
  }
  @Test
  public void updateRecordTest() {
    Integer recordId = 0; //TODO: set record ID for test

    String testUrl = baseUrl + "/" + recordId;

    FeatureDto testDto = new FeatureDto();
    //TODO: set DTO fields

    RequestSpecification requestPost =
        RestAssured.given().auth().basic(operatorName, operatorPassword)
            .header("Content-Type", "application/json") 
            .body(testDto);

    Response postResponse = requestPost.post(testUrl);

    int statusCode = postResponse.statusCode();
    Assert.assertEquals(200, statusCode);

    //TODO: check response DTO
  }
  @Test
  public void deleteRecordTest() {
    Integer recordId = 0; //TODO: set record ID for test

    String testUrl = baseUrl + "/" + recordId;

    RequestSpecification requestDelete =
        RestAssured.given().auth().basic(operatorName, operatorPassword);

    Response deleteResponse = requestDelete.delete(testUrl);

    int statusCode = deleteResponse.statusCode();
    Assert.assertEquals(200, statusCode); //TODO: maybe should be 204
  }
  @Test
  public void createRecordTest() {
    String testUrl = baseUrl;

    FeatureDto testDto = new FeatureDto();
    //TODO: set DTO fields

    RequestSpecification requestPost =
        RestAssured.given().auth().basic(operatorName, operatorPassword)
            .header("Content-Type", "application/json")
            .body(testDto);

    Response postResponse = requestPost.post(testUrl);

    int statusCode = postResponse.statusCode();
    Assert.assertEquals(201, statusCode);
  }

/**********************************************/
  // CRUD complex test
  private String createRecord(FeatureDto testDto, String location) {
    RequestSpecification requestPost =
        RestAssured.given().auth().basic(operatorName, operatorPassword)
            .header("Content-Type", "application/json")
            .body(testDto);

    Response postResponse = requestPost.post(location);

    int statusCode = postResponse.statusCode();
    Assert.assertEquals(201, statusCode);
    return postResponse.getHeader("Location");
  }

  private void updateRecord(FeatureDto testDto, Integer recordId) {
    updateRecord(testDto, baseUrl + "/" + recordId);
  }

  private void updateRecord(FeatureDto testDto, String location) {
    RequestSpecification requestPost =
        RestAssured.given().auth().basic(operatorName, operatorPassword)
            .header("Content-Type", "application/json")
            .body(testDto);
    Response postResponse = requestPost.put(location);

    int statusCode = postResponse.statusCode();
    Assert.assertEquals(200, statusCode);
  }

  private FeatureDto getRecord(String location) {
    Response response = RestAssured.given().auth().basic(operatorName, operatorPassword).when().get(location);
    Assert.assertEquals(location, 200, response.statusCode());
    Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
    return gson.fromJson(response.asString(), FeatureDto.class);
  }

  private FeatureDto getRecord(Integer recordId) {
    String testUrl = baseUrl + "/" + recordId;
    return getRecord(testUrl);
  }

  private void deleteRecord(int recordId) {
    deleteRecord(baseUrl + "/" + recordId);
  }

  private void deleteRecord(String location) {
    RequestSpecification requestDelete =
        RestAssured.given().auth().basic(operatorName, operatorPassword);

    Response deleteResponse = requestDelete.delete(location);

    int statusCode = deleteResponse.statusCode();
    Assert.assertEquals(200, statusCode); //TODO: maybe should be 204
  }

  @Test
  public void FeatureCrudTest() {
    String testUrl = baseUrl;

    FeatureDto dto = new FeatureDto();
    //TODO: set DTO fields
    dto.setFeatureName("name");
    dto.setFeatureNameEn("name");
    dto.setDescription("TestDescription");

    String location = createRecord(dto, testUrl);

    FeatureDto createdDto = getRecord(location);
    //TODO: compare dto and createdDto

    FeatureDto newDto = new FeatureDto();
    newDto.setFeatureName("newName");
    newDto.setFeatureNameEn("newName");

    updateRecord(newDto, location);

    FeatureDto updatedDto = getRecord(location);
    //TODO: compare newDto and updatedDto

    deleteRecord(location);
  }
  // test search API
  private String postSearchRequest(SearchRequestDto<FeatureSearchDto> searchRequestDto, String location) {

    RequestSpecification request =
        RestAssured.given().auth().basic(operatorName, operatorPassword)
            .header("content-type", MediaType.APPLICATION_JSON + ";charset=utf-8")
            .body(searchRequestDto);

    Response postResponse = request.post(location);
    Assert.assertEquals(201, postResponse.statusCode());

    return postResponse.getHeader("location");
  }

  private SearchRequestDto<FeatureSearchDto> getSearchRequest(String searchLocation) {
    RequestSpecification request =
        RestAssured.given().auth().basic(operatorName, operatorPassword);

    Response response = request.get(searchLocation);
    Assert.assertEquals(200, response.getStatusCode());

    return new Gson().fromJson(response.asString(), SearchRequestDto.class);
  }

  private Integer getFeatureSearchResultSetSize(String location) {
    String testUrl = location + "/resultset-size";

    RequestSpecification request =
        RestAssured.given().auth().basic(operatorName, operatorPassword);

    Response response = request.get(testUrl);
    Assert.assertEquals(200, response.getStatusCode());

    return response.getBody().as(Integer.TYPE);
  }

  private FeatureDto getFeatureResult(String location) {
    RequestSpecification request =
        RestAssured.given().auth().basic(operatorName, operatorPassword);

    Response response = request.get(location);

    Assert.assertEquals(200, response.statusCode());

    Gson gson = new GsonBuilder().create();
    return gson.fromJson(response.asString(), FeatureDto.class);
  }

  @Test
  public void FeatureSearchTest() {
    String testUrl = baseUrl + "/search";

    SearchRequestDto<FeatureSearchDto> searchRequestDto = new SearchRequestDto<>();

    FeatureSearchDto searchDto = new FeatureSearchDto();
    searchDto.setFeatureId(151);
    searchRequestDto.setTemplate(searchDto);

    String searchLocation = postSearchRequest(searchRequestDto, testUrl);

    SearchRequestDto<FeatureSearchDto> readRequest = getSearchRequest(searchLocation);
    //TODO: compare SearchRequestDto-s

    if (getFeatureSearchResultSetSize(searchLocation) > 0) {
      getFeatureResult(searchLocation);
    }

  }

}
