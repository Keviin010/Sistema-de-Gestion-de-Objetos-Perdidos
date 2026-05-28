package com.objetos.perdidos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = System.getenv("DB_URL") != null
            ? System.getenv("DB_URL")
            : "jdbc:sqlserver://localhost:1433;databaseName=ObjetosPerdidosDB;encrypt=false;trustServerCertificate=true";

    private static final String USER = System.getenv("DB_USER") != null
            ? System.getenv("DB_USER")
            : "TU_USUARIO_AQUI";

    private static final String PASSWORD = System.getenv("DB_PASSWORD") != null
            ? System.getenv("DB_PASSWORD")
            : "TU_CONTRASENA_AQUI";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}