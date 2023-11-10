package org.kruskopf.tictactoe;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


class ModelTest {

    Model model = new Model();

    @Test
    @DisplayName("check for valid moves Button 9 should be True")
    void checkForValidMovesButton9ShouldBeTrue() {

        //Arrange
        model.resetBoard();
        model.board[0].set("O");
        model.board[1].set("X");
        model.board[2].set("O");
        model.board[3].set("O");
        model.board[4].set("X");
        model.board[5].set("X");
        model.board[6].set("X");


        //Act
        Boolean button9 = model.board[8].get().isEmpty();
        //Assert
        assertThat(button9).isTrue();

    }

    @Test
    @DisplayName("check for valid moves Button 7 should be False")
    void checkForValidMovesButton7ShouldBeFalse() {

        //Arrange
        model.resetBoard();
        model.board[0].set("O");
        model.board[1].set("X");
        model.board[2].set("O");
        model.board[3].set("O");
        model.board[4].set("X");
        model.board[5].set("X");
        model.board[6].set("X");


        Boolean button7 = model.board[6].get().isEmpty();

        assertThat(button7).isFalse();
    }


    @Test
    @DisplayName("check if game is over when three same symbols in row")
    void checkIfGameIsOverWhenThreeSameSymbolsInRow() {
        model.resetBoard();
        model.board[0].set("O");
        model.board[1].set("O");
        model.board[2].set("O");

        model.checkForDrawOrWinnerOfRound();

        assertThat(model.board).noneMatch(s -> s.get().isEmpty());
    }
    @Test
    @DisplayName("check if there is a winner")
    void checkIfThereIsAWinner() {
        //Arrange
        model.resetBoard();
        model.board[0].set(model.player2.getSymbol());
        model.board[1].set(model.player1.getSymbol());
        model.board[2].set(model.player2.getSymbol());
        model.board[3].set(model.player1.getSymbol());
        model.board[4].set(model.player1.getSymbol());
        model.board[5].set(model.player1.getSymbol());
        model.board[6].set(model.player1.getSymbol());
        model.board[7].set(model.player2.getSymbol());
        model.board[8].set(model.player2.getSymbol());
        //Act
        model.checkForWinnerOfRound();

        assertThat(model.winnerProperty().isEqualTo("Player 1 won!"));

    }

    @Test
    @DisplayName("check if round is tied")
    void checkIfRoundIsTied() {

        //Arrange
        //Act
        model.resetBoard();
        model.board[0].set("O");
        model.board[1].set("X");
        model.board[2].set("O");
        model.board[3].set("X");
        model.board[4].set("O");
        model.board[5].set("X");
        model.board[6].set("O");
        model.board[7].set("X");
        model.board[8].set("O");

        //Assert
        assertThat(model.roundIsDraw()).isTrue();

    }
}