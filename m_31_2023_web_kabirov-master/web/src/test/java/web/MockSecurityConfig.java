package web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import web.dao.model.UserModel;
import web.security.UserDetailsImpl;
import web.security.UserRole;

import java.util.List;
import java.util.Set;

@Configuration
public class MockSecurityConfig {
    @Bean
    public UserDetailsService getUserDetailService() {
        return username -> new UserDetailsImpl(
                new UserModel(
                        "1",
                        "A",
                        "B",
                        "email",
                        "pass",
                        List.of(),
                        Set.of(UserRole.USER)
                )
        );
    }
}
