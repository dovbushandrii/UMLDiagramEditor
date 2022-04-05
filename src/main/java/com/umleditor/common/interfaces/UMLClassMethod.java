package com.umleditor.common.interfaces;

import java.util.List;

public interface UMLClassMethod extends UMLClassAttribute{
    List<String> getArgumentTypes();
    void setArgumentTypes(List<String> argumentTypes);
}