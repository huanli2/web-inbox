package lih.services.mapper;

import lih.services.objects.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by huanli on 29/04/2017.
 */
@Mapper
public interface UserMapper {

    @Insert("insert into users(username, password, salt) " +
            "VALUES(#{username}, #{password}, #{salt})")
    void addUser(User user);

    @Select("select username, password, salt, added_time as addedTime from users where username = #{userName}")
    User getUser(String userName);
}
