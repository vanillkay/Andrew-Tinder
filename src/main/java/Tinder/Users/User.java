package Tinder.Users;

import java.util.Objects;

public class User {
    public final String ID;
    public final String NAME;
    public final String AVATAR_URI;
    public final String EMAIL;
    public final String PASSWORD;
    public final String JOB;
    public boolean isLiked = false;

    public String getNAME() {
        return NAME;
    }

    public String getAVATAR_URI() {
        return AVATAR_URI;
    }

    public String getJOB() {
        return JOB;
    }

    public User(String id, String name, String avatar_uri, String email, String password, String job) {
        ID = id;
        NAME = name;
        AVATAR_URI = avatar_uri;
        EMAIL = email;
        PASSWORD = password;
        JOB = job;
    }

    public String getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + ID + '\'' +
                ", NAME='" + NAME + '\'' +
                ", AVATAR_URI='" + AVATAR_URI + '\'' +
                ", isLiked=" + isLiked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(ID, user.ID) && Objects.equals(NAME, user.NAME) && Objects.equals(AVATAR_URI, user.AVATAR_URI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, NAME, AVATAR_URI);
    }
}
