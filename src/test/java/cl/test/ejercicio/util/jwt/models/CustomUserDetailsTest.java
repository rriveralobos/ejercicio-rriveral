package cl.test.ejercicio.util.jwt.models;

import org.junit.Test;

import cl.ejercicio.util.jwt.models.CustomUserDetails;

import java.util.Arrays;

import static cl.ejercicio.util.AppConstant.ROL_ADMIN;
import static cl.ejercicio.util.AppConstant.ROL_USER;
import static org.junit.Assert.assertEquals;

public class CustomUserDetailsTest {

  @Test
  public void customUserDetailsTest() {
    final CustomUserDetails customUserDetails =
        new CustomUserDetails(
            "usernameExample", "examplePassword", Arrays.asList(ROL_ADMIN, ROL_USER));
    assertEquals(customUserDetails, customUserDetails);
  }
}
