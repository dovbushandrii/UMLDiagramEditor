package com.umleditor.common.impl;

import com.umleditor.common.interfaces.UMLElement;

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
