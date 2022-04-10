package com.umleditor.controller.enums;

public enum AppPage {
    MAIN_MENU ("UML Diagram Editor"),
    CLASS_DIAGRAM ("Edit Class Diagram"),
    // TODO: Replace "Under Construction" to "Edit Sequence Diagram" after SequenceDiagram implementation
    SEQUENCE_DIAGRAM ("Under Construction");

    private final String stageTitle;

    AppPage(String stageTitle) {
        this.stageTitle = stageTitle;
    }

    public String getStageTitle() {
        return stageTitle;
    }
}
