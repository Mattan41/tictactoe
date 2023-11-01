package org.kruskopf.tictactoe;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.List;

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


        gameMode.setOnAction(event -> startGame());
    }

    public void startGame() {

        mode = gameMode.getValue();

        if (mode.equals("SinglePlayer")) {
            model = new Model(true);
            model.ifComputerTurn();
        } else if (mode.equals("MultiPlayer")) {
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

        playerScoreLabel.textProperty().bind(model.PlayerScoreProperty());
        winner.textProperty().bind(model.winnerProperty());
        Bindings.bindBidirectional(newMatch.disableProperty(), model.newMatchProperty());

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
    public void restartGame(ActionEvent event) {
        model.resetBoard();
        newMatch.setDisable(true);
    }

}
