package com.pyo.pyostagram.config.auth;

import com.pyo.pyostagram.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails , OAuth2User {


    private User user;

    private Map<String, Object> attributes;

    public PrincipalDetails(User user){
        this.user = user;
    }



    //권한은 1개가 아닐수 있기때문에 Collection되어있음.
    //그리고 ArrayList의 부모는 ~~~ Collection
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> { return user.getRole();});
        return collector;
}


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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


    @Override
    public String getName() {
        return (String)attributes.get("name");
    }
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }


    public PrincipalDetails(User user,Map<String, Object> attributes){
        this.user = user;
    }
}
