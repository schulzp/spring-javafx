package org.springframework.javafx;

import javafx.fxml.FXMLLoader;
import javafx.util.BuilderFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A spring based wrapper for {@link FXMLLoader}.
 */
public class FXMLControllerLoader {

    /**
     * ResourceBundle used for i18n.
     * @see FXMLLoader#setResources(ResourceBundle)
     */
    private ResourceBundle resourceBundle;

    /**
     * BuilderFactory for mapping nodes to instances.
     * @see FXMLLoader#setBuilderFactory(BuilderFactory)
     */
    private BuilderFactory builderFactory;

    /**
     * Callback for mapping controller classes to instances.
     * @see FXMLLoader#setControllerFactory(Callback)
     */
    private Callback<Class<?>, Object> controllerFactory;

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public void setBuilderFactory(BuilderFactory builderFactory) {
        this.builderFactory = builderFactory;
    }

    public void setControllerFactory(Callback<Class<?>, Object> controllerFactory) {
        this.controllerFactory = controllerFactory;
    }

    /**
     * Loads the specified {@code location} controlled by {@code controller}.
     * @param location the resource location, see {@link FXMLLoader#setLocation(URL)}
     * @param controller the root node controller, see {@link FXMLLoader#setController(Object)}
     * @return the loaded root node
     * @throws IOException see see {@link FXMLLoader#load()}
     */
    public Object load(URL location, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);
        loader.setControllerFactory(controllerFactory);
        loader.setBuilderFactory(builderFactory);
        loader.setResources(resourceBundle);
        loader.setController(controller);
        return loader.load();
    }

}