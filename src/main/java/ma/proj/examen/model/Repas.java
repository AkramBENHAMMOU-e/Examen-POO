package ma.proj.examen.model;

import java.util.ArrayList;
import java.util.List;

public class Repas {
    private int idRepas; // Identifiant unique du repas
    private PlatPrincipal platPrincipal; // Plat principal du repas
    private List<Ingredient> listeIngredients; // Liste des ingrédients supplémentaires
    private List<Supplement> listeSupplements; // Liste des suppléments

    // Constructeur
    public Repas(int idRepas, PlatPrincipal platPrincipal) {
        this.idRepas = idRepas;
        this.platPrincipal = platPrincipal;
        this.listeIngredients = new ArrayList<>();
        this.listeSupplements = new ArrayList<>();
    }

    // Méthode pour calculer le prix total du repas
    public double calculerTotal() {
        double total = platPrincipal.calculerPrix(); // Prix du plat principal

        // Ajouter le coût des ingrédients supplémentaires
        for (Ingredient ingredient : listeIngredients) {
            total += ingredient.getPrixUnitaire(); // Exemple : prix unitaire par ingrédient
        }

        // Ajouter le coût des suppléments
        for (Supplement supplement : listeSupplements) {
            total += supplement.getPrixSupplement();
        }

        return total;
    }

    // Méthode pour ajouter un ingrédient supplémentaire
    public void ajouterIngredient(Ingredient ingredient) {
        listeIngredients.add(ingredient);
    }

    // Méthode pour supprimer un ingrédient supplémentaire
    public void supprimerIngredient(Ingredient ingredient) {
        listeIngredients.remove(ingredient);
    }

    // Méthode pour ajouter un supplément
    public void ajouterSupplement(Supplement supplement) {
        listeSupplements.add(supplement);
    }

    // Méthode pour supprimer un supplément
    public void supprimerSupplement(Supplement supplement) {
        listeSupplements.remove(supplement);
    }

    // Getters et Setters
    public int getIdRepas() {
        return idRepas;
    }

    public void setIdRepas(int idRepas) {
        this.idRepas = idRepas;
    }

    public PlatPrincipal getPlatPrincipal() {
        return platPrincipal;
    }

    public void setPlatPrincipal(PlatPrincipal platPrincipal) {
        this.platPrincipal = platPrincipal;
    }

    public List<Ingredient> getListeIngredients() {
        return listeIngredients;
    }

    public void setListeIngredients(List<Ingredient> listeIngredients) {
        this.listeIngredients = listeIngredients;
    }

    public List<Supplement> getListeSupplements() {
        return listeSupplements;
    }

    public void setListeSupplements(List<Supplement> listeSupplements) {
        this.listeSupplements = listeSupplements;
    }

    // Méthode pour afficher les détails du repas
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Repas ID: ").append(idRepas).append("\n");
        sb.append("Plat Principal: ").append(platPrincipal.getNomPlat()).append("\n");
        sb.append("Ingrédients supplémentaires:\n");
        for (Ingredient ingredient : listeIngredients) {
            sb.append("- ").append(ingredient.getNomIngredient()).append("\n");
        }
        sb.append("Suppléments:\n");
        for (Supplement supplement : listeSupplements) {
            sb.append("- ").append(supplement.getNomSupplement()).append("\n");
        }
        sb.append("Prix total du repas: ").append(calculerTotal()).append(" DH\n");
        return sb.toString();
    }
}
