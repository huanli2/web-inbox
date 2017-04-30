package lih.services.mapper;

import lih.server.domain.ChatHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by huanli on 30/04/2017.
 */
@Mapper
public interface MessageMapper {

    @Insert("insert into messages(sender, receiver, content) " +
            "VALUES(#{sender}, #{receiver}, #{content})")
    void addMessage(
            @Param("sender") String sender,
            @Param("receiver") String receiver,
            @Param("content") String content);

    @Update("update messages set is_read = true, read_time = sysdate() " +
            "where sender = #{sender} and receiver = #{receiver}")
    void readMessages(@Param("sender")String sender, @Param("receiver") String receiver);

    @Select("select sender, receiver, added_time as sendDate, content from " +
            "messages where sender = #{sender} and receiver = #{receiver} order by added_time")
    List<ChatHistory> getChatHistory(@Param("sender")String sender, @Param("receiver")String receiver);
}
