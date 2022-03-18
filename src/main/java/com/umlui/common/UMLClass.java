package com.umlui.common;

import java.util.List;

public interface UMLClass extends UMLElement{
    boolean isAbstract();
    List<UMLClassAttribute> getFields();
    List<UMLClassMethod> getMethods();
}
