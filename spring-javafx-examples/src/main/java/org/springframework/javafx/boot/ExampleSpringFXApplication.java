package org.springframework.javafx.boot;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.javafx.SpringFXApplication;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.javafx.configuration.ExampleConfiguration;
import org.springframework.javafx.controller.MainController;

/**
 * Example application.
 */
public class ExampleSpringFXApplication extends SpringFXApplication {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Autowired
    private MainController mainController;

    public static void main(String[] args) {
        launch(args);
    }

    public ExampleSpringFXApplication() {
        super(ExampleConfiguration.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(messageSourceAccessor.getMessage("application.title"));
        primaryStage.setScene(new Scene(mainController.getRootNode()));
        primaryStage.show();
    }

}
