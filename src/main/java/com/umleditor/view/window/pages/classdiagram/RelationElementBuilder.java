package com.umleditor.view.window.pages.classdiagram;

import com.umleditor.model.classdiagram.enums.UMLCDRelationType;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class RelationElementBuilder {

    private enum ArrowDirection {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    private static ArrowDirection directionCalc(Node classFrom, Node classTo) {
        double x = classTo.getLayoutX() - classFrom.getLayoutX();
        double y = classTo.getLayoutY() - classFrom.getLayoutY();
        double deg45 = Math.PI / 4;
        x = x * Math.cos(deg45) - y * Math.sin(deg45);
        y = x * Math.cos(deg45) + y * Math.sin(deg45);
        if (x >= 0) {
            if (y >= 0) {
                return ArrowDirection.RIGHT;
            }
            return ArrowDirection.DOWN;
        } else {
            if (y >= 0) {
                return ArrowDirection.UP;
            }
            return ArrowDirection.LEFT;
        }
    }

    public static Node constructRelation(Node classFrom, Node classTo, UMLCDRelationType type) {
        Line arrow = new Line();
        classFrom.layoutXProperty().addListener((l, o, n) -> {
            double offset = classFrom.getBoundsInParent().getWidth() / 2;
            switch (directionCalc(classFrom, classTo)) {
                case RIGHT:
                    offset *= 2;
                    break;
                case LEFT:
                    offset *= 0;
                    break;
                default:
                    break;
            }
            arrow.setStartX(l.getValue().doubleValue() + offset);
            arrow.toBack();
        });
        classFrom.layoutYProperty().addListener((l, o, n) -> {
            double offset = classFrom.getBoundsInParent().getHeight() / 2;
            switch (directionCalc(classFrom, classTo)) {
                case DOWN:
                    offset *= 0;
                    break;
                case UP:
                    offset *= 2;
                    break;
                default:
                    break;
            }
            arrow.setStartY(l.getValue().doubleValue() + offset);
            arrow.toBack();
        });
        classTo.layoutXProperty().addListener((l, o, n) -> {
            double offset = classTo.getBoundsInParent().getWidth() / 2;
            switch (directionCalc(classFrom, classTo)) {
                case RIGHT:
                    offset *= 0;
                    break;
                case LEFT:
                    offset *= 2;
                    break;
                default:
                    break;
            }
            arrow.setEndX(l.getValue().doubleValue() + offset);
            arrow.toBack();
        });
        classTo.layoutYProperty().addListener((l, o, n) -> {
            double offset = classTo.getBoundsInParent().getHeight() / 2;
            switch (directionCalc(classFrom, classTo)) {
                case UP:
                    offset *= 0;
                    break;
                case DOWN:
                    offset *= 2;
                    break;
                default:
                    break;
            }
            arrow.setEndY(l.getValue().doubleValue() + offset);
            arrow.toBack();
        });

        return styleArrow(arrow, type);
    }

    private static Group styleArrow(Line arrow, UMLCDRelationType type) {
        if (type == UMLCDRelationType.COMPOSITION
                || type == UMLCDRelationType.AGGREGATION) {
            return attachToBack(getArrowHead(type), arrow);
        } else {
            if (type == UMLCDRelationType.IMPLEMENTATION
                    || type == UMLCDRelationType.DEPENDENCY) {
                arrow.getStrokeDashArray().addAll(15d, 12d);
            }
            return attachToHead(getArrowHead(type), arrow);
        }
    }

    private static Group attachToHead(Node arrowHead, Line arrow) {
        arrow.startXProperty().addListener((l, o, n) -> {
            arrowHead.setLayoutX(l.getValue().doubleValue() - arrowHead.getBoundsInParent().getWidth() / 2);
            double rotate = calculateRotationDeg(arrow);
            arrowHead.setRotate(rotate);
        });
        arrow.startYProperty().addListener((l, o, n) -> {
            arrowHead.setLayoutY(l.getValue().doubleValue() - arrowHead.getBoundsInParent().getHeight() / 2);
            double rotate = calculateRotationDeg(arrow);
            arrowHead.setRotate(rotate);
        });
        Group group = new Group();
        group.getChildren().addAll(arrowHead, arrow);
        return group;
    }

    private static Group attachToBack(Node arrowHead, Line arrow) {
        arrow.endXProperty().addListener((l, o, n) -> {
            arrowHead.setLayoutX(l.getValue().doubleValue() - arrowHead.getBoundsInParent().getWidth() / 2);
            double rotate = calculateRotationDeg(arrow);
            arrowHead.setRotate(rotate);
        });
        arrow.endYProperty().addListener((l, o, n) -> {
            arrowHead.setLayoutY(l.getValue().doubleValue() - arrowHead.getBoundsInParent().getHeight() / 2);
            double rotate = calculateRotationDeg(arrow);
            arrowHead.setRotate(rotate);
        });
        Group group = new Group();
        group.getChildren().addAll(arrowHead, arrow);
        return group;
    }

    private static double calculateRotationDeg(Line line) {
        double x = line.getEndX() - line.getStartX();
        double y = line.getEndY() - line.getStartY();
        return (-1) * Math.atan(x / y) / Math.PI * 180.0;
    }

    private static Node getArrowHead(UMLCDRelationType type) {
        Rectangle rec = new Rectangle(15, 15);
        rec.setFill(Color.TRANSPARENT);
        rec.setStroke(Color.BLACK);
        rec.setRotate(45);
        Group head = new Group();
        head.getChildren().add(rec);
        return head;
    }

    private static Node constructAssociationHead() {
        return null;
    }
}
