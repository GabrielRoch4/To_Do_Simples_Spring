package com.gabriel.todosimple.security;

import com.gabriel.todosimple.models.enums.ProfileEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class UserSpringSecurity implements UserDetails {

    Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSpringSecurity(Long id, String username, String password, Set<ProfileEnum> profileEnums) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = profileEnums.stream().map(x -> new SimpleGrantedAuthority(x.getDescription()))
                .collect(Collectors.toSet());
    }

    // true pois o sistema não possui expiração de contas
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // true pois o sistema não possui travamento de contas
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // true pois não haverá expiração de credenciais
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Garantir todas contas ativas
    @Override
    public boolean isEnabled() {
        return true;
    }

    // Se a conta é algum tipo de role
    public boolean hasRole(ProfileEnum profileEnum) {
        // Verificar na lista de possui alguma role
        return getAuthorities().contains(new SimpleGrantedAuthority(profileEnum.getDescription()));
    }

}
