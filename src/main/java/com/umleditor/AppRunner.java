package com.umleditor;

import com.umleditor.controller.controllers.window.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppRunner extends Application {
    public static void startApp(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MainWindow.start(primaryStage);
    }
}
