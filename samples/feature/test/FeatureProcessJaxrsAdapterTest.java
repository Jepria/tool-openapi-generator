package com.technology.jep.jepriashowcase.featureFeatureIdFeatureProcess;
import com.technology.jep.jepriashowcase.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.technology.jep.jepriashowcase.feature.dto.FeatureProcessDto;
import com.technology.jep.jepriashowcase.feature.dto.FeatureProcessSearchDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import javax.ws.rs.core.MediaType;
import org.jepria.server.data.SearchRequestDto;
import org.junit.Assert;
import org.junit.Test;

public class FeatureProcessJaxrsAdapterTest {

  private static final String baseUrl          = ""; // TODO
  private static final String operatorName     = ""; // TODO
  private static final String operatorPassword = ""; // TODO

  @Test
  public void findFeatureProcess() {
    String testUrl = baseUrl + "";

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
  public void createRecordTest() {
    String testUrl = baseUrl;

    FeatureProcessDto testDto = new FeatureProcessDto();
    //TODO: set DTO fields

    RequestSpecification requestPost =
        RestAssured.given().auth().basic(operatorName, operatorPassword)
            .header("Content-Type", "application/json")
            .body(testDto);

    Response postResponse = requestPost.post(testUrl);

    int statusCode = postResponse.statusCode();
    Assert.assertEquals(201, statusCode);
  }
  @Test
  public void getRecordByIdTest() {
    Integer recordId = 1; //TODO: set record ID for test

    String   testUrl  = baseUrl + "/" + recordId;
    Response response = RestAssured.given().auth().basic(operatorName, operatorPassword).when().get(testUrl);
    Assert.assertEquals(testUrl, 200, response.statusCode());
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

/**********************************************/

}
