package org.kruskopf.tictactoe;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Random;

public class Model {
    public Image image1;
    public static Image image2;
    public static Image image3;

    private static ObjectProperty<Image>[][] images;

    public Model() {
        image1 = new Image(getClass().getResource("images/blank.png").toExternalForm());
        image2 = new Image(getClass().getResource("images/cross.png").toExternalForm());
        image3 = new Image(getClass().getResource("images/circle.png").toExternalForm());

        images = new SimpleObjectProperty[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                images[i][j] = new SimpleObjectProperty<>(image1);
            }
        }
    }

    public Image getImage(int row, int col) {
        return images[row][col].get();
    }

    public ObjectProperty<Image> imageProperty(int row, int col) {
        return images[row][col];
    }

    public static void setImage(int row, int col, Image image) {
        images[row][col].set(image);
    }


    public static void changeImageToCross(int row, int col) {
        Image crossImage = image2;
        setImage(row, col, crossImage);
    }

    public static void changeImageToCircle(int row, int col) {
        Image circleImage = image3;
        setImage(row, col, circleImage);
    }


}
