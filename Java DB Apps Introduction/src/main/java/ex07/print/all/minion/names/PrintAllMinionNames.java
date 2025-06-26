package ex07.print.all.minion.names;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "1234");

        solution2(connection);

    }

    private static void solution2(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT m.name from minions m", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery();

        int count = 0;
        while (resultSet.next()) {
            count++;
        }

        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                resultSet.absolute(i/2 + 1);
            }
            else {
                resultSet.absolute(count - (i - 1) / 2);
            }

            String minionName = resultSet.getString(1);
            System.out.println(minionName);
        }
    }

    // Easier solution
    private static void getMinionsInMemory(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT m.name from minions m");
        ResultSet resultSet = statement.executeQuery();

        List<String> minionNames = new ArrayList<>();
        while (resultSet.next()) {
            minionNames.add(resultSet.getString(1));
        }

        for (int i = 0; i < minionNames.size(); i++) {
            String next;
            if (i % 2 == 0) {
                next = minionNames.get(i/2);
            }
            else {
                next = minionNames.get(minionNames.size()-(i+1)/2);
            }

            System.out.println(next);
        }
    }
}
