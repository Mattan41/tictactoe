package org.kruskopf.tictactoe;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


class ModelTest {

    Model model = new Model();

    @Test
    @DisplayName("check for valid moves")
    void checkForValidMoves() {

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
        Boolean button7 = model.board[6].get().isEmpty();
        Boolean button9 = model.board[8].get().isEmpty();
        //Assert
        assertThat(button9).isTrue();
        assertThat(button7).isFalse();
    }


    @Test
    @DisplayName("check if game is over when three in row")
    void checkIfGameIsOverWhenThreeInRow() {
        model.resetBoard();
        model.board[0].set("O");
        model.board[1].set("O");
        model.board[2].set("O");

        model.checkForDrawOrWinnerOfRound();
        boolean noMoreMoves = Arrays.stream(model.board)
                .noneMatch(s -> s.get().isEmpty());

        assertThat(noMoreMoves).isTrue();
    }
    @Test
    @DisplayName("check if there is a winner")
    void checkIfThereIsAWinner() {
        //Arrange
        model.resetBoard();
        model.board[0].set("O");
        model.board[1].set("X");
        model.board[2].set("O");
        model.board[3].set("X");
        model.board[4].set("X");
        model.board[5].set("X");
        model.board[6].set("X");
        model.board[7].set("0");
        model.board[8].set("0");
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