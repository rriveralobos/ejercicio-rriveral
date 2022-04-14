package cl.ejercicio.util.jwt.exceptions;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cl.ejercicio.util.AppConstant.SESSION_EXCEPTION_MESSAGE;
import static cl.ejercicio.util.formats.HandlerMessage.formatMessageException;

import java.io.IOException;
import java.io.Serializable;

public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint, Serializable {

  private static final long serialVersionUID = -9206078375353792395L;

  public AuthenticationEntryPointHandler() {}

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException {
    formatMessageException(response, HttpServletResponse.SC_BAD_REQUEST, SESSION_EXCEPTION_MESSAGE);
  }
}
