package org.springframework.javafx.controller;

import javafx.scene.control.SplitPane;
import org.springframework.javafx.FXMLComponent;

/**
 * An FXML backed controller.
 */
@FXMLComponent(location = "fxml/MainLayout.fxml")
public class MainController implements FXMLComponent.RootNodeAware<SplitPane> {

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
