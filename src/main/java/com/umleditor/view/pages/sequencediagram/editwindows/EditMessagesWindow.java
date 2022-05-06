package com.umleditor.view.pages.sequencediagram.editwindows;

import com.umleditor.model.common.UMLElement;
import com.umleditor.model.sequencediagram.UMLMessage;
import com.umleditor.model.sequencediagram.UMLSequenceDiagram;
import com.umleditor.view.errorwindow.ErrorWindow;
import com.umleditor.view.pages.interfaces.Shortcuts;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Modal Window for message editing in Sequence Diagram
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class EditMessagesWindow {
    private Stage window;
    private ListView<UMLMessage> messageList;
    private UMLSequenceDiagram diagram;

    public EditMessagesWindow(UMLSequenceDiagram diagram) {
        this.diagram = diagram;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Pane mainPane = new VBox();

        messageList = constructMessageList(diagram);
        Button addMessage = new Button("New Message");
        addMessage.setOnAction(e -> {
            if (diagram.getAllObjects().size() > 1) {
                UMLMessage newbie = new UMLMessage();
                newbie.setFrom(diagram.getAllObjects().get(0));
                newbie.setTo(diagram.getAllObjects().get(1));
                try {
                    diagram.addMessage(newbie);
                    messageList.getItems().setAll(diagram.getAllMessages());
                    messageList.getSelectionModel().select(newbie);
                    double height = 30.0 * diagram.getAllMessages().size();
                    messageList.setMinHeight(height);
                    messageList.setMaxHeight(height);
                } catch (Exception exception) {
                    ErrorWindow.showError("Cannot add new message", exception.getMessage());
                }
            }
        });

        Button deleteMessage = new Button("Delete Selected Message");
        deleteMessage.setOnAction(e -> {
            UMLMessage umlMessage = messageList.getSelectionModel().getSelectedItem();
            if (umlMessage != null) {
                diagram.deleteMessage(umlMessage);
                messageList.getItems().remove(umlMessage);
                double height = 30.0 * diagram.getAllMessages().size();
                messageList.setMinHeight(height);
                messageList.setMaxHeight(height);
            }
        });

        Button moveUp = new Button("Move Up Selected");
        moveUp.setOnAction(e -> {
            UMLMessage umlMessage = messageList.getSelectionModel().getSelectedItem();
            if (umlMessage != null) {
                diagram.moveUpMessage(umlMessage);
                messageList.getItems().setAll(diagram.getAllMessages());
                messageList.getSelectionModel().select(umlMessage);
                double height = 30.0 * diagram.getAllMessages().size();
                messageList.setMinHeight(height);
                messageList.setMaxHeight(height);
            }
        });

        Button moveDown = new Button("Move Down Selected");
        moveDown.setOnAction(e -> {
            UMLMessage umlMessage = messageList.getSelectionModel().getSelectedItem();
            if (umlMessage != null) {
                diagram.moveDownMessage(umlMessage);
                messageList.getItems().setAll(diagram.getAllMessages());
                messageList.getSelectionModel().select(umlMessage);
                double height = 30.0 * diagram.getAllMessages().size();
                messageList.setMinHeight(height);
                messageList.setMaxHeight(height);
            }
        });

        Button closeButton = new Button("Save and Close");
        closeButton.setOnAction(e -> {
            window.close();
        });

        VBox buttonMenu = new VBox();
        HBox editButtons = new HBox();
        editButtons.getChildren().addAll(addMessage, deleteMessage);

        HBox moveButtons = new HBox();
        moveButtons.getChildren().addAll(moveUp, moveDown);

        buttonMenu.getChildren().addAll(editButtons, moveButtons);

        mainPane.getChildren().addAll(messageList, buttonMenu, closeButton);

        Scene scene = new Scene(mainPane, 700, 500);

        window.setScene(scene);
        window.setTitle("Edit Relations");
    }

    public void show() {
        window.showAndWait();
    }

    private ListView<UMLMessage> constructMessageList(UMLSequenceDiagram diagram) {
        ListView<UMLMessage> messageList = new ListView<>();
        if (diagram.getAllClasses().size() > 0) {
            messageList.getItems().setAll(diagram.getAllMessages());
            setRelationsCellFactory(messageList);
        }

        messageList.setEditable(true);

        messageList.setFixedCellSize(30);

        double height = 30.0 * diagram.getAllMessages().size();
        messageList.setMinHeight(height);
        messageList.setMaxHeight(height);
        return messageList;
    }

    private void setCellFactory(ListView<UMLElement> elements) {
        elements.setCellFactory(new Callback<ListView<UMLElement>,
                ListCell<UMLElement>>() {
            @Override
            public ListCell<UMLElement> call(ListView<UMLElement> list) {
                return new ListCell<UMLElement>() {
                    @Override
                    public void updateItem(UMLElement element, boolean empty) {
                        super.updateItem(element, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else if (element != null) {
                            setText(null);
                            setGraphic(new Label(element.getName()));
                        } else {
                            setText("null");
                            setGraphic(null);
                        }
                    }
                };
            }
        });
    }

    private ListView<UMLElement> constructObjectsList(UMLSequenceDiagram diagram) {
        ListView<UMLElement> objectList = new ListView<>();
        objectList.getItems().setAll(diagram.getAllObjects());
        setCellFactory(objectList);
        objectList.setEditable(false);
        objectList.setFixedCellSize(30);
        Shortcuts.setFixedHeight(objectList, 30);
        return objectList;
    }

    private Pane constructObjectCell(UMLMessage message) {
        HBox cell = new HBox();

        // From Object
        ListView<UMLElement> fromObject = constructObjectsList(diagram);
        fromObject.getSelectionModel().select(message.getFrom());

        // Message
        TextField messageText = new TextField(message.getMessage());
        messageText.textProperty().addListener((o, ov, nv) -> {
            message.setMessage(messageText.getText());
        });

        // To Object
        ListView<UMLElement> toObject = constructObjectsList(diagram);
        toObject.getSelectionModel().select(message.getTo());

        fromObject.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) -> {
            UMLElement selectFrom = fromObject.getSelectionModel().getSelectedItem();
            message.setFrom(selectFrom);
        });
        toObject.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) -> {
            UMLElement selectTo = toObject.getSelectionModel().getSelectedItem();
            message.setTo(selectTo);
        });

        cell.getChildren().addAll(fromObject, messageText, toObject);

        return cell;
    }

    private void setRelationsCellFactory(ListView<UMLMessage> messageList) {
        messageList.setCellFactory(new Callback<ListView<UMLMessage>, ListCell<UMLMessage>>() {
            @Override
            public ListCell<UMLMessage> call(ListView<UMLMessage> umlClassAttributeListView) {
                return new ListCell<UMLMessage>() {
                    @Override
                    public void updateItem(UMLMessage message, boolean empty) {
                        super.updateItem(message, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else if (message != null) {
                            setText(null);
                            Pane cell = constructObjectCell(message);
                            setGraphic(cell);
                        } else {
                            setText("null");
                            setGraphic(null);
                        }
                    }
                };
            }
        });
    }
}
