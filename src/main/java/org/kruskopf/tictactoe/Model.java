package org.kruskopf.tictactoe;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Duration;

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

    private final BooleanProperty disable = new SimpleBooleanProperty(false);
    StringProperty[] board;


    private StringProperty text = new SimpleStringProperty("");

    public StringProperty textProperty() {
        return text;
    }

    public StringProperty[] getBoard() {
        return board;
    }

    public BooleanProperty disableProperty() {
        return disable;
    }
    public String getWinner() {
        return winner.get();
    }
    public void setWinner(String winner) {
        this.winner.set(winner);
    }

    public void setPlayerMode(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }

    public Model() {

        board = new StringProperty[9];
        for (int i = 0; i < board.length; i++) {
            board[i] = new SimpleStringProperty(" ");
        }
        newMatch = restartRoundProperty();
        random = new Random();
    }

    public void setSymbolAndDisable(int index) {
        if (playerTurn == PlayerTurn.PLAYER1) {
            board[index].set("X");
            playerTurn = PlayerTurn.PLAYER2;

        } else if(playerTurn == PlayerTurn.PLAYER2){
            board[index].set("O");
            playerTurn = PlayerTurn.PLAYER1;
        }
        checkGameOver();
        boardcount++;
        ifComputerTurn();
    }


    public StringProperty PlayerScoreProperty() {
        return playerScore;
    }

    public BooleanProperty restartRoundProperty(){
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
                    case 0 -> board[0].get() + board[1].get()+ board[2].get();
                    case 1 -> board[3].get() + board[4].get()+ board[5].get();
                    case 2 -> board[6].get() + board[7].get()+ board[8].get();
                    case 3 -> board[0].get() + board[3].get()+ board[6].get();
                    case 4 -> board[1].get() + board[4].get()+ board[7].get();
                    case 5 -> board[2].get() + board[5].get()+ board[8].get();
                    case 6 -> board[0].get() + board[4].get()+ board[8].get();
                    case 7 -> board[2].get() + board[4].get()+ board[6].get();
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

    //public boolean isGameOver() {
        //return Arrays.stream(board).anyMatch(button -> !button.isDisabled());
        //ToDo: Draw -  kolla om inget index har tomt är tom text draw.
    //}

    public void matchOver() {
        for (StringProperty stringProperty : board) {
            if (stringProperty.get().isEmpty())
                stringProperty.set(" ");
        }
        playerTurn = PlayerTurn.PLAYER1;
        setPlayerScore("Player 1: " + player1Score + " wins\nPlayer 2: "+ player2Score +" wins");
        boardcount=0;
        newMatch.set(false);

    }

   public void resetBoard() {
       for (StringProperty stringProperty : board) {
           stringProperty.set("");
       }
        winner.set("Tic-Tac-Toe");
    }


    public boolean isComputerTurn() {
       return singlePlayer && playerTurn == PlayerTurn.PLAYER2;
    }

    public void ifComputerTurn() {
        if (isComputerTurn()) {

            int randomIndex;
            int index;

            do {
                randomIndex = random.nextInt(9);
            } while (!board[randomIndex].get().isEmpty());

            index = randomIndex;

            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(500 + (Math.random() * 1000)),
                    ae -> setSymbolAndDisable(index)));
             timeline.play();



             //TODO: disable scene while waiting. Platform.runLater();
            // TODO: Check på vems tur det är och ignorera klick på buttons om det inte är dens spelarens tur

        }
    }

}


//Todo: Draw replace boardCount with method to check anyEmpty
//toDo: add tests
//TODo: MultiPlayer, Network klient?
//Todo:
