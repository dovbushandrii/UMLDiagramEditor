package com.umleditor;

import com.umleditor.context.AppConfig;
import com.umleditor.controller.controllers.window.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class AppRunner extends Application {
    public static void startApp(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            AppConfig.initializeContext();
            MainWindow.start(primaryStage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
