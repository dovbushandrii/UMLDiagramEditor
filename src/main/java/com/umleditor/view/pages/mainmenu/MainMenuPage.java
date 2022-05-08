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

    private Text guideText() {
        Text text = new Text();
        text.setText("To open project file click 'Open' button from main menu or you can do it from\n" +
                "Edit Project page when you have another project opened.\n" +
                "\n" +
                "Each project contains a list of classes, list of class diagrams and list of\n" +
                "sequence diagrams.\n" +
                "\n" +
                "You can also create new project by clicking 'New Project' button.\n" +
                "\n" +
                "After you will be redirected to 'Edit Project' page. From there you can\n" +
                "change project name, go to edit Class Diagrams and edit Sequence Diagrams.\n" +
                "\n" +
                "While Editing Class Diagram Classes, or Sequence Diagram Objects, you can create\n" +
                "new class in project (and create New Actor in SD), add existing class from project, if it \n" +
                "is not presented on diagram, delete class/object only from diagram or perform Full Delete from\n" +
                "project. Actors are part of Sequence Diagram, not part of the project, so they cannot be used\n" +
                "in other diagrams.\n\n" +
                "Project saved as JSON inside .uml files.\n");
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
        root.getChildren().add(guideText());
    }

    @Override
    public void updateProject(UMLProject project) {
        constructPage();
    }
}
