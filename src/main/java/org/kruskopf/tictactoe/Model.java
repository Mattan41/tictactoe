package org.kruskopf.tictactoe;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Arrays;
import java.util.Random;

public class Model {

    public static PlayerTurn playerTurn = PlayerTurn.PLAYER1;
    int player1Score = 0;
    int player2Score = 0;

    private StringProperty winner = new SimpleStringProperty("tic-tac-toe");

    private StringProperty playerScore = new SimpleStringProperty("Player 1: " + player1Score + " wins\nPlayer 2: " + player2Score + " wins");
    private BooleanProperty newMatch = new SimpleBooleanProperty(true);
    private boolean singlePlayer;
    private int boardcount;

    Button[] board;
    private final BooleanProperty disable = new SimpleBooleanProperty(false);
    private final StringProperty text = new SimpleStringProperty("");
    private Random random;

    public Button[] getBoard() {
        return board;
    }

    public void setBoard(Button[] board) {
        this.board = board;
    }
    public BooleanProperty disableProperty() {
        return disable;
    }
    public StringProperty textProperty() {
        return text;
    }
        public String getWinner() {
        return winner.get();
    }
    public void setWinner(String winner) {
        this.winner.set(winner);
    }

    public Model(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
        board = new Button[9];
        for (int i = 0; i < board.length; i++) {
            board[i] = new Button();
            board[i].setDisable(false);
            board[i].setText("");
        }
        newMatch = newMatchProperty();
        random = new Random();
    }

    public void setSymbolAndDisable(int index) {
        if (playerTurn == PlayerTurn.PLAYER1) {
            board[index].setText("X");
            board[index].setDisable(true);
            playerTurn = PlayerTurn.PLAYER2;
        } else {
            board[index].setText("O");
            board[index].setDisable(true);
            playerTurn = PlayerTurn.PLAYER1;
        }
        boardcount++;
        checkGameOver();
    }


    public void makeMove(int row, int col, String player) {
        Button button = getButton(row, col);
        button.setText(player);
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
        return Arrays.stream(board).anyMatch(button -> !button.isDisabled());
        //ToDo: Draw -  kolla om inget index har tomt  är tom text draw.
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

    public BooleanProperty newMatchProperty(){
        return newMatch;
    }
    public StringProperty winnerProperty() {
        return winner;
    }

    public void setPlayerScore(String playerScore) {
        this.playerScore.set(playerScore);
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



    void checkGameOver() {

        if (boardcount==9){
            winner.set("It's a draw!");
            matchOver();
        }
        else
            for (int i = 0; i < 8; i++) {

                String line = switch (i){
                    case 0 -> board[0].getText() + board[1].getText()+ board[2].getText();
                    case 1 -> board[3].getText() + board[4].getText()+ board[5].getText();
                    case 2 -> board[6].getText() + board[7].getText()+ board[8].getText();
                    case 3 -> board[0].getText() + board[3].getText()+ board[6].getText();
                    case 4 -> board[1].getText() + board[4].getText()+ board[7].getText();
                    case 5 -> board[2].getText() + board[5].getText()+ board[8].getText();
                    case 6 -> board[0].getText() + board[4].getText()+ board[8].getText();
                    case 7 -> board[2].getText() + board[4].getText()+ board[6].getText();
                    default -> null;
                };
                if (line.equals("XXX")) {
                    winner.set("Player 1 won!");
                    player1Score++;
                    matchOver();
                    break;
                }
                else if(line.equals("OOO")) {
                    winner.set("Player 2 won!");
                    player2Score++;
                    matchOver();
                    break;

                }
            }
    }


    public void matchOver() {
        for (Button button : board) {
            button.setDisable(true);
        }
        playerTurn = PlayerTurn.PLAYER1;
        setPlayerScore("Player 1: " + player1Score + " wins\nPlayer 2: "+ player2Score +" wins");
        boardcount=0;
        newMatch.set(false);
    }

    void resetBoard() {
        for (Button button : board) {
            button.setDisable(false);
            button.setText("");
        }
        winner.set("Tic-Tac-Toe");
    }




    void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }

    public boolean isComputerTurn() {
        return singlePlayer && getCurrentPlayer().equals("O");
    }

    void ifComputerTurn() {
        if (isComputerTurn()) {
            int[] move = getNextMove();
            int row = move[0];
            int col = move[1];
            Button button = getButton(row, col);
            button.setText("O");
            makeMove(row, col, "O");
            playerTurn = PlayerTurn.PLAYER1;
        }
    }

    /*
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


     */
    public enum PlayerTurn {
        PLAYER1, PLAYER2
    }

}
