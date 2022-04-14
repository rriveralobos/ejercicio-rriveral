package cl.ejercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.ejercicio.services.UnProtectedServiceImpl;
import cl.ejercicio.models.LoginRequest;
import cl.ejercicio.models.LoginResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/unprotected")
public class UserController {
  private final UnProtectedServiceImpl ejercicioServiceImpl;
  @Autowired
  public UserController(final UnProtectedServiceImpl ejercicioServiceImpl) {
    this.ejercicioServiceImpl = ejercicioServiceImpl;
  }
  @RequestMapping(
      value = "/doSignIn",
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public LoginResponse doSignIn(@Valid @RequestBody final LoginRequest loginRequest) {

    return ejercicioServiceImpl.doSignIn(loginRequest);
  }
}
