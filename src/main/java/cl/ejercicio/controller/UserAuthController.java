package cl.ejercicio.controller;

import cl.ejercicio.services.UnProtectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.ejercicio.models.FindUserByEmailRequest;
import cl.ejercicio.models.FindUserByEmailResponse;
import cl.ejercicio.models.GetUsersResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/protected")
public class UserAuthController {
@Autowired
UnProtectedService unProtectedService;

  @RequestMapping(
      value = "/getUsers",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)

  public GetUsersResponse getUsers() {
    return unProtectedService.getUsers();
  }
  @RequestMapping(
      value = "/findUserByEmail",
      method = RequestMethod.GET,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public FindUserByEmailResponse findUserByEmail(
      @RequestBody @Valid final FindUserByEmailRequest findUserByEmailRequest) {
    return unProtectedService.findUserByEmail(findUserByEmailRequest);
  }
}
