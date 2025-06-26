package ex03.villains.names;

import java.sql.*;

public class GetVillainNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "1234");
        PreparedStatement statement = connection.prepareStatement("SELECT v.name, count(*) from villains v join minions_villains as mv on v.id = mv.villain_id group by v.id having count(*) > 15 order by count(*) desc");

        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()) {
            String name = resultSet.getString(1);
            int count = resultSet.getInt(2);

            System.out.printf("%s %d%n", name, count);
        }
    }
}
