package com.ckgui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoadCsvTable {

    private JTable csvTable;
    private DefaultTableModel tableModel;
    private JPanel panel;

    public LoadCsvTable() {
        tableModel = new DefaultTableModel();
        csvTable = new JTable(tableModel);
        csvTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Enable horizontal scrolling

        JScrollPane csvScrollPane = new JScrollPane(csvTable);
        csvScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        csvScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(csvScrollPane, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void loadCsvData(File file) {
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
        JOptionPane.showMessageDialog(panel, message, title, JOptionPane.ERROR_MESSAGE);
    }
}