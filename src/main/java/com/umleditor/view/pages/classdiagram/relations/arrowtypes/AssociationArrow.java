package com.umleditor.view.pages.classdiagram.relations.arrowtypes;

import com.umleditor.context.AppContext;
import javafx.beans.InvalidationListener;
import javafx.scene.shape.Line;

/**
 * Element for Association-type Arrow.
 * Solid line and classic 'V' arrowhead on the end.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class AssociationArrow extends Arrow {

    private final Line line;

    public AssociationArrow() {
        this(new Line(), new Line(), new Line());
    }

    private final double arrowLength;
    private final double arrowWidth;

    private AssociationArrow(Line line, Line arrow1, Line arrow2) {
        super(line, arrow1, arrow2);
        this.line = line;
        arrowLength = Double.parseDouble(AppContext.getProperty("arrow-head-length"));
        arrowWidth = Double.parseDouble(AppContext.getProperty("arrow-head-width"));
        InvalidationListener updater = o -> {
            double ex = getEndX();
            double ey = getEndY();
            double sx = getStartX();
            double sy = getStartY();

            arrow1.setEndX(ex);
            arrow1.setEndY(ey);
            arrow2.setEndX(ex);
            arrow2.setEndY(ey);

            if (ex == sx && ey == sy) {
                // arrow parts of length 0
                arrow1.setStartX(ex);
                arrow1.setStartY(ey);
                arrow2.setStartX(ex);
                arrow2.setStartY(ey);
            } else {
                double hypot = Math.hypot(sx-ex, sy-ey);
                double factor = arrowLength / hypot;
                double factorO = arrowWidth / hypot;

                // part in direction of main line
                double dx = (sx - ex) * factor;
                double dy = (sy - ey) * factor;

                // part orthogonal to main line
                double ox = (sx - ex) * factorO;
                double oy = (sy - ey) * factorO;

                arrow1.setStartX(ex + dx - oy);
                arrow1.setStartY(ey + dy + ox);
                arrow2.setStartX(ex + dx + oy);
                arrow2.setStartY(ey + dy - ox);
            }
        };

        // add updater to properties
        startXProperty().addListener(updater);
        startYProperty().addListener(updater);
        endXProperty().addListener(updater);
        endYProperty().addListener(updater);
        updater.invalidated(null);
    }
}
