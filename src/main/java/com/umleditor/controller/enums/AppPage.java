package com.umleditor.controller.enums;

/**
 * Application Page enum that defines name and title of pages
 * for Application Context.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public enum AppPage {
    MAIN_MENU ("UML Diagram Editor"),
    EDIT_PROJECT ("Edit Project"),
    EDIT_CLASS_DIAGRAMS("Edit Class Diagram"),
    EDIT_SEQUENCE_DIAGRAMS ("Edit Sequence Diagram");

    private final String stageTitle;

    AppPage(String stageTitle) {
        this.stageTitle = stageTitle;
    }

    public String getStageTitle() {
        return stageTitle;
    }
}
