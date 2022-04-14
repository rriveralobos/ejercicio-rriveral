package cl.ejercicio.util.formats;

import javax.servlet.http.HttpServletResponse;

import static cl.ejercicio.util.AppConstant.CHARACTER_ENCODING_MESSAGE_JWT;
import static cl.ejercicio.util.AppConstant.FORMAT_MESSAGE_JWT;
import static cl.ejercicio.util.AppConstant.TYPE_MESSAGE_JWT;

import java.io.IOException;

public class HandlerMessage {

  public HandlerMessage() {}

  public static void formatMessageException(
      final HttpServletResponse response, final int handlerStatus, final String handlerMeessage)
      throws IOException {

    String json = String.format(FORMAT_MESSAGE_JWT, handlerMeessage);
    response.setStatus(handlerStatus);
    response.setContentType(TYPE_MESSAGE_JWT);
    response.setCharacterEncoding(CHARACTER_ENCODING_MESSAGE_JWT);
    response.getWriter().write(json);
  }

}
