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

public class Main extends JFrame {

    private JTextField projectDirTextField;
    private JTextField outputDirTextField;
    private JCheckBox useJarsCheckBox;
    private JCheckBox variablesAndFieldsCheckBox;
    private JTextField maxFilesPerPartitionTextField;
    private JButton generateReportButton;
    private JButton openOutputFolderButton;
    private JTable csvTable;
    private DefaultTableModel tableModel;

    public Main() {
        setTitle("CK Metrics GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create GUI components
        projectDirTextField = new JTextField();
        outputDirTextField = new JTextField();
        useJarsCheckBox = new JCheckBox("Use Jars");
        variablesAndFieldsCheckBox = new JCheckBox("Variables and Fields Metrics");
        maxFilesPerPartitionTextField = new JTextField("0");
        generateReportButton = new JButton("Generate Report");
        openOutputFolderButton = new JButton("Open Output Folder");

        // Add action listeners
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });
        openOutputFolderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openOutputFolder();
            }
        });

        // Create layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // First horizontal container for project and output folder selection
        JPanel folderPanel = new JPanel();
        folderPanel.setLayout(new BoxLayout(folderPanel, BoxLayout.X_AXIS));
        folderPanel.add(new JLabel("Project Directory:"));
        folderPanel.add(projectDirTextField);
        JButton browseProjectButton = new JButton("Browse");
        browseProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectProjectDir();
            }
        });
        folderPanel.add(browseProjectButton);
        folderPanel.add(new JLabel("Output Directory:"));
        folderPanel.add(outputDirTextField);
        JButton browseOutputButton = new JButton("Browse");
        browseOutputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectOutputDir();
            }
        });
        folderPanel.add(browseOutputButton);

        // Second horizontal container for other parameters
        JPanel parameterPanel = new JPanel();
        parameterPanel.setLayout(new BoxLayout(parameterPanel, BoxLayout.X_AXIS));
        parameterPanel.add(useJarsCheckBox);
        parameterPanel.add(variablesAndFieldsCheckBox);
        parameterPanel.add(new JLabel("Max Files Per Partition:"));
        parameterPanel.add(maxFilesPerPartitionTextField);
        parameterPanel.add(generateReportButton);
        parameterPanel.add(openOutputFolderButton);

        // CSV Reader components
        JButton loadCsvButton = new JButton("Load CSV");
        loadCsvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCsvFile();
            }
        });

        tableModel = new DefaultTableModel();
        csvTable = new JTable(tableModel);
        JScrollPane csvScrollPane = new JScrollPane(csvTable);
        csvScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        csvScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel csvPanel = new JPanel();
        csvPanel.setLayout(new BorderLayout());
        csvPanel.add(loadCsvButton, BorderLayout.NORTH);
        csvPanel.add(csvScrollPane, BorderLayout.CENTER);

        // Add components to main panel
        mainPanel.add(folderPanel);
        mainPanel.add(parameterPanel);
        mainPanel.add(csvPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane);
    }

    private void selectProjectDir() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = chooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File projectDir = chooser.getSelectedFile();
            projectDirTextField.setText(projectDir.getAbsolutePath());
        }
    }

    private void selectOutputDir() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = chooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File outputDir = chooser.getSelectedFile();
            outputDirTextField.setText(outputDir.getAbsolutePath());
        }
    }

    private void generateReport() {
        String projectDir = projectDirTextField.getText();
        String outputDir = outputDirTextField.getText();
        boolean useJars = useJarsCheckBox.isSelected();
        boolean variablesAndFields = variablesAndFieldsCheckBox.isSelected();
        String maxFilesPerPartition = maxFilesPerPartitionTextField.getText();

        if (projectDir.isEmpty() || outputDir.isEmpty()) {
            showAlert("Error", "Project directory and output directory must be specified.");
            return;
        }

        int maxFiles;
        try {
            maxFiles = Integer.parseInt(maxFilesPerPartition);
        } catch (NumberFormatException e) {
            showAlert("Error", "Max Files Per Partition must be a valid integer.");
            return;
        }

        // Build the command and execute it
        String jarPath = "gui/ck-0.7.1-SNAPSHOT-jar-with-dependencies.jar";
        String command = "java -jar " + jarPath + " \"" + projectDir + "\" " +
                (useJars ? "true" : "false") + " " +
                maxFiles + " " +
                (variablesAndFields ? "true" : "false") + " \"" + outputDir + "\\output\"";
        System.out.println("Running command: " + command);
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openOutputFolder() {
        String outputDir = outputDirTextField.getText();
        if (outputDir.isEmpty()) {
            showAlert("Error", "Output directory must be specified.");
            return;
        }

        File outputFolder = new File(outputDir);
        if (!outputFolder.exists()) {
            showAlert("Error", "The specified output directory does not exist.");
            return;
        }

        try {
            Desktop.getDesktop().open(outputFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                new Main().setVisible(true);
            }
        });
    }
}