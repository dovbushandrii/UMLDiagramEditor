package com.umleditor.common.interfaces;

import java.util.List;

public interface UMLClass extends UMLElement{
    boolean getIsAbstract();
    void setIsAbstract(boolean isAbstract);
    List<UMLClassAttribute> getFields();
    void setFields(List<UMLClassAttribute> fields);
    List<UMLClassMethod> getMethods();
    void setMethods(List<UMLClassMethod> methods);
}
