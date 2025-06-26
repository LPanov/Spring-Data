package ex04.add.minion;

import java.sql.*;
import java.util.Scanner;

public class AddMinion {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "1234");

        Scanner scanner = new Scanner(System.in);
        String[] minionData = scanner.nextLine().split("\\s+");
        String minionName = minionData[1];
        int minionAge = Integer.parseInt(minionData[2]);
        String townName = minionData[3];

        String[] villainData = scanner.nextLine().split("\\s+");
        String villainName = villainData[1];

        int townId = ensureTown(connection, townName);
        int villainId = ensureVillain(connection, villainName);
        int minionId = createMInion(connection, minionName, minionAge, townId);
        connectMinionAndVillain(connection, villainId, minionId);

        System.out.printf("Successfully added %s to be minion of %s%n", minionName, villainName);
    }

    private static  int ensureTown(Connection connection, String name) throws SQLException {
        PreparedStatement selectStatement = connection.prepareStatement("SELECT t.id FROM towns t WHERE t.name = ?");
        selectStatement.setString(1,name);

        ResultSet resultSet = selectStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }

        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO towns (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        insertStatement.setString(1, name);

        insertStatement.executeUpdate();

        ResultSet generatedKeys = insertStatement.getGeneratedKeys();
        if (!generatedKeys.next()) {
            throw new IllegalStateException("Could not access generated keys");
        }

        System.out.printf("Town %s was added to the database.%n", name);
        return generatedKeys.getInt(1);
    }

    private static int ensureVillain(Connection connection, String name) throws SQLException {
        PreparedStatement selectStatement = connection.prepareStatement("SELECT v.id FROM villains v WHERE v.name = ?");
        selectStatement.setString(1,name);

        ResultSet resultSet = selectStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }

        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO villains(name, evilness_factor) VALUE (?, 'evil')",  Statement.RETURN_GENERATED_KEYS);
        insertStatement.setString(1,name);

        insertStatement.executeUpdate();

        ResultSet generatedKeys = insertStatement.getGeneratedKeys();
        if (!generatedKeys.next()) {
            throw new IllegalStateException("Could not access generated keys for villain");
        }

        System.out.printf("Villain %s was added to the database.%n", name);
        return generatedKeys.getInt(1);
    }

    private static int createMInion(Connection connection, String name, int age, int townId) throws SQLException {
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO minions(name, age, town_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        insertStatement.setString(1,name);
        insertStatement.setInt(2,age);
        insertStatement.setInt(3,townId);

        insertStatement.executeUpdate();

        ResultSet generatedKeys = insertStatement.getGeneratedKeys();
        if (!generatedKeys.next()) {
            throw new IllegalStateException("Could not access generated keys for minions");
        }

        return generatedKeys.getInt(1);
    }

    private static void connectMinionAndVillain(Connection connection, int villainId, int minionId) throws SQLException {
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO minions_villains(minion_id, villain_id) VALUES (?, ?) ");
        insertStatement.setInt(1, minionId);
        insertStatement.setInt(2, villainId);

        insertStatement.executeUpdate();
    }
}
