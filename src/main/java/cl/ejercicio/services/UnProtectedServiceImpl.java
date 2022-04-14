package cl.ejercicio.services;

import cl.ejercicio.repository.FindUserByEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ejercicio.models.LoginRequest;
import cl.ejercicio.models.LoginResponse;
import cl.ejercicio.models.FindUserByEmailRequest;
import cl.ejercicio.models.FindUserByEmailResponse;
import cl.ejercicio.models.GetUsersResponse;

@Service
public class UnProtectedServiceImpl implements UnProtectedService {

  // region field
  private final LoginService loginService;
  private final GetUsersService getUsersService;
  private final FindUserByEmailRepository findUserByEmailRepository;
  // endregion

  @Autowired
  public UnProtectedServiceImpl(
      final LoginService loginService,
      final GetUsersService getUsersService,
      final FindUserByEmailRepository findUserByEmailRepository) {
    this.loginService = loginService;
    this.getUsersService = getUsersService;
    this.findUserByEmailRepository = findUserByEmailRepository;
  }

  @Override
  public LoginResponse doSignIn(final LoginRequest loginRequest) {
    // Redirect to doSignInUseCase
    return loginService.doSignIn(loginRequest);
  }

  @Override
  public GetUsersResponse getUsers() {
    // Redirect to doGetUsers
    return getUsersService.getUsers();
  }

  @Override
  public FindUserByEmailResponse findUserByEmail(FindUserByEmailRequest findUserByEmailRequest) {
    // Redirect to findUserByEmail
    return findUserByEmailRepository.findUserByEmail(findUserByEmailRequest);
  }

}
