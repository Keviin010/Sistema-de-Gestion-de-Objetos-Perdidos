package com.objetos.perdidos.dao;

import com.objetos.perdidos.DBConnection;
import com.objetos.perdidos.model.Reclamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReclamoDAO {

    public void listar() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT R.ReclamoID, P.Nombre AS Persona, O.Descripcion AS Objeto, " +
                        "R.FechaReclamo, R.EstadoReclamo " +
                        "FROM Reclamos R " +
                        "INNER JOIN Personas P ON R.PersonaID = P.PersonaID " +
                        "INNER JOIN ObjetosPerdidos O ON R.ObjetoID = O.ObjetoID";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("ReclamoID") + " | " +
                        rs.getString("Persona") + " | " +
                        rs.getString("Objeto") + " | " +
                        rs.getString("FechaReclamo") + " | " +
                        rs.getString("EstadoReclamo")
                );
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void listarPendientes() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT R.ReclamoID, P.Nombre AS Persona, P.Telefono, " +
                        "O.Descripcion AS Objeto, R.FechaReclamo " +
                        "FROM Reclamos R " +
                        "INNER JOIN Personas P ON R.PersonaID = P.PersonaID " +
                        "INNER JOIN ObjetosPerdidos O ON R.ObjetoID = O.ObjetoID " +
                        "WHERE R.EstadoReclamo = 'Pendiente'";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("ReclamoID") + " | " +
                        rs.getString("Persona") + " | " +
                        rs.getString("Telefono") + " | " +
                        rs.getString("Objeto") + " | " +
                        rs.getString("FechaReclamo")
                );
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void insertar(Reclamo r) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO Reclamos (ObjetoID, PersonaID, FechaReclamo, EstadoReclamo) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, r.objetoId);
            ps.setInt(2, r.personaId);
            ps.setString(3, r.fechaReclamo);
            ps.setString(4, r.estadoReclamo);

            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void actualizar(int id, String estado) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE Reclamos SET EstadoReclamo=? WHERE ReclamoID=?";

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

            String sql = "DELETE FROM Reclamos WHERE ReclamoID=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
