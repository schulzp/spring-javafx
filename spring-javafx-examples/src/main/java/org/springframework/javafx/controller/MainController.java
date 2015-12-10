package org.springframework.javafx.controller;

import javafx.scene.control.SplitPane;
import org.springframework.javafx.FXMLController;

/**
 * An FXML backed controller.
 */
@FXMLController(location = "fxml/MainLayout.fxml")
public class MainController implements FXMLController.RootNodeAware<SplitPane> {

    private SplitPane rootNode;

    @Override
    public void setRootNode(SplitPane rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public SplitPane getRootNode() {
        return rootNode;
    }

}
