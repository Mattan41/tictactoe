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

    public Model getModel() {
        return model;
    }

    public void initialize() {
        board = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
        board.forEach(this::setButtonProperties);
        board.forEach(this::disableButtons);
        gameMode.setOnAction(event -> startGame());

    }

    public void startGame() {
        String mode = gameMode.getValue();

            if (mode.equals("Single Player")) {
                model = new Model(true);

                model.ifComputerTurn(this);
            } else if (mode.equals("Multiplayer")) {
                model = new Model(false);

            }

            board.forEach(this::enableButtons);

            playerScoreLabel.textProperty().bind(model.PlayerScoreProperty());

    }

    public void makeMove(ActionEvent event) {
        Button button = (Button) event.getSource();
        int row = GridPane.getRowIndex(button);
        int col = GridPane.getColumnIndex(button);
        String player = model.getCurrentPlayer();
        button.setText(player);
        model.makeMove(row, col, player);

        if (model.isGameOver()) {
            // Game over
            // ...
        } else if (model.isComputerTurn()) {
            int[] move = model.getNextMove();
            row = move[0];
            col = move[1];
            button = getButton(row, col);
            button.setText("O");
            model.makeMove(row, col, "O");
            if (model.isGameOver()) {
                // Game over
                // ...
            }
        }
    }

    public Button getButton(int row, int col) {
        for (Button button : board) {
            if (GridPane.getRowIndex(button) == row && GridPane.getColumnIndex(button) == col) {
                return button;
            }
        }
        return null;
    }

    private int[] getNextMove() {
        int[] move = new int[2];
        Random random = new Random();
        do {
            int index = random.nextInt(board.size());
            Button button = board.get(index);
            move[0] = GridPane.getRowIndex(button);
            move[1] = GridPane.getColumnIndex(button);
        } while (!isEmpty(move[0], move[1]));
        return move;
    }

    private boolean isEmpty(int row, int col) {
        Button button = getButton(row, col);
        return button.getText().isEmpty();
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

    //TODO: SinglePlayer/MultiPlayer choice
    //ToDo: computer controls player 2 in singlePlayerMode
    //toDo: add tests
    //TODo: MultiPlayer


}
