package com.umleditor.model.daos.json;

import com.umleditor.model.UMLProject;
import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLClassAttribute;
import com.umleditor.model.common.UMLClassMethod;
import com.umleditor.model.common.UMLElement;
import com.umleditor.model.sequencediagram.UMLActor;
import com.umleditor.model.sequencediagram.UMLSequenceDiagram;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * Converter From UMLProject object to JSON string
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class ProjectToJsonConverter {
    public static String projectToJson(UMLProject project) {
        JSONObject jsonProject = new JSONObject();
        jsonProject.put("name",project.getName());

        JSONArray jsonAllClasses = computeAllClasses(project);
        jsonProject.put("allClasses",jsonAllClasses);

        JSONArray jsonClassDiagrams = computeClassDiagrams(project);
        jsonProject.put("classDiagrams",jsonClassDiagrams);

        JSONArray jsonSequenceDiagrams = computeSequenceDiagrams(project);
        jsonProject.put("sequenceDiagrams",jsonSequenceDiagrams);

        return jsonProject.toJSONString();
    }

    private static JSONArray computeAllClasses(UMLProject project) {
        JSONArray jsonAllClasses = new JSONArray();
        List<UMLClass> allClasses = project.getAllClasses();
        allClasses.forEach(c -> {
            JSONObject jsonClass = new JSONObject();

            jsonClass.put("name",c.getName());

            jsonClass.put("abstract",c.isAbstract());

            JSONArray fields = computeClassFields(c);
            jsonClass.put("fields",fields);

            JSONArray methods = computeClassMethods(c);
            jsonClass.put("methods",methods);

            jsonAllClasses.add(jsonClass);
        });
        return jsonAllClasses;
    }

    private static JSONArray computeClassFields(UMLClass clazz) {
        JSONArray jsonFields = new JSONArray();
        List<UMLClassAttribute> fields = clazz.getFields();
        fields.forEach(f -> {
            JSONObject jsonField = new JSONObject();

            jsonField.put("name",f.getName());
            jsonField.put("modifier",f.getModifier().getModifierSymbol());
            jsonField.put("type", f.getType());

            jsonFields.add(jsonField);
        });
        return jsonFields;
    }

    private static JSONArray computeClassMethods(UMLClass clazz) {
        JSONArray jsonFields = new JSONArray();
        List<UMLClassMethod> methods = clazz.getMethods();
        methods.forEach(m -> {
            JSONObject jsonField = new JSONObject();

            jsonField.put("name",m.getName());
            jsonField.put("modifier",m.getModifier().getModifierSymbol());
            jsonField.put("type", m.getType());

            JSONArray jsonArgTypes = new JSONArray();
            m.getArgumentTypes().forEach(jsonArgTypes::add);

            jsonField.put("argumentTypes", jsonArgTypes);

            jsonFields.add(jsonField);
        });
        return jsonFields;
    }

    private static JSONArray computeClassDiagrams(UMLProject project) {
        JSONArray jsonClassDiagrams = new JSONArray();
        List<UMLClassDiagram> diagrams = project.getClassDiagrams();
        diagrams.forEach(d -> {
            JSONObject jsonDiagram = computeClassDiagram(d, project.getAllClasses());
            jsonClassDiagrams.add(jsonDiagram);
        });
        return jsonClassDiagrams;
    }

    private static JSONArray computeSequenceDiagrams(UMLProject project) {
        JSONArray jsonSequenceDiagrams = new JSONArray();
        List<UMLSequenceDiagram> diagrams = project.getSequenceDiagrams();
        diagrams.forEach(d -> {
            JSONObject jsonDiagram = computeSequenceDiagram(d, project.getAllClasses());
            jsonSequenceDiagrams.add(jsonDiagram);
        });
        return jsonSequenceDiagrams;
    }

    private static JSONObject computeClassDiagram(UMLClassDiagram diagram, List<UMLClass> allClasses) {
        JSONObject jsonDiagram = new JSONObject();

        jsonDiagram.put("name", diagram.getName());

        JSONArray jsonClassIndexes = new JSONArray();
        diagram.getAllClasses().forEach(dc -> jsonClassIndexes.add(allClasses.indexOf(dc)));
        jsonDiagram.put("classes",jsonClassIndexes);

        JSONArray jsonRelations = new JSONArray();
        diagram.getAllRelations().forEach(r -> {
            JSONObject jsonRelation = new JSONObject();
            jsonRelation.put("from",allClasses.indexOf(r.getFrom()));
            jsonRelation.put("to",allClasses.indexOf(r.getTo()));
            jsonRelation.put("type",r.getType().getTypeSymbol());
            jsonRelations.add(jsonRelation);
        });
        jsonDiagram.put("relations",jsonRelations);

        return jsonDiagram;
    }

    private static JSONObject computeSequenceDiagram(UMLSequenceDiagram diagram, List<UMLClass> allClasses) {
        JSONObject jsonDiagram = new JSONObject();

        jsonDiagram.put("name", diagram.getName());

        // Actors on Sequence Diagrams
        JSONArray jsonActors = new JSONArray();
        diagram.getAllActors().forEach(a -> jsonActors.add(a.getName()));
        jsonDiagram.put("actors", jsonActors);

        // Indexes of All objects on diagram
        JSONArray jsonObjects = new JSONArray();
        diagram.getAllObjects().forEach(o -> {
            if(o instanceof UMLActor) {
                List<UMLActor> actors = diagram.getAllActors();
                jsonObjects.add(actors.indexOf(o) + "a");
            }
            else {
                jsonObjects.add(allClasses.indexOf(o) + "c");
            }
        });
        jsonDiagram.put("objects",jsonObjects);

        // Messages between objects on diagram
        JSONArray jsonMessages = new JSONArray();
        diagram.getAllMessages().forEach(m -> {
            JSONObject jsonMessage = new JSONObject();

            UMLElement from = m.getFrom();
            if(from instanceof UMLActor) {
                List<UMLActor> actors = diagram.getAllActors();
                jsonMessage.put("from", actors.indexOf(from) + "a");
            }
            else {
                jsonMessage.put("from", allClasses.indexOf(from) + "c");
            }

            UMLElement to = m.getTo();
            if(to instanceof UMLActor) {
                List<UMLActor> actors = diagram.getAllActors();
                jsonMessage.put("to", actors.indexOf(to) + "a");
            }
            else {
                jsonMessage.put("to", allClasses.indexOf(to) + "c");
            }

            jsonMessage.put("message", m.getMessage());

            jsonMessages.add(jsonMessage);
        });
        jsonDiagram.put("messages",jsonMessages);

        return jsonDiagram;
    }

}
