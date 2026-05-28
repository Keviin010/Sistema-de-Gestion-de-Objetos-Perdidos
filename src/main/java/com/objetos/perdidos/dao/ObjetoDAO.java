package com.objetos.perdidos.dao;

import com.objetos.perdidos.DBConnection;
import com.objetos.perdidos.model.Objeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ObjetoDAO {

    public void listar() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT O.ObjetoID, O.Descripcion, C.Nombre AS Categoria, " +
                        "U.Nombre AS Ubicacion, O.FechaEncontrado, O.Estado " +
                        "FROM ObjetosPerdidos O " +
                        "INNER JOIN Categorias C ON O.CategoriaID = C.CategoriaID " +
                        "INNER JOIN Ubicaciones U ON O.UbicacionID = U.UbicacionID";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("ObjetoID") + " | " +
                        rs.getString("Descripcion") + " | " +
                        rs.getString("Categoria") + " | " +
                        rs.getString("Ubicacion") + " | " +
                        rs.getString("FechaEncontrado") + " | " +
                        rs.getString("Estado")
                );
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void listarDisponibles() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT O.ObjetoID, O.Descripcion, C.Nombre AS Categoria, " +
                        "U.Nombre AS Ubicacion, O.FechaEncontrado " +
                        "FROM ObjetosPerdidos O " +
                        "INNER JOIN Categorias C ON O.CategoriaID = C.CategoriaID " +
                        "INNER JOIN Ubicaciones U ON O.UbicacionID = U.UbicacionID " +
                        "WHERE O.Estado = 'Disponible'";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("ObjetoID") + " | " +
                        rs.getString("Descripcion") + " | " +
                        rs.getString("Categoria") + " | " +
                        rs.getString("Ubicacion") + " | " +
                        rs.getString("FechaEncontrado")
                );
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void insertar(Objeto o) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO ObjetosPerdidos (Descripcion, CategoriaID, UbicacionID, FechaEncontrado, Estado) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, o.descripcion);
            ps.setInt(2, o.categoriaId);
            ps.setInt(3, o.ubicacionId);
            ps.setString(4, o.fechaEncontrado);
            ps.setString(5, o.estado);

            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void actualizar(int id, String estado) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE ObjetosPerdidos SET Estado=? WHERE ObjetoID=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, estado);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void eliminar(int id) {

        try {

            Connection con = DBConnection.getConnection();

            String sql1 = "DELETE FROM Reclamos WHERE ObjetoID=?";

            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, id);
            ps1.executeUpdate();

            String sql2 = "DELETE FROM ObjetosPerdidos WHERE ObjetoID=?";

            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, id);
            ps2.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
