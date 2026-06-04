import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/company_db";
        String user = "root";
        String password = "root";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
                    DriverManager.getConnection(url, user, password);

            System.out.println("Database Connected Successfully");

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}