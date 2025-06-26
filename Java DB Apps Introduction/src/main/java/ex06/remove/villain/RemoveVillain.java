package ex06.remove.villain;

import java.sql.*;
import java.util.Scanner;

public class RemoveVillain {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int villainId = Integer.parseInt(sc.nextLine());

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "1234");

        String villainName = findVillain(connection, villainId);
        if (villainName == null) {
            System.out.println("No such villain was found");
        }
        else {
            connection.setAutoCommit(false);

            try {
                int releasedMinionsCount =  deleteConnectedEntities(connection, villainId);
                deleteVillain(connection, villainId);
                connection.commit();

                System.out.printf("%s was deleted%n", villainName);
                System.out.printf("%d minions released%n", releasedMinionsCount);
            } catch (Exception e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }

        }
    }

    private static String findVillain(Connection connection, int villainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT v.name FROM villains v WHERE v.id = ?");
        statement.setInt(1, villainId);

        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
            return null;
        }

        return resultSet.getString(1);
    }

    private static int deleteConnectedEntities(Connection connection, int villainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM minions_villains mv where mv.villain_id = ?");
        statement.setInt(1, villainId);

        return statement.executeUpdate();
    }

    private static void deleteVillain(Connection connection, int villainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM villains v where v.id = ?");
        statement.setInt(1, villainId);

        statement.executeUpdate();
    }


}
