package cl.ejercicio.util.jwt.exceptions;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import cl.ejercicio.repository.UserRepository;
import cl.ejercicio.entity.User;
import cl.ejercicio.util.jwt.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cl.ejercicio.util.AppConstant.NAME_TOKEN_SESSION;

import java.io.Serializable;

public class CustomLogoutHandler implements LogoutHandler, Serializable {

  private static final long serialVersionUID = -3409865897460953769L;

  private final transient JwtTokenProvider jwtTokenProvider;
  private final transient UserRepository userRepository;

  public CustomLogoutHandler(
      final JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
    this.userRepository = userRepository;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public void logout(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    String tokenSearch = request.getHeader(NAME_TOKEN_SESSION);
    final User findEmail = userRepository.findUserByToken(tokenSearch);
    final String updateToken =
        jwtTokenProvider.revocateToken(
            jwtTokenProvider.getUsername(tokenSearch));
    userRepository.revocateToken(tokenSearch, updateToken);
  }
}
