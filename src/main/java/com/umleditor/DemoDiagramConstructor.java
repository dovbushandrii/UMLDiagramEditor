package com.umleditor;

import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLClassAttribute;
import com.umleditor.model.common.UMLClassMethod;
import com.umleditor.model.common.enums.UMLElementModifier;
import com.umleditor.model.common.interfaces.UMLDiagram;

import java.util.Arrays;

public class DemoDiagramConstructor {
    public static UMLDiagram constructDemoDiagram() {
        UMLClassDiagram diagram = new UMLClassDiagram();
        diagram.setName("Dog-Owner-Diagram");
        diagram.addClass(constructDog());
        diagram.addClass(constructOwner());
        diagram.addClass(abstractClass());
        return diagram;
    }

    private static UMLClass constructDog() {
        UMLClass clazz = new UMLClass();
        clazz.setName("Dog");
        clazz.addField(new UMLClassAttribute(
            "dog_id",
                UMLElementModifier.PRIVATE,
                "int"
        ));
        clazz.addField(new UMLClassAttribute(
                "name",
                UMLElementModifier.PRIVATE,
                "string"
        ));
        clazz.addMethod(new UMLClassMethod(
                "getId",
                null,
                UMLElementModifier.PUBLIC,
                "int"
        ));
        clazz.addMethod(new UMLClassMethod(
                "setId",
                Arrays.asList("int"),
                UMLElementModifier.PUBLIC,
                "void"
        ));
        clazz.addMethod(new UMLClassMethod(
                "getName",
                null,
                UMLElementModifier.PUBLIC,
                "string"
        ));
        clazz.addMethod(new UMLClassMethod(
                "setName",
                Arrays.asList("string"),
                UMLElementModifier.PUBLIC,
                "void"
        ));
        return clazz;
    }

    private static UMLClass constructOwner() {
        UMLClass clazz = new UMLClass();
        clazz.setName("Owner");
        clazz.addField(new UMLClassAttribute(
                "ow_id",
                UMLElementModifier.PROTECTED,
                "int"
        ));
        clazz.addField(new UMLClassAttribute(
                "name",
                UMLElementModifier.PUBLIC,
                "string"
        ));
        clazz.addMethod(new UMLClassMethod(
                "getId",
                null,
                UMLElementModifier.PUBLIC,
                "int"
        ));
        clazz.addMethod(new UMLClassMethod(
                "setId",
                Arrays.asList("int"),
                UMLElementModifier.PUBLIC,
                "void"
        ));
        clazz.addMethod(new UMLClassMethod(
                "setName",
                Arrays.asList("string","string"),
                UMLElementModifier.PRIVATE,
                "void"
        ));
        return clazz;
    }

    private static UMLClass abstractClass() {
        UMLClass clazz = new UMLClass();
        clazz.setIsAbstract(true);
        clazz.setName("Object");
        clazz.addMethod(new UMLClassMethod(
                "toString",
                null,
                UMLElementModifier.PUBLIC,
                "string"
        ));
        clazz.addMethod(new UMLClassMethod(
                "notify",
                null,
                UMLElementModifier.PUBLIC,
                "void"
        ));
        clazz.addMethod(new UMLClassMethod(
                "wait",
                null,
                UMLElementModifier.PUBLIC,
                "void"
        ));
        clazz.addMethod(new UMLClassMethod(
                "delete",
                null,
                UMLElementModifier.PUBLIC,
                "bool"
        ));
        return clazz;
    }
}
