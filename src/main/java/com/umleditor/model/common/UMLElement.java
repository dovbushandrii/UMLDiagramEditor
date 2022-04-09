package com.umleditor.model.common;

import java.io.Serializable;

public class UMLElement implements Serializable {

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
