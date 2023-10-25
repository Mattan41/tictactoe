package org.kruskopf.tictactoe;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicTacToeController {

    private Model model = new Model();
    public Model getModel() {
        return model;
    }
    @FXML
    private Label playerScoreLabel;


    @FXML
    public Button button1;

    @FXML
    public Button button2;
    @FXML
    public Button button3;
    @FXML
    public Button button4;
    @FXML
    public Button button5;
    @FXML
    public Button button6;
    @FXML
    public Button button7;
    @FXML
    public Button button8;
    @FXML
    public Button button9;
    List<Button> buttons;

    @FXML
    public Text winner;
    public Button newMatch;

    public void initialize() {
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
        buttons.forEach(this::setButtonProperties);
        playerScoreLabel.textProperty().bind(model.PlayerScoreProperty());
    }

    public void setButtonProperties(Button button) {
        button.setOnAction(event -> setSymbolAndDisable(button));
        button.setFocusTraversable(false);
    }

    private void setSymbolAndDisable(Button button) {
        model.setSymbol(button);
        button.setDisable(true);
        model.checkGameOver(this);
    }

    @FXML
    private void restartGame(ActionEvent event) {
        buttons.forEach(model::resetButton);
        winner.setText("Tic-Tac-Toe");
        newMatch.setDisable(true);
    }

    public void disableButtons (Button button){
        button.setDisable(true);
    }

    //toDo: add tests
    //toDo: refactor som code to model?
    //TODO: SinglePlayer/MultiPlayer choice
    //ToDo: computer controls player 2 in singlePlayerMode
    //TODo: MultiPlayer


}
