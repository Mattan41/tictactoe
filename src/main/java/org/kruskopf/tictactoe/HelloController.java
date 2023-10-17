package org.kruskopf.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class HelloController {
    public Button button1;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onLabelClicked(MouseEvent mouseEvent) {
        welcomeText.setText("");
        button1.setText("Label was Here!");
    }

    public void mouseOverLabel(MouseEvent mouseEvent) {
        button1.setText("Mouse coords: x=" + mouseEvent.getX() + "y=" + mouseEvent.getY());
    }
}