//package security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
////   private UserPrincipalDetailsService userPrincipalDetailsService;
////
////   public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
////      this.userPrincipalDetailsService = userPrincipalDetailsService;
////   }
////
////   @Override
////   protected void configure(AuthenticationManagerBuilder auth) {
////      auth.authenticationProvider(authenticationProvider());
////   }
//
//   @Override
//   protected void configure(HttpSecurity http) throws Exception {
//      http
//            // remove csrf and state in session because in jwt we do not need them
//            .csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            // add jwt filters (1. authentication, 2. authorization)
//            //.addFilter(new JwtAuthenticationFilter(authenticationManager()))
//            //.addFilter(new JwtAuthorizationFilter(authenticationManager()))
//            .authorizeRequests()
//            //.antMatchers(HttpMethod.POST, "/login").anonymous()
//            // configure access rules
//            .antMatchers("/*").hasRole("DOCTOR")
//            .anyRequest().authenticated();
//   }
//
////   @Bean
////   DaoAuthenticationProvider authenticationProvider(){
////      DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
////      daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
////      daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
////
////      return daoAuthenticationProvider;
////   }
//
////   @Bean
////   PasswordEncoder passwordEncoder() {
////      return new BCryptPasswordEncoder();
////   }
//}
//
