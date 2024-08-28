package example.micronaut.client_app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewClient {


    // Создание таблицы в качестве параметра класса, для возможности ее обновления
    private static JTable table = new JTable(
            DataConverter.convertFromListToArray(Commands.loadDataFromDataBase()),
            DataConverter.getColumnNames()
    ) {
        @Override
        public boolean isCellEditable(int i, int i1) {
            return false;
        }
    };
    // Команда для обновления данных таблицы
    private static void updateTable() {
        table = new JTable(
                DataConverter.convertFromListToArray(Commands.loadDataFromDataBase()),
                DataConverter.getColumnNames()
        ) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
    }
    // Создание панели прокрутки, внутри которой будет лежать наша таблица
    private static JScrollPane scrollPane = new JScrollPane(table);
    // Команда для обновления компонента прокрутки
    private static void updScrollPane() {
        scrollPane = new JScrollPane(table);
    }

    public static void run() {

        JFrame generalFrame = new JFrame("TEST APP");
        generalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generalFrame.setSize(1024, 512);
        generalFrame.setLocationRelativeTo(null);
        generalFrame.setResizable(false);

        JButton generalAddButton = new JButton("ADD PERSONA");
        JButton generalDeleteButton = new JButton("DELETE PERSONA");

        JPanel generalPanel = new JPanel();
        generalPanel.add(generalAddButton, BorderLayout.WEST);
        generalPanel.add(generalDeleteButton, BorderLayout.EAST);

        generalFrame.add(scrollPane, BorderLayout.NORTH);
        generalFrame.add(generalPanel, BorderLayout.SOUTH);

        generalFrame.setVisible(true);

        generalDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generalDeleteBtnPressed(generalFrame);
            }
        });
        generalAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generalAddBtnPressed(generalFrame);
            }
        });

    }

    private static void generalDeleteBtnPressed(JFrame generalFrame) {
        JFrame deleteFrame = new JFrame("DELETE");
        deleteFrame.setSize(256, 256);
        deleteFrame.setLocationRelativeTo(null);
        deleteFrame.setResizable(false);
        deleteFrame.setLayout(new BorderLayout());
        JPanel deletePanel = new JPanel(null);
        deleteFrame.add(deletePanel, BorderLayout.CENTER);
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 20, 200, 20);
        JTextField idTextField = new JTextField("");
        idTextField.setBounds(20,50,200,20);
        JButton deleteBtnDelFrame = new JButton("DELETE");
        deleteBtnDelFrame.setBounds(20, 150, 100, 20);
        JButton cancelBtnDelFrame = new JButton("CANCEL");
        cancelBtnDelFrame.setBounds(120, 150, 100, 20);
        deletePanel.add(idTextField);
        deletePanel.add(idLabel);
        deletePanel.add(deleteBtnDelFrame);
        deletePanel.add(cancelBtnDelFrame);
        deleteFrame.setVisible(true);

        deleteBtnDelFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Commands.deleteById(Integer.parseInt(idTextField.getText()));
                generalFrame.getContentPane().setVisible(false);
                generalFrame.remove(scrollPane);
                updateTable();
                updScrollPane();
                generalFrame.add(scrollPane);
                generalFrame.getContentPane().setVisible(true);
                deleteFrame.setVisible(false);
            }
        });
        cancelBtnDelFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFrame.setVisible(false);
            }
        });
    }

    private static void generalAddBtnPressed(JFrame generalFrame) {
        JFrame addFrame = new JFrame("ADD");
        addFrame.setSize(256, 256);
        addFrame.setLocationRelativeTo(null);
        addFrame.setResizable(false);
        addFrame.setLayout(new BorderLayout());
        JPanel addPanel = new JPanel(null);
        addFrame.add(addPanel, BorderLayout.CENTER);
        JLabel nameLabel = new JLabel("NAME:");
        JLabel ageLabel = new JLabel("AGE:");
        JLabel genderLabel = new JLabel("GENDER:");
        nameLabel.setBounds(5, 20, 125, 20);
        ageLabel.setBounds(5, 50, 125, 20);
        genderLabel.setBounds(5, 80, 125, 20);
        JTextField nameTextField = new JTextField("");
        JTextField ageTextField = new JTextField("");
        JTextField genderTextField = new JTextField("");
        nameTextField.setBounds(75,20,150,20);
        ageTextField.setBounds(75,50,150,20);
        genderTextField.setBounds(75,80,150,20);
        JButton addBtnAddFrame = new JButton("ADD");
        addBtnAddFrame.setBounds(20, 150, 100, 20);
        JButton cancelBtnAddFrame = new JButton("CANCEL");
        cancelBtnAddFrame.setBounds(120, 150, 100, 20);
        addPanel.add(nameTextField);
        addPanel.add(ageTextField);
        addPanel.add(genderTextField);
        addPanel.add(nameLabel);
        addPanel.add(ageLabel);
        addPanel.add(genderLabel);
        addPanel.add(addBtnAddFrame);
        addPanel.add(cancelBtnAddFrame);
        addFrame.setVisible(true);

        addBtnAddFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Commands.addNewPersona(nameTextField.getText(),
                        Integer.parseInt(ageTextField.getText()),
                        genderTextField.getText()
                );
                generalFrame.getContentPane().setVisible(false);
                generalFrame.remove(scrollPane);
                updateTable();
                updScrollPane();
                generalFrame.add(scrollPane);
                generalFrame.getContentPane().setVisible(true);
                addFrame.setVisible(false);
            }
        });
        cancelBtnAddFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrame.setVisible(false);
            }
        });

    }

}
