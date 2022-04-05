package com.umleditor.common.interfaces;

import java.io.Serializable;

public interface UMLElement extends Serializable {
    String getName();
    void setName(String name);
}
