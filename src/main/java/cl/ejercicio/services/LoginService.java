package cl.ejercicio.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.ejercicio.models.LoginRequest;
import cl.ejercicio.models.LoginResponse;
import cl.ejercicio.repository.UserRepository;
import cl.ejercicio.entity.User;
import cl.ejercicio.util.jwt.JwtTokenProvider;

import static cl.ejercicio.util.formats.Date.dateNow;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {

  // region fields
  private final Logger logsDoSignUpUseCase;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  // endregion

  @Autowired
  public LoginService(
          final AuthenticationManager authenticationManager,
          final JwtTokenProvider jwtTokenProvider,
          final UserRepository userRepository,
          final PasswordEncoder passwordEncoder) {
    this.logsDoSignUpUseCase = LogManager.getLogger(LoginService.class);
    this.authenticationManager = authenticationManager;
    this.jwtTokenProvider = jwtTokenProvider;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public LoginResponse doSignIn(final LoginRequest loginRequest) {
    // EncryptPassword
    final LoginRequest encryptDoSignInRequest =
            new LoginRequest(
                    loginRequest.getName(),
                    loginRequest.getEmail(),
                    passwordEncoder.encode(loginRequest.getPassword()),
                    loginRequest.getPhones());

    logsDoSignUpUseCase.info(
            "Class:DoSignIn, Method: doSignIn, Message: {}", encryptDoSignInRequest);

    User user = userRepository.findByEmail(loginRequest.getEmail());
    final List<String> listRoles = new ArrayList<>();
    listRoles.add ("ROL_USER");

    final String token = jwtTokenProvider.createToken(loginRequest.getEmail(),listRoles);

    if (user == null) {

      user =
              userRepository.save(
                      new User(
                              loginRequest.getName(),
                              loginRequest.getEmail(),
                              encryptDoSignInRequest.getPassword(),
                              loginRequest.getPhones(),
                              dateNow(),
                              dateNow(),
                              dateNow(),
                              token,
                              listRoles,
                              true));
    }
    //
    if (user.getToken() != null) {

      logsDoSignUpUseCase.info(
              "Class:DoSign, Method: doSignIn, Action: Create User, Message: {}",
              user);

      final Authentication doAuthentication =
              authenticationManager.authenticate(
                      new UsernamePasswordAuthenticationToken(
                              user.getEmail(), loginRequest.getPassword()));

      logsDoSignUpUseCase.info(
              "Class:DoSignInUseCase, Method: doSignIn, Action: user authenticate, Message: {}",
              doAuthentication.getAuthorities());

      logsDoSignUpUseCase.info(
              "Class:DoSignInUseCase, Method: doSignIn, Action: create token user");

      final String dateNow = dateNow();

      final int updateUser =
              userRepository.updateUser(user.getEmail(), token, dateNow, dateNow);

      logsDoSignUpUseCase.info(
              "Here I Am: Class:DoSignInUseCase, Method: doSignIn, Action: update user, Message: {}",
              updateUser);
    }
    final LoginResponse loginResponse =
            new LoginResponse(
                    user.getId(),
                    user.getCreated(),
                    user.getModified(),
                    user.getLastLogin(),
                    user.getToken(),
                    user.isActive());

    logsDoSignUpUseCase.info(
            "Here I Am: Class:DoSignInUseCase, Method: doSignIn, Action: create response, Message: {}",
            loginResponse);
    return loginResponse;
  }
}
