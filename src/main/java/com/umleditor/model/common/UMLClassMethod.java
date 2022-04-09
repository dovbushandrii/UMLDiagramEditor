package com.umleditor.model.common;

import java.util.ArrayList;
import java.util.List;

public class UMLClassMethod extends UMLClassAttribute {

    private List<String> argumentTypes = new ArrayList<>();

    public List<String> getArgumentTypes() {
        return this.argumentTypes;
    }

    public void setArgumentTypes(List<String> argumentTypes) {
        this.argumentTypes = argumentTypes;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }
}
