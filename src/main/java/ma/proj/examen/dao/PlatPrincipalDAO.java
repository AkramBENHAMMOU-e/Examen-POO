package ma.proj.examen.dao;

import ma.proj.examen.connectionDB.DatabaseConnection;
import ma.proj.examen.model.PlatPrincipal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlatPrincipalDAO {
    // Méthode pour créer un plat principal
    public void createPlat(PlatPrincipal plat) {
        String sql = "INSERT INTO PlatPrincipal (nomPlat, prixBase) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, plat.getNomPlat());
            pstmt.setDouble(2, plat.getPrixBase());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PlatPrincipal> listerPlats() {
        String sql = "SELECT * FROM PlatPrincipal";
        List<PlatPrincipal> plats = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                PlatPrincipal plat = new PlatPrincipal(
                        rs.getInt("idPlat"),
                        rs.getString("nomPlat"),
                        rs.getDouble("prixBase")
                );
                plats.add(plat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plats;
    }

    // Méthode pour lire un plat principal par son ID
    public PlatPrincipal readPlat(int idPlat) {
        String sql = "SELECT * FROM PlatPrincipal WHERE idPlat = ?";
        PlatPrincipal plat = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPlat);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                plat = new PlatPrincipal(
                        rs.getInt("idPlat"),
                        rs.getString("nomPlat"),
                        rs.getDouble("prixBase")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plat;
    }

    // Méthode pour mettre à jour un plat principal
    public void updatePlat(PlatPrincipal plat) {
        String sql = "UPDATE PlatPrincipal SET nomPlat = ?, prixBase = ? WHERE idPlat = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, plat.getNomPlat());
            pstmt.setDouble(2, plat.getPrixBase());
            pstmt.setInt(3, plat.getIdPlat());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un plat principal
    public void deletePlat(int idPlat) {
        String sql = "DELETE FROM PlatPrincipal WHERE idPlat = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPlat);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
