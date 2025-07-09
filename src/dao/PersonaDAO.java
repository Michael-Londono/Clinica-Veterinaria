package dao;

import modelo.Conexion;
import modelo.Persona;

import java.sql.*;
import java.util.ArrayList;

public class PersonaDAO {
    public boolean insertar(Persona p) {
        try (Connection conn = Conexion.conectar()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO persona VALUES (?, ?, ?)");
            ps.setString(1, p.getDocumento());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getTelefono());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public Persona consultar(String documento) {
        try (Connection conn = Conexion.conectar()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM persona WHERE documento = ?");
            ps.setString(1, documento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Persona(rs.getString("documento"), rs.getString("nombre"), rs.getString("telefono"));
            }
        } catch (Exception e) {}
        return null;
    }

    public boolean actualizar(Persona p) {
        try (Connection conn = Conexion.conectar()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE persona SET nombre = ?, telefono = ? WHERE documento = ?");
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());
            ps.setString(3, p.getDocumento());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(String documento) {
        try (Connection conn = Conexion.conectar()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM persona WHERE documento = ?");
            ps.setString(1, documento);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Persona> listar() {
        ArrayList<Persona> lista = new ArrayList<>();
        try (Connection conn = Conexion.conectar()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM persona");
            while (rs.next()) {
                lista.add(new Persona(rs.getString("documento"), rs.getString("nombre"), rs.getString("telefono")));
            }
        } catch (Exception e) {}
        return lista;
    }
}
