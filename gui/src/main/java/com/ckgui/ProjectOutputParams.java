package com.ckgui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ProjectOutputParams {

    private JTextField projectDirTextField;
    private JTextField outputDirTextField;
    private JCheckBox useJarsCheckBox;
    private JCheckBox variablesAndFieldsCheckBox;
    private JTextField maxFilesPerPartitionTextField;
    private JButton generateReportButton;
    private JButton openOutputFolderButton;
    private JPanel panel;

    public ProjectOutputParams() {
        projectDirTextField = new JTextField();
        projectDirTextField.setPreferredSize(new Dimension(100, 20));
        projectDirTextField.setMaximumSize(new Dimension(100, 20));
        outputDirTextField = new JTextField();
        outputDirTextField.setPreferredSize(new Dimension(100, 20));
        outputDirTextField.setMaximumSize(new Dimension(100, 20));
        useJarsCheckBox = new JCheckBox("Use Jars");
        variablesAndFieldsCheckBox = new JCheckBox("Variables and Fields Metrics");
        maxFilesPerPartitionTextField = new JTextField("0");
        maxFilesPerPartitionTextField.setPreferredSize(new Dimension(40, 20));
        maxFilesPerPartitionTextField.setMaximumSize(new Dimension(40, 20));
        generateReportButton = new JButton("Generate Report");
        openOutputFolderButton = new JButton("Open Output Folder");

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Project Directory:"));
        panel.add(projectDirTextField);
        panel.add(new JLabel("Output Directory:"));
        panel.add(outputDirTextField);
        panel.add(useJarsCheckBox);
        panel.add(variablesAndFieldsCheckBox);
        // panel.add(new JLabel("Max Files Per Partition:"));
        // panel.add(maxFilesPerPartitionTextField);
        panel.add(generateReportButton);
        panel.add(openOutputFolderButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    public String getProjectDir() {
        return projectDirTextField.getText();
    }

    public String getOutputDir() {
        return outputDirTextField.getText();
    }

    public boolean isUseJarsSelected() {
        return useJarsCheckBox.isSelected();
    }

    public boolean isVariablesAndFieldsSelected() {
        return variablesAndFieldsCheckBox.isSelected();
    }

    public String getMaxFilesPerPartition() {
        return maxFilesPerPartitionTextField.getText();
    }

    public void selectProjectDir() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = chooser.showOpenDialog(panel);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File projectDir = chooser.getSelectedFile();
            projectDirTextField.setText(projectDir.getAbsolutePath());
        }
    }

    public void selectOutputDir() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = chooser.showOpenDialog(panel);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File outputDir = chooser.getSelectedFile();
            outputDirTextField.setText(outputDir.getAbsolutePath());
        }
    }
}