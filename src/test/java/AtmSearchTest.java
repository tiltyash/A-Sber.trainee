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

        assertThat(response.getCount(), greaterThanOrEqualTo(0));
        assertThat(response.getPageNumber(), equalTo(1));
        assertThat(response.getPageSize(), equalTo(2));

        if (response.getCount() > 0) {
            assertThat(response.getAtm(), hasSize(lessThanOrEqualTo(2)));

            response.getAtm().forEach(atm -> {
                assertThat(atm.getId(), notNullValue());
                assertThat(atm.getName(), notNullValue());
                assertThat(atm.getName(), containsStringIgnoringCase("а"));
                assertThat(atm.getStreetType(), notNullValue());
                assertThat(atm.getStreet(), notNullValue());
                assertThat(atm.getHouse(), notNullValue());
                assertThat(atm.getAtmLatitude(), notNullValue());
                assertThat(atm.getAtmLongitude(), notNullValue());
            });
        }
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
        assertThat(response.getPageSize(), equalTo(12)); // default
    }
}