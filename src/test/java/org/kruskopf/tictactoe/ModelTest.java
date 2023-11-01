package org.kruskopf.tictactoe;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Boolean button9 = model.board[8].get().isEmpty();
        Boolean button8 = model.board[7].get().isEmpty();
        //Assert
        assertThat(button8 && button9).isTrue();
    }

    @Test
    @DisplayName("check if there is a winner")
    void checkIfThereIsAWinner() {
        //Arrange
        //Act
        //Assert

    }

    @Test
    @DisplayName("check if match is tied")
    void checkIfMatchIsTied() {

        //Arrange
        //Act
        //Assert

        //kolla inga drag kvar

    }
}