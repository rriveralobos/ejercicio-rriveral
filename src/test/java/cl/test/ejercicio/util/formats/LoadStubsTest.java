package cl.test.ejercicio.util.formats;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.ejercicio.util.formats.LoadStubs;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoadStubsTest {

  @Test
  public void getStubs() {

    assertNotNull(LoadStubs.getStubs("user.json"));
  }
}
