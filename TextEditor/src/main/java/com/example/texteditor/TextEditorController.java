package com.example.texteditor;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;


import java.io.*;

public class TextEditorController {
    @FXML
    private TabPane tabs;

    private File fileToSave;

    //    ---------------File menu controller--------
    @FXML
    private void onNewClicked() {
        MyTab myTab = new MyTab("Untitled");
        tabs.getTabs().add(myTab);
    }

    @FXML
    private File onOpenClicked() {
        MyTab currentTab = (MyTab) tabs.getSelectionModel().getSelectedItem();
        if (currentTab == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("To open the file you should create a new tab");
            alert.showAndWait();
            return null;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().add
                (new FileChooser.ExtensionFilter("All Text Files", "*.txt", "*.html"));
        fileChooser.getExtensionFilters().add
                (new FileChooser.ExtensionFilter("All Files", "*"));
        File file = fileChooser.showOpenDialog(currentTab.getTextArea().getScene().getWindow());
        fileToSave = file;
        if (fileToSave == null)
            return null;
        currentTab.setText(fileToSave.getName());
        currentTab.getTextArea().clear();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                currentTab.getTextArea().appendText(line + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @FXML
    private void onSaveClicked() {
        MyTab currentTab = (MyTab) tabs.getSelectionModel().getSelectedItem();
        if (fileToSave != null) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToSave));
                bufferedWriter.write(currentTab.getTextArea().getText());
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            onSaveAsClicked();
    }

    @FXML
    private void onSaveAsClicked() {
        MyTab currentTab = (MyTab) tabs.getSelectionModel().getSelectedItem();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File As");
        File file = fileChooser.showSaveDialog(currentTab.getTextArea().getScene().getWindow());
        if (file == null)
            return;
        try {
            currentTab.setText(file.getName());
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(currentTab.getTextArea().getText());
            bufferedWriter.close();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void onCloseClicked() {
        MyTab currentTab = (MyTab) tabs.getSelectionModel().getSelectedItem();
        tabs.getTabs().remove(currentTab);
    }

    @FXML
    private void onExitClicked() {
        System.exit(0);
    }
}