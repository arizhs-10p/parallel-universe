package com.parallel.universe.base;

import com.parallel.universe.config.ConfigReader;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import com.parallel.universe.models.JwtTokenRequest;
import com.parallel.universe.models.JwtTokenResponse;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class BaseClassApi {
    protected JwtTokenResponse jwtTokenResponse;

        ConfigReader configReader = new ConfigReader();
        String HeaderAuth1 = configReader.getApiHeaderAuth1();
        String HeaderAuth2 = configReader.getApiHeaderAuth2();
        String ApiBaseURL = configReader.getBaseApiUrl();
        String EmailAddress = configReader.getApiEmailAddress();
        String DeviceSessionId = configReader.getApiDeviceSessionId();
        String Password = configReader.getApiPassword();

    @BeforeMethod
    public void beforeMethod() throws Exception {
        System.out.println("I am in start of beforeMethod - API");

        JwtTokenRequest jwtTokenRequest = new JwtTokenRequest(EmailAddress, DeviceSessionId, Password);
        Response response = given().header(HeaderAuth1 , HeaderAuth2)
                .body(jwtTokenRequest).when().post(ApiBaseURL+"v1/authenticate").then().extract().response();

        jwtTokenResponse = response.getBody().as(new TypeRef<JwtTokenResponse>() {});
        System.out.println(jwtTokenResponse.getCountry());
        System.out.println(jwtTokenRequest.getDeviceSessionId());
        System.out.println(jwtTokenRequest.getEmailAddress());
    }

    @AfterMethod
    public void afterMethod()
    {
        System.out.println("I am in afterMethod - API");
    }
}
