package org.example.atm.utils;

import io.restassured.specification.RequestSpecification;

public class RestAssuredUtils {
    public static RequestSpecification queryParamIfNotNull(RequestSpecification spec, String name, Object value) {
        return value != null ? spec.queryParam(name, value) : spec;
    }
}