package org.kruskopf.tictactoe;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Random;

public class Model {


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
            if(line.equals("XXX")) {
                ticTacToeController.winner.setText("Player X won!");
                ticTacToeController.buttons.forEach(ticTacToeController::disableButtons);
                ticTacToeController.newMatch.setDisable(false);
                break;
            }
            else if(line.equals("OOO")) {
                ticTacToeController.winner.setText("Player O won!");
                ticTacToeController.buttons.forEach(ticTacToeController::disableButtons);
                ticTacToeController.newMatch.setDisable(false);
                break;
            }
        }
    }


    void setSymbol(Button button, TicTacToeController ticTacToeController) {
        if (ticTacToeController.playerTurn % 2 == 0){
            button.setText("X");
            ticTacToeController.playerTurn = 1;
        }
        else{
            button.setText("O");
            ticTacToeController.playerTurn = 0;
        }
    }

    void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }
}
