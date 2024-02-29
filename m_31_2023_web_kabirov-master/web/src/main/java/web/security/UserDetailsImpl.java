package web.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import web.dao.model.UserModel;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final UserModel userModel;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userModel.getUserRoles().stream()
                .map(GrantedAuthorityImpl::new)
                .collect(Collectors.toSet());
    }

    public String getId() {
        return userModel.getId();
    }

    @Override
    public String getPassword() {
        return userModel.getPass();
    }

    @Override
    public String getUsername() {
        return userModel.getFirstName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
