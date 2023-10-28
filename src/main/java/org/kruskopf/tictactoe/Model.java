package org.kruskopf.tictactoe;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;
import java.util.ArrayList;
import java.util.Random;

public class Model {

    public static PlayerTurn playerTurn = PlayerTurn.PLAYER1;

    int player1Score = 0;

    int player2Score = 0;
    private StringProperty playerScore = new SimpleStringProperty("Player 1: " + player1Score + " wins\nPlayer 2: " + player2Score + " wins");

    private ArrayList<Button> board;
    private boolean singlePlayer;
    private int boardcount;
    private Random random;

    public Model(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
        board = new ArrayList<>();
        random = new Random();
    }

    public void makeMove(int row, int col, String player) {
        Button button = getButton(row, col);
        button.setText(player);
    }

    public boolean isComputerTurn() {
        return singlePlayer && getCurrentPlayer().equals("O");
    }

    public int[] getNextMove() {
        int[] move = new int[2];
        do {
            move[0] = random.nextInt(3);
            move[1] = random.nextInt(3);
        } while (!isEmpty(move[0], move[1]));
        return move;
    }

    public boolean isGameOver() {
        return board.stream().anyMatch(button -> !button.isDisabled());
        //ToDo: Draw -  denna verkar inte fungera. med allMatch så blir det draw på första klicket.
    }


    private boolean isEmpty(int row, int col) {
        Button button = getButton(row, col);
        assert button != null;
        return button.getText().isEmpty();
    }

    private Button getButton(int row, int col) {
        for (Button button : board) {
            if (GridPane.getRowIndex(button) == row && GridPane.getColumnIndex(button) == col) {
                return button;
            }
        }
        return null;
    }

    public String getCurrentPlayer() {

        if (playerTurn == PlayerTurn.PLAYER1){
            return "X";
        }
        else return "O";
    }



    public String getPlayerScore() {
        return playerScore.get();
    }

    public StringProperty PlayerScoreProperty() {
        return playerScore;
    }

    public void setPlayerScore(String playerScore) {
        this.playerScore.set(playerScore);
    }


    void checkGameOver(TicTacToeController ticTacToeController) {

        for (int i = 0; i < 8; i++) {

            String line = switch (i){
                case 0 -> ticTacToeController.button1.getText()+ ticTacToeController.button2.getText()+ ticTacToeController.button3.getText();
                case 1 -> ticTacToeController.button4.getText()+ ticTacToeController.button5.getText()+ ticTacToeController.button6.getText();
                case 2 -> ticTacToeController.button7.getText()+ ticTacToeController.button8.getText()+ ticTacToeController.button9.getText();
                case 3 -> ticTacToeController.button1.getText()+ ticTacToeController.button4.getText()+ ticTacToeController.button7.getText();
                case 4 -> ticTacToeController.button2.getText()+ ticTacToeController.button5.getText()+ ticTacToeController.button8.getText();
                case 5 -> ticTacToeController.button3.getText()+ ticTacToeController.button6.getText()+ ticTacToeController.button9.getText();
                case 6 -> ticTacToeController.button1.getText()+ ticTacToeController.button5.getText()+ ticTacToeController.button9.getText();
                case 7 -> ticTacToeController.button3.getText()+ ticTacToeController.button5.getText()+ ticTacToeController.button7.getText();
                default -> null;
            };
                if(line.equals("OOO")) {
                ticTacToeController.winner.setText("Player 2 won!");
                player2Score++;
                matchOver(ticTacToeController);
                break;

            }
            else if (line.equals("XXX")) {
                ticTacToeController.winner.setText("Player 1 won!");
                player1Score++;
                matchOver(ticTacToeController);
                break;
            }
            else if (boardcount==9){
                ticTacToeController.winner.setText("It's a draw!");
                matchOver(ticTacToeController);
            }
        }
    }


    public void matchOver(TicTacToeController ticTacToeController) {
        ticTacToeController.board.forEach(ticTacToeController::disableButtons);
        ticTacToeController.newMatch.setDisable(false);
        playerTurn = PlayerTurn.PLAYER1;
        boardcount=0;
        setPlayerScore("Player 1: " + player1Score + " wins\nPlayer 2: "+ player2Score +" wins");
    }


    void setSymbol(Button button) {
        if (playerTurn == PlayerTurn.PLAYER1) {
            button.setText("X");
            boardcount++;
            playerTurn = PlayerTurn.PLAYER2;
        }
         else {
            button.setText("O");
            boardcount++;
            playerTurn = PlayerTurn.PLAYER1;
        }
    }


    void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }

    void ifComputerTurn(TicTacToeController ticTacToeController) {
        if (isComputerTurn()) {
            int[] move = getNextMove();
            int row = move[0];
            int col = move[1];
            Button button = ticTacToeController.getButton(row, col);
            button.setText("O");
            makeMove(row, col, "O");
            playerTurn = PlayerTurn.PLAYER1;
        }
    }
    public void computerMove(TicTacToeController ticTacToeController) {
        if (isComputerTurn()) {
            Random rand = new Random();
            int index = rand.nextInt(9);
            Button button = board.get(index);
            while (button.isDisabled()) {
                index = rand.nextInt(9);
                button = board.get(index);
            }
            ticTacToeController.setSymbolAndDisable(button);

            if (!isGameOver()) {
                playerTurn = PlayerTurn.PLAYER1;
            }
            matchOver(ticTacToeController);
        }
    }

    public enum PlayerTurn {
        PLAYER1, PLAYER2
    }

}
