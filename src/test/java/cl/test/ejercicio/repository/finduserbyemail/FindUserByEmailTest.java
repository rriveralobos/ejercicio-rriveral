package cl.test.ejercicio.repository.finduserbyemail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import cl.ejercicio.services.LoginService;
import cl.ejercicio.controller.UserAuthController;
import cl.ejercicio.repository.UserRepository;
import cl.ejercicio.entity.User;
import cl.ejercicio.repository.FindUserByEmailRepository;
import cl.ejercicio.services.GetUsersService;
import cl.ejercicio.models.FindUserByEmailResponse;
import cl.ejercicio.util.exceptions.ExceptionHandlerResponse;

import java.io.IOException;

import static cl.test.ejercicio.repository.UserStubs.getUser;
import static cl.test.ejercicio.repository.finduserbyemail.FindUserByEmailStubs.getFindUserByEmailRequest;
import static cl.test.ejercicio.repository.finduserbyemail.FindUserByEmailStubs.getFindUserByEmailResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class FindUserByEmailTest {

  @Mock private LoginService loginService;
  @Mock private GetUsersService getUsersService;
  @Mock private UserRepository userRepository;

  private UserAuthController authController;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(16);
  }

  @Before
  public void setup() throws IOException {

    final FindUserByEmailRepository findUserByEmailRepository =
        new FindUserByEmailRepository(userRepository);



    when(userRepository.findByEmail(any(String.class)))
        .thenReturn(
            new User(
                getUser().getId(),
                getUser().getCreated(),
                getUser().getModified(),
                getUser().getLastLogin(),
                getUser().getToken(),
                getUser().isActive()));
  }

  @Test
  public void itShouldFindUserByEmailWhenApiSuccess() throws IOException {
    assertNotNull(getFindUserByEmailRequest());

    final FindUserByEmailResponse findUserByEmailResponse =
        authController.findUserByEmail(getFindUserByEmailRequest());

    assertNotNull(getFindUserByEmailResponse());
    assertNotNull(findUserByEmailResponse);
    assertEquals(findUserByEmailResponse, getFindUserByEmailResponse());
  }

  @Test
  public void itShouldFindUserByEmailWhenApiFailure() throws IOException {
    assertNotNull(getFindUserByEmailRequest());
    try {
      authController.findUserByEmail(null);
    } catch (NullPointerException ex) {
      assertEquals(new ExceptionHandlerResponse<>(null).getMessage(), ex.getMessage());
    }
  }
}
