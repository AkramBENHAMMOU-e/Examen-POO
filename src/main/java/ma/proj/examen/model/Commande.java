package ma.proj.examen.model;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    private int idCommande; // Identifiant unique de la commande
    private Client client; // Client associé à la commande
        private List<Repas> listeRepas; // Liste des repas commandés

    // Constructeur
    public Commande(int idCommande, Client client) {
        this.idCommande = idCommande;
        this.client = client;
        this.listeRepas = new ArrayList<>();
    }

    // Méthode pour calculer le prix total de la commande
    public double calculerTotal() {
        double total = 0;
        for (Repas repas : listeRepas) {
            total += repas.calculerTotal(); // Ajoute le prix de chaque repas
        }
        return total;
    }

    // Méthode pour ajouter un repas à la commande
    public void ajouterRepas(Repas repas) {
        listeRepas.add(repas);
    }

    // Méthode pour supprimer un repas de la commande
    public void supprimerRepas(Repas repas) {
        listeRepas.remove(repas);
    }

    // Getters et Setters
    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Repas> getListeRepas() {
        return listeRepas;
    }

    public void setListeRepas(List<Repas> listeRepas) {
        this.listeRepas = listeRepas;
    }

    // Méthode pour afficher les détails de la commande
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commande ID: ").append(idCommande).append("\n");
        sb.append("Client: ").append(client.getNom()).append(" ").append(client.getPrenom()).append("\n");
        sb.append("Repas commandés:\n");
        for (Repas repas : listeRepas) {
            sb.append(repas.toString()).append("\n");
        }
        sb.append("Prix total de la commande: ").append(calculerTotal()).append(" DH\n");
        return sb.toString();
    }
}
