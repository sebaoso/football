package se.osorio.football;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableWebMvc
public class FootballApplication {

  public static void main(String[] args) {
    SpringApplication.run(FootballApplication.class, args);
  }

  @Value("${openapi.local.url}")
  String localUrl;
  @Value("${openapi.local.port}")
  String localPort;
  @Value("${info.app.version}")
  String buildVersion;

  @Bean
  public OpenAPI publicApi() {
    List<Server> servers = new ArrayList<>();
    servers.add(new Server().url(localUrl + ":" + localPort));

    return new OpenAPI()
        .info(new Info().title("Football app Openapi example")
            .description("Ett exempel på en Fotboll-service som beskrivs med en OpenApi-specifikation")
            .version(buildVersion)
            .contact(new Contact()
                .name("Sebastian Osorio")))
        .servers(servers);
  }

}
