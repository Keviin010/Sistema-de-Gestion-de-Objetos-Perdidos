package com.objetos.perdidos.dao;

import com.objetos.perdidos.DBConnection;
import com.objetos.perdidos.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategoriaDAO {

    public void listar() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM Categorias";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("CategoriaID") + " | " +
                                rs.getString("Nombre")
                );
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public int buscarIdPorNombre(String nombre) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT CategoriaID FROM Categorias WHERE Nombre = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("CategoriaID");
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return -1;
    }

    public void insertar(Categoria c) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO Categorias (Nombre) VALUES (?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.nombre);
            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}