package cl.test.ejercicio.repository.getusers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.ejercicio.models.GetUsersResponse;
import cl.ejercicio.util.formats.LoadStubs;

import java.io.IOException;

public class GetUsersStubs {
  public GetUsersStubs() {}

  public static GetUsersResponse getGetUsersResponse() throws IOException {
    return new ObjectMapper()
        .readValue(
            LoadStubs.getStubs("get-users-response.json"),
            new TypeReference<GetUsersResponse>() {});
  }
}
