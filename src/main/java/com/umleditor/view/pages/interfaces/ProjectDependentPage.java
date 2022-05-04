package com.umleditor.view.pages.interfaces;

import com.umleditor.model.UMLProject;

public interface ProjectDependentPage extends ProjectIndependentPage {
    void updateProject(UMLProject project);
}
