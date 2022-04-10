package com.umleditor.view.window.pages.sequencediagram;

import com.umleditor.controller.controllers.pagecontrol.interfaces.DiagramPageController;
import com.umleditor.controller.controllers.window.MainWindow;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.view.window.pages.interfaces.DiagramPageBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SequenceDiagramPageBuilder implements DiagramPageBuilder {
    @Override
    public Parent build(DiagramPageController controller) {
        VBox root = new VBox();
        Button button = new Button();
        button.setText("Back to Menu");
        button.setOnAction(event ->{
            MainWindow.setPage(AppPage.MAIN_MENU);
        });
        root.getChildren().add(button);
        return root;
    }
}
