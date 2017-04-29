package lih.services.impl;

import lih.server.domain.User;
import lih.server.domain.UserContact;
import lih.services.UserService;
import lih.services.mapper.UserMapper;
import lih.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        lih.services.objects.User dbUser = new lih.services.objects.User();
        dbUser.setUsername(user.getUserName());
        String salt = utils.generateSalt();
        dbUser.setSalt(salt);
        dbUser.setPassword(shaPasswordEncoder.encodePassword(user.getPassword(), salt));

        mapper.addUser(dbUser);
    }

    @Override
    public List<UserContact> searchUsers(String username) {
        //TODO
        List<UserContact> users = new ArrayList<UserContact>();
        users.add(new UserContact("test", false));
        users.add(new UserContact("test", true));
        return users;
    }

}
