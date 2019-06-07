package com.timezone.demo.config;

import com.timezone.demo.services.UserPrincipalDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserPrincipalDetailsService userPrincipalDetailsService;

    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
       auth.authenticationProvider(authenticationProvider());
        //----------IN MEMORY AUTHENTICATION------------------//
              /* .inMemoryAuthentication()
               .withUser("cc")
               .password(passwordEncoder().encode("pass"))
               .roles("ADMIN").authorities("ACCESS_PROCESSFINDFORM")
               .and()
               .withUser("grant")
               .password(passwordEncoder().encode("pass"))
               .roles("USER").authorities("ACCESS_PROCESSFINDFORM");*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                /*----------permit all users to view the home page-------*/
                .antMatchers("/index.html",
                             "/js/**",
                             "/css/**",
                             "/images/**",
                             "/webjars/**",
                             "/login.html").permitAll()
                /*------protect all folders and their pages*/
                .antMatchers( "/workers/**", "/coworkers/**", "/clients/**", "/fragments/**").authenticated()
                /*----------protect methods inside your controllers----------*/
               /* .antMatchers("/api/public/processFindForm").authenticated()*/
             /*   .antMatchers("/api/public/users").hasRole("ADMIN")*/
                .and()
                //Replace basic with form based Auth
                /*.httpBasic();*/
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                 .and()
                //TODO fix csrf disable
                //Disables in order to perform CRUD operations
                 .csrf().disable();


    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
