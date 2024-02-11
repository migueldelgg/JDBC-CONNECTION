package jdbc6.application;

import jdbc6.db.DB;
import jdbc6.db.DbExeption;
import jdbc6.db.DbIntegrityExecption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {
        // Atualizando um salario de um vendedor

        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.getConnection();

            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 1090 WHERE DepartmentId = 1");

            /*int x = 1;
            if (x < 2) {
                throw new SQLException("Fake error.");
            }*/

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.setAutoCommit(true);

            System.out.println("rows1 "+ rows1);
            System.out.println("rows2 "+ rows2);

        } catch (SQLException e1) {
            try {
                conn.rollback();
                throw new DbExeption("Transaction rolled back! Caused by: "+ e1.getMessage());
            } catch (SQLException e2) {
                throw new DbExeption("Error trying to rolled back! Caused by: "+ e2.getMessage());
            }
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
