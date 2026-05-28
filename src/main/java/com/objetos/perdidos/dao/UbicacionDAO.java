package com.objetos.perdidos.dao;

import com.objetos.perdidos.DBConnection;
import com.objetos.perdidos.model.Ubicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UbicacionDAO {

    public void listar() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM Ubicaciones";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("UbicacionID") + " | " +
                                rs.getString("Nombre") + " | " +
                                rs.getString("TipoLugar")
                );
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public int buscarIdPorNombre(String nombre) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT UbicacionID FROM Ubicaciones WHERE Nombre = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("UbicacionID");
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return -1;
    }

    public void insertar(Ubicacion u) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO Ubicaciones (Nombre, TipoLugar) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, u.nombre);
            ps.setString(2, u.tipoLugar);

            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}