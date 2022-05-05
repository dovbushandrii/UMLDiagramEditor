package com.umleditor.view.pages.classdiagram;

import com.umleditor.DemoDiagramConstructor;
import com.umleditor.model.UMLProject;
import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.view.pages.interfaces.DiagramEditSpace;
import com.umleditor.view.pages.interfaces.Shortcuts;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Class that control edit space for Class Diagram
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class ClassDiagramEditSpace implements DiagramEditSpace {

    public final Pane editSpace;
    public UMLProject project;
    public int selectedDiagram = 0;
    public ClassDiagramEditTab currentTab = null;

    public ClassDiagramEditSpace(UMLProject project) {
        this.editSpace = new VBox();
        this.project = project;
        constructEditSpace();
    }

    @Override
    public Pane getEditSpace() {
        return this.editSpace;
    }

    @Override
    public void updateEditSpace(UMLProject project) {
        this.project = project;
        constructEditSpace();
    }

    private Button newButton() {
        Button button = new Button();
        button.setText("New");
        button.setOnAction(event -> {
            UMLClassDiagram diagram = project.createNewClassDiagram("Diagram " + (project.getClassDiagrams().size() + 1));
            selectedDiagram = project.getClassDiagrams().size() - 1;
            constructEditSpace();
        });
        return button;
    }

    private Button deleteButton() {
        Button button = new Button();
        button.setText("Delete");
        button.setOnAction(event -> {
            try {
                project.getClassDiagrams().remove(selectedDiagram);
            }catch (Exception e) {}
            selectedDiagram = 0;
            constructEditSpace();
        });
        return button;
    }

    private Button tabButton(String name, int index) {
        Button button = new Button();
        button.setText(name);
        button.setOnAction(event -> {
            this.selectedDiagram = index;
            constructEditSpace();
        });
        return button;
    }

    private void constructEditSpace() {
        this.editSpace.getChildren().clear();

        // Buttons
        HBox tabs = new HBox();
        Shortcuts.bindWidth(tabs, this.editSpace);

        tabs.getChildren().addAll(newButton(), deleteButton());

        this.editSpace.getChildren().add(tabs);

        if (project != null) {
            List<UMLClassDiagram> diagrams = project.getClassDiagrams();
            for (int i = 0; i < diagrams.size(); i++) {
                Button tab = tabButton(diagrams.get(i).getName(), i);
                tabs.getChildren().add(tab);
            }
            if(diagrams.size() > 0) {
                currentTab = new ClassDiagramEditTab(diagrams.get(selectedDiagram));
                this.editSpace.getChildren().add(currentTab.getEditTab());
            }
        }
    }
}
