package se.osorio.football;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import se.osorio.football.client.ApiClient;
import se.osorio.football.client.Configuration;
import se.osorio.football.client.api.HmtaAllaLagApi;
import se.osorio.football.client.model.Team;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class OpenApiClientTest {
    @Test
    void testGetTeams_teamsListIsNotNull() throws Exception {
        log.info("Kör testGetTeams_teamsListIsNotNull");
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setHost("localhost");
        defaultClient.setScheme("http");
        defaultClient.setPort(8080);

        HmtaAllaLagApi hmtaAllaLagApi = new HmtaAllaLagApi(defaultClient);

        List<Team> allTeams = hmtaAllaLagApi.getAllTeams();

        assertNotNull(allTeams);
    }
}