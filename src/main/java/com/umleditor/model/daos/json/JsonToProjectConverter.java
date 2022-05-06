package com.umleditor.model.daos.json;

import com.umleditor.model.UMLProject;
import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.classdiagram.enums.UMLRelationType;
import com.umleditor.model.classdiagram.exceptions.ClassIsNotPresentOnDiagramException;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLClassAttribute;
import com.umleditor.model.common.UMLClassMethod;
import com.umleditor.model.common.UMLElement;
import com.umleditor.model.common.enums.UMLElementModifier;
import com.umleditor.model.sequencediagram.UMLActor;
import com.umleditor.model.sequencediagram.UMLMessage;
import com.umleditor.model.sequencediagram.UMLSequenceDiagram;
import com.umleditor.model.sequencediagram.exceptions.ObjectIsNotPresentOnDiagramException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Converter From JSON string tp UMLProject object
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class JsonToProjectConverter {
    public static UMLProject jsonToProject(String json) throws ParseException {
        UMLProject newProject = new UMLProject();

        // Parsing Json
        JSONParser parser = new JSONParser();
        JSONObject root = (JSONObject) parser.parse(json);

        //Setting Project Name
        newProject.setName((String) root.get("name"));

        //Setting Project Classes
        List<UMLClass> classes = parseClasses((JSONArray) root.get("allClasses"));
        newProject.setAllClasses(classes);

        //Setting Project Class Diagrams
        List<UMLClassDiagram> classDiagrams = parseClassDiagrams((JSONArray) root.get("classDiagrams"), newProject);
        newProject.setClassDiagrams(classDiagrams);

        //Setting Project Sequence Diagrams
        List<UMLSequenceDiagram> sequenceDiagrams = parseSequenceDiagrams((JSONArray) root.get("sequenceDiagrams"), newProject);
        newProject.setSequenceDiagrams(sequenceDiagrams);

        return newProject;
    }

    private static List<UMLClass> parseClasses(JSONArray classes) {
        List<UMLClass> allClasses = new ArrayList<>();
        classes.forEach(jo -> {
            JSONObject jsonClass = (JSONObject) jo;
            UMLClass newbie = new UMLClass();
            newbie.setName((String) jsonClass.get("name"));
            newbie.setAbstract((Boolean)jsonClass.get("abstract"));
            List<UMLClassAttribute> fields = parseClassFields((JSONArray) jsonClass.get("fields"));
            newbie.setFields(fields);
            List<UMLClassMethod> methods = parseClassMethods((JSONArray) jsonClass.get("methods"));
            newbie.setMethods(methods);

            allClasses.add(newbie);
        });
        return allClasses;
    }

    private static List<UMLClassAttribute> parseClassFields(JSONArray fields) {
        List<UMLClassAttribute> allFields = new ArrayList<>();
        fields.forEach(jo -> {
            JSONObject jsonField = (JSONObject) jo;
            UMLClassAttribute newbie = new UMLClassAttribute();
            newbie.setName((String) jsonField.get("name"));
            String modifier = (String) jsonField.get("modifier");
            newbie.setModifier(UMLElementModifier.getModifierBySymbol(modifier));
            newbie.setType((String) jsonField.get("type"));

            allFields.add(newbie);
        });
        return allFields;
    }

    private static List<UMLClassMethod> parseClassMethods(JSONArray methods) {
        List<UMLClassMethod> allFields = new ArrayList<>();
        methods.forEach(jo -> {
            JSONObject jsonMethod = (JSONObject) jo;
            UMLClassMethod newbie = new UMLClassMethod();
            newbie.setName((String) jsonMethod.get("name"));

            String modifier = (String) jsonMethod.get("modifier");
            newbie.setModifier(UMLElementModifier.getModifierBySymbol(modifier));
            newbie.setType((String) jsonMethod.get("type"));

            List<String> argTypes = new ArrayList<>();
            JSONArray jsonArgTypes = (JSONArray)jsonMethod.get("argumentTypes");
            jsonArgTypes.forEach(ja -> argTypes.add((String)ja));
            newbie.setArgumentTypes(argTypes);

            allFields.add(newbie);
        });
        return allFields;
    }

    private static List<UMLClassDiagram> parseClassDiagrams(JSONArray jsonClassDiagrams, UMLProject newProject) {
        List<UMLClassDiagram> classDiagrams = new ArrayList<>();
        jsonClassDiagrams.forEach(cd -> {
            classDiagrams.add(parseClassDiagram((JSONObject) cd, newProject));
        });
        return classDiagrams;
    }

    private static List<UMLSequenceDiagram> parseSequenceDiagrams(JSONArray jsonSequenceDiagrams, UMLProject newProject) {
        List<UMLSequenceDiagram> sequenceDiagrams = new ArrayList<>();
        jsonSequenceDiagrams.forEach(sd -> {
            sequenceDiagrams.add(parseSequenceDiagram((JSONObject) sd, newProject));
        });
        return sequenceDiagrams;
    }

    private static UMLClassDiagram parseClassDiagram(JSONObject jsonDiagram, UMLProject project) {
        UMLClassDiagram diagram = project.createNewClassDiagram((String)jsonDiagram.get("name"));

        JSONArray classes = (JSONArray) jsonDiagram.get("classes");
        classes.forEach(jc -> {
            UMLClass clazz;
            try{
                clazz = project.getAllClasses().get((int)(long) jc);
            } catch (Exception ex) {
                throw new ClassIsNotPresentOnDiagramException("No such class ID exists: " + ((long) jc));
            }
            diagram.addClass(clazz);
        });

        JSONArray relations = (JSONArray) jsonDiagram.get("relations");
        relations.forEach(jr -> {
            JSONObject jsonRelation = (JSONObject) jr;
            UMLClass from;
            UMLClass to;
            int idFrom = (int)(long) jsonRelation.get("from");
            int idTo = (int)(long) jsonRelation.get("to");
            try {
                from = project.getAllClasses().get(idFrom);
            }
            catch (Exception ex) {
                throw new ObjectIsNotPresentOnDiagramException("No such class ID exists: " + idFrom);
            }
            try {
                to = project.getAllClasses().get(idTo);
            }
            catch (Exception ex) {
                throw new ObjectIsNotPresentOnDiagramException("No such class ID exists: " + idTo);
            }
            UMLRelationType type = UMLRelationType.getTypeBySymbol((String) jsonRelation.get("type"));
            diagram.addRelation(from, to, type);
        });

        return diagram;
    }

    private static UMLSequenceDiagram parseSequenceDiagram(JSONObject jsonDiagram, UMLProject project) {
        UMLSequenceDiagram diagram = project.createNewSequenceDiagram((String)jsonDiagram.get("name"));
        // Actors
        List<UMLActor> actors = new ArrayList<>();
        JSONArray jsonActors = (JSONArray) jsonDiagram.get("actors");
        jsonActors.forEach(ja -> {
            UMLActor actor = new UMLActor();
            actor.setName((String) ja);
            actors.add(actor);
        });

        // Object List Construction
        JSONArray jsonObjects = (JSONArray) jsonDiagram.get("objects");
        jsonObjects.forEach(jo -> {
            String sid = (String) jo;
            String type = String.valueOf(sid.charAt(sid.length() - 1));
            Integer id = Integer.parseInt(sid.substring(0, sid.length() - 1));
            UMLElement newbie;
            if(type.equals("c")) {
                try {
                    newbie = project.getAllClasses().get(id);
                } catch (Exception ex) {
                    throw new ObjectIsNotPresentOnDiagramException("No such class ID exists: " + sid);
                }
            }
            else {
                try {
                    newbie = actors.get(id);
                } catch (Exception ex) {
                    throw new ObjectIsNotPresentOnDiagramException("No such actor ID exists: " + sid);
                }
            }
            diagram.addObject(newbie);
        });

        //Messages
        JSONArray jsonMessages = (JSONArray) jsonDiagram.get("messages");
        jsonMessages.forEach(jm -> {
            JSONObject jsonMessage = (JSONObject) jm;
            // From Entity
            UMLElement from = getObject((String) jsonMessage.get("from"), project, actors);

            // To Entity
            UMLElement to = getObject((String) jsonMessage.get("to"), project, actors);

            // Message text
            String message = (String) jsonMessage.get("message");

            diagram.addMessage(from, to, message);
        });

        return diagram;
    }

    public static UMLElement getObject(String sid, UMLProject project, List<UMLActor> actors) {
        String type = String.valueOf(sid.charAt(sid.length() - 1));
        Integer id = Integer.parseInt(sid.substring(0, sid.length() - 1));
        if(type.equals("c")) {
            try {
                return project.getAllClasses().get(id);
            } catch (Exception ex) {
                throw new ObjectIsNotPresentOnDiagramException("No such class ID exists: " + sid);
            }
        }
        else {
            try {
                return actors.get(id);
            } catch (Exception ex) {
                throw new ObjectIsNotPresentOnDiagramException("No such actor ID exists: " + sid);
            }
        }
    }

}
