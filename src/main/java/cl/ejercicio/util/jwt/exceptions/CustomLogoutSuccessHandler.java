package cl.ejercicio.util.jwt.exceptions;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cl.ejercicio.util.AppConstant.SUCCESS_LOGOUT_MESSAGE;
import static cl.ejercicio.util.formats.HandlerMessage.formatMessageException;

import java.io.IOException;
import java.io.Serializable;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler, Serializable {

  private static final long serialVersionUID = -7033940199937833003L;

  public CustomLogoutSuccessHandler() {}

  @Override
  public void onLogoutSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {

    formatMessageException(response, HttpServletResponse.SC_OK, SUCCESS_LOGOUT_MESSAGE);
  }
}
