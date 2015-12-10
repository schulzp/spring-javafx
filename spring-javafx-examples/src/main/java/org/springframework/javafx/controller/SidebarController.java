package org.springframework.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * A node controller.
 */
@Component
public class SidebarController {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @FXML
    private Text text;

    @FXML
    private Button button;

    private static final Color[] COLORS = {Color.ROYALBLUE, Color.GREENYELLOW, Color.ORANGERED};

    private int offset;

    @FXML
    public void initialize() {
        button.setOnAction(event -> {
            text.setFill(COLORS[offset++ % COLORS.length]);
            text.setText(messageSourceAccessor.getMessage("event.color.changed"));
        });
    }

}
