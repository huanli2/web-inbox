package lih.services.mapper;

import lih.server.domain.Contact;
import lih.services.objects.Contacts;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by huanli on 30/04/2017.
 */
@Mapper
public interface ContactMapper {

    @Insert("insert into contacts(user1_name, user2_name) " +
            "values(#{user1Name}, #{user2Name})")
    void addContact(Contacts contact);


    @Select("select c.user2_name as userName, count(m.id) as unReadCount from contacts c " +
            "left join messages m on m.sender = c.user2_name and m.is_read = false " +
            "where c.user1_name = #{username} and c.is_deleted = false " +
            "group by c.user2_name")
    List<Contact> getContactsUnreadNum(@Param("username") String username);

    @Update("update contacts set is_deleted = true, deleted_time = sysdate()" +
            " where user1_name = #{user1} and user2_name = #{user2}")
    int deleteContacts(@Param("user1") String user1, @Param("user2") String user2);
}

