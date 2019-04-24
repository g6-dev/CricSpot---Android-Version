package android.g6.cricspot;

public class Player {

    private String name;
    private String userName;
    private String password;
    private String age;
    private String phone;
    private String team;

    public Player() {
    }

    public Player(String name, String userName, String password, String age, String phone, String team) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.phone = phone;
        this.team = team;
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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player : {"+ this.name + ", " + this.userName + ", " + this.password + ", " +
                this.age + ", " + this.phone + "}";
    }
}
