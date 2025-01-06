package ma.proj.examen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TicketController {
    @FXML
    private Label ticketLabel;

    public void setTicket(String ticket) {
        ticketLabel.setText(ticket);
    }
}