package Tinder.Users;

import Tinder.DB.DBCPDataSource;
import Tinder.DB.Statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class UserService implements DAO {


    @Override
    public List<User> getAll() throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.GET_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String avatar_uri = resultSet.getString("avatar_uri");
                String job = resultSet.getString("job");
                users.add(new User(id, name, avatar_uri, "", "", job, LocalDate.now()));
            }
            return users;
        }
    }

    public Optional<User> getUserForLike(String UUID) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select id, name, avatar_uri " +
                    "from users " +
                    "where id not in " +
                    "(select l.to from likes l where l.from like ?)" +
                    "and id not like ?");
            preparedStatement.setString(1, UUID);
            preparedStatement.setString(2, UUID);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String avatar_uri = resultSet.getString("avatar_uri");
                users.add(new User(id, name, avatar_uri, "", "", "", LocalDate.now()));
            }
            return users.stream().findFirst();
        }
    }


    @Override
    public Optional<User> getUserByID(String UUID) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select id, name, avatar_uri, job " +
                    "from users where id=?");
            preparedStatement.setString(1, UUID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) return Optional.empty();
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String avatar_uri = resultSet.getString("avatar_uri");
            String job = resultSet.getString("job");
            User user = new User(id, name, avatar_uri, "", "", job, LocalDate.now());
            return Optional.of(user);
        }
    }

    public Optional<User> getUserByEmail(String email) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select * from users where email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) return Optional.empty();
            User user = new User(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("avatar_uri"),
                    email,
                    resultSet.getString("password"), "", LocalDate.now());
            return Optional.of(user);
        }
    }

    public List<User> getLikedUsersByID(String UUID) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select id, name, avatar_uri, job, last_login " +
                    "from users " +
                    "join likes l on users.id = l.\"to\" " +
                    "where l.\"from\" like ? and l.\"like\" = 't'");
            preparedStatement.setString(1, UUID);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String avatar_uri = resultSet.getString("avatar_uri");
                String job = resultSet.getString("job");
                LocalDate lastLogin = resultSet.getObject("last_login", LocalDate.class);
                users.add(new User(id, name, avatar_uri, "", "", job, lastLogin));
            }
            return users;
        }
    }

    @Override
    public void addUser(User user) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "insert into users " +
                    "(id, name, avatar_uri, email, password) " +
                    "values (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.ID);
            preparedStatement.setString(2, user.NAME);
            preparedStatement.setString(3, user.AVATAR_URI);
            preparedStatement.setString(4, user.EMAIL);
            preparedStatement.setString(5, user.PASSWORD);
            preparedStatement.execute();
        }
    }

    public void setLike(String from, String to, boolean isLike) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "insert into likes values (?,?,?)");
            preparedStatement.setString(1, from);
            preparedStatement.setString(2, to);
            preparedStatement.setBoolean(3, isLike);
            preparedStatement.execute();
        }
    }

    public void updateUserLastLogin(String UUID, LocalDate login) throws SQLException {
        try(Connection connection = DBCPDataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("update users set last_login=? where id=?");
            preparedStatement.setObject(1, login);
            preparedStatement.setString(2, UUID);
            preparedStatement.execute();
        }
    }

    @Override
    public void updateUser(User user) {

//        users.put(user.ID, user);
    }

}
