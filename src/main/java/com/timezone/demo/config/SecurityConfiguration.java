package com.timezone.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth
               .inMemoryAuthentication()

               .withUser("cc")
               .password(passwordEncoder().encode("pass"))
               .roles("ADMIN").authorities("ACCESS_PROCESSFINDFORM")

               .and()

               .withUser("grant")
               .password(passwordEncoder().encode("pass"))
               .roles("USER").authorities("ACCESS_PROCESSFINDFORM");



    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                /*----------permit all users to view the home page-------*/
                .antMatchers("/index.html").permitAll()
                /*------protect all folders and their contents*/
                .antMatchers( "/workers/**", "/coworkers/**", "/clients/**", "/fragments/**").authenticated()
                /*----------protect methods inside your controllers----------*/
                .antMatchers("/api/public/processFindForm").authenticated()
                .antMatchers("/api/public/users").hasRole("ADMIN")
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
