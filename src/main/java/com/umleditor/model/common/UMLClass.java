package com.umleditor.model.common;

import java.util.ArrayList;
import java.util.List;

/**
 * UML Class DTO class
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
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

    public void addField(UMLClassAttribute field) {
        this.fields.add(field);
    }

    public List<UMLClassMethod> getMethods() {
        return this.methods;
    }

    public void setMethods(List<UMLClassMethod> methods) {
        this.methods = methods;
    }

    public void addMethod(UMLClassMethod method) {
        this.methods.add(method);
    }
}
