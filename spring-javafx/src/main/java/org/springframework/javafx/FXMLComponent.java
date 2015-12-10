package org.springframework.javafx;

import javafx.scene.Node;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Extends {@link Component} to be FXML resource aware.
 *
 * <h3>Usage</h3>
 *
 * Any class annotated with {@link FXMLComponent} will be used as a controller for the root node loaded from the
 * FXML resource specified in {@link FXMLComponent#location()}.
 *
 * See {@link FXMLComponentPostProcessor} for details regarding the resource resolution.
 *
 * <pre title="with implicit location">
 * &#64;FXMLComponent // location defaults to the annotated class' name
 * public class Sidebar {
 *
 *     &#64;FXML
 *     private TreeView treeView; // gets injected by FXMLLoader
 *
 * }
 * </pre>
 * <pre title="with explicit location">
 * &#64;FXMLComponent(location = "fxml/alternative-sidebar.fxml")
 * public class Sidebar {
 *
 *     &#64;FXML
 *     private TreeView treeView; // gets injected by FXMLLoader
 *
 * }
 * </pre>
 */
@Target(value={ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface FXMLComponent {

    String DEFAULT_LOCATION = "";

    /**
     * FXML resource location.
     * By default the name of the annotated class is used.
     * @return location of the FXML resource
     */
    String location() default DEFAULT_LOCATION;

    /**
     * Marks a {@link FXMLComponent} annotated as parent aware.
     *
     * <h3>Usage</h3>
     *
     * Any {@link FXMLComponent} annotated class implementing {@link RootNodeAware} will get the root node injected.
     *
     * <pre>
     * &#64;FXMLComponent
     * pubilc class Sidebar implements RootNodeAware&lt;VBox&gt; {
     *
     *     private VBox parent; // gets injected by FXMLComponentLoader
     *
     *     &#64;Override
     *     public VBox getRootNode() {
     *         return parent;
     *     }
     *
     *     &#64;Override
     *     public void setRootNode(VBox parent) {
     *         this.parent = parent;
     *     }
     *
     * }
     * </pre>
     *
     * @param <R> parent type
     */
    interface RootNodeAware<R extends Node> {

        void setRootNode(R rootNode);

        R getRootNode();

    }

}
