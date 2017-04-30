package lih.services.objects;

import java.util.Date;

/**
 * Created by huanli on 30/04/2017.
 */
public class Contacts {

    private long id;

    private String user1Name;

    private String user2Name;

    private boolean isDeleted;

    private Date addedTime;

    private Date deletedTime;

    public Contacts() {}

    public Contacts(String user1Name, String user2Name) {
        this.user1Name = user1Name;
        this.user2Name = user2Name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser1Name() {
        return user1Name;
    }

    public void setUser1Name(String user1Name) {
        this.user1Name = user1Name;
    }

    public String getUser2Name() {
        return user2Name;
    }

    public void setUser2Name(String user2Name) {
        this.user2Name = user2Name;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }

    public Date getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Date deletedTime) {
        this.deletedTime = deletedTime;
    }
}
