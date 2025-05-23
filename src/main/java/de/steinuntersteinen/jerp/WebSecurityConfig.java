package de.steinuntersteinen.jerp;

import de.steinuntersteinen.jerp.core.Persistence.DataBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Objects;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers
                            .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/registration","/close","/styles/*", "/files/*", "/upload-dir/*").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        String userNameloaded = DataBase.loadUser().getFirstName();
        String userName = userNameloaded == null ? "Dummy" : userNameloaded;
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username(userName)
                        .password(userName)
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}