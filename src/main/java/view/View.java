package view;

import controller.Controller;
import javax.swing.*;


public interface View {

    void setController(Controller controller);
    void refresh(String text);
    JTextField getField();
    JTextArea getAllOperations();
}
