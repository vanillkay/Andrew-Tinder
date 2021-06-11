package Tinder.Users;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO {
    List<User> getAll() throws SQLException;

    Optional<User> getUserByID(String UUID) throws SQLException;

    void addUser(User user) throws SQLException;

    void updateUser( User user);
}
