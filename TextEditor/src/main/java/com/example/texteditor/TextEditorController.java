package com.example.texteditor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;


import java.io.*;


public class TextEditorController {
    @FXML
    private TabPane tabs;
    @FXML
    private Spinner<Integer> fontSpinner;
    @FXML
    private ToggleButton bold;
    @FXML
    private ToggleButton italic;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ToggleButton underlined;
    @FXML
    private ComboBox<String> fontComboBox;
    private final Clipboard clipboard = Clipboard.getSystemClipboard();
    private final ClipboardContent content = new ClipboardContent();


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
        if (currentTab == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("To save the file you should create a new tab");
            alert.showAndWait();
            return;
        } else if (fileToSave != null) {
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
        if (currentTab == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("To save the file you should create a new tab");
            alert.showAndWait();
            return;
        }
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
    private void onClearClicked() {
        MyTab currentTab = (MyTab) tabs.getSelectionModel().getSelectedItem();
        if (currentTab == null)
            return;
        else
            currentTab.getTextArea().clear();
    }
    @FXML
    private void onDeleteClicked(){
        MyTab currentTab = (MyTab) tabs.getSelectionModel().getSelectedItem();
        if (currentTab == null)
            return;
        currentTab.getTextArea().replaceSelection("");
    }
    @FXML
    private void onAboutClicked(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("This is a simple text editor done by Kucherenko Daniil");
        alert.setContentText("To continue, click OK");
        alert.showAndWait();
    }

    @FXML
    private void onExitClicked() {
        System.exit(0);
    }

    //    ------------Buttons controller----------
    @FXML
    private void onBoldClicked() {
        MyTab currentTab = (MyTab) tabs.getSelectionModel().getSelectedItem();
        if (currentTab == null)
            return;
        else if (bold.isSelected() && italic.isSelected())
            currentTab.getTextArea().setFont(Font.font(currentTab.getTextArea().getFont().getFamily(), FontWeight.BOLD, FontPosture.ITALIC, fontSpinner.getValue()));
        else if (bold.isSelected())
            currentTab.getTextArea().setFont(Font.font(currentTab.getTextArea().getFont().getFamily(), FontWeight.BOLD, fontSpinner.getValue()));
        else if (italic.isSelected())
            currentTab.getTextArea().setFont(Font.font(currentTab.getTextArea().getFont().getFamily(), FontWeight.NORMAL, FontPosture.ITALIC, fontSpinner.getValue()));
        else
            currentTab.getTextArea().setFont(Font.font(currentTab.getTextArea().getFont().getFamily(), FontWeight.NORMAL, fontSpinner.getValue()));
    }

    @FXML
    private void onItalicClicked() {
        MyTab currentTab = (MyTab) tabs.getSelectionModel().getSelectedItem();
        if (currentTab == null)
            return;
        else if (bold.isSelected() && italic.isSelected())
            currentTab.getTextArea().setFont(Font.font(currentTab.getTextArea().getFont().getFamily(), FontWeight.BOLD, FontPosture.ITALIC, fontSpinner.getValue()));
        else if (italic.isSelected())
            currentTab.getTextArea().setFont(Font.font(currentTab.getTextArea().getFont().getFamily(), FontPosture.ITALIC, fontSpinner.getValue()));
        else if (bold.isSelected())
            currentTab.getTextArea().setFont(Font.font(currentTab.getTextArea().getFont().getFamily(), FontWeight.BOLD, FontPosture.REGULAR, fontSpinner.getValue()));
        else
            currentTab.getTextArea().setFont(Font.font(currentTab.getTextArea().getFont().getFamily(), FontPosture.REGULAR, fontSpinner.getValue()));

    }

    @FXML
    private void onCopyClicked() {
        MyTab currentTab = (MyTab) tabs.getSelectionModel().getSelectedItem();
        if (currentTab == null)
            return;
        String text = currentTab.getTextArea().getSelectedText();
        content.putString(text);
        clipboard.setContent(content);
    }

    @FXML
    private void onCutClicked() {
        MyTab currentTab = (MyTab) tabs.getSelectionModel().getSelectedItem();
        if (currentTab == null)
            return;
        String text = currentTab.getTextArea().getSelectedText();
        currentTab.getTextArea().replaceSelection("");
        content.putString(text);
        clipboard.setContent(content);
    }

    @FXML
    private void onPasteClicked() {
        MyTab currentTab = (MyTab) tabs.getSelectionModel().getSelectedItem();
        if (currentTab == null)
            return;
        currentTab.getTextArea().paste();
        clipboard.clear();
    }

    @FXML
    private void setFontComboBox() {
        String[] fonts = Font.getFamilies().toArray(new String[0]);
        fontComboBox.getItems().addAll(fonts);
    }

    @FXML
    private void getFontFromComboBox() {
        MyTab currentTab = (MyTab) tabs.getSelectionModel().getSelectedItem();
        if (currentTab == null || currentTab.getTextArea() == null)
            return;
        else
            currentTab.getTextArea().setFont(Font.font(fontComboBox.getValue(), currentFontValue));
    }

    @FXML
    private void onColorClicked() {
        MyTab currentTab = (MyTab) tabs.getSelectionModel().getSelectedItem();
        Color color = colorPicker.getValue();
        currentTab.getTextArea().setStyle("-fx-text-fill: " + toRgbString(color) + ';');
    }

    private String toRgbString(Color c) {
        return "rgb(" + c.getRed() * 255 + "," + c.getGreen() * 255 + "," + c.getBlue() * 255 + ")";
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