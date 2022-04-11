/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file AppPage.java
 */
package com.umleditor.controller.enums;

/**
 * Application Page enum that defines name and title of pages
 * for Application Context
 */
public enum AppPage {
    MAIN_MENU ("UML Diagram Editor"),
    CLASS_DIAGRAM ("Edit Class Diagram"),
    // TODO: Replace "Under Construction" to "Edit Sequence Diagram" after SequenceDiagram will be implemented
    SEQUENCE_DIAGRAM ("Under Construction");

    private final String stageTitle;

    AppPage(String stageTitle) {
        this.stageTitle = stageTitle;
    }

    public String getStageTitle() {
        return stageTitle;
    }
}
