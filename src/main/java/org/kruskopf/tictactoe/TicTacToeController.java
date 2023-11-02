package org.kruskopf.tictactoe;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

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

    public Button startRound;
    private Model model;

    @FXML
    private Button endGame;
    private String mode;



    public Model getModel() {
        return this.model;
    }

    public void initialize() {
        model = new Model();


        String buttonStyle = "-fx-font-size: 40px; -fx-font-weight: bold;";

        button1.setStyle(buttonStyle);
        button2.setStyle(buttonStyle);
        button3.setStyle(buttonStyle);
        button4.setStyle(buttonStyle);
        button5.setStyle(buttonStyle);
        button6.setStyle(buttonStyle);
        button7.setStyle(buttonStyle);
        button8.setStyle(buttonStyle);
        button9.setStyle(buttonStyle);



        button1.disableProperty().bind(Bindings.isNotEmpty(model.getBoard()[0]));
        button2.disableProperty().bind(Bindings.isNotEmpty(model.getBoard()[1]));
        button3.disableProperty().bind(Bindings.isNotEmpty(model.getBoard()[2]));
        button4.disableProperty().bind(Bindings.isNotEmpty(model.getBoard()[3]));
        button5.disableProperty().bind(Bindings.isNotEmpty(model.getBoard()[4]));
        button6.disableProperty().bind(Bindings.isNotEmpty(model.getBoard()[5]));
        button7.disableProperty().bind(Bindings.isNotEmpty(model.getBoard()[6]));
        button8.disableProperty().bind(Bindings.isNotEmpty(model.getBoard()[7]));
        button9.disableProperty().bind(Bindings.isNotEmpty(model.getBoard()[8]));

        Bindings.bindBidirectional(button1.textProperty(), model.getBoard()[0]);
        Bindings.bindBidirectional(button2.textProperty(), model.getBoard()[1]);
        Bindings.bindBidirectional(button3.textProperty(), model.getBoard()[2]);
        Bindings.bindBidirectional(button4.textProperty(), model.getBoard()[3]);
        Bindings.bindBidirectional(button5.textProperty(), model.getBoard()[4]);
        Bindings.bindBidirectional(button6.textProperty(), model.getBoard()[5]);
        Bindings.bindBidirectional(button7.textProperty(), model.getBoard()[6]);
        Bindings.bindBidirectional(button8.textProperty(), model.getBoard()[7]);
        Bindings.bindBidirectional(button9.textProperty(), model.getBoard()[8]);

        playerScoreLabel.textProperty().bind(model.PlayerScoreProperty());
        winner.textProperty().bind(model.winnerProperty());
        Bindings.bindBidirectional(startRound.disableProperty(), model.restartRoundProperty());
        Bindings.bindBidirectional(endGame.disableProperty(), model.endGameProperty());
        Bindings.bindBidirectional(gameMode.disableProperty(), model.gameModeProperty());
        gameMode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setGameMode(newValue));

    }

    private void setGameMode(String newValue) {
        if (newValue != null && newValue.equals("SinglePlayer")) {
            model.setSinglePlayerMode(true);
            model.ifComputerTurn();
            startRound.setDisable(false);
        } else if (newValue != null && newValue.equals("MultiPlayer")) {
            startRound.setDisable(false);
            model.setSinglePlayerMode(false);
        }
    }

    public void onButtonClicked(ActionEvent actionEvent) {

        Button button = (Button) actionEvent.getSource();
        int row = GridPane.getRowIndex(button);
        int col = GridPane.getColumnIndex(button);
        int index = (row * 3) + col;

        //int index = Arrays.asList(model.getBoard()).indexOf(button);
            //denna funkar inte...
        model.setSymbolAndDisable(index);


    }
    public void restartRound(ActionEvent event) {
        model.resetBoard();
        startRound.setDisable(true);
        gameMode.setDisable(true);
    }

    public void endGame(ActionEvent actionEvent) {
        model.endGameAndDeclareWinner();
    }
}
