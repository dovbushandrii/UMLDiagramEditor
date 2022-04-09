package com.umleditor.model.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UMLClass extends UMLElement {

    private boolean isAbstract = false;
    private List<UMLClassAttribute> fields = new ArrayList<>();
    private List<UMLClassMethod> methods = new ArrayList<>();

    public boolean getIsAbstract() {
        return isAbstract;
    }

    public void setIsAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    public List<UMLClassAttribute> getFields() {
        return this.fields;
    }

    public void setFields(List<UMLClassAttribute> fields) {
        this.fields = fields;
    }

    public List<UMLClassMethod> getMethods() {
        return this.methods;
    }

    public void setMethods(List<UMLClassMethod> methods) {
        this.methods = methods;
    }
}
