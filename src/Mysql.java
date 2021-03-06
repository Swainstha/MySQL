import java.sql.*;
import java.util.Scanner;

public class Mysql {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Student";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        try{
            //STEP 2: Register JDBC driver. dynamically load the driver's class file into memory
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the last name whose data you want to query");
            String data = scanner.nextLine();

            /** Query without index **/
            QuerySimple(conn,data," query without index ");

            /** Query with index on last name **/
            QueryWithIndex(conn, data);


            //STEP 6: Clean-up environment
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }//end main

    public static void QuerySimple(Connection conn, String data, String choice) throws SQLException {

        String sql = "SELECT * FROM student WHERE last=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,data);

        long time1 = System.nanoTime();
        ResultSet rs = stmt.executeQuery();
        long time2 = System.nanoTime();

        System.out.println("The time taken for " + choice + (time2 - time1) + "ns");
        showData(rs);
        rs.close();
        stmt.close();

    }

    public static void QueryWithIndex(Connection conn, String data) throws SQLException {

        String sql = "CREATE INDEX last_index ON student (last)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        QuerySimple(conn, data," for query with index ");

        sql = "DROP INDEX last_index ON student";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        stmt.close();

    }

    public static void showData(ResultSet rs) {
        //STEP 5: Extract data from result set
        try{
            while(rs.next()) {
                //Display values
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Name: " + rs.getString("name"));
                System.out.print(", Last: " + rs.getString("last"));
                System.out.println(", Place: " + rs.getString("place"));
            }
        } catch (java.sql.SQLException sql) {
            System.out.println("SQL Exception");
        }
    }

}//end Mysql

