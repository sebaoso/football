package se.osorio.football;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class FootballApiTest {

    private static final String ARGENTINA = "Argentina";

    @Test
    public void givenTeamExists_whenGetAllTeams_thenArgentinaIsPresent() throws IOException {

        HttpUriRequest request = new HttpGet("http://localhost:8080/teams");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        String jsonResponse = EntityUtils.toString(response.getEntity());

        ObjectMapper objectMapper = new ObjectMapper();
        List<Team> teamList = Arrays.asList(objectMapper.readValue(jsonResponse, Team[].class));
        log.info(jsonResponse);
        assertTrue(teamList.stream().anyMatch(team -> team.getName().equals(ARGENTINA)));
    }
}
