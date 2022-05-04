package com.umleditor.view.pages.classdiagram.relations;

import com.umleditor.model.classdiagram.enums.UMLRelationType;
import com.umleditor.view.pages.classdiagram.relations.arrowtypes.*;
import javafx.scene.Node;

/**
 * Builder for relation "arrow" elements
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class RelationElementBuilder {

    private enum ArrowDirection {
        N,
        N_E,
        E,
        S_E,
        S,
        S_W,
        W,
        N_W
    }

    public static Node constructRelation(Node classFrom, Node classTo, UMLRelationType type) {
        final Arrow arrow;
        switch (type) {
            case ASSOCIATION: arrow = new AssociationArrow(); break;
            case AGGREGATION: arrow = new AggregationArrow(); break;
            case COMPOSITION: arrow = new CompositionArrow(); break;
            case GENERALIZATION: arrow = new GeneralizationArrow(); break;
            default: {
                throw new RuntimeException("No such relation was defined");
            }
        }
        classFrom.layoutXProperty().addListener((l, o, n) -> updateLine(classFrom, classTo, arrow));
        classFrom.layoutYProperty().addListener((l, o, n) -> updateLine(classFrom, classTo, arrow));
        classTo.layoutXProperty().addListener((l, o, n) -> updateLine(classFrom, classTo, arrow));
        classTo.layoutYProperty().addListener((l, o, n) -> updateLine(classFrom, classTo, arrow));
        return arrow;
    }

    /**
     * Listener for line ends movements.
     */
    public static void updateLine(Node classFrom, Node classTo, Arrow arrow) {
        double offsetX1 = classFrom.getBoundsInParent().getWidth() / 2;
        double offsetY1 = classFrom.getBoundsInParent().getHeight() / 2;
        double offsetX2 = classTo.getBoundsInParent().getWidth() / 2;
        double offsetY2 = classTo.getBoundsInParent().getHeight() / 2;

        ArrowDirection objAng = getDirection(calculateObjectDirectionAngle(classFrom, classTo));

        if (objAng == ArrowDirection.N || objAng == ArrowDirection.N_E) {
            offsetY1 *= 0;
            if (objAng == ArrowDirection.N) {
                offsetY2 *= 2;
            } else {
                offsetX2 *= 0;
            }
        } else if (objAng == ArrowDirection.E || objAng == ArrowDirection.S_E) {
            offsetX1 *= 2;
            if (objAng == ArrowDirection.E) {
                offsetX2 *= 0;
            } else {
                offsetY2 *= 0;
            }
        } else if (objAng == ArrowDirection.S || objAng == ArrowDirection.S_W) {
            offsetY1 *= 2;
            if (objAng == ArrowDirection.S) {
                offsetY2 *= 0;
            } else {
                offsetX2 *= 2;
            }
        } else if (objAng == ArrowDirection.W || objAng == ArrowDirection.N_W) {
            offsetX1 *= 0;
            if (objAng == ArrowDirection.W) {
                offsetX2 *= 2;
            } else {
                offsetY2 *= 2;
            }
        }

        arrow.setStartX(classFrom.getLayoutX() + offsetX1);
        arrow.setStartY(classFrom.getLayoutY() + offsetY1);
        arrow.setEndX(classTo.getLayoutX() + offsetX2);
        arrow.setEndY(classTo.getLayoutY() + offsetY2);
        arrow.toBack();
    }

    /**
     * Converts degrees on 8 directions: N, NE, E, SE, S, SW, W, NW
     * These 8 directions are: N - TOP, E - RIGHT, S - BOTTOM, W - LEFT
     * @param degOrig Degrees calculated on window coordinates.
     * @return Direction on screen.
     */
    private static ArrowDirection getDirection(double degOrig) {
        double deg = 360 - degOrig - 22.5;
        while (deg < 0) deg += 360;
        while (deg > 360) deg -= 360;
        if (deg >= 0 && deg < 45) return ArrowDirection.N_E;
        else if (deg >= 45 && deg < 90) return ArrowDirection.N;
        else if (deg >= 90 && deg < 135) return ArrowDirection.N_W;
        else if (deg >= 135 && deg < 180) return ArrowDirection.W;
        else if (deg >= 180 && deg < 225) return ArrowDirection.S_W;
        else if (deg >= 225 && deg < 270) return ArrowDirection.S;
        else if (deg >= 270 && deg < 315) return ArrowDirection.S_E;
        else return ArrowDirection.E;

    }

    /**
     *  Calculates angle on window coordinates of direction
     *  between two nodes.
     */
    private static double calculateObjectDirectionAngle(Node classFrom, Node classTo) {
        double x = classTo.getLayoutX() - classFrom.getLayoutX();
        double y = classTo.getLayoutY() - classFrom.getLayoutY();
        return Math.toDegrees(Math.atan2(y, x));
    }
}
