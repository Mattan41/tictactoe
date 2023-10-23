package org.kruskopf.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TicTacToeController {

    private Model model = new Model();

    public Model getModel() {
        return model;
    }

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;

    List<Button> buttons;
    @FXML
    private Text winner;
    private int playerTurn = 0;

    public void initialize() {
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));

        buttons.forEach(this::setButtonProperties);

    }


    public void setButtonProperties(Button button) {
        button.setOnAction(event -> setSymbolAndDisable(button));
        button.setFocusTraversable(false);
    }

    private void setSymbolAndDisable(Button button) {
        setSymbol(button);
        button.setDisable(true);
        checkGameOver();
    }

    private void setSymbol(Button button) {
        if (playerTurn % 2 == 0){
            button.setText("X");
            playerTurn = 1;
        }
        else{
            button.setText("O");
            playerTurn = 0;
        }
    }

    private void checkGameOver() {

        for (int i = 0; i < 8; i++) {
            String line = switch (i){
                case 0 -> button1.getText()+button2.getText()+button3.getText();
                case 1 -> button4.getText()+button5.getText()+button6.getText();
                case 2 -> button7.getText()+button8.getText()+button9.getText();
                case 3 -> button1.getText()+button4.getText()+button7.getText();
                case 4 -> button2.getText()+button5.getText()+button8.getText();
                case 5 -> button3.getText()+button6.getText()+button9.getText();
                case 6 -> button1.getText()+button5.getText()+button9.getText();
                case 7 -> button3.getText()+button5.getText()+button7.getText();
                default -> null;

            };
            if(line.equals("XXX")) {
                winner.setText("X vann!");
                buttons.forEach(this::disableButtons);
            }


            else if(line.equals("OOO")) {
                winner.setText("O vann!");
                buttons.forEach(this::disableButtons);
            }

        }

    }

    @FXML
    private void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        winner.setText("Tic-Tac-Toe");
    }

    public void disableButtons (Button button){
        button.setDisable(true);
    }
    private void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }

    private void pointsTracker() {
        //TODO: poängräknare
    }


    //ToDo: computer controls player 2 in singleplayermode
    //TODO: SinglePlayer/MultiPlayer choice
    //TODo: Mutliplayer
}
