package service;

import com.milanwhite.springbootuserservice.api.UserIn;
import com.milanwhite.springbootuserservice.api.UserOut;

import java.util.HashMap;
import java.util.Map;

import java.util.NoSuchElementException;

public class UserService {

    private final Map<Long, UserRow> store = new HashMap<>();

    private long curr = 1;
    private long nextId() { return curr++; }

    public UserOut create(UserIn in) {
        if (in == null || blank(in.getName()) || blank(in.getEmail()) || blank(in.getPassword()))
            throw new IllegalArgumentException("invalid input");

        long id = nextId();
        store.put(id, new UserRow(id, in.getName(), in.getEmail(), hash(in.getPassword())));
        return new UserOut(id, in.getName(), in.getEmail());
    }

    public UserOut get(long id) {
        var row = store.get(id);
        if (row == null) {
            throw new NoSuchElementException("User not found");
        }

        return new UserOut(row.id, row.name, row.email);
    }

    private static boolean blank(String s){ return s == null || s.isBlank(); }
    private static String hash(String p){ return "PLAIN:" + p; }

    private static final class UserRow {
        final long id;
        final String name;
        final String email;
        final String passwordHash;

        UserRow(long id, String name, String email, String passwordHash){
            this.id=id;
            this.name=name;
            this.email=email;
            this.passwordHash=passwordHash;
        }
    }
}
