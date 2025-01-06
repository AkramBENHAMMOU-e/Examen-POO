package ma.proj.examen.model;

public class Supplement {
    private int idSupplement; // Identifiant unique du supplément
    private String nomSupplement; // Nom du supplément
    private double prixSupplement; // Prix du supplément

    // Constructeur
    public Supplement(int idSupplement, String nomSupplement, double prixSupplement) {
        this.idSupplement = idSupplement;
        this.nomSupplement = nomSupplement;
        this.prixSupplement = prixSupplement;
    }

    // Getters et Setters
    public int getIdSupplement() {
        return idSupplement;
    }

    public void setIdSupplement(int idSupplement) {
        this.idSupplement = idSupplement;
    }

    public String getNomSupplement() {
        return nomSupplement;
    }

    public void setNomSupplement(String nomSupplement) {
        this.nomSupplement = nomSupplement;
    }

    public double getPrixSupplement() {
        return prixSupplement;
    }

    public void setPrixSupplement(double prixSupplement) {
        this.prixSupplement = prixSupplement;
    }

    // Méthode pour afficher les détails du supplément
    @Override
    public String toString() {
        return "Supplement{" +
                "idSupplement=" + idSupplement +
                ", nomSupplement='" + nomSupplement + '\'' +
                ", prixSupplement=" + prixSupplement +
                '}';
    }
}
