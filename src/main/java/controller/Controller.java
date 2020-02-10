package controller;

import model.Model;
import view.View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Controller {

    private Model model;
    private View viewPayTracker;
    private TimerTask timer;
    final private String EXIT = "quit";


    public Controller() {
        timer = new TimerTask() {
            @Override
            public void run() {
                viewPayTracker.refresh(model.print());
            }
        };
        Timer time = new Timer(true);
        time.scheduleAtFixedRate(timer, TimeUnit.MINUTES.toMillis(1), TimeUnit.MINUTES.toMillis(1));
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setViewPayTracker(View viewPayTracker) {
        this.viewPayTracker = viewPayTracker;
    }

    public void putOneItem (String str) throws IOException {
        if (str.equalsIgnoreCase(EXIT)){
            System.exit(0);
        }
        String [] element = str.trim().split(" ");
        if (element.length==2){
            Integer amount = Integer.parseInt(element[1]);
            model.addItem(element[0], amount);
            viewPayTracker.getAllOperations().append(str.trim()+"\n");
        } else throw new IOException("Invalid data format");
    }

    public void putFromFiles (String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String current;
            while ((current=reader.readLine())!=null){
                putOneItem(current);
            }
            viewPayTracker.getField().setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
