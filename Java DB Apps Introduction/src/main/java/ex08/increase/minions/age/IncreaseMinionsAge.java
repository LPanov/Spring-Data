package ex08.increase.minions.age;

import java.sql.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int[] minionIds = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "1234");

        updateMinions(connection, minionIds);
        printMinions(connection);

    }

    private static void updateMinions(Connection connection, int[] minionsIds) throws SQLException {
        String idParameters = String.join(",", Collections.nCopies(minionsIds.length, "?"));
        PreparedStatement statement = connection.prepareStatement(String.format("UPDATE minions m SET m.age = m.age + 1, m.name = lower(m.name) WHERE m.id in (%s)", idParameters));

        for (int i = 0; i < minionsIds.length; i++) {
            statement.setInt(i + 1, minionsIds[i]);
        }

        statement.executeUpdate();
    }

    private static void printMinions(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT name, age FROM minions");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString(1);
            int age = resultSet.getInt(2);

            System.out.printf("%s %d%n", name, age);
        }
    }
}
