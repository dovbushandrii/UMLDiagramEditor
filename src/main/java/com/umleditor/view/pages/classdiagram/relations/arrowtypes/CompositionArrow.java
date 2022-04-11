/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file CompositionArrow.java
 */
package com.umleditor.view.pages.classdiagram.relations.arrowtypes;

import com.umleditor.context.AppContext;
import javafx.beans.InvalidationListener;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

/**
 * Element for Composition-type Arrow
 */
public class CompositionArrow extends Arrow {

    private final Line line;

    public CompositionArrow() {
        this(new Line(), new Polygon());
    }

    private final double arrowLength;
    private final double arrowWidth;

    private CompositionArrow(Line line, Polygon poly) {
        super(line, poly);
        this.line = line;
        poly.setFill(Color.BLACK);
        poly.setStroke(Color.BLACK);
        arrowLength = Double.parseDouble(AppContext.getProperty("arrow-head-length"));
        arrowWidth = Double.parseDouble(AppContext.getProperty("arrow-head-width"));
        InvalidationListener updater = o -> {
            double ex = getEndX();
            double ey = getEndY();
            double sx = getStartX();
            double sy = getStartY();

            poly.getPoints().clear();

            poly.getPoints().addAll(ex, ey);

            if (ex == sx && ey == sy) {
                // arrow parts of length 0
                poly.getPoints().addAll(ex, ey);
            } else {
                double hypot = Math.hypot(sx - ex, sy - ey);
                double factor = arrowLength / hypot;
                double factorO = arrowWidth / hypot;

                // part in direction of main line
                double dx = (sx - ex) * factor;
                double dy = (sy - ey) * factor;

                // part orthogonal to main line
                double ox = (sx - ex) * factorO;
                double oy = (sy - ey) * factorO;

                poly.getPoints().addAll(ex + dx - oy, ey + dy + ox);
                poly.getPoints().addAll(ex + dx * 2, ey + dy * 2);
                poly.getPoints().addAll(ex + dx + oy, ey + dy - ox);
            }
            poly.toFront();
        };

        // add updater to properties
        startXProperty().addListener(updater);
        startYProperty().addListener(updater);
        endXProperty().addListener(updater);
        endYProperty().addListener(updater);
        updater.invalidated(null);
    }
}
