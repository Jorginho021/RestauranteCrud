package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class bancoDAO {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/restaurante_db";
    private static final String user = "root";
    private static final String password = "root";

    public static Connection conectar() {
        try {
            Class.forName(driver);
            Connection conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Conexão falhou: " + e.getMessage());
            return null;
        }
    }
}