module com.texteditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.texteditor to javafx.fxml;
    exports com.example.texteditor;
}