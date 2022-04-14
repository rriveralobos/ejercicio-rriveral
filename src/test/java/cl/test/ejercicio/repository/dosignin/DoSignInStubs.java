package cl.test.ejercicio.repository.dosignin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.ejercicio.models.LoginRequest;
import cl.ejercicio.models.LoginResponse;
import cl.ejercicio.util.formats.LoadStubs;

import java.io.IOException;

public class DoSignInStubs {
  public DoSignInStubs() {}

  public static LoginRequest getDoSignInRequest() throws IOException {
    return new ObjectMapper()
        .readValue(
            LoadStubs.getStubs("sign-in-request.json"), new TypeReference<LoginRequest>() {});
  }

  public static LoginResponse getDoSignInResponse() throws IOException {
    return new ObjectMapper()
        .readValue(
            LoadStubs.getStubs("sign-in-response.json"), new TypeReference<LoginResponse>() {});
  }
}
