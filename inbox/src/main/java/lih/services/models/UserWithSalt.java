package lih.services.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by huanli on 29/04/2017.
 */
public class UserWithSalt extends User {

    private String salt;

    public UserWithSalt(String username, String salt, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.salt = salt;
    }

    public String getSalt() {

        return this.salt;
    }
}
