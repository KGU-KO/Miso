package Application.Domain;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -6365743330089212031L;
    //private static final long serialVersionUID = 1L;

    String id;
    String pwd;

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void clearUser() {
        id = null;
        pwd = null;
    }
}