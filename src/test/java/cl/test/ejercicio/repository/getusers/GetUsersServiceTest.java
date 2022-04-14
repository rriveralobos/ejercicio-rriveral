package cl.test.ejercicio.repository.getusers;

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
import cl.ejercicio.models.GetUsersResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cl.test.ejercicio.repository.UserStubs.getUser;
import static cl.test.ejercicio.repository.getusers.GetUsersStubs.getGetUsersResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class GetUsersServiceTest {

  @Mock private LoginService loginService;
  @Mock private FindUserByEmailRepository findUserByEmailRepository;
  @Mock private UserRepository userRepository;

  private UserAuthController authController;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(16);
  }

  @Before
  public void setup() throws IOException {

    final GetUsersService getUsersService = new GetUsersService(userRepository);


    final List<User> listUser = new ArrayList<>();
    listUser.add(
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
            getUser().isActive()));

    when(userRepository.findAll()).thenReturn(listUser);
  }

  @Test
  public void itShouldDoGetUsersWhenApiSuccess() throws IOException {
    final GetUsersResponse getUsersResponse = authController.getUsers();

    assertNotNull(getUsersResponse);
    assertEquals(getUsersResponse, getGetUsersResponse());
  }
}
