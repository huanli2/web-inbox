package lih.server.domain;

/**
 * Created by huanli on 28/04/2017.
 */
public class Contact extends User {

    private int unReadCount;

    public Contact() {
        super();
        this.unReadCount = 0;
    }

    public Contact(String userName) {
        super(userName);
        this.unReadCount = 0;
    }

    public Contact(String userName, int unReadCount) {
        super(userName);
        this.unReadCount = unReadCount;
    }

    public int getUnReadCount() {
        return this.unReadCount;
    }
}
