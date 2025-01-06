package ma.proj.examen.model;

public class Ingredient {
    private int idIngredient; // Identifiant unique de l'ingrédient
    private String nomIngredient; // Nom de l'ingrédient
    private double prixUnitaire; // Prix unitaire de l'ingrédient (par gramme, unité, etc.)

    // Constructeur
    public Ingredient(int idIngredient, String nomIngredient, double prixUnitaire) {
        this.idIngredient = idIngredient;
        this.nomIngredient = nomIngredient;
        this.prixUnitaire = prixUnitaire;
    }

    // Getters et Setters
    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getNomIngredient() {
        return nomIngredient;
    }

    public void setNomIngredient(String nomIngredient) {
        this.nomIngredient = nomIngredient;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    // Méthode pour afficher les détails de l'ingrédient
    @Override
    public String toString() {
        return "Ingredient{" +
                "idIngredient=" + idIngredient +
                ", nomIngredient='" + nomIngredient + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                '}';
    }
}
