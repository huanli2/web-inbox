package lih.services.objects;

import java.util.Date;

/**
 * Created by huanli on 29/04/2017.
 */
public class Users {
    private long id;

    private String username;

    private String password;

    private String salt;

    private Date addedTime;

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
