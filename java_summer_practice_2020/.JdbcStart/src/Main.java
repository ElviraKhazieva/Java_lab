import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/digital_store";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Qwer1234";

    public static void main(String[] args) throws SQLException {
        SimpleDataSource dataSource = new SimpleDataSource();
        Connection connection = dataSource.openConnection(URL, USER, PASSWORD);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from consumer");

        while(resultSet.next()) {
            System.out.println("ID " + resultSet.getInt("id"));
            System.out.println("First Name " + resultSet.getString("first_name"));
            System.out.println("Last Name " + resultSet.getString("last_name"));
            System.out.println("Age " + resultSet.getString("age"));
            System.out.println("Product ID " + resultSet.getInt("product_id"));
        }
        System.out.println("---------------------------");

        resultSet.close();

        resultSet = statement.executeQuery("select c.id as c_id,  *\n" +
                "from consumer c\n" +
                "         full outer join product p on c.product_id = p.id;");

        while (resultSet.next()) {
            System.out.println("ID " + resultSet.getInt("c_id"));
        }
        connection.close();
    }
}
