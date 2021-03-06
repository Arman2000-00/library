package am.tt.library.config;

import am.tt.library.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


  private final SecurityService securityService;


  private final PasswordEncoder passwordEncoder;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(securityService)
        .passwordEncoder(passwordEncoder);

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .formLogin()
        .loginPage("/sign-in")
        .loginProcessingUrl("/perform_login")
        .defaultSuccessUrl("/successLogin")
        .and()
        .authorizeRequests()
        .antMatchers("/").authenticated()
        .antMatchers("/user").hasAnyAuthority("ADMIN", "USER")
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/");
  }

  @Override
  public void configure(WebSecurity webSecurity) {
    webSecurity.ignoring().antMatchers("/resources/**");
    webSecurity.ignoring().antMatchers("/css/**");
    webSecurity.ignoring().antMatchers("/images/**");
    webSecurity.ignoring().antMatchers("/js/**");
    webSecurity.ignoring().antMatchers("/assets/**");
    webSecurity.ignoring().antMatchers("/plugins/**");
    webSecurity.ignoring().antMatchers("/pages/**");
    webSecurity.ignoring().antMatchers("/web/**");
  }

}
