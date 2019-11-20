package Model;


public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    private int rg;
    private String Password;
    private int userType;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public User(int id, String name, String email, String phone, int rg, String Password, int userType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.rg = rg;
        this.Password = Password;
        this.userType = userType;
    }

    public User() {
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
        
    @Override
    public String toString(){
        return this.getName();
    }
}
