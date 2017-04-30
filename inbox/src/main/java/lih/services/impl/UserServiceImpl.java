package lih.services.impl;

import lih.server.domain.User;
import lih.server.domain.UserContact;
import lih.services.UserService;
import lih.services.mapper.UserMapper;
import lih.services.objects.Users;
import lih.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huanli on 28/04/2017.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private Utils utils;

    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @Override
    public void addUser(User user) {

        Users dbUser = new Users();
        dbUser.setUsername(user.getUserName());
        String salt = utils.generateSalt();
        dbUser.setSalt(salt);
        dbUser.setPassword(shaPasswordEncoder.encodePassword(user.getPassword(), salt));

        mapper.addUser(dbUser);
    }

    @Override
    public List<UserContact> searchUsers(String input, String username) {

        if (input == null) input = "";

        return mapper.searchUserContacts(input, username);
    }

}
