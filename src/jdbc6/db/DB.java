package jdbc6.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            if (conn == null) {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }
        } catch (SQLException e) {
            throw new DbExeption(e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbExeption(e.getMessage());
            }
        }

    }

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbExeption(e.getMessage());
        }
    }

    public static void closeStatement(Statement st) {

        // quer dizer que esta instanciado
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbExeption(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {

        // quer dizer que esta instanciado
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbExeption(e.getMessage());
            }
        }
    }
}
