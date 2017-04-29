package lih.services;

import lih.server.domain.User;
import lih.server.domain.UserContact;

import java.util.List;

/**
 * Created by huanli on 28/04/2017.
 */
public interface UserService {

    void addUser(User user);

    List<UserContact> searchUsers(String username);
}
