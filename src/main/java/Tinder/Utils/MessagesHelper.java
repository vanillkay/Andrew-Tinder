package Tinder.Utils;

import Tinder.DB.DBCPDataSource;
import Tinder.Messages.Messages;
import Tinder.Users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessagesHelper {
    public static void senMessage(String from, String to, String text, LocalDateTime time) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into messages (\"from\", \"to\", text, date) values (?,?,?,?)");
            preparedStatement.setString(1, from);
            preparedStatement.setString(2, to);
            preparedStatement.setString(3, text);
            preparedStatement.setObject(4, time);
            preparedStatement.execute();
        }
    }

    public static List<Messages> getMessages(String from, String to) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select text, \"from\", date from messages where (\"from\" like ? and \"to\" like ?) or (\"from\" like ? and \"to\" like ?) order by date");
            preparedStatement.setString(1, from);
            preparedStatement.setString(2, to);
            preparedStatement.setString(3, to);
            preparedStatement.setString(4, from);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Messages> messages = new ArrayList<>();
            while (resultSet.next()) {
                String text = resultSet.getString("text");
                String fromUser = resultSet.getString("from");
                messages.add(new Messages(fromUser.equals(from), text));
            }
            return messages;
        }
    }
}
