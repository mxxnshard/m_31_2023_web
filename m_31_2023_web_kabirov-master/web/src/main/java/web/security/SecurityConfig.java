package web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final static String[] PERMIT_ALL = {"/web/login", "/css/**", "/js/**", "/**"};

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a -> a
                        .requestMatchers(PERMIT_ALL).permitAll()
                        .requestMatchers("/web/login-post").hasAnyRole(UserRole.USER.name())
                )
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/web/login")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .loginProcessingUrl("/web/login-post")
                                .defaultSuccessUrl("/web/index")
                )
                .logout(logout -> logout
                        .logoutUrl("/web/logout")
                        .logoutSuccessUrl("/web/login")

                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
