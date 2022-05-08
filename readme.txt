Description:
Simple UML Class&Sequence Diagrams editor

Authors:
- Andrii Dovbush (xdovbu00)
- Anastasiia Oberemko (xobere00)
------------------------------------------
How to compile:
Navigate to root directory (xobere00) and run:
1.'mvn javadoc:javadoc clean'
2.'mvn install'
3.'rm -r target'
------------------------------------------
How to run:
Navigate to root directory (xobere00) and run: 'java -jar dest/ija-app.jar'
------------------------------------------
To open project file click 'Open' button from main menu or you can do it from
Edit Project page when you have another project opened.

Each project contains a list of classes, list of class diagrams and list of
sequence diagrams.

You can also create new project by clicking 'New Project' button.

After you will be redirected to 'Edit Project' page. From there you can
change project name, go to edit Class Diagrams and edit Sequence Diagrams.

While Editing Class Diagram Classes, or Sequence Diagram Objects, you can create
new class in project (and create New Actor in SD), add existing class from project, if it
is not presented on diagram, delete class/object only from diagram or perform Full Delete from
project. Actors are part of Sequence Diagram, not part of the project, so they cannot be used
in other diagrams.

Project saved as JSON inside .uml files.
