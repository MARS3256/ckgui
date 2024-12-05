package com.ckgui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public CSVReader() {
        setTitle("CSV Reader");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create GUI components
        JButton loadCsvButton = new JButton("Load CSV");
        loadCsvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCsvFile();
            }
        });

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Create layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(loadCsvButton, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    private void loadCsvFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadCsvData(selectedFile);
        }
    }

    private void loadCsvData(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String[] headers = br.readLine().split(",");
            tableModel.setColumnIdentifiers(headers);

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
        } catch (IOException e) {
            showAlert("Error", "Failed to load CSV file.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CSVReader().setVisible(true);
            }
        });
    }
}