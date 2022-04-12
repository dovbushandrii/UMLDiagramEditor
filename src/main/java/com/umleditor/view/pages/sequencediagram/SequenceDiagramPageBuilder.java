package com.umleditor.view.pages.sequencediagram;

import com.umleditor.controller.controllers.pagecontrol.interfaces.DiagramPageController;
import com.umleditor.controller.controllers.window.MainWindow;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.view.pages.interfaces.DiagramPageBuilder;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Constructs page for Sequence Diagram editing.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
//TODO: Implement
public class SequenceDiagramPageBuilder implements DiagramPageBuilder {
    @Override
    public Pane build(DiagramPageController controller) {
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
