package ma.proj.examen.model;

import java.util.HashMap;
import java.util.Map;

public class PlatPrincipal {
    private int idPlat; // Identifiant unique du plat
    private String nomPlat; // Nom du plat
    private double prixBase; // Prix de base du plat
    private Map<Ingredient, Double> listeIngredients; // Liste des ingrédients avec leurs quantités

    // Constructeur
    public PlatPrincipal(int idPlat, String nomPlat, double prixBase) {
        this.idPlat = idPlat;
        this.nomPlat = nomPlat;
        this.prixBase = prixBase;
        this.listeIngredients = new HashMap<>(); // Initialisation de la liste des ingrédients
    }

    public PlatPrincipal() {

    }

    // Méthode pour calculer le prix total du plat
    public double calculerPrix() {
        double total = prixBase; // Commence avec le prix de base du plat
        for (Map.Entry<Ingredient, Double> entry : listeIngredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            double quantite = entry.getValue();
            total += ingredient.getPrixUnitaire() * quantite; // Ajoute le coût de chaque ingrédient
        }
        return total;
    }

    // Méthode pour ajouter un ingrédient au plat
    public void ajouterIngredient(Ingredient ingredient, double quantite) {
        listeIngredients.put(ingredient, quantite);
    }

    // Méthode pour supprimer un ingrédient du plat
    public void supprimerIngredient(Ingredient ingredient) {
        listeIngredients.remove(ingredient);
    }

    // Getters et Setters
    public int getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(int idPlat) {
        this.idPlat = idPlat;
    }

    public String getNomPlat() {
        return nomPlat;
    }

    public void setNomPlat(String nomPlat) {
        this.nomPlat = nomPlat;
    }

    public double getPrixBase() {
        return prixBase;
    }

    public void setPrixBase(double prixBase) {
        this.prixBase = prixBase;
    }

    public Map<Ingredient, Double> getListeIngredients() {
        return listeIngredients;
    }

    public void setListeIngredients(Map<Ingredient, Double> listeIngredients) {
        this.listeIngredients = listeIngredients;
    }

    // Méthode pour afficher les détails du plat
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Plat: ").append(nomPlat).append("\n");
        sb.append("Prix de base: ").append(prixBase).append("\n");
        sb.append("Ingrédients:\n");
        for (Map.Entry<Ingredient, Double> entry : listeIngredients.entrySet()) {
            sb.append("- ").append(entry.getKey().getNomIngredient())
                    .append(": ").append(entry.getValue()).append(" grammes\n");
        }
        sb.append("Prix total: ").append(calculerPrix()).append("\n");
        return sb.toString();
    }
}
