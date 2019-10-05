package com.epam.interview.objects;

import java.util.Objects;

public class User {

    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

    public  User(){}

    public User(Integer id, String email, String first_name, String last_name, String avatar) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean selfIntegrityCheck(){
        return id != null && !email.contains(null) && !first_name.contains(null) && !last_name.contains(null) && !avatar.contains(null);
    }

    @Override
    public String toString(){
        return first_name + "\n" + last_name + "\n" + email + "\n" + avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                email.equals(user.email) &&
                first_name.equals(user.first_name) &&
                last_name.equals(user.last_name) &&
                avatar.equals(user.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, first_name, last_name, avatar);
    }
}
