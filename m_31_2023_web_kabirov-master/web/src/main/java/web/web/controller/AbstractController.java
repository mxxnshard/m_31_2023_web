package web.web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import web.security.UserDetailsImpl;

import java.util.Optional;

public abstract class AbstractController {
    protected final static String USER_ID_ATTR = "userId";

    protected Optional<String> getUserId() {
        return Optional.ofNullable(getUser()).map(UserDetailsImpl::getId);
    }

    private UserDetailsImpl getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return (UserDetailsImpl)authentication.getPrincipal();
    }
}
