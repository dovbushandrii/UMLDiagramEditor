/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file PrimitivePageBuilder.java
 */
package com.umleditor.view.pages.interfaces;

import javafx.scene.layout.Pane;

/**
 * Page is primitive, when it does not need controller
 */
public interface PrimitivePageBuilder {
    Pane build();
}
