package cl.test.ejercicio.util.exceptions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.ejercicio.util.exceptions.CustomExceptionsHandler;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomExceptionsHandlerTest {

  @Test
  public void handleConstraintViolationException() {

    assertNotNull(new CustomExceptionsHandler().handleConstraintViolationException());
  }

  @Test
  public void handleNullPointerException() {

    assertNotNull(
        new CustomExceptionsHandler()
            .handleNullPointerException(new NullPointerException("nullPointerException")));
  }
}
