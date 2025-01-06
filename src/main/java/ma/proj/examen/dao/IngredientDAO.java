package ma.proj.examen.dao;

import ma.proj.examen.connectionDB.DatabaseConnection;
import ma.proj.examen.model.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO {
    // Méthode pour créer un ingrédient
    public void createIngredient(Ingredient ingredient) {
        String sql = "INSERT INTO Ingredient (nomIngredient, prixUnitaire) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ingredient.getNomIngredient());
            pstmt.setDouble(2, ingredient.getPrixUnitaire());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ingredient> listerIngredients() {
        String sql = "SELECT * FROM Ingredient";
        List<Ingredient> ingredients = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Ingredient ingredient = new Ingredient(
                        rs.getInt("idIngredient"),
                        rs.getString("nomIngredient"),
                        rs.getDouble("prixUnitaire")
                );
                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    // Méthode pour lire un ingrédient par son ID
    public Ingredient readIngredient(int idIngredient) {
        String sql = "SELECT * FROM Ingredient WHERE idIngredient = ?";
        Ingredient ingredient = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idIngredient);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ingredient = new Ingredient(
                        rs.getInt("idIngredient"),
                        rs.getString("nomIngredient"),
                        rs.getDouble("prixUnitaire")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredient;
    }



    // Méthode pour mettre à jour un ingrédient
    public void updateIngredient(Ingredient ingredient) {
        String sql = "UPDATE Ingredient SET nomIngredient = ?, prixUnitaire = ? WHERE idIngredient = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ingredient.getNomIngredient());
            pstmt.setDouble(2, ingredient.getPrixUnitaire());
            pstmt.setInt(3, ingredient.getIdIngredient());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un ingrédient
    public void deleteIngredient(int idIngredient) {
        String sql = "DELETE FROM Ingredient WHERE idIngredient = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idIngredient);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
