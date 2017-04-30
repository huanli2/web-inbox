package lih.services.impl;

import lih.server.domain.ChatHistory;
import lih.services.mapper.MessageMapper;
import lih.services.objects.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huanli on 30/04/2017.
 */

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper mapper;

    @Override
    public void sendMessage(String sender, String receiver, String content) {

        mapper.addMessage(sender, receiver, content);
    }

    @Override
    public void readAllMessages(String sender, String receiver) {

        mapper.readMessages(sender, receiver);
    }

    @Override
    public List<ChatHistory> getChatHistory(String user1, String user2) {

        List<ChatHistory> fromSender = mapper.getChatHistory(user1, user2);
        List<ChatHistory> fromReceiver = mapper.getChatHistory(user2, user1);

        List<ChatHistory> result = new ArrayList<ChatHistory>();
        int rSize = fromReceiver.size();
        int sSize = fromSender.size();
        int sIndex = 0;
        int rIndex = 0;

        while (sIndex < sSize && rIndex < rSize) {

            Date rDate = fromReceiver.get(rIndex).getSendDate();
            Date sDate = fromSender.get(sIndex).getSendDate();

            if (rDate.before(sDate)) {

                result.add(fromReceiver.get(rIndex));
                ++rIndex;

            } else {

                result.add(fromSender.get(sIndex));
                ++sIndex;
            }
        }

        if (sIndex < sSize) {
            result.addAll(fromSender.subList(sIndex, sSize));
        }

        if (rIndex < rSize) {
            result.addAll(fromReceiver.subList(rIndex, rSize));
        }

        return result;
    }
}
