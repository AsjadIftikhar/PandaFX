package pandaFX.controller;
import java.sql.*;

public class Connection_DataBase {
    public static final String DB_NAME = "pandaFxDb.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/kai/IdeaProjects/pandaFx/src/pandaFX/" + DB_NAME;
    public Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch(SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if(conn != null) {
                conn.close();
            }
        } catch(SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }


}
