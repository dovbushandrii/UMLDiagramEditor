package com.umleditor.model.common.impl;

import com.umleditor.model.common.interfaces.UMLElement;

public class UMLElementImpl implements UMLElement {

    private String name;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
