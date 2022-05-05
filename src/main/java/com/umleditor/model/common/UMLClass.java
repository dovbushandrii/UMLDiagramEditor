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

    public void addField(UMLClassAttribute field) {
        this.fields.add(field);
    }

    public void deleteField(UMLClassAttribute field) {
        this.fields.remove(field);
    }

    public void addMethod(UMLClassMethod method) {
        this.methods.add(method);
    }

    public void deleteMethod(UMLClassMethod method) {
        this.methods.remove(method);
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public void setFields(List<UMLClassAttribute> fields) {
        this.fields = fields;
    }

    public void setMethods(List<UMLClassMethod> methods) {
        this.methods = methods;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public List<UMLClassAttribute> getFields() {
        return fields;
    }

    public List<UMLClassMethod> getMethods() {
        return methods;
    }
}
