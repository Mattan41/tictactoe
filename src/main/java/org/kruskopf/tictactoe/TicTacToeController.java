package org.kruskopf.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TicTacToeController {



    @FXML
    private Label playerScoreLabel;
    @FXML
    public Text winner;
    @FXML
    private ChoiceBox<String> gameMode;
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
    List<Button> board;
    public Button newMatch;
    private Model model;

    private String mode;



    public Model getModel() {
        return this.model;
    }

    public void initialize() {
        board = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
        board.forEach(this::setButtonProperties);
        board.forEach(this::disableButtons);
        gameMode.setOnAction(event -> startGame());

    }

    public void startGame() {
        mode = gameMode.getValue();

            if (mode.equals("Single Player")) {
                model = new Model(true);
                model.ifComputerTurn(this);
            } else if (mode.equals("Multiplayer")) {
                model = new Model(false);

            }

            board.forEach(this::enableButtons);

            playerScoreLabel.textProperty().bind(model.PlayerScoreProperty());


    }

    public Button getButton(int row, int col) {
        for (Button button : board) {
            if (GridPane.getRowIndex(button) == row && GridPane.getColumnIndex(button) == col) {
                return button;
            }
        }
        return null;
    }


    private boolean isEmpty(int row, int col) {
        Button button = getButton(row, col);
        return button.getText().isEmpty();
    }



    public void setButtonProperties(Button button) {
        button.setOnAction(event -> setSymbolAndDisable(button));
        button.setFocusTraversable(false);
    }

    public void setSymbolAndDisable(Button button) {
        model.setSymbol(button);
        button.setDisable(true);
        model.checkGameOver(this);
    }

    @FXML
    private void restartGame(ActionEvent event) {
        board.forEach(model::resetButton);
        winner.setText("Tic-Tac-Toe");
        newMatch.setDisable(true);
    }

    public void disableButtons (Button button){
        button.setDisable(true);
    }
    public void enableButtons(Button button){
        button.setDisable(false);
    }

    public String getMode() {
        return this.mode;
    }

    //TODO: REFACTOR, move metods to Model, add properties, reference buttons to array in Model. class board?

    //ToDo: computer controls player 2 in singlePlayerMode
    //ToDO: draw
    //toDo: add tests
    //TODo: MultiPlayer


}
