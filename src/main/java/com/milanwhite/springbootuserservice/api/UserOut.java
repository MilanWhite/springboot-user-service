package com.milanwhite.springbootuserservice.api;

public class UserOut {
    private final long id;
    private final String name;
    private final String email;

    public UserOut(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
