package web.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class GrantedAuthorityImpl implements GrantedAuthority {
    private final UserRole role;

    @Override
    public String getAuthority() {
        return "ROLE_" + role.name();
    }
}
