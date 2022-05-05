package com.umleditor;

import com.umleditor.context.AppContext;
import com.umleditor.model.UMLProject;
import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.classdiagram.enums.UMLRelationType;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLClassAttribute;
import com.umleditor.model.common.UMLClassMethod;
import com.umleditor.model.common.enums.UMLElementModifier;
import com.umleditor.model.common.interfaces.UMLDiagram;

import java.util.Arrays;

public class DemoDiagramConstructor {
    public static void constructDemoProject(UMLClassDiagram diagram) {

        UMLClass dog = constructDog();
        UMLClass owner = constructOwner();
        UMLClass food = constructFood();
        UMLClass object = abstractClass();
        diagram.fullAddClass(dog);
        diagram.fullAddClass(owner);
        diagram.fullAddClass(food);
        diagram.fullAddClass(object);

        diagram.addRelation(dog,owner, UMLRelationType.ASSOCIATION);
        diagram.addRelation(owner,object, UMLRelationType.AGGREGATION);
        diagram.addRelation(object,food, UMLRelationType.COMPOSITION);
        diagram.addRelation(food,dog, UMLRelationType.GENERALIZATION);
    }

    private static UMLClass constructDog() {
        UMLClass clazz = new UMLClass();
        clazz.setName("Dog");
        return clazz;
    }

    private static UMLClass constructFood() {
        UMLClass clazz = new UMLClass();
        clazz.setName("Food");
        return clazz;
    }

    private static UMLClass constructOwner() {
        UMLClass clazz = new UMLClass();
        clazz.setName("Owner");
        return clazz;
    }

    private static UMLClass abstractClass() {
        UMLClass clazz = new UMLClass();
        clazz.setAbstract(true);
        clazz.setName("Object");
        return clazz;
    }
}
