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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import swe.spec.config.SpringTestConfig;
import swe.spec.usecases.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringTestConfig.class})
@SpringBootTest
@AutoConfigureMockMvc
public class Scenario {
  @Autowired private WebApplicationContext context;
  @Autowired protected ObjectMapper objectMapper;
  @Autowired protected MockMvc mockMvc;
  @Autowired protected CreateUserUsecase createUserUsecase;
  @Autowired protected LoginUsecase loginUsecase;
  @Autowired protected CreateCommunityUsecase createCommunityUsecase;
  @Autowired protected SendPostUsecase sendPostUsecase;
  @Autowired protected GetUserCommunitiesUsecase getUserCommunitiesUsecase;
  @Autowired protected GetCommunityMembersUsecase getCommunityMembersUsecase;
  @Autowired protected SubscribeToCommunityUsecase subscribeToCommunityUsecase;
  @Autowired protected DeletePostUsecase deletePostUsecase;

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
