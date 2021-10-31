package com.example.reboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.reboot.security.AppRole.ATM;
import static org.springframework.security.web.csrf.CookieCsrfTokenRepository.withHttpOnlyFalse;

@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.enable.csrf}")
    boolean isCsrfEnabled;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if (isCsrfEnabled) {
            http
                    .csrf()
                    .csrfTokenRepository(withHttpOnlyFalse());
        } else {
            http
                    .csrf()
                    .disable();
        }

        http
                .authorizeRequests()
                .antMatchers("/api/**").hasAnyRole(ATM.name())
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails atm = User.builder()
                .username("atm")
                .password("{bcrypt}$2a$12$HmJFWyvk022Jaw83tno6w.VSme1ITAb4OodEgVWoIG0vFolQc7upW")
                .roles(ATM.name())
                .build();
        return new InMemoryUserDetailsManager(atm);
    }
}
