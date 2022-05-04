package com.umleditor;

import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.classdiagram.enums.UMLRelationType;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLClassAttribute;
import com.umleditor.model.common.UMLClassMethod;
import com.umleditor.model.common.enums.UMLElementModifier;
import com.umleditor.model.common.interfaces.UMLDiagram;

import java.util.Arrays;

public class DemoDiagramConstructor {
    /*
    public static UMLDiagram constructDemoDiagram() {
        UMLClassDiagram diagram = new UMLClassDiagram();
        diagram.setName("Dog-Owner-Diagram");
        UMLClass dog = constructDog();
        UMLClass owner = constructOwner();
        UMLClass food = constructFood();
        UMLClass object = abstractClass();
        diagram.addClass(dog);
        diagram.addClass(owner);
        diagram.addClass(food);
        diagram.addClass(object);

        diagram.addRelation(dog,owner, UMLRelationType.ASSOCIATION);
        diagram.addRelation(owner,object, UMLRelationType.AGGREGATION);
        diagram.addRelation(object,food, UMLRelationType.COMPOSITION);
        diagram.addRelation(food,dog, UMLRelationType.GENERALIZATION);

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

    private static UMLClass constructFood() {
        UMLClass clazz = new UMLClass();
        clazz.setName("Food");
        clazz.addField(new UMLClassAttribute(
                "food_id",
                UMLElementModifier.PRIVATE,
                "int"
        ));
        clazz.addField(new UMLClassAttribute(
                "name",
                UMLElementModifier.PRIVATE,
                "string"
        ));
        clazz.addField(new UMLClassAttribute(
                "weight",
                UMLElementModifier.PRIVATE,
                "double"
        ));
        clazz.addMethod(new UMLClassMethod(
                "getId",
                null,
                UMLElementModifier.PUBLIC,
                "int"
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
        clazz.addMethod(new UMLClassMethod(
                "getWeight",
                null,
                UMLElementModifier.PUBLIC,
                "double"
        ));
        clazz.addMethod(new UMLClassMethod(
                "setWeight",
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

     */
}
