package org.kruskopf.tictactoe;

import javafx.beans.binding.Bindings;
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
        /*board = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
        board.forEach(this::setButtonProperties);
        board.forEach(this::disableButtons);
         */
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

        Bindings.bindBidirectional(button1.disableProperty(), model.getBoard()[0].disableProperty());
        Bindings.bindBidirectional(button2.disableProperty(), model.getBoard()[1].disableProperty());
        Bindings.bindBidirectional(button3.disableProperty(), model.getBoard()[2].disableProperty());
        Bindings.bindBidirectional(button4.disableProperty(), model.getBoard()[3].disableProperty());
        Bindings.bindBidirectional(button5.disableProperty(), model.getBoard()[4].disableProperty());
        Bindings.bindBidirectional(button6.disableProperty(), model.getBoard()[5].disableProperty());
        Bindings.bindBidirectional(button7.disableProperty(), model.getBoard()[6].disableProperty());
        Bindings.bindBidirectional(button8.disableProperty(), model.getBoard()[7].disableProperty());
        Bindings.bindBidirectional(button9.disableProperty(), model.getBoard()[8].disableProperty());

        Bindings.bindBidirectional(button1.textProperty(), model.getBoard()[0].textProperty());
        Bindings.bindBidirectional(button2.textProperty(), model.getBoard()[1].textProperty());
        Bindings.bindBidirectional(button3.textProperty(), model.getBoard()[2].textProperty());
        Bindings.bindBidirectional(button4.textProperty(), model.getBoard()[3].textProperty());
        Bindings.bindBidirectional(button5.textProperty(), model.getBoard()[4].textProperty());
        Bindings.bindBidirectional(button6.textProperty(), model.getBoard()[5].textProperty());
        Bindings.bindBidirectional(button7.textProperty(), model.getBoard()[6].textProperty());
        Bindings.bindBidirectional(button8.textProperty(), model.getBoard()[7].textProperty());
        Bindings.bindBidirectional(button9.textProperty(), model.getBoard()[8].textProperty());


        //board.forEach(this::enableButtons);

            playerScoreLabel.textProperty().bind(model.PlayerScoreProperty());


    }

    // Metod som anropas när knappen trycks på
    @FXML
    private void onButtonClicked() {
        // Uppdatera modellen med ett nytt tecken
        model.updateText();
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


/*
    public void setButtonProperties(Button button) {
        button.setOnAction(event -> setSymbolAndDisable(button));
        button.setFocusTraversable(false);
    }

    public void setSymbolAndDisable(Button button) {
        model.setSymbol(button);
        button.setDisable(true);
        model.checkGameOver(this);
    }

 */

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
