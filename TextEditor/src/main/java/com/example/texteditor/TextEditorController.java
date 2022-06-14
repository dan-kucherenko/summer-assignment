package com.example.texteditor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TabPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;


import java.io.*;

public class TextEditorController {
    @FXML
    private TabPane tabs;
    @FXML
    private Spinner<Integer> fontSpinner;


    private File fileToSave;
    private int currentFontValue = 12;

    //    ---------------Menu bar controller--------
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

    //    ------------Buttons controller----------
    @FXML
    private void onBoldClicked() {
    }

    @FXML
    private void onItalicClicked() {
    }

    @FXML
    private void onUnderlinedClicked() {
    }

    @FXML
    private void implementSpinner() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        valueFactory.setValue(currentFontValue);
        fontSpinner.setValueFactory(valueFactory);
    }

    @FXML
    private void setFontSpinner() {
        MyTab currentTab = (MyTab) tabs.getSelectionModel().getSelectedItem();
        currentFontValue = fontSpinner.getValue();
        if (currentTab == null || currentTab.getTextArea() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("To change the font you should type some text");
            alert.showAndWait();
            return;
        }
        fontSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                currentFontValue = fontSpinner.getValue();
                currentTab.getTextArea().setFont(new Font(currentFontValue));
            }
        });
    }
}