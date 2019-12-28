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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@Slf4j
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login.html", "/*", "/index.html", "/app/**", "/css/**", "/js/**").permitAll()
                .and().authorizeRequests().antMatchers("/api/registration**").permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().formLogin().permitAll()
                .loginProcessingUrl("/login")
                .loginPage("/#/login")
                .defaultSuccessUrl("/#/id")
                .failureHandler(authFailHandler())
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .permitAll();
    }

    private AuthenticationFailureHandler authFailHandler() {
        return (request, response, exception) -> {
            response.getWriter().println("Invalid email or password");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        };
    }

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth, SimpleUserDetailsService userDetailsService, BCryptPasswordEncoder encoder)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }

    @Bean
    public SimpleGrantedAuthority authority() {
        return new SimpleGrantedAuthority("USER");
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(5);
    }

}
