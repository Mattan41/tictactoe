package org.kruskopf.tictactoe;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Random;

public class Model {

    public static PlayerTurn playerTurn = PlayerTurn.PLAYER1;
    int player1Score = 0;
    int player2Score = 0;
    private StringProperty winner = new SimpleStringProperty("tic-tac-toe");
    private StringProperty playerScore = new SimpleStringProperty("Player 1: " + player1Score + " wins\nPlayer 2: " + player2Score + " wins");
    private BooleanProperty newMatch = new SimpleBooleanProperty(true);
    private Random random;
    private boolean singlePlayer;
    private int boardcount;

    Button[] board;
    private final BooleanProperty disable = new SimpleBooleanProperty(false);
    private final StringProperty text = new SimpleStringProperty("");

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
        ifComputerTurn();
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

    public boolean isGameOver() {
        return Arrays.stream(board).anyMatch(button -> !button.isDisabled());
        //ToDo: Draw -  kolla om inget index har tomt  är tom text draw.
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

   public void resetBoard() {
        for (Button button : board) {
            button.setDisable(false);
            button.setText("");
        }
        winner.set("Tic-Tac-Toe");
    }





    //dator nedan

    public boolean isComputerTurn() {
       return singlePlayer && playerTurn == PlayerTurn.PLAYER2;
    }

    public void ifComputerTurn() {
        if (isComputerTurn()) {

            int randomIndex;
            int index;

            do {
                randomIndex = random.nextInt(9);
            } while (!board[randomIndex].getText().isEmpty());

            index = randomIndex;

            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(500 + (Math.random() * 1000)),
                    ae -> setSymbolAndDisable(index)));
            timeline.play();
        }
    }


    public enum PlayerTurn {
        PLAYER1, PLAYER2
    }

}


// Todo: Draw replace boardCount with method to check anyEmpty
//ToDo: computer controls player 2 in singlePlayerMode
//toDo: add tests
//TODo: MultiPlayer
