package com.umleditor.view.window.pages.classdiagram;

import com.umleditor.model.classdiagram.enums.UMLCDRelationType;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;

public class RelationElementBuilder {

    private static enum ArrowDirection {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    public static Node constructRelation(Node classFrom, Node classTo, UMLCDRelationType type) {
        Line arrow = new Line();
        arrow.setStyle("-fx-border-color: red");
        Path path = new Path();

        classFrom.layoutXProperty().addListener((l,o,n) -> {
            double offset = classFrom.getBoundsInParent().getWidth() / 2;
            switch (directionCalc(classFrom,classTo)) {
                case RIGHT: offset *= 2; break;
                case LEFT: offset *= 0; break;
                default: break;
            }
            arrow.setStartX(l.getValue().doubleValue() + offset);
            arrow.toBack();
        });
        classFrom.layoutYProperty().addListener((l,o,n) -> {
            double offset = classFrom.getBoundsInParent().getHeight() / 2;
            switch (directionCalc(classFrom,classTo)) {
                case DOWN: offset *= 0; break;
                case UP: offset *= 2; break;
                default: break;
            }
            arrow.setStartY(l.getValue().doubleValue() + offset);
            arrow.toBack();
        });
        classTo.layoutXProperty().addListener((l,o,n) -> {
            double offset = classTo.getBoundsInParent().getWidth() / 2;
            switch (directionCalc(classFrom,classTo)) {
                case RIGHT: offset *= 0; break;
                case LEFT: offset *= 2; break;
                default: break;
            }
            arrow.setEndX(l.getValue().doubleValue() + offset);
            arrow.toBack();
        });
        classTo.layoutYProperty().addListener((l,o,n) -> {
            double offset = classTo.getBoundsInParent().getHeight() / 2;
            switch (directionCalc(classFrom,classTo)) {
                case UP: offset *= 0; break;
                case DOWN: offset *= 2; break;
                default: break;
            }
            arrow.setEndY(l.getValue().doubleValue() + offset);
            arrow.toBack();
        });
        return arrow;
    }

    private static ArrowDirection directionCalc(Node classFrom, Node classTo) {
        double x = classTo.getLayoutX() - classFrom.getLayoutX();
        double y = classTo.getLayoutY() - classFrom.getLayoutY();
        double deg45 = Math.PI / 4;
        x = x * Math.cos(deg45) - y * Math.sin(deg45);
        y = x * Math.cos(deg45) + y * Math.sin(deg45);
        if(x >= 0) {
            if (y >= 0) {
                return ArrowDirection.RIGHT;
            }
            return ArrowDirection.DOWN;
        }
        else {
            if (y >= 0) {
                return ArrowDirection.UP;
            }
            return ArrowDirection.LEFT;
        }
    }
}
