package cl.ejercicio.util.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import cl.ejercicio.repository.UserRepository;
import cl.ejercicio.entity.User;
import cl.ejercicio.util.jwt.models.CustomUserDetails;

@Component
public class CustomUserDetailsService implements UserDetailsService {

  private UserRepository users;
  @SuppressWarnings("rawtypes")


  public CustomUserDetailsService(UserRepository users) {
    this.users = users;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    CustomUserDetails customUserDetails = null;

    final User user = users.findByEmail(username);
    if (user != null) {
      customUserDetails =
          new CustomUserDetails(user.getEmail(), user.getPassword(), user.getRoles());
    }

    return customUserDetails;
  }
}
