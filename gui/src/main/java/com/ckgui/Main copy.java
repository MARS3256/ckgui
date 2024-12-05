// package com.ckgui;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.io.File;
// import java.io.IOException;

// public class Main extends JFrame {

//     private JTextField projectDirTextField;
//     private JTextField outputDirTextField;
//     private JCheckBox useJarsCheckBox;
//     private JCheckBox variablesAndFieldsCheckBox;
//     private JTextField maxFilesPerPartitionTextField;
//     private JButton generateReportButton;
//     private JButton openOutputFolderButton;

//     public Main() {
//         setTitle("CK Metrics GUI");
//         setSize(400, 300);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLocationRelativeTo(null);

//         // Create GUI components
//         projectDirTextField = new JTextField();
//         outputDirTextField = new JTextField();
//         useJarsCheckBox = new JCheckBox("Use Jars");
//         variablesAndFieldsCheckBox = new JCheckBox("Variables and Fields Metrics");
//         maxFilesPerPartitionTextField = new JTextField("0");
//         generateReportButton = new JButton("Generate Report");
//         openOutputFolderButton = new JButton("Open Output Folder");

//         // Add action listeners
//         generateReportButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 generateReport();
//             }
//         });
//         openOutputFolderButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 openOutputFolder();
//             }
//         });

//         // Create layout
//         JPanel panel = new JPanel();
//         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//         panel.add(new JLabel("Project Directory:"));
//         panel.add(projectDirTextField);
//         JButton browseProjectButton = new JButton("Browse");
//         browseProjectButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 selectProjectDir();
//             }
//         });
//         panel.add(browseProjectButton);
//         panel.add(new JLabel("Output Directory:"));
//         panel.add(outputDirTextField);
//         JButton browseOutputButton = new JButton("Browse");
//         browseOutputButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 selectOutputDir();
//             }
//         });
//         panel.add(browseOutputButton);
//         panel.add(useJarsCheckBox);
//         panel.add(variablesAndFieldsCheckBox);
//         panel.add(new JLabel("Max Files Per Partition:"));
//         panel.add(maxFilesPerPartitionTextField);
//         panel.add(generateReportButton);
//         panel.add(openOutputFolderButton);

//         JScrollPane scrollPane = new JScrollPane(panel);
//         scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

//         add(scrollPane);
//     }

//     private void selectProjectDir() {
//         JFileChooser chooser = new JFileChooser();
//         chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//         int returnValue = chooser.showOpenDialog(this);
//         if (returnValue == JFileChooser.APPROVE_OPTION) {
//             File projectDir = chooser.getSelectedFile();
//             projectDirTextField.setText(projectDir.getAbsolutePath());
//         }
//     }

//     private void selectOutputDir() {
//         JFileChooser chooser = new JFileChooser();
//         chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//         int returnValue = chooser.showOpenDialog(this);
//         if (returnValue == JFileChooser.APPROVE_OPTION) {
//             File outputDir = chooser.getSelectedFile();
//             outputDirTextField.setText(outputDir.getAbsolutePath());
//         }
//     }

//     private void generateReport() {
//         String projectDir = projectDirTextField.getText();
//         String outputDir = outputDirTextField.getText();
//         boolean useJars = useJarsCheckBox.isSelected();
//         boolean variablesAndFields = variablesAndFieldsCheckBox.isSelected();
//         String maxFilesPerPartition = maxFilesPerPartitionTextField.getText();

//         if (projectDir.isEmpty() || outputDir.isEmpty()) {
//             showAlert("Error", "Project directory and output directory must be specified.");
//             return;
//         }

//         int maxFiles;
//         try {
//             maxFiles = Integer.parseInt(maxFilesPerPartition);
//         } catch (NumberFormatException e) {
//             showAlert("Error", "Max Files Per Partition must be a valid integer.");
//             return;
//         }

//         // Build the command and execute it
//         String jarPath = "gui/ck-0.7.1-SNAPSHOT-jar-with-dependencies.jar";
//         String command = "java -jar " + jarPath + " \"" + projectDir + "\" " +
//                 (useJars ? "true" : "false") + " " +
//                 maxFilesPerPartition + " " +
//                 (variablesAndFields ? "true" : "false") + " \"" + outputDir + "\\output\"";
//         System.out.println("Running command: " + command);
//         try {
//             Runtime.getRuntime().exec(command);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     private void openOutputFolder() {
//         String outputDir = outputDirTextField.getText();
//         if (outputDir.isEmpty()) {
//             showAlert("Error", "Output directory must be specified.");
//             return;
//         }

//         File outputFolder = new File(outputDir);
//         if (!outputFolder.exists()) {
//             showAlert("Error", "The specified output directory does not exist.");
//             return;
//         }

//         try {
//             Desktop.getDesktop().open(outputFolder);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     private void showAlert(String title, String message) {
//         JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(new Runnable() {
//             @Override
//             public void run() {
//                 new Main().setVisible(true);
//             }
//         });
//     }
// }