package lih.services.impl;

import lih.services.mapper.UserMapper;
import lih.services.models.UserWithSalt;
import lih.services.objects.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by huanli on 29/04/2017.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("check for username: {}", username);

        Users user = mapper.getUser(username);

        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> sets = new HashSet<GrantedAuthority>();
        sets.add(new SimpleGrantedAuthority("USER"));
        return new UserWithSalt(user.getUsername(), user.getSalt(), user.getPassword(), sets);
    }
}
