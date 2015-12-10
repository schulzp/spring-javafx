package org.springframework.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.javafx.FXMLController;
import org.springframework.javafx.FXMLControllerFactory;

import javax.annotation.PostConstruct;

/**
 * An FXML backed controller.
 */
@FXMLController(location = "fxml/MainLayout.fxml")
public class MainController implements FXMLController.RootNodeAware<SplitPane> {

    private SplitPane rootNode;

    @FXML
    private GridPane content;

    @Autowired
    private FXMLControllerFactory<ContentBlock> contentBlockFactory;

    @Override
    public void setRootNode(SplitPane rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public SplitPane getRootNode() {
        return rootNode;
    }

    @PostConstruct
    private void initialize() {
        for (int col = 0, row = 0; row < 4; row += col == 1 ? 1 : 0, col = ++col % 2) {
            ContentBlock contentBlock = contentBlockFactory.call(ContentBlock.class);
            content.add(contentBlock.getRootNode(), col, row);
        }
    }

}
