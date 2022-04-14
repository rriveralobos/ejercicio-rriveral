package cl.test.ejercicio.repository.finduserbyemail;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.ejercicio.models.FindUserByEmailRequest;
import cl.ejercicio.models.FindUserByEmailResponse;
import cl.ejercicio.util.formats.LoadStubs;

import java.io.IOException;

public class FindUserByEmailStubs {
  public FindUserByEmailStubs() {}

  public static FindUserByEmailResponse getFindUserByEmailResponse() throws IOException {
    return new ObjectMapper()
        .readValue(
            LoadStubs.getStubs("find-user-by-email-response.json"),
            new TypeReference<FindUserByEmailResponse>() {});
  }

  public static FindUserByEmailRequest getFindUserByEmailRequest() throws IOException {
    return new ObjectMapper()
        .readValue(
            LoadStubs.getStubs("find-user-by-email-request.json"),
            new TypeReference<FindUserByEmailRequest>() {});
  }
}
