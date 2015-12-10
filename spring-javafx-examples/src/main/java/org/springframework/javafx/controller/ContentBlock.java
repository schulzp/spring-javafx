package org.springframework.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.javafx.FXMLController;

import javax.annotation.PostConstruct;

@Scope("prototype")
@FXMLController
public class ContentBlock implements FXMLController.RootNodeAware<VBox> {

    private static int instanceCount = 0;

    { instanceCount++; }

    private VBox rootNode;

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @FXML
    private Text text;

    @PostConstruct
    private void initialize() {
        text.setText(messageSourceAccessor.getMessage("content.block", new Object[]{ instanceCount }));
    }

    @Override
    public void setRootNode(VBox rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public VBox getRootNode() {
        return rootNode;
    }

}
