package com.epam.interview.objects;

public class UserBuilder {

    private User user = new User();

    public UserBuilder setFirstName(String name){
        user.setFirst_name(name);
        return this;
    }

    public UserBuilder setLastName(String last_name){
        user.setLast_name(last_name);
        return this;
    }

    public UserBuilder setEmail(String email){
        user.setEmail(email);
        return this;
    }

    public UserBuilder setAvatar(String avatarPath){
        user.setAvatar(avatarPath);
        return  this;
    }

    public User build(){
        return user;
    }
}
