package com.hjbello.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hjbello.security.CustomUserDetailsService;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
@EnableTransactionManagement
@EntityScan( basePackages = {"com"} )
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

 @Autowired 
 private UserDetailsService userDetailsService;
 
 @Autowired
 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {    
	 auth.userDetailsService(userDetailsService);
 } 
 

 
 @Override
 protected void configure(HttpSecurity http) throws Exception {
   http.authorizeRequests()
  .antMatchers("/hello").access("hasRole('ROLE_ADMIN')")
  .antMatchers("/hello/home2").access("hasRole('ROLE_ADMIN')")
  .anyRequest().permitAll()
  .and()
    .formLogin().loginPage("/login")
    .usernameParameter("username").passwordParameter("password")
  .and()
    .logout().logoutSuccessUrl("/login?logout") 
   .and()
   .exceptionHandling().accessDeniedPage("/403")
  .and()
    .csrf();
   http.headers().frameOptions().disable();
   http.authorizeRequests().antMatchers("/").permitAll().and()
	.authorizeRequests().antMatchers("/console/**").permitAll();
	http.csrf().disable();
	
 }
 
 @Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
     return new BCryptPasswordEncoder();
    }
}