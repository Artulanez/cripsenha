package br.com.cripsenha.route;

import br.com.cripsenha.service.UserService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserConsumerRoute extends RouteBuilder {

    private final UserService userService;

    public UserConsumerRoute(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void configure() throws Exception {
        // Ponto de entrada para o RabbitMQ
        from("spring-rabbitmq:{{rabbitmq.topic}}?queues={{rabbitmq.queue}}&routingKey=#")
            .routeId("userConsumerRoute")
            .to("direct:userConsumerRouteStart");
    }
}
