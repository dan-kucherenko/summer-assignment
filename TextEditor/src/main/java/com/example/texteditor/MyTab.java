package com.example.texteditor;

import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class MyTab extends Tab {
    private TextArea textArea;

    public MyTab(String text) {
        setText(text);
        AnchorPane container = new AnchorPane();
        textArea = new TextArea();
        textArea.setWrapText(true);
        AnchorPane.setBottomAnchor(textArea, 0.0);
        AnchorPane.setLeftAnchor(textArea, 0.0);
        AnchorPane.setRightAnchor(textArea, 0.0);
        AnchorPane.setTopAnchor(textArea, 0.0);

        container.getChildren().add(textArea);
        setContent(container);
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }
}
