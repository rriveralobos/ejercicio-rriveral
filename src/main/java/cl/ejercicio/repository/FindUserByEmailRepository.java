package cl.ejercicio.repository;

import cl.ejercicio.models.FindUserByEmailRequest;
import cl.ejercicio.models.FindUserByEmailResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ejercicio.entity.User;

import static cl.ejercicio.util.AppConstant.USER_DOES_NOT_EXIST_EXCEPTION_MESSAGE;

import java.util.Objects;

@Service
public class FindUserByEmailRepository {

  private UserRepository userRepository;
  private final Logger logsFindUserByEmailUseCase;

  @Autowired
  public FindUserByEmailRepository(final UserRepository userRepository) {
    this.userRepository = userRepository;
    this.logsFindUserByEmailUseCase = LogManager.getLogger(FindUserByEmailRepository.class);
  }

  public FindUserByEmailResponse findUserByEmail(
      final FindUserByEmailRequest findUserByEmailRequest) {
    // Do log class
    logsFindUserByEmailUseCase.info(
        "Class:FindUserByEmail, Method: findUserByEmail, Message: {}",
        findUserByEmailRequest);
    // Do find email user
    final User user = userRepository.findByEmail(findUserByEmailRequest.getEmail());
    // Do log find email user
    logsFindUserByEmailUseCase.info(
        "Class:FindUserByEmail, Method: findUserByEmail, Action: findUserByEmail, Message: {}",
        user);

    Objects.requireNonNull(user, USER_DOES_NOT_EXIST_EXCEPTION_MESSAGE);

    final FindUserByEmailResponse findUserByEmailResponse =
        new FindUserByEmailResponse(
            user.getId(),
            user.getCreated(),
            user.getModified(),
            user.getLastLogin(),
            user.getToken(),
            user.isActive());
   
    logsFindUserByEmailUseCase.info(
        "Class:FindUserByEmail, Method: findUserByEmail, Action: create response, Message: {}",
        findUserByEmailResponse);

    return findUserByEmailResponse;
  }
}
