package ma.proj.examen.dao;

import ma.proj.examen.connectionDB.DatabaseConnection;
import ma.proj.examen.model.Supplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplementDAO {
    // Méthode pour créer un supplément
    public void createSupplement(Supplement supplement) {
        String sql = "INSERT INTO Supplement (nomSupplement, prixSupplement) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, supplement.getNomSupplement());
            pstmt.setDouble(2, supplement.getPrixSupplement());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour lire un supplément par son ID
    public Supplement readSupplement(int idSupplement) {
        String sql = "SELECT * FROM Supplement WHERE idSupplement = ?";
        Supplement supplement = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idSupplement);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                supplement = new Supplement(
                        rs.getInt("idSupplement"),
                        rs.getString("nomSupplement"),
                        rs.getDouble("prixSupplement")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplement;
    }

    public List<Supplement> listerSupplements() {
        String sql = "SELECT * FROM Supplement";
        List<Supplement> supplements = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Supplement supplement = new Supplement(
                        rs.getInt("idSupplement"),
                        rs.getString("nomSupplement"),
                        rs.getDouble("prixSupplement")
                );
                supplements.add(supplement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplements;
    }

    // Méthode pour mettre à jour un supplément
    public void updateSupplement(Supplement supplement) {
        String sql = "UPDATE Supplement SET nomSupplement = ?, prixSupplement = ? WHERE idSupplement = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, supplement.getNomSupplement());
            pstmt.setDouble(2, supplement.getPrixSupplement());
            pstmt.setInt(3, supplement.getIdSupplement());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un supplément
    public void deleteSupplement(int idSupplement) {
        String sql = "DELETE FROM Supplement WHERE idSupplement = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idSupplement);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}