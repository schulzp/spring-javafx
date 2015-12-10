package org.springframework.javafx;

import javafx.scene.Node;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

/**
 * Extends {@link Component} to be FXML resource aware.
 *
 * <h3>Usage</h3>
 *
 * Any class annotated with {@link FXMLController} will be used as a controller for the root node loaded from the
 * FXML resource specified in {@link FXMLController#location()}.
 *
 * See {@link FXMLControllerPostProcessor} for details regarding the resource resolution.
 *
 * <pre title="with implicit location">
 * &#64;FXMLController // location defaults to the annotated class' name
 * public class Sidebar {
 *
 *     &#64;FXML
 *     private TreeView treeView; // gets injected by FXMLLoader
 *
 * }
 * </pre>
 * <pre title="with explicit location">
 * &#64;FXMLController(location = "fxml/alternative-sidebar.fxml")
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
@Controller
public @interface FXMLController {

    String DEFAULT_LOCATION = "";

    /**
     * FXML resource location.
     * By default the name of the annotated class is used.
     * @return location of the FXML resource
     */
    String location() default DEFAULT_LOCATION;

    /**
     * Marks a {@link FXMLController} annotated as parent aware.
     *
     * <h3>Usage</h3>
     *
     * Any {@link FXMLController} annotated class implementing {@link RootNodeAware} will get the root node injected.
     *
     * <pre>
     * &#64;FXMLController
     * pubilc class Sidebar implements RootNodeAware&lt;VBox&gt; {
     *
     *     private VBox parent; // gets injected by FXMLControllerLoader
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
