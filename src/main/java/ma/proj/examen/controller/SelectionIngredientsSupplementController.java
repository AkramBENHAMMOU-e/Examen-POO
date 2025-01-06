package ma.proj.examen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import ma.proj.examen.TicketGenerator;
import ma.proj.examen.dao.IngredientDAO;
import ma.proj.examen.dao.SupplementDAO;
import ma.proj.examen.model.Ingredient;
import ma.proj.examen.model.PlatPrincipal;
import ma.proj.examen.model.Repas;
import ma.proj.examen.model.Supplement;

public class SelectionIngredientsSupplementController {
    @FXML
    private Label platLabel;
    @FXML
    private ListView<Ingredient> ingredientsListView;
    @FXML
    private ListView<Supplement> supplementsListView;
    @FXML
    private Label totalLabel;

    private PlatPrincipal platSelectionne;
    private Repas repas;

    private IngredientDAO ingredientDAO = new IngredientDAO();
    private SupplementDAO supplementDAO = new SupplementDAO();

    public void setPlatSelectionne(PlatPrincipal plat) {
        this.platSelectionne = plat;
        platLabel.setText("Plat : " + plat.getNomPlat());
        repas = new Repas(0, plat);
        updateTotal();
    }

    @FXML
    public void initialize() {
        // Charger la liste des ingrédients et suppléments
        ingredientsListView.getItems().addAll(ingredientDAO.listerIngredients());
        supplementsListView.getItems().addAll(supplementDAO.listerSupplements());

        // Gérer la sélection des ingrédients et suppléments
        ingredientsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                repas.ajouterIngredient(newVal);
                updateTotal();
            }
        });

        supplementsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                repas.ajouterSupplement(newVal);
                updateTotal();
            }
        });
    }

    @FXML
    public void handleEnregistrer() {
        // Enregistrer le repas et afficher le ticket
        TicketGenerator.genererTicket(repas);
        ((Stage) platLabel.getScene().getWindow()).close();
    }

    private void updateTotal() {
        double total = repas.calculerTotal();
        totalLabel.setText("Total : " + total + " DH");
    }
}