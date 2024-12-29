package com.ckgui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URI;

public class Main extends JFrame {

    private JTextField projectDirTextField;
    private JTextField outputDirTextField;
    private JCheckBox useJarsCheckBox;
    private JCheckBox variablesAndFieldsCheckBox;
    private JTextField maxFilesPerPartitionTextField;
    private JButton generateReportButton;
    private JButton openOutputFolderButton;
    private JButton loadCsvButton;
    private JTable csvTable;
    private DefaultTableModel tableModel;

    public Main() {
        setTitle("CK Metrics GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create GUI components
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
        loadCsvButton = new JButton("Load CSV");

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
        loadCsvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCsvFile();
            }
        });

        // Create layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // First horizontal container for project and output folder selection
        JPanel folderPanel = new JPanel();
        folderPanel.setLayout(new BoxLayout(folderPanel, BoxLayout.X_AXIS));
        folderPanel.add(new JLabel("Project Directory:"));
        folderPanel.add(Box.createRigidArea(new Dimension(8, 0)));
        folderPanel.add(projectDirTextField);
        folderPanel.add(Box.createRigidArea(new Dimension(8, 0)));
        JButton browseProjectButton = new JButton("Browse");
        browseProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectProjectDir();
            }
        });
        folderPanel.add(browseProjectButton);
        folderPanel.add(Box.createRigidArea(new Dimension(8, 0)));
        folderPanel.add(new JLabel("Output Directory:"));
        folderPanel.add(Box.createRigidArea(new Dimension(8, 0)));
        folderPanel.add(outputDirTextField);
        folderPanel.add(Box.createRigidArea(new Dimension(8, 0)));
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
        parameterPanel.add(Box.createRigidArea(new Dimension(8, 0)));
        parameterPanel.add(variablesAndFieldsCheckBox);
        parameterPanel.add(Box.createRigidArea(new Dimension(8, 0)));
        // parameterPanel.add(new JLabel("Max Files Per Partition:"));
        // parameterPanel.add(Box.createRigidArea(new Dimension(8, 0)));
        // parameterPanel.add(maxFilesPerPartitionTextField);

        // Third horizontal container for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(generateReportButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(8, 0)));
        buttonPanel.add(openOutputFolderButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(8, 0)));
        buttonPanel.add(loadCsvButton);

        // CSV Reader components
        tableModel = new DefaultTableModel();
        csvTable = new JTable(tableModel);
        csvTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Enable horizontal scrolling
        JScrollPane csvScrollPane = new JScrollPane(csvTable);
        csvScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        csvScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel csvPanel = new JPanel();
        csvPanel.setLayout(new BorderLayout());
        csvPanel.add(csvScrollPane, BorderLayout.CENTER);

        // Add components to main panel
        mainPanel.add(folderPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        mainPanel.add(parameterPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        mainPanel.add(csvPanel);

        JLabel mydetails = new JLabel("Made by, Md. As-Adur Rahman Sajid 233014037");
        mydetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mydetails.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/MARS3256"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        JLabel credits = new JLabel("<html><a href=''>Credits: https://github.com/mauricioaniche/ck</a></html>");
        credits.setCursor(new Cursor(Cursor.HAND_CURSOR));
        credits.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/mauricioaniche/ck"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        mainPanel.add(mydetails);
        // mainPanel.add(credits);

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

        // Build the command

        String jarPath = "src/main/resources/ck-0.7.1-SNAPSHOT-jar-with-dependencies.jar";
        String command = "java -jar " + jarPath + " \"" + projectDir + "\" " +
                (useJars ? "true" : "false") + " " +
                maxFiles + " " +
                (variablesAndFields ? "true" : "false") + " \"" + outputDir + "\\output\"";
        System.out.println("Running command: " + command);

        executeCommandWithProgress(command, outputDir);
    }

    private void executeCommandWithProgress(String command, String outputDir) {
        JDialog progressDialog = new JDialog(this, "Generating Report", true);
        progressDialog.setSize(400, 200);
        progressDialog.setLayout(new BorderLayout());

        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);

        progressDialog.add(scrollPane, BorderLayout.CENTER);
        progressDialog.add(progressBar, BorderLayout.SOUTH);

        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
                processBuilder.directory(new File("gui"));
                processBuilder.redirectErrorStream(true);
                Process process = processBuilder.start();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        BufferedReader errorReader = new BufferedReader(
                                new InputStreamReader(process.getErrorStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        publish(line);
                    }
                    while ((line = errorReader.readLine()) != null) {
                        publish(line);
                    }
                }

                int exitCode = process.waitFor();
                if (exitCode != 0) {
                    throw new IOException("Command execution failed with exit code " + exitCode);
                }

                return null;
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                for (String line : chunks) {
                    outputTextArea.append(line + "\n");
                }
            }

            @Override
            protected void done() {
                progressBar.setIndeterminate(false);
                progressDialog.dispose();

                try {
                    get();
                    File csvFile = new File(outputDir, "outputclass.csv");
                    if (csvFile.exists()) {
                        loadCsvData(csvFile);
                    } else {
                        showAlert("Error", "The output CSV file was not found.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error", "An error occurred while executing the command.");
                }
            }
        };

        worker.execute();
        progressDialog.setVisible(true);
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