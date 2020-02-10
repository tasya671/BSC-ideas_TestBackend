package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ViewPayTracker extends JFrame implements View {

    public static final int DEFAULT_WIDTH = 1000;
    public static final int DEFAULT_HEIGHT = 1500;
    private JTextArea label;
    private JTextField field;
    private JTextArea allOperations;
    private JFileChooser chooser;
    private Controller controller;


    public ViewPayTracker() throws HeadlessException {

        setTitle("PayTracker");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        Font font = new Font("Yu Gothic Medium", Font.CENTER_BASELINE, 40);
        Font fontMenu = new Font("Yu Gothic Medium", Font.CENTER_BASELINE, 35);

        JPanel base = new JPanel();
        base.setLayout(new BorderLayout());

        // область вывода данных
        label = new JTextArea();
        label.setEditable(false);
        label.setFont(font);
        label.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        Insets insets = label.getInsets();
        label.setBorder(BorderFactory.createEmptyBorder(insets.top, 30, insets.bottom, insets.right));
        JScrollPane scrollPane = new JScrollPane(label);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // панель отображения всех операций
        allOperations = new JTextArea();
        allOperations.setEditable(false);
        allOperations.setText("    All operations:    \n");
        allOperations.setFont(font);
        allOperations.setBackground(Color.LIGHT_GRAY);
        Insets insetsOpp = allOperations.getInsets();
        allOperations.setBorder(BorderFactory.createEmptyBorder(insetsOpp.top, 30, insetsOpp.bottom, insetsOpp.right));
        JScrollPane paneOptions = new JScrollPane(allOperations);
        paneOptions.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // полe ввода данных
        field = new JTextField();
        field.setFont(font);
        field.setBackground(Color.CYAN);
        field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String payTrack = field.getText();
                field.setText("");
                try {
                    controller.putOneItem(payTrack); }
                catch (IllegalArgumentException | IOException exp){
                    label.append("\nInvalid data format"); }
            }
        });
        base.add(BorderLayout.SOUTH, field);
        base.add(BorderLayout.CENTER, scrollPane);
        base.add(BorderLayout.EAST, paneOptions);
        add(base);

        chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new FileNameExtensionFilter("File txt","txt"));

        // элементы меню
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("File");
        menu.setFont(fontMenu);
        menuBar.add(menu);
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setFont(fontMenu);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(fontMenu);
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogChooser();
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(openItem);
        menu.add(exitItem);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void refresh(String text){
        label.append("\n*****************");
        label.append("\nNew transact:\n");
        label.append(text);
    }

    public JTextField getField() {
        return field;
    }

    public JTextArea getAllOperations() {
        return allOperations;
    }

    private void dialogChooser(){
        field.setVisible(false);
        chooser.setCurrentDirectory(new File("."));
        int result = chooser.showOpenDialog(ViewPayTracker.this);
        if(result == JFileChooser.APPROVE_OPTION){
            String name = chooser.getSelectedFile().getPath();
            controller.putFromFiles(name);
        }
    }
}
