package org.kruskopf.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.kruskopf.tictactoe.Model;

public class TicTacToeController {

    private Model model = new Model();

    public Model getModel() {
        return model;
    }
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;


    public void initialize() {
    }


    public void imageClicked(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        int row = GridPane.getRowIndex(imageView);
        int col = GridPane.getColumnIndex(imageView);

        // Hämta informationen från getUserData-metoden
        Object userData = imageView.getUserData();

        // todo: change image to cross or circle dependant on which player/computer
        if (userData.equals("blank")) {
            Model.changeImageToCross(row, col);
        } else if (userData.equals("cross")) {
            Model.changeImageToCircle(row, col);
        }

    }
}
