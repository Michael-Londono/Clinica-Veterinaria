package dao;

import modelo.Conexion;
import modelo.Mascota;
import modelo.Persona;

import java.sql.*;
import java.util.ArrayList;

public class MascotaDAO {
    public boolean insertar(Mascota m) {
        try (Connection conn = Conexion.conectar()) {
            // Verificar si el dueño existe
            PreparedStatement psCheck = conn.prepareStatement("SELECT * FROM persona WHERE documento = ?");
            psCheck.setString(1, m.getDocumentoDueno());
            ResultSet rs = psCheck.executeQuery();
            if (!rs.next()) {
                return false;
            }

            PreparedStatement ps = conn.prepareStatement("INSERT INTO mascota(nombre, especie, raza, documento_dueno) VALUES (?, ?, ?, ?)");
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getEspecie());
            ps.setString(3, m.getRaza());
            ps.setString(4, m.getDocumentoDueno());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public Mascota consultar(int id) {
        try (Connection conn = Conexion.conectar()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM mascota WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Mascota(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("especie"),
                        rs.getString("raza"),
                        rs.getString("documento_dueno")
                );
            }
        } catch (Exception e) {}
        return null;
    }

    public boolean actualizar(Mascota m) {
        try (Connection conn = Conexion.conectar()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE mascota SET nombre=?, especie=?, raza=?, documento_dueno=? WHERE id=?");
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getEspecie());
            ps.setString(3, m.getRaza());
            ps.setString(4, m.getDocumentoDueno());
            ps.setInt(5, m.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        try (Connection conn = Conexion.conectar()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM mascota WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Mascota> listar() {
        ArrayList<Mascota> lista = new ArrayList<>();
        try (Connection conn = Conexion.conectar()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM mascota");
            while (rs.next()) {
                lista.add(new Mascota(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("especie"),
                        rs.getString("raza"),
                        rs.getString("documento_dueno")
                ));
            }
        } catch (Exception e) {}
        return lista;
    }
}
