package ex02.minions.names;

import java.sql.*;
import java.util.Scanner;

public class GetMinionNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "1234");

        Scanner scanner = new Scanner(System.in);
        int villainId = Integer.parseInt(scanner.nextLine());


        boolean villainExist = printVillain(connection, villainId);
        if (villainExist) {
            printMinions(connection, villainId);
        }


    }

    private static boolean printVillain(Connection connection, int villainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select v.name from villains as v where id = ?");
        statement.setInt(1, villainId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            System.out.printf("Villain: %s%n", name);

            return true;
        }
        else {
            System.out.println("Villain with this ID does not exist");
        }

        return false;
    }

    public static void printMinions(Connection connection, int villainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select distinct m.name, m.age from minions_villains mv join minions m on mv.minion_id = m.id where mv.villain_id = ?");
        statement.setInt(1, villainId);

        int order = 0;
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString(1);
            int age = resultSet.getInt(2);

            System.out.printf("%d. %s %d%n", ++order, name, age);
        }
    }
}
