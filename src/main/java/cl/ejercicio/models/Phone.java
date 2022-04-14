package cl.ejercicio.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotBlank;

import static cl.ejercicio.util.AppConstant.CITYWIDE_EMPTY_ERROR_MESSAGE;
import static cl.ejercicio.util.AppConstant.COUNTRYSIDE_EMPTY_ERROR_MESSAGE;
import static cl.ejercicio.util.AppConstant.NUMBER_EMPTY_ERROR_MESSAGE;


import java.io.Serializable;
import java.util.Objects;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Phone implements Serializable {

  private static final long serialVersionUID = -2989644983507113348L;


  @NotBlank(message = NUMBER_EMPTY_ERROR_MESSAGE)
  private String number;

  @NotBlank(message = CITYWIDE_EMPTY_ERROR_MESSAGE)
  private String cityCode;

  @NotBlank(message = COUNTRYSIDE_EMPTY_ERROR_MESSAGE)
  private String countryCode;

  public Phone() {}

  public Phone(final String number, final String cityCode, final String countryCode) {
    this.number = number;
    this.cityCode = cityCode;
    this.countryCode = countryCode;
  }

  public String getNumber() {
    return number;
  }

  public String getCityCode() {
    return cityCode;
  }

  public String getCountryCode() {
    return countryCode;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Phone phone = (Phone) o;
    return Objects.equals(getNumber(), phone.getNumber())
        && Objects.equals(getCityCode(), phone.getCityCode())
        && Objects.equals(getCountryCode(), phone.getCountryCode());
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, cityCode, countryCode);
  }

  @Override
  public String toString() {
    return "Phone{"
        + "number='"
        + getNumber()
        + '\''
        + ", cityCode='"
        + getCityCode()
        + '\''
        + ", countryCode='"
        + getCountryCode()
        + '\''
        + '}';
  }
}
