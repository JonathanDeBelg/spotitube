package nl.han.dea;

public class User {
    private String name;
    private String token;
    private String password;

    public User() {
    }

    public User(String name, String token, String password) {
        this.name = name;
        this.token = token;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
