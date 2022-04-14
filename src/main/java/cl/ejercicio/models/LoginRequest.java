package cl.ejercicio.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static cl.ejercicio.util.AppConstant.CREDENTIALS_EMPTY_ERROR_MESSAGE;
import static cl.ejercicio.util.AppConstant.CREDENTIALS_NULL_ERROR_MESSAGE;
import static cl.ejercicio.util.AppConstant.EMAIL_EMPTY_ERROR_MESSAGE;
import static cl.ejercicio.util.AppConstant.EMAIL_FORMAT_ERROR_MESSAGE;
import static cl.ejercicio.util.AppConstant.EMAIL_NULL_ERROR_MESSAGE;
import static cl.ejercicio.util.AppConstant.NAME_EMPTY_ERROR_MESSAGE;
import static cl.ejercicio.util.AppConstant.NAME_NULL_ERROR_MESSAGE;
import static cl.ejercicio.util.AppConstant.PHONES_EMPTY_ERROR_MESSAGE;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LoginRequest implements Serializable {

  private static final long serialVersionUID = -4496080854528033222L;

  @NotNull(message = NAME_NULL_ERROR_MESSAGE)
  @NotBlank(message = NAME_EMPTY_ERROR_MESSAGE)
  private String name;

  @NotNull(message = EMAIL_NULL_ERROR_MESSAGE)
  @NotBlank(message = EMAIL_EMPTY_ERROR_MESSAGE)
  @Email(message = EMAIL_FORMAT_ERROR_MESSAGE)
  private String email;

  @NotNull(message = CREDENTIALS_NULL_ERROR_MESSAGE)
  @NotBlank(message = CREDENTIALS_EMPTY_ERROR_MESSAGE)
  private String password;

  @Valid
  @NotEmpty(message = PHONES_EMPTY_ERROR_MESSAGE)
  private List<Phone> phones;

  public LoginRequest() {}

  public LoginRequest(
      final String name, final String email, final String password, final List<Phone> phones) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.phones = phones;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public List<Phone> getPhones() {
    return phones;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final LoginRequest that = (LoginRequest) o;
    return Objects.equals(getName(), that.getName())
        && Objects.equals(getEmail(), that.getEmail())
        && Objects.equals(getPassword(), that.getPassword())
        && Objects.equals(getPhones(), that.getPhones());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getEmail(), getPassword(), getPhones());
  }

  @Override
  public String toString() {
    return "LoginRequest{"
        + "name='"
        + getName()
        + '\''
        + ", email='"
        + getEmail()
        + '\''
        + ", password='"
        + getPassword()
        + '\''
        + ", phones="
        + getPhones()
        + '}';
  }
}
