package com.umleditor.view.pages.mainmenu;

import com.umleditor.controller.controllers.diagramload.DiagramLoadController;
import com.umleditor.controller.controllers.window.MainWindow;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.view.pages.interfaces.PrimitivePageBuilder;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Builder for Main Menu Page.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class MainMenuPageBuilder implements PrimitivePageBuilder {

    private Button openButton() {
        Button button = new Button();
        button.setText("Open File");
        button.setOnAction(event ->{
            DiagramLoadController.loadDiagram();
        });
        return button;
    }

    private Button classDiagramButton() {
        Button button = new Button();
        button.setText("Class Diagram Edit");
        button.setOnAction(event ->{
            MainWindow.setPage(AppPage.CLASS_DIAGRAM);
        });
        return button;
    }

    private Button sequenceDiagramButton() {
        Button button = new Button();
        button.setText("Sequence Diagram Edit");
        button.setOnAction(event ->{
            MainWindow.setPage(AppPage.SEQUENCE_DIAGRAM);
        });
        return button;
    }

    private Text whatsImplementedText() {
        Text text = new Text();
        text.setText("What's implemented on 12 April:\n" +
                "- You can open '*.uml' files with Class Diagram in it.\n" +
                "- You can save '*.uml' files with Class Diagram in it.\n" +
                "- You can view Class Diagram with draggable elements.\n" +
                "- Example file is included: 'data/test_class_diagram.uml' at root folder\n");
        text.setFont(Font.font(13));
        return text;
    }
    private Text guideText() {
        Text text = new Text();
        text.setText("To open diagram file click 'Open' button from main menu or\n" +
                "go to 'Class Diagram Edit' -> 'Open'.\n" +
                "After the file is opened, drag class elements how you want.\n" +
                "\n" +
                "To save diagram click 'Save' button on Class Diagram Edit page.\n" +
                "Click 'New' button on Class Diagram Edit page to remove previous diagram\n" +
                "from edit space.");
        text.setFont(Font.font(13));
        return text;
    }

    @Override
    public Pane build() {
        VBox root = new VBox();
        HBox menuBar = new HBox();

        menuBar.getChildren().add(openButton());
        menuBar.getChildren().add(classDiagramButton());
        menuBar.getChildren().add(sequenceDiagramButton());

        root.getChildren().add(menuBar);
        root.getChildren().add(whatsImplementedText());
        root.getChildren().add(guideText());

        return root;
    }
}
