package lih.server.domain;

/**
 * Created by huanli on 29/04/2017.
 */
public class UserContact {

    private String username;

    private boolean isContact;

    public UserContact() {}

    public UserContact(String username, boolean isContact) {
        this.username = username;
        this.isContact = isContact;
    }

    public boolean isContact() {
        return isContact;
    }

    public void setContact(boolean contact) {
        isContact = contact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
