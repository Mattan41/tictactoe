package org.kruskopf.tictactoe;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Random;

public class Model {

    StringProperty[] board;
    public static PlayerTurn playerTurn = PlayerTurn.PLAYER1;
    private Player player1;
    private Player player2;


    int player1Score = 0;
    int player2Score = 0;
    private StringProperty winner = new SimpleStringProperty("tic-tac-toe");
    private StringProperty playerScore = new SimpleStringProperty("Player 1: " + player1Score + " points\nPlayer 2: " + player2Score + " points");
    private BooleanProperty restartRound = new SimpleBooleanProperty(true);
    private BooleanProperty endGame = new SimpleBooleanProperty(false);
    private BooleanProperty gameMode = new SimpleBooleanProperty(false);
    private Random random;

    private boolean singlePlayer;

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private boolean gameOver;
    private boolean gameHost;

    public boolean isGameHost() {
        return gameHost;
    }

    public void setGameHost(boolean gameHost) {
        this.gameHost = gameHost;
    }

    private final BooleanProperty disable = new SimpleBooleanProperty(false);
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

    public boolean isSinglePlayer() {
        return singlePlayer;
    }

    public void setSinglePlayerMode(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }

    public Model() {

        player1 = new Player("");
        player2 = new Player("");
        board = new StringProperty[9];
        for (int i = 0; i < board.length; i++) {
            board[i] = new SimpleStringProperty(" ");
        }
        restartRound = restartRoundProperty();
        random = new Random();
    }

    public void setSymbolAndDisableForPlayer1(int index) {
        if (playerTurn == PlayerTurn.PLAYER2) {
            return;
        }
        else if (playerTurn == PlayerTurn.PLAYER1) {
            board[index].set(player1.getSymbol());
            playerTurn = PlayerTurn.PLAYER2;
        }

        checkForDrawOrWinnerOfRound();
        if (!isGameOver())
            ifComputerTurn();
    }

    public void setSymbolAndDisableForPlayer2(int index) {
        if (playerTurn == PlayerTurn.PLAYER1)
            return;
        else if (playerTurn == PlayerTurn.PLAYER2) {
            board[index].set(player2.getSymbol());
            playerTurn = PlayerTurn.PLAYER1;
        }


        checkForDrawOrWinnerOfRound();

    }



    public StringProperty PlayerScoreProperty() {
        return playerScore;
    }

    public BooleanProperty endGameProperty() {
        return endGame;
    }
    public BooleanProperty gameModeProperty() {
        return gameMode;
    }
    public BooleanProperty restartRoundProperty(){
        return restartRound;
    }
    public StringProperty winnerProperty() {
        return winner;
    }

    public void setPlayerScore(String playerScore) {
        this.playerScore.set(playerScore);
    }
    void checkForDrawOrWinnerOfRound() {

        checkForDrawRound();
        checkForWinnerOfRound();
    }

    public void checkForWinnerOfRound() {
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
            if (line.equals(player1.getSymbol() + player1.getSymbol() + player1.getSymbol())) {
                winner.set("Player 1 won!");
                player1Score++;
                roundOver();
                break;
            }
            else if(line.equals(player2.getSymbol() + player2.getSymbol() + player2.getSymbol())) {
                winner.set("Player 2 won!");
                player2Score++;
                roundOver();
                break;

            }

        }
    }

    private void checkForDrawRound() {
        if (roundIsDraw()){
            winner.set("It's a draw!");
            roundOver();
        }
    }

    public boolean roundIsDraw() {
        return Arrays.stream(board).noneMatch(s -> s.get().isEmpty());
    }

    public void roundOver() {
        for (StringProperty stringProperty : board) {
            if (stringProperty.get().isEmpty())
                stringProperty.set(" ");
        }
        setPlayerScore("Player 1: " + player1Score + " points\nPlayer 2: "+ player2Score +" points");
        restartRound.set(false);
        setGameOver(true);
    }

   public void resetBoard() {
       for (StringProperty stringProperty : board) {
           stringProperty.set("");
       }
        winner.set("Tic-Tac-Toe");
       ifComputerTurn();
       setGameOver(false);
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
                    ae -> setSymbolAndDisableForPlayer2(index)));
             timeline.play();

        }
    }

    public void endGameAndDeclareWinner() {
        if (player1Score>player2Score)
            winner.set("Player1 won: " + player1Score + "-" + player2Score);
        else if (player1Score<player2Score)
            winner.set("Player2 won: " + player2Score + "-" + player1Score);
        else if (player1Score==player2Score)
            winner.set("It is a tie! " + player1Score + "-" + player2Score);

        player1Score=0;
        player2Score=0;
        setPlayerScore("Player 1: " + player1Score + " points\nPlayer 2: "+ player2Score +" points");
        gameMode.set(false);
        playerTurn = PlayerTurn.PLAYER1;

    }

    public void setSymbolChoiceForPlayer1(String symbol) {
        player1.setSymbol(symbol);

    }
    public void setSymbolChoiceForPlayer2(String symbol) {
        player2.setSymbol(symbol);
    }

    public void sendMessageToServer(String player, int index) throws IOException, InterruptedException {
        String positionAtBoard = String.valueOf(index);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://ntfy.sh/M4TS_F4NT4STISK4_SPEL"))
                .POST(HttpRequest.BodyPublishers.ofString(player+positionAtBoard))
                .build();

        HttpResponse<Void> response =
                client.send(request,HttpResponse.BodyHandlers.discarding());
    }

    public void setSymbolAndDisable(int index) throws IOException, InterruptedException {

            if (!isSinglePlayer()) {
                //TODO: Lägga till om man är Server/Host på modellen, sortering i modellen beroende på vilken roll, även singel/multiplayer där?
                //TODO: om man är klient/join köra HttpPublish i modellen. Om man är Host uppdatera modellen. behöver starta Consume för att ta emot alla
                //TODO if Host model.setSymbolAndDisableForPlayer1(index);
                //TODO if Join model.setSymbolAndDisableForPlayer2(index);
                //senMessageToServer fungerar utanför if-satser, något med gameHost
                if (!isGameHost()) {
                    String player= "P2:";
                    sendMessage(index, player);
                } else if (isGameHost()) {
                    String player= "P1:";
                    sendMessage(index, player);
                    setSymbolAndDisableForPlayer1(index);
                }
            }
            else if (isSinglePlayer()) {
            setSymbolAndDisableForPlayer1(index);
        }
    }

    private void sendMessage(int index, String player) throws IOException, InterruptedException {
        sendMessageToServer(player, index);
    }

    private void receiveMessage() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://ntfy.sh/M4TS_F4NT4STISK4_SPEL/raw"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(HttpResponse::body)
                .thenAccept(inputStream -> {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    reader.lines().forEach(line -> {

                        int index = getIndexPosition(line);
                        Platform.runLater(() -> {
                            try {
                                setSymbolAndDisable(index);
                            } catch (IOException | InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    });
                });
    }

    public static int getIndexPosition(String line) {

        int index=0;

        if (line.startsWith("P1:"))
            index = Integer.parseInt(line.substring(line.length() - 1));
         else if(line.startsWith("P2:"))
            index = Integer.parseInt(line.substring(line.length() - 1));

        return index;
    }

}
