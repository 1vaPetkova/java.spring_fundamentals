package com.example.java_spring_fund_lab_01.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public ApplicationSecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //  with this line we allow access to all static resources
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                //  the next line allows access to the home page, login page and registration page to everyone
                .antMatchers("/", "/users/login", "/users/register").permitAll()
                //  next we forbid all other pages for unauthenticated users
                .antMatchers("/**").authenticated()
                .and()
                //  configure login with HTML login form with two input fields
                .formLogin()
                //  our login page is located at http://<<serveraddress>>:<port>/users/login
                .loginPage("/users/login")
                //  this is the name of the <input...> in the login form where user enters their username/email
                //  the value of this input will be presented to our User details service
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                //  this is the name of the <input...> in the login form where user enters their password
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                //  the place where we should go in case that the login is successful
                .defaultSuccessUrl("/")
                //  the place where we should go in case that the login is not successful
                .failureForwardUrl("/users/login-error")
                .and()
                .logout()
                //  this is the URL which spring will implement for me and will log the user out
                .logoutUrl("/users/logout")
                //  where to go after the logout
                .logoutSuccessUrl("/")
                // remove the session from the server
                .invalidateHttpSession(true)
                //  delete the cookie that references my session
                .deleteCookies("JSESSIONID")        ;


        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //This gives spring two important components:
        //1. Our user details that translates usernames/emails, phone numbers, etc./ to UserDetails
        //2. Password encoder - the component that can decide if the user password matches
        auth.userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder);
        super.configure(auth);
    }
}
