package android.g6.cricspot;

public class Player {

    private String name;
    private String userName;
    private String password;
    private String age;
    private String phone;

    public Player() {
    }

    public Player(String name, String userName, String password, String age, String phone) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
