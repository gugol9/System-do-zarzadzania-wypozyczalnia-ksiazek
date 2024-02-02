package com.KamilMarkowski.library;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .accessDeniedPage("/error/403") // Przekierowanie do strony błędu 403
                .and()
                .authorizeRequests()
                .antMatchers( "/login", "/public").permitAll() // Dostęp dla wszystkich do tych ścieżek
                .antMatchers("/departments/delete/**").hasAuthority("administrator")
                .antMatchers("/departments/edit/**").hasAuthority("administrator")
                .antMatchers("/departments/create/add").hasAuthority("administrator")
                .antMatchers("/register").hasAuthority("administrator")
                //.antMatchers("/employees/list").hasAnyAuthority("chief", "manager") // dostęp do /books/list tylko dla roli chief lub manager
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/books/list", true) // przekierowanie po udanym zalogowaniu
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .csrf().disable(); // Wyłącz CSRF (tylko do celów deweloperskich) WYMAGANE DO DOADAANI Z HASLEM UZYTKOWNIKOW
    }




/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register").permitAll() // Dostęp dla wszystkich do ścieżki /register
                .antMatchers("/login").permitAll() // Dostęp dla wszystkich do ścieżki /login
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") // Strona logowania
                .defaultSuccessUrl("/") // Po poprawnym zalogowaniu przekieruj na stronę główną
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .csrf().disable(); // Wyłącz CSRF (tylko do celów deweloperskich)
    }

 */

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/error/403");
        };
    }
}