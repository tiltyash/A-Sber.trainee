package org.example.atm.client;

import org.example.atm.model.AtmResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.example.atm.utils.RestAssuredUtils.queryParamIfNotNull;

public class AtmClient {
    private final RequestSpecification requestSpec;

    public AtmClient() {
        requestSpec = RestAssured.given()
                .baseUri(org.example.atm.utils.Config.BASE_URI)
                .basePath(org.example.atm.utils.Config.BASE_PATH);
    }

    public AtmResponse searchAtms(
            String city,
            Boolean cashWithdraw,
            Boolean cashDeposit,
            Boolean acceptPayments,
            Boolean moneyTransfer,
            Boolean nfc,
            Boolean is24Hour,
            String searchQuery,
            Integer pageNumber,
            Integer pageSize,
            String sortBy) {

        // Создаем временную переменную для накопления параметров
        RequestSpecification spec = requestSpec.queryParam("city", city);

        // Применяем все параметры через статический метод
        spec = queryParamIfNotNull(spec, "cashWithdraw", cashWithdraw);
        spec = queryParamIfNotNull(spec, "cashDeposit", cashDeposit);
        spec = queryParamIfNotNull(spec, "acceptPayments", acceptPayments);
        spec = queryParamIfNotNull(spec, "moneyTransfer", moneyTransfer);
        spec = queryParamIfNotNull(spec, "nfc", nfc);
        spec = queryParamIfNotNull(spec, "is24Hour", is24Hour);
        spec = queryParamIfNotNull(spec, "searchQuery", searchQuery);
        spec = queryParamIfNotNull(spec, "pageNumber", pageNumber);
        spec = queryParamIfNotNull(spec, "pageSize", pageSize);
        spec = queryParamIfNotNull(spec, "sortBy", sortBy);

        Response response = spec
                .when()
                .get("/search")
                .then()
                .extract()
                .response();

        return response.as(AtmResponse.class);
    }
}