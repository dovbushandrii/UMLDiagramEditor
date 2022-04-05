package com.umleditor.common.impl;

import com.umleditor.common.interfaces.UMLClassMethod;

import java.util.ArrayList;
import java.util.List;

public class UMLClassMethodImpl extends UMLClassAttributeImpl implements UMLClassMethod {

    private List<String> argumentTypes = new ArrayList<>();

    @Override
    public List<String> getArgumentTypes() {
        return this.argumentTypes;
    }

    @Override
    public void setArgumentTypes(List<String> argumentTypes) {
        this.argumentTypes = argumentTypes;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }
}
