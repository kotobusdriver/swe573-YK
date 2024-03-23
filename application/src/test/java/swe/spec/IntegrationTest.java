package swe.spec;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import swe.spec.config.SpringTestConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringTestConfig.class})
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired private WebApplicationContext context;
    @Autowired protected ObjectMapper objectMapper;
    @Autowired protected MockMvc mockMvc;

    @BeforeEach
    public void baseSetup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(context);
        Assertions.assertNotNull(objectMapper);
        Assertions.assertNotNull(mockMvc);
    }
}
