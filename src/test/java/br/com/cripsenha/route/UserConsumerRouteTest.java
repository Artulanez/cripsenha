package br.com.cripsenha.route;

import br.com.cripsenha.dto.UserDTO;
import br.com.cripsenha.service.UserService;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest
@CamelSpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserConsumerRouteTest {

    @Autowired
    private ProducerTemplate template;

    @Autowired
    private CamelContext camelContext;

    @MockBean
    private UserService userService;

    @Test
    void route_DeveProcessarMensagemJsonEChamarService() {
        // Arrange
        String json = "{\n" +
                "    \"id\": 1,\n" +
                "    \"nome\": \"João Silva\",\n" +
                "    \"senha\": \"senhaSegura123\",\n" +
                "    \"email\": \"joao.silva@email.com\",\n" +
                "    \"telefone\": \"11988887777\"\n" +
                "}";

        // Act
        // Como removemos o direct, para testar a rota sem o broker RabbitMQ real,
        // enviamos para o endpoint da rota. Em testes unitários, o Camel geralmente
        // substitui componentes externos por mocks se configurado, ou podemos
        // disparar diretamente para o ID da rota se for um teste de integração.
        
        template.sendBody("spring-rabbitmq:testExchange?queues=testQueue", json);

        // Assert
        verify(userService, timeout(5000)).processarUsuario(any(UserDTO.class));
    }
}
