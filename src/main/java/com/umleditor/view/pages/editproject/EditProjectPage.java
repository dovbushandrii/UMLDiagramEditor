package com.umleditor.view.pages.editproject;

import com.umleditor.context.AppContext;
import com.umleditor.controller.controllers.diagramload.ProjectLoadController;
import com.umleditor.controller.controllers.window.MainWindow;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.model.UMLProject;
import com.umleditor.view.pages.interfaces.ProjectDependentPage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class EditProjectPage implements ProjectDependentPage {

    private UMLProject project;
    private final Pane root = new VBox();

    private Button backButton() {
        Button button = new Button();
        button.setText("Back");
        button.setOnAction(event ->{
            MainWindow.setPage(AppPage.MAIN_MENU);
        });
        return button;
    }

    private Button newButton() {
        Button button = new Button();
        button.setText("New");
        button.setOnAction(event ->{
            AppContext.loadUMLProject(new UMLProject());
            AppContext.refreshProjectDependentPages();
            MainWindow.setPage(AppPage.EDIT_PROJECT);
        });
        return button;
    }

    private Button openButton() {
        Button button = new Button();
        button.setText("Open");
        button.setOnAction(event ->{
            ProjectLoadController.loadProject();
        });
        return button;
    }

    private Button saveButton() {
        Button button = new Button();
        button.setText("Save");
        button.setOnAction(event ->{
            ProjectLoadController.saveProject(project);
        });
        return button;
    }

    private Button EditCDButton() {
        Button button = new Button();
        button.setText("Edit Class Diagrams");
        button.setOnAction(event ->{
            MainWindow.setPage(AppPage.EDIT_CLASS_DIAGRAMS);
        });
        return button;
    }

    private Button EditSDButton() {
        Button button = new Button();
        button.setText("Edit Sequence Diagrams");
        button.setOnAction(event ->{
            MainWindow.setPage(AppPage.EDIT_SEQUENCE_DIAGRAMS);
        });
        return button;
    }

    @Override
    public Pane build() {
        this.project = AppContext.getUMLProject();
        constructPage();
        return root;
    }

    private void constructPage() {
        root.getChildren().clear();

        HBox menuBar = new HBox();
        menuBar.getChildren().addAll(backButton(), newButton(), openButton(), saveButton(), EditCDButton(), EditSDButton());

        root.getChildren().addAll(menuBar);

        if(project != null) {
            HBox line = new HBox();
            Label prjName = new Label("Project name:");
            TextField projectName = new TextField(project.getName());
            projectName.textProperty().addListener((o,ov,nv) -> {
                project.setName(projectName.getText());
            });
            line.getChildren().addAll(prjName, projectName);
            root.getChildren().addAll(line);
        }
    }

    @Override
    public void updateProject(UMLProject project) {
        this.project = project;
        constructPage();
    }
}
