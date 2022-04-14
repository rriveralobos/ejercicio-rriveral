package cl.test.ejercicio.util.exceptions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.ejercicio.util.exceptions.FieldsErrors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class FieldsErrorsTest {

  @Test
  public void fieldErrorsTest() {
    final FieldsErrors fieldsErrors = new FieldsErrors("exampleField", "errorFieldExample");
    assertEquals(fieldsErrors, fieldsErrors);
  }
}
