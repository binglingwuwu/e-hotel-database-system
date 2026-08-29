package entity;

import java.util.Objects;

public class Log {

    private String Userid;
    private String Password;
    private String Role;

    public Log(String userid, String password, String role) {
        Userid = userid;
        Password = password;
        Role = role;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(getUserid(), log.getUserid()) && Objects.equals(getPassword(), log.getPassword()) && Objects.equals(getRole(), log.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserid(), getPassword(), getRole());
    }

    @Override
    public String toString() {
        return "Log{" +
                "Userid='" + Userid + '\'' +
                ", Password='" + Password + '\'' +
                ", Role='" + Role + '\'' +
                '}';
    }
}
