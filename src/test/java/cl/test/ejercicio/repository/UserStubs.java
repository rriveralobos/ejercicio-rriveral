package cl.test.ejercicio.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.ejercicio.entity.User;
import cl.ejercicio.util.formats.LoadStubs;

import java.io.IOException;

public class UserStubs {

  public UserStubs() {}

  public static User getUser() throws IOException {
    return new ObjectMapper()
        .readValue(LoadStubs.getStubs("user.json"), new TypeReference<User>() {});
  }
}
