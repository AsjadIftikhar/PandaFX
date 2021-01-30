package pandaFX;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class Stu_TableMessage {

    private SimpleStringProperty Messages;

    public Stu_TableMessage(String m) {

        Messages = new SimpleStringProperty(m);

    }

    public String getMessages() {
        return Messages.get();
    }

    public SimpleStringProperty messagesProperty() {
        return Messages;
    }

    public void setMessages(String messages) {
        this.Messages.set(messages);
    }
}