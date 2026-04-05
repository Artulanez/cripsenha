package br.com.cripsenha.route;

import br.com.cripsenha.dto.UserDTO;
import br.com.cripsenha.service.UserService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class UserConsumerRoute extends RouteBuilder {

    private final UserService userService;

    public UserConsumerRoute(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void configure() throws Exception {
        from("spring-rabbitmq:{{rabbitmq.topic}}?queue={{rabbitmq.queue}}&routingKey=#")
            .routeId("userConsumerRoute")
            .unmarshal().json(JsonLibrary.Jackson, UserDTO.class)
            .bean(userService, "processarUsuario");
    }
}
