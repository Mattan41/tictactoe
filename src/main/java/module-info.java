module org.kruskopf.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.kruskopf.tictactoe to javafx.fxml;
    exports org.kruskopf.tictactoe;
    exports org.kruskopf.tictactoe.model;
    opens org.kruskopf.tictactoe.model to javafx.fxml;
    exports org.kruskopf.tictactoe.controller;
    opens org.kruskopf.tictactoe.controller to javafx.fxml;
}