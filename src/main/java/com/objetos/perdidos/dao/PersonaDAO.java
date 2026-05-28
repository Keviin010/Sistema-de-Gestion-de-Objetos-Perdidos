package com.objetos.perdidos.dao;

import com.objetos.perdidos.DBConnection;
import com.objetos.perdidos.model.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonaDAO {

    public void listar() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM Personas";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("PersonaID") + " | " +
                        rs.getString("Nombre") + " | " +
                        rs.getString("Telefono") + " | " +
                        rs.getString("Email") + " | " +
                        rs.getString("TipoPersona")
                );
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void insertar(Persona p) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO Personas (Nombre, Telefono, Email, TipoPersona) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, p.nombre);
            ps.setString(2, p.telefono);
            ps.setString(3, p.email);
            ps.setString(4, p.tipoPersona);

            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void actualizar(int id, String telefono, String email) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE Personas SET Telefono=?, Email=? WHERE PersonaID=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, telefono);
            ps.setString(2, email);
            ps.setInt(3, id);

            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void eliminar(int id) {

        try {

            Connection con = DBConnection.getConnection();

            String sql1 = "DELETE FROM Reclamos WHERE PersonaID=?";

            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, id);
            ps1.executeUpdate();

            String sql2 = "DELETE FROM Personas WHERE PersonaID=?";

            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, id);
            ps2.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
