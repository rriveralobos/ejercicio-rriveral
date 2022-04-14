package cl.ejercicio.util.jwt;

import static cl.ejercicio.util.AppConstant.API_REST_PROTECTED_DOSIGNOUT;
import static cl.ejercicio.util.AppConstant.API_REST_PROTECTED_FIND_USER_BY_EMAIL;
import static cl.ejercicio.util.AppConstant.API_REST_PROTECTED_GETUSERS;
import static cl.ejercicio.util.AppConstant.API_REST_UNPROTECTED_DOSIGNIN;
import static cl.ejercicio.util.AppConstant.HAS_ROL_ADMIN;
import static cl.ejercicio.util.AppConstant.HAS_ROL_USER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.ejercicio.repository.UserRepository;
import cl.ejercicio.util.jwt.exceptions.AuthenticationEntryPointHandler;
import cl.ejercicio.util.jwt.exceptions.CustomAccessDeniedHandler;
import cl.ejercicio.util.jwt.exceptions.CustomLogoutHandler;
import cl.ejercicio.util.jwt.exceptions.CustomLogoutSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired JwtTokenProvider jwtTokenProvider;
  @Autowired UserRepository userRepository;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic()
        .disable()
        .csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(API_REST_UNPROTECTED_DOSIGNIN)
        .permitAll()
        .antMatchers(API_REST_PROTECTED_GETUSERS)
        .hasAnyRole(HAS_ROL_USER, HAS_ROL_ADMIN)
        .antMatchers(API_REST_PROTECTED_FIND_USER_BY_EMAIL)
        .hasAnyRole(HAS_ROL_USER, HAS_ROL_ADMIN)
        .anyRequest()
        .authenticated()
        .and()
        .apply(new JwtConfigurer(jwtTokenProvider))
        .and()
        .logout()
        .logoutRequestMatcher(
            new AntPathRequestMatcher(
                API_REST_PROTECTED_DOSIGNOUT, RequestMethod.POST.toString(), true))
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .deleteCookies("SESSION")
        .deleteCookies("JSESSIONID")
        .addLogoutHandler(new CustomLogoutHandler(jwtTokenProvider, userRepository))
        .logoutSuccessHandler(new CustomLogoutSuccessHandler())
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(new AuthenticationEntryPointHandler())
        .accessDeniedHandler(new CustomAccessDeniedHandler());
  }
}
