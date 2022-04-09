package com.umleditor.controller.enums;

public enum AppPage {
    MAIN_MENU ("UML Diagram Editor"),
    CLASS_DIAGRAM ("Edit Class Diagram"),
    SEQUENCE_DIAGRAM ("Edit Sequence Diagram");

    private final String stageTitle;

    AppPage(String stageTitle) {
        this.stageTitle = stageTitle;
    }

    public String getStageTitle() {
        return stageTitle;
    }
}
