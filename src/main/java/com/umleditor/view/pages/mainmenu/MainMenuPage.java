package com.umleditor.view.pages.mainmenu;

import com.umleditor.context.AppContext;
import com.umleditor.controller.controllers.diagramload.ProjectLoadController;
import com.umleditor.controller.controllers.window.MainWindow;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.model.UMLProject;
import com.umleditor.view.pages.interfaces.ProjectDependentPage;
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
public class MainMenuPage implements ProjectDependentPage {

    private Pane root = new VBox();

    private Button openButton() {
        Button button = new Button();
        button.setText("Open Project");
        button.setOnAction(event ->{
            ProjectLoadController.loadProject();
        });
        return button;
    }

    private Button newButton() {
        Button button = new Button();
        button.setText("New Project");
        button.setOnAction(event ->{
            AppContext.loadUMLProject(new UMLProject());
            AppContext.refreshProjectDependentPages();
            MainWindow.setPage(AppPage.EDIT_PROJECT);
        });
        return button;
    }

    private Button continueButton() {
        Button button = new Button();
        button.setText("Current Project");
        button.setOnAction(event ->{
            MainWindow.setPage(AppPage.EDIT_PROJECT);
        });
        return button;
    }

    private Button saveButton() {
        Button button = new Button();
        button.setText("Save Project");
        button.setOnAction(event ->{
            ProjectLoadController.saveProject(AppContext.getUMLProject());
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
        constructPage();
        return root;
    }

    private void constructPage() {
        root.getChildren().clear();

        HBox menuBar = new HBox();

        menuBar.getChildren().addAll(openButton(),newButton());

        if(AppContext.getUMLProject() != null) {
            menuBar.getChildren().addAll(continueButton(), saveButton());
        }

        root.getChildren().add(menuBar);
        root.getChildren().add(whatsImplementedText());
        root.getChildren().add(guideText());
    }

    @Override
    public void updateProject(UMLProject project) {
        constructPage();
    }
}
