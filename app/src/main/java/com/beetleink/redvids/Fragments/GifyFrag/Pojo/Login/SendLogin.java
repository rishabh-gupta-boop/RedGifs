package com.beetleink.redvids.Fragments.GifyFrag.Pojo.Login;

public class SendLogin {
    String username;
    String password;
    String grant_type;
    public SendLogin(String grant_type, String username, String password) {
        this.grant_type = grant_type;
        this.username = username;
        this.password = password;


    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
