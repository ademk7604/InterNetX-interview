package com.internetx.security.service;

import com.internetx.domain.Role;
import com.internetx.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 1. CLASS TO IMPLEMENT
 * Security helper class
 * This class uses our user which has been fetched from DB by UserDetailsServiceImpl class
 * load by userName method.
 * This user will be fulfilled with user details. Userdetails are overwritten methods below.
 * At the end we have com.internetx.security.service.UserDetailsImpl object that hat credentials and user details.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private String email;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * @param user to get details
     * @return com.internetx.security.service.UserDetailsImpl ( email + password +authorities )
     */
    public static UserDetailsImpl build(User user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        Role role = user.getRole();

        if (role != null) {
            if (role.isDevelop()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_DEVELOP"));
            }
            if (role.isCctld()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_CCTLD"));
            }
            if (role.isGtld()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_GTLD"));
            }
            if (role.isBilling()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_BILLING"));
            }
            if (role.isRegistry()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_REGISTRY"));
            }
            if (role.isCanReadPurchases()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_PURCHASE_READ"));
            }
            if (role.isCanWritePurchases()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_PURCHASE_WRITE"));
            }
            if (role.isCanWriteSales()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_SALE_WRITE"));
            }
            if (role.isCanExecuteSql()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_SQL"));
            }
            if (role.isAdmin()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
        }
        return new UserDetailsImpl(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    //methods that return true can be changed according to business needs.
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
