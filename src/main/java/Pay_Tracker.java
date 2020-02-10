import controller.Controller;
import view.ViewPayTracker;
import model.MoneyModel;

import javax.swing.*;
import java.awt.*;

public class Pay_Tracker  {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MoneyModel money = new MoneyModel();
                Controller controller = new Controller();
                ViewPayTracker frame = new ViewPayTracker();
                controller.setModel(money);
                controller.setViewPayTracker(frame);
                frame.setController(controller);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}
