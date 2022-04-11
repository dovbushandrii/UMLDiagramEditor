/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file ClassElementBuilder.java
 */
package com.umleditor.view.pages.classdiagram;

import com.umleditor.context.AppContext;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLClassAttribute;
import com.umleditor.model.common.UMLClassMethod;
import com.umleditor.view.pages.interfaces.Shortcuts;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.codehaus.plexus.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Builder for Class elements on Class Diagram
 */
public class ClassElementBuilder {

    public static Pane constructClassElement(UMLClass umlClass) {
        VBox root = new VBox();
        root.setStyle("-fx-border-color: black");
        Shortcuts.setFixedWidth(root, Double.parseDouble(AppContext.getProperty("class-width")));

        // Header
        VBox header = constructHeader(umlClass);
        Shortcuts.bindWidth(header, root);
        root.getChildren().add(header);
        // Attributes
        VBox attr = constructAttributesArea(umlClass);
        Shortcuts.bindWidth(attr, root);
        root.getChildren().add(attr);


        return root;
    }

    private static VBox constructHeader(UMLClass umlClass) {
        VBox header = new VBox();
        header.setStyle("-fx-background-color:" + AppContext.getProperty("class-header-color") + ";"
        + "-fx-border-color: black");

        if (umlClass.getIsAbstract()) {
            Label abs = new Label("<<abstract>>");
            Shortcuts.bindWidth(abs, header);
            header.getChildren().add(abs);
        }

        // Name of umlClass
        Label name = new Label(umlClass.getName());
        Shortcuts.bindWidth(name, header);
        header.getChildren().add(name);
        return header;
    }

    private static VBox constructAttributesArea(UMLClass umlClass) {
        VBox header = new VBox();
        header.setStyle("-fx-background-color:" + AppContext.getProperty("class-attributes-color"));
        header.setMinHeight(Double.parseDouble(AppContext.getProperty("class-attributes-min-height")));

        // Fields area
        Pane farea = constructFieldsArea(umlClass.getFields());
        Shortcuts.bindWidth(farea, header);
        header.getChildren().add(farea);

        // Method area
        Pane marea = constructMethodsArea(umlClass.getMethods());
        Shortcuts.bindWidth(marea, header);
        header.getChildren().add(marea);
        return header;
    }

    private static Pane constructFieldsArea(List<UMLClassAttribute> fields) {
        VBox root = new VBox();
        if(fields.size() > 0) {
            root.setStyle("-fx-border-color: black");
            List<Pane> fieldLines = fields.stream()
                    .map(ClassElementBuilder::constructFieldLine)
                    .collect(Collectors.toList());
            fieldLines.forEach(c -> {
                Shortcuts.bindPrefWidth(c, root);
                root.getChildren().add(c);
            });
        }
        return root;
    }

    private static Pane constructFieldLine(UMLClassAttribute field) {
        HBox line = new HBox();
        Label modifier = new Label(" " + field.getModifier().getModifierSymbol());
        Label name = new Label(" " + field.getName());
        Label type = new Label(" : " + field.getType());
        line.getChildren().add(modifier);
        line.getChildren().add(name);
        line.getChildren().add(type);
        return line;
    }

    private static Pane constructMethodsArea(List<UMLClassMethod> methods) {
        VBox root = new VBox();
        if(methods.size() > 0) {
            root.setStyle("-fx-border-color: black");
            List<Pane> methodLines = methods.stream()
                    .map(ClassElementBuilder::constructMethodLine)
                    .collect(Collectors.toList());
            methodLines.forEach(c -> {
                Shortcuts.bindPrefWidth(c, root);
                root.getChildren().add(c);
            });
        }
        return root;
    }

    private static Pane constructMethodLine(UMLClassMethod method) {
        HBox line = new HBox();
        Label modifier = new Label(" " + method.getModifier().getModifierSymbol());
        Label type = new Label(" " + method.getType());
        Label name = new Label(" " + method.getName());
        Label args = new Label("("
                + StringUtils.join(method.getArgumentTypes().toArray(), ", ")
                + ")");

        line.getChildren().add(modifier);
        line.getChildren().add(type);
        line.getChildren().add(name);
        line.getChildren().add(args);
        return line;
    }
}
