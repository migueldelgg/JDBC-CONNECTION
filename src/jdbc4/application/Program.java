package jdbc4.application;

import jdbc4.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {
        // Atualizando um salario de um vendedor

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "UPDATE seller "
                    + "SET BaseSalary = BaseSalary + ? "
                    + "WHERE "
                    + "(DepartmentId = ?)");

            st.setDouble(1, 200.0);
            st.setInt(2, 2);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! Rows Affected: "+ rowsAffected);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
