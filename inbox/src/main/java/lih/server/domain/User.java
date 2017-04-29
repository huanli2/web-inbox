package lih.server.domain;

/**
 * Created by huanli on 28/04/2017.
 */


public class User {

    //用户唯一标示
    private String userName;

    private String password;

    public User() {

    }

    public User(String username) {
        this.userName = username;
    }

    public User(String userName, String password) {
        this(userName);
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
