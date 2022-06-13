package com.example.texteditor;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextEditorController {
    @FXML
    public TextArea textArea;

    //    ---------------File menu controller--------
    @FXML
    private void onNewClicked() {
    }

    @FXML
    private void onOpenClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().add
                (new FileChooser.ExtensionFilter("All Text Files", "*.txt", "*.html"));
        fileChooser.getExtensionFilters().add
                (new FileChooser.ExtensionFilter("All Files", "*"));
        File file = fileChooser.showOpenDialog(textArea.getScene().getWindow());
        if (file == null)
            return;
        textArea.clear();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                textArea.appendText(line + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSaveClicked() {
    }

    @FXML
    private void onSaveAsClicked() {
    }

    @FXML
    private void onCloseClicked() {
    }

    @FXML
    private void onExitClicked() {
        System.exit(0);
    }
}