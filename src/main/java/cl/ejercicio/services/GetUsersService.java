package cl.ejercicio.services;

import cl.ejercicio.repository.UserRepository;
import cl.ejercicio.models.GetUsersResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ejercicio.entity.User;


import java.util.List;

@Service
public class GetUsersService {


  private final Logger logsDoGetUsersUseCase;
  private final UserRepository userRepository;

  @Autowired
  public GetUsersService(final UserRepository userRepository) {
    this.logsDoGetUsersUseCase =
        LogManager.getLogger(GetUsersService.class);
    this.userRepository = userRepository;
  }

  public GetUsersResponse getUsers() {

    logsDoGetUsersUseCase.info("Class:DoGetUsers, Method: findUserByEmail");

    final List<User> users = userRepository.findAll();
  
    logsDoGetUsersUseCase.info(
        "Class:DoGetUsers, Method: findUserByEmail, Action: Success Find Users");

    return new GetUsersResponse(userRepository.findAll());
  }
}
