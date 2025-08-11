import org.example.atm.client.AtmClient;
import org.example.atm.model.AtmResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AtmSearchTest {
    private static AtmClient atmClient;

    @BeforeAll
    public static void setUp() {
        atmClient = new AtmClient();
    }

    @Test
    public void testAtmSearchWithAllParameters() {
        AtmResponse response = atmClient.searchAtms(
                "Москва",      // city
                true,          // cashWithdraw
                true,          // cashDeposit
                true,          // acceptPayments
                true,          // moneyTransfer
                true,          // nfc
                false,         // is24Hour
                "а",           // searchQuery
                1,             // pageNumber
                2,             // pageSize
                "name"         // sortBy
        );

    @Test
    public void testAtmSearchWithAllParameters() {
        AtmResponse response = atmClient.searchAtms(
                "Москва",      // city
                true,          // cashWithdraw
                true,          // cashDeposit
                true,          // acceptPayments
                true,          // moneyTransfer
                true,          // nfc
                false,         // is24Hour
                "а",           // searchQuery
                1,             // pageNumber
                2,             // pageSize
                "name"         // sortBy
        );

        // Проверяем, что в найденных банкоматах хотя бы одно из полей содержит "а"
        assertThat(response.getAtm(), everyItem(
                anyOf(
                        hasProperty("name", containsStringIgnoringCase("а")),
                        hasProperty("street", containsStringIgnoringCase("а")),
                        hasProperty("metroStation", containsStringIgnoringCase("а"))
                )
        ));

        // Дополнительные проверки
        assertThat(response.getCount(), greaterThanOrEqualTo(0));
        assertThat(response.getAtm().size(), lessThanOrEqualTo(10));
    }

    @Test
    public void testAtmSearchWithRequiredParametersOnly() {
        AtmResponse response = atmClient.searchAtms(
                "Москва",      // city
                null,          // cashWithdraw
                null,          // cashDeposit
                null,          // acceptPayments
                null,          // moneyTransfer
                null,          // nfc
                null,          // is24Hour
                null,         // searchQuery
                null,         // pageNumber
                null,         // pageSize
                null         // sortBy
        );

        assertThat(response.getCount(), greaterThanOrEqualTo(0));
        assertThat(response.getPageNumber(), equalTo(1)); // default
        //assertThat(response.getPageSize(), equalTo(12)); // default
    }

}
