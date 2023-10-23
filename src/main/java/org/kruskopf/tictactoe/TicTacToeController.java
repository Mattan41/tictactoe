package org.kruskopf.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicTacToeController {

    private Model model = new Model();

    public Model getModel() {
        return model;
    }
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML

    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;

    List<Button> buttons;


    public void initialize() {
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));

        buttons.forEach(this::setButtonProperties);

    }


    public void setButtonProperties(Button button) {
        button.setOnMouseClicked(event -> setSymbolAndDisable(button));
        button.setFocusTraversable(false);
    }


    private void setSymbolAndDisable(Button button) {
        setSymbol(button);
        button.setDisable(true);
        checkGameOver();
    }


    private void setSymbol(Button button) {
        //Todo: skapa metod för att sätta symbol X eller O i model?
    }
    private void checkGameOver() {
        //Todo: skapa metod i model kolla tre i rad av lika. switch??
    }

    private void restartGame() {
        //ToDO: skapa metod och knapp för att starta om spelet
    }

    private void resetButtons() {
        //todo reset buttons
    }

    private void pointsTracker() {
        //TODO: poängräknare
    }
    //ToDo: computer controls player 2 in singleplayermode
    //TODO: SinglePlayer/MultiPlayer choice
    //TODo: Mutliplayer
}
