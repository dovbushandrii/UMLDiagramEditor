package com.umleditor.model.common.impl;

import com.umleditor.model.common.interfaces.UMLClass;
import com.umleditor.model.common.interfaces.UMLClassAttribute;
import com.umleditor.model.common.interfaces.UMLClassMethod;

import java.util.ArrayList;
import java.util.List;

public class UMLClassImpl extends UMLElementImpl implements UMLClass {

    private boolean isAbstract = false;
    private List<UMLClassAttribute> fields = new ArrayList<>();
    private List<UMLClassMethod> methods = new ArrayList<>();

    @Override
    public boolean getIsAbstract() {
        return isAbstract;
    }

    @Override
    public void setIsAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    @Override
    public List<UMLClassAttribute> getFields() {
        return this.fields;
    }

    @Override
    public void setFields(List<UMLClassAttribute> fields) {
        this.fields = fields;
    }

    @Override
    public List<UMLClassMethod> getMethods() {
        return null;
    }

    @Override
    public void setMethods(List<UMLClassMethod> methods) {
        this.methods = methods;
    }
}
