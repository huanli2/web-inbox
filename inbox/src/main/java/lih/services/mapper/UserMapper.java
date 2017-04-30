package lih.services.mapper;

import lih.server.domain.UserContact;
import lih.services.objects.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by huanli on 29/04/2017.
 */
@Mapper
public interface UserMapper {

    @Insert("insert into users(username, password, salt) " +
            "VALUES(#{username}, #{password}, #{salt})")
    void addUser(Users user);

    @Select("select username, password, salt, added_time as addedTime from users where username = #{userName}")
    Users getUser(String userName);

    @Select("select u1.username,  (case when c.id is null then 0 else 1 END) as isContact from " +
            "    (select username from users where username like concat('%', #{input}, '%') ) u1 " +
            "left join contacts c on c.user2_name = u1.username and c.user1_name = #{username} " +
            "and c.is_deleted = false")
    List<UserContact> searchUserContacts(@Param("input") String input, @Param("username") String username);
}
