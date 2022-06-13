package com.example.texteditor;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;

public class TextEditorController {
    @FXML
    public TextArea textArea;

    protected File fileToSave;

    //    ---------------File menu controller--------
    @FXML
    private void onNewClicked() {
    }

    @FXML
    private File onOpenClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().add
                (new FileChooser.ExtensionFilter("All Text Files", "*.txt", "*.html"));
        fileChooser.getExtensionFilters().add
                (new FileChooser.ExtensionFilter("All Files", "*"));
        File file = fileChooser.showOpenDialog(textArea.getScene().getWindow());
        fileToSave = file;
        if (file == null)
            return null;
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
        return file;
    }

    @FXML
    private void onSaveClicked() {
        if (fileToSave != null) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToSave));
                bufferedWriter.write(textArea.getText());
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            onSaveAsClicked();
    }

    @FXML
    private void onSaveAsClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File As");
        File file = fileChooser.showSaveDialog(textArea.getScene().getWindow());
        if (file == null)
            return;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(textArea.getText());
            bufferedWriter.close();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void onCloseClicked() {
    }

    @FXML
    private void onExitClicked() {
        System.exit(0);
    }
}