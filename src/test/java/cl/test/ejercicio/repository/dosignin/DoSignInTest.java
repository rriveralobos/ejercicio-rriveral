package cl.test.ejercicio.repository.dosignin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import cl.ejercicio.services.LoginService;
import cl.ejercicio.models.LoginResponse;
import cl.ejercicio.controller.UserController;
import cl.ejercicio.repository.UserRepository;
import cl.ejercicio.entity.User;
import cl.ejercicio.repository.FindUserByEmailRepository;
import cl.ejercicio.services.GetUsersService;
import cl.ejercicio.util.exceptions.ExceptionHandlerResponse;
import cl.ejercicio.util.jwt.JwtTokenProvider;

import java.io.IOException;

import static cl.test.ejercicio.repository.UserStubs.getUser;
import static cl.test.ejercicio.repository.dosignin.DoSignInStubs.getDoSignInRequest;
import static cl.test.ejercicio.repository.dosignin.DoSignInStubs.getDoSignInResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class DoSignInTest {

  @Mock private GetUsersService getUsersService;
  @Mock private FindUserByEmailRepository findUserByEmailRepository;
  @Mock private UserRepository userRepository;
  @MockBean AuthenticationManager authenticationManager;
  @MockBean JwtTokenProvider jwtTokenProvider;

  private UserController controller;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(16);
  }

  @Before
  public void setup() throws IOException {

    final LoginService loginService =
        new LoginService(
            authenticationManager, jwtTokenProvider, userRepository, passwordEncoder());



    final User user =
        new User(
            getUser().getId(),
            getUser().getName(),
            getUser().getEmail(),
            getUser().getPassword(),
            getUser().getPhones(),
            getUser().getCreated(),
            getUser().getModified(),
            getUser().getLastLogin(),
            getUser().getToken(),
            getUser().getRoles(),
            getUser().isActive());
    when(userRepository.save(any(User.class))).thenReturn(user);
    when(userRepository.findByEmail(any(String.class))).thenReturn(user);
    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenReturn(
            new UsernamePasswordAuthenticationToken(
                getDoSignInRequest().getEmail(), getDoSignInRequest().getPassword()));
    when(userRepository.updateUser(
            any(String.class), any(String.class), any(String.class), any(String.class)))
        .thenReturn(1);
    when(jwtTokenProvider.createToken(any(), any())).thenReturn(getDoSignInResponse().getToken());
  }

  @Test
  public void itShouldDoSignInWhenApiSuccess() throws IOException {
    assertNotNull(getDoSignInRequest());

    final LoginResponse loginResponse = controller.doSignIn(getDoSignInRequest());

    assertNotNull(getDoSignInResponse());
    assertNotNull(loginResponse);
    assertEquals(
        loginResponse,
        new LoginResponse(
            getDoSignInResponse().getId(),
            getDoSignInResponse().getCreated(),
            getDoSignInResponse().getModified(),
            getDoSignInResponse().getLastLogin(),
            getDoSignInResponse().getToken(),
            getDoSignInResponse().isActive()));
  }

  @Test
  public void itShouldDoSignInWhenApiFailure() throws IOException {
    assertNotNull(getDoSignInRequest());
    try {
      controller.doSignIn(null);
    } catch (NullPointerException ex) {
      assertEquals(new ExceptionHandlerResponse<>(null).getMessage(), ex.getMessage());
    }
  }
}
