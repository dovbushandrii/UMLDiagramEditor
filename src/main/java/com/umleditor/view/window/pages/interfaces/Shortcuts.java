package com.umleditor.view.window.pages.interfaces;

import javafx.scene.layout.Region;
import javafx.stage.Window;

public interface Shortcuts {

    static void bindWidth(Region a1, Region a2) {
        a1.prefWidthProperty().bind(a2.widthProperty());
    }
    static void bindHeight(Region a1, Region a2) {
        a1.prefHeightProperty().bind(a2.heightProperty());
    }

    static void bindWidth(Region a1, Window a2) {
        a1.prefWidthProperty().bind(a2.widthProperty());
    }
    static void bindHeight(Region a1, Window a2) {
        a1.prefHeightProperty().bind(a2.heightProperty());
    }

    static void bindPrefWidth(Region a1, Region a2) {
        a1.prefWidthProperty().bind(a2.prefWidthProperty());
    }
    static void bindPrefHeight(Region a1, Region a2) {
        a1.prefHeightProperty().bind(a2.prefHeightProperty());
    }

    static void setFixedWidth(Region r, double width) {
        r.setMaxWidth(width);
        r.setMinWidth(width);
    }
    static void setFixedHeight(Region r, double height) {
        r.setMaxHeight(height);
        r.setMinHeight(height);
    }
}
