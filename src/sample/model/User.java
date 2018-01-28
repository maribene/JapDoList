package sample.model;

/**
 * Created by mariabenetgarcia on 20.01.2018.
 */
public class User {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String country;
    private String gender;

    public User(){

    }
    public User(String firstname, String lastname, String username, String password, String country, String gender){
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.country = country;
        this.gender = gender;
    }
    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getCountry(){
        return country;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }

}
