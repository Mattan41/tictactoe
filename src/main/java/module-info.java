module org.kruskopf.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;


    opens org.kruskopf.tictactoe to javafx.fxml;
    exports org.kruskopf.tictactoe;
}