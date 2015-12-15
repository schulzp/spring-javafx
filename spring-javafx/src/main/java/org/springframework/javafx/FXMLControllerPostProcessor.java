package org.springframework.javafx;

import javafx.scene.Node;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

/**
 * BeanPostProcessor for {@link FXMLController} annotation.
 */
@Component
public class FXMLControllerPostProcessor implements BeanPostProcessor {

    public static final String DEFAULT_STRIP_SUFFIX = "Controller";
    protected final FXMLControllerLoader componentLoader;
    protected final ResourceLoader resourceLoader;

    /**
     * Override the root node ID.
     */
    private boolean overrideRootNodeId = true;

    /**
     * Resource location prefix.
     */
    private String locationPrefix = "/fxml/";

    /**
     * Resource location suffix.
     */
    private String locationSuffix = ".fxml";

    private String stripSuffix = DEFAULT_STRIP_SUFFIX;

    public FXMLControllerPostProcessor(FXMLControllerLoader componentLoader, ResourceLoader resourceLoader) {
        this.componentLoader = componentLoader;
        this.resourceLoader = resourceLoader;
    }

    public void setLocationPrefix(String locationPrefix) {
        this.locationPrefix = locationPrefix;
    }

    public void setLocationSuffix(String locationSuffix) {
        this.locationSuffix = locationSuffix;
    }

    public void setOverrideRootNodeId(boolean overrideRootNodeId) {
        this.overrideRootNodeId = overrideRootNodeId;
    }

    public void setStripSuffix(String stripSuffix) {
        this.stripSuffix = stripSuffix;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        FXMLController annotation = AnnotationUtils.findAnnotation(beanClass, FXMLController.class);
        if (annotation != null) {
            try {
                String location = getLocation(annotation).orElseGet(() -> getLocation(beanClass));
                link(bean, beanName, load(location, bean));
            } catch (IOException|ReflectiveOperationException e) {
                throw new BeanInitializationException("Failed to initialize FXML component " + bean, e);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * Maps the resource location to a resource URL.
     * @param location the resource location
     * @return the resource URL
     * @throws IOException if the resource cannot be found
     */
    protected URL getResourceUrl(String location) throws IOException {
        Resource resource = resourceLoader.getResource(location);
        return resource.getURL();
    }

    /**
     * Loads the specified resource.
     * @param location the location of the resource to be loaded
     * @param controller the root object controller
     * @return the loaded root object
     * @throws IOException
     * @throws ReflectiveOperationException
     */
    protected Object load(String location, Object controller) throws IOException, ReflectiveOperationException {
        return componentLoader.load(getResourceUrl(location), controller);
    }

    /**
     * Links the {@code controller} with the loaded root object.
     * If {@code rootObject} is a {@link Node} and &hellip;
     * <ul>
     *     <li>&hellip; {@link #overrideRootNodeId} is {@code true} the node's ID set to {@code rootId}</li>
     *     <li>&hellip; {@code controller} implements {@link FXMLController.RootNodeAware} {@code rootObject} is passed to {@link FXMLController.RootNodeAware#setRootNode(Node)}</li>
     * </ul>
     * @param controller the controller used as controller
     * @param rootId the name of the controller used as controller
     * @param rootObject the loaded root object
     * @throws IllegalStateException if {@link #overrideRootNodeId} is {@code true} and the root node ID is already set
     * @throws ClassCastException if {@code controller} is {@link FXMLController.RootNodeAware}
     * and {@link FXMLController.RootNodeAware#setRootNode(Node)} cannot be set to {@code rootObject}
     */
    protected void link(Object controller, String rootId, Object rootObject) {
        if (rootObject instanceof Node) {
            Node rootNode = (Node) rootObject;
            if (overrideRootNodeId) {
                if (rootNode.getId() != null) {
                    throw new IllegalStateException("Root node id is already set for "
                            + rootId + " (" + controller + "): " + rootNode.getId());
                }
                rootNode.setId(rootId);
            }

            if (controller instanceof FXMLController.RootNodeAware) {
                ((FXMLController.RootNodeAware) controller).setRootNode(rootNode);
            }
        }
    }

    /**
     * Creates a resource location from the specified class.
     * @param beanClass bean class
     * @return a String
     */
    protected String getLocation(Class<?> beanClass) {
        String fileName = beanClass.getSimpleName();
        if (stripSuffix != null) {
            int end = fileName.lastIndexOf(stripSuffix);
            if (end > 0) {
                fileName = fileName.substring(0, end);
            }
        }
        return locationPrefix + fileName + locationSuffix;
    }

    /**
     * Extract a resource location from the specified annotation.
     * @param annotation the annotation
     * @return an optional String which is empty if {@link FXMLController#location()} is not set
     */
    protected Optional<String> getLocation(FXMLController annotation) {
        String location = annotation.location();
        if (location.equals(FXMLController.DEFAULT_LOCATION)) {
            return Optional.empty();
        }
        return Optional.of(location);
    }

}
