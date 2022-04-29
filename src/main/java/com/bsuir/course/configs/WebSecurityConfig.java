package com.bsuir.course.configs;

import com.bsuir.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/cars").permitAll()
                .antMatchers("/cars/filter").permitAll()
                .antMatchers("/cars/search").permitAll()
                .regexMatchers("/cars/(\\d+)").permitAll()
                .regexMatchers("/cars/(\\d+)\\?idPhone=(\\d+)\\-(\\d+)\\-(\\d+)").permitAll()
                .regexMatchers("/cars/(\\d+)/orders\\?").hasAnyRole("ADMIN", "BOOKER")
                .antMatchers("/servicerating").permitAll()
                .antMatchers("/serviceentries/add").permitAll()
                .antMatchers("/orders").hasAnyRole("ADMIN", "BOOKER")
                .antMatchers("/testdriveEntries").hasAnyRole("ADMIN", "BOOKER")
                .antMatchers("/serviceentries").hasAnyRole("ADMIN", "BOOKER")
                .antMatchers("/feedbacks").hasAnyRole("ADMIN", "BOOKER")
                .antMatchers("/cars/**").hasRole("ADMIN")
                .antMatchers("/orders/**").hasRole("ADMIN")
                .antMatchers("/testdriveEntries/**").hasRole("ADMIN")
                .antMatchers("/serviceentries/**").hasAnyRole("ADMIN")

//                .antMatchers("/orders/{id}").hasAnyRole("ADMIN", "BOOKER")
//
//                .antMatchers("/testdriveEntries/{id}").hasAnyRole("ADMIN", "BOOKER")
//
//                .antMatchers("/serviceentries/{id}").hasAnyRole("ADMIN", "BOOKER")
//                .antMatchers("/cars/{id}/orders").hasAnyRole("ADMIN", "BOOKER")


                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}
