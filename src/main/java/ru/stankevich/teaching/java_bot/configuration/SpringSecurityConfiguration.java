package ru.stankevich.teaching.java_bot.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.stankevich.teaching.java_bot.model.UserAuthority;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SpringSecurityConfiguration.class);
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        log.info("Password user1: {}", encoder.encode("User1"));
        log.info("Password Admin: {}", encoder.encode("Admin"));
        return encoder;
    }

    @Primary
    @Bean
    public PasswordEncoder passwordEncoderSimple() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(rawPassword.toString());
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/jokes").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/jokes").hasAuthority(UserAuthority.PLACE_ORDERS.getAuthority())
                        .requestMatchers(HttpMethod.PUT, "/api/jokes/**").hasAuthority(UserAuthority.MANAGE_ORDERS.getAuthority())
                        .requestMatchers(HttpMethod.DELETE, "/api/jokes/**").hasAuthority(UserAuthority.MANAGE_ORDERS.getAuthority())
                        .requestMatchers("/api/users/**").hasAuthority(UserAuthority.FULL.getAuthority())
                        .requestMatchers("/error", "/login", "/registration").permitAll()
                        .requestMatchers("/api/users/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .logout(LogoutConfigurer::permitAll)
                .csrf(CsrfConfigurer::disable)
                .build();
    }

}