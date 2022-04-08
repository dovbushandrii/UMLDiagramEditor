package com.umleditor;

import com.umleditor.controller.controllers.windowcontrol.MainWindowController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainRunner extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MainWindowController.start(primaryStage);
    }
}
