Spring Powered JavaFX Applications
==================================

This project provides Spring integration for JavaFX. That is, FXML Controllers are first-class citizen Spring Beans.

# Installation

The artifacts are hosted on [JFrog's Bintray](https://bintray.com/schulzp/maven/spring-javafx/view).

## Gradle

Add the following to your `build.gradle`.

```groovy
dependencies {
    compile "org.springframework.boot:spring-boot-javafx:$vSpringBootJavaFx"
}

repositories {
    maven {
        url "https://dl.bintray.com/schulzp/maven/"
    }
}
```

## Maven

Add the following to your `pom.xml`.

```xml
<repositories>
    <repository>
        <id>repository.spring.javafx</id>
        <name>Spring JavaFX Integration</name>
        <url>https://dl.bintray.com/schulzp/maven/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-javafx</artifactId>
        <version>$vSpringBootJavaFx</version>
    </dependency>
</dependencies>
```

# Usage

Please have a look at the examples project.
