package lih.services.objects;


import lih.server.domain.ChatHistory;

import java.util.List;

/**
 * Created by huanli on 30/04/2017.
 */
public interface MessageService {

    void sendMessage(String sender, String receiver, String content);

    void readAllMessages(String sender, String receiver);

    List<ChatHistory> getChatHistory(String user1, String user2);
}
