package swe.spec.scenarios;

import org.junit.jupiter.api.Test;
import swe.spec.Scenario;

public class CreateUserTest extends Scenario {

  @Test
  public void create_user() throws Exception {
    createUserUsecase.invokeUsecase("Amy", "amy@company.com", "password");
    loginUsecase.invokeUsecase("amy@company.com", "password");
  }
}
