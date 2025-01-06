package ma.proj.examen.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import ma.proj.examen.dao.PlatPrincipalDAO;
import ma.proj.examen.model.PlatPrincipal;

import java.io.IOException;

public class SelectionRepasController {
    @FXML
    private ComboBox<PlatPrincipal> platsComboBox;

    private PlatPrincipalDAO platDAO = new PlatPrincipalDAO();

    @FXML
    public void initialize() {
        // Charger la liste des plats dans la ComboBox
        platsComboBox.getItems().addAll(platDAO.listerPlats());
    }

    @FXML
    public void handleSuivant() {
        PlatPrincipal platSelectionne = platsComboBox.getSelectionModel().getSelectedItem();
        if (platSelectionne != null) {
            // Ouvrir la fenêtre de sélection des ingrédients et suppléments
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ma/proj/examen/SelectionIngredientsSupplement.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                SelectionIngredientsSupplementController controller = loader.getController();
                controller.setPlatSelectionne(platSelectionne); // Passer le plat sélectionné
                stage.setTitle("Personnaliser votre repas");
                stage.show();

                // Fermer la fenêtre actuelle
                ((Stage) platsComboBox.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Veuillez sélectionner un plat.");
        }
    }
}