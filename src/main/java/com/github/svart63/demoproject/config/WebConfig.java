package com.github.svart63.demoproject.config;

import com.github.svart63.demoproject.service.SimpleUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Slf4j
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
                .csrf().disable()
                .formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/#/login")
                .defaultSuccessUrl("/profile")
                .failureUrl("/error")
                .loginPage("/#/login")
                .failureHandler((request, response, exception) -> {
                    response.getWriter().println("Invalid credentials");
                    response.setStatus(400);
                    log.error("Login failed", exception);
                })
                .permitAll()
                .and().authorizeRequests()
                .mvcMatchers("/login**", "/registration**", "/", "/css/**", "/js/**", "/index.html", "/app/**")
                .permitAll()
                .mvcMatchers("/profile**")
                .authenticated();

    }

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth, SimpleUserDetailsService userDetailsService, BCryptPasswordEncoder encoder)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }

    @Bean
    public SimpleGrantedAuthority authority() {
        return new SimpleGrantedAuthority("user");
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(5);
    }

}
