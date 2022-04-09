package com.umleditor.view.window.sequencediagram;

import com.umleditor.controller.controllers.pagecontrol.interfaces.DiagramPageController;
import com.umleditor.controller.controllers.window.MainWindow;
import com.umleditor.controller.enums.AppPage;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SequenceDiagramPageBuilder {
    public static Parent buildPage(DiagramPageController controller) {
        VBox root = new VBox();
        Button button = new Button();
        button.setText("SequenceDiagram");
        button.setOnAction(event ->{
            MainWindow.setPage(AppPage.MAIN_MENU);
        });
        root.getChildren().add(button);
        return root;
    }
}
