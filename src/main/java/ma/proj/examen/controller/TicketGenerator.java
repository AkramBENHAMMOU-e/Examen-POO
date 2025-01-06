package ma.proj.examen;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ma.proj.examen.model.Ingredient;
import ma.proj.examen.model.Repas;
import ma.proj.examen.model.Supplement;

public class TicketGenerator {
    public static void genererTicket(Repas repas) {
        StringBuilder ticket = new StringBuilder();
        ticket.append("=== Ticket de Commande ===\n");
        ticket.append("Plat Principal: ").append(repas.getPlatPrincipal().getNomPlat()).append("\n");
        ticket.append("Prix de base: ").append(repas.getPlatPrincipal().getPrixBase()).append(" DH\n");

        if (!repas.getListeIngredients().isEmpty()) {
            ticket.append("Ingrédients supplémentaires:\n");
            for (Ingredient ingredient : repas.getListeIngredients()) {
                ticket.append("- ").append(ingredient.getNomIngredient()).append(": ")
                        .append(ingredient.getPrixUnitaire()).append(" DH\n");
            }
        }

        if (!repas.getListeSupplements().isEmpty()) {
            ticket.append("Suppléments:\n");
            for (Supplement supplement : repas.getListeSupplements()) {
                ticket.append("- ").append(supplement.getNomSupplement()).append(": ")
                        .append(supplement.getPrixSupplement()).append(" DH\n");
            }
        }

        double total = repas.calculerTotal();
        ticket.append("Total: ").append(total).append(" DH\n");

        // Afficher le ticket dans une nouvelle fenêtre
        Stage stage = new Stage();
        VBox root = new VBox(10);
        Label ticketLabel = new Label(ticket.toString());
        ticketLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        root.getChildren().add(ticketLabel);
        Scene scene = new Scene(root, 1090, 600);
        stage.setScene(scene);
        stage.setTitle("Ticket de Commande");
        stage.show();
    }
}