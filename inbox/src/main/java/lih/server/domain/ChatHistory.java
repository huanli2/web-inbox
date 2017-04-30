package lih.server.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huanli on 30/04/2017.
 */
public class ChatHistory {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

    private String sender;

    private String receiver;

    private String content;

    private Date sendDate;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getSenderDateString() {

        return sdf.format(this.sendDate);
    }
}
