package cl.ejercicio.services;

import cl.ejercicio.models.LoginRequest;
import cl.ejercicio.models.LoginResponse;
import cl.ejercicio.models.FindUserByEmailRequest;
import cl.ejercicio.models.FindUserByEmailResponse;
import cl.ejercicio.models.GetUsersResponse;

public interface UnProtectedService {

  LoginResponse doSignIn(LoginRequest loginRequest);

  GetUsersResponse getUsers();

  FindUserByEmailResponse findUserByEmail(FindUserByEmailRequest findUserByEmailRequest);


}
