// package com.ckgui;

// import javax.swing.*;
// import java.awt.*;

// public class Main extends JFrame {

//     private ProjectOutputParams projectOutputParams;
//     private LoadCsvTable loadCsvTable;

//     public Main() {
//         setTitle("CK Metrics GUI");
//         setSize(800, 600);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLocationRelativeTo(null);

//         projectOutputParams = new ProjectOutputParams();
//         loadCsvTable = new LoadCsvTable();

//         // Create layout
//         JPanel mainPanel = new JPanel();
//         mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//         mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

//         mainPanel.add(projectOutputParams.getPanel());
//         mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));
//         mainPanel.add(loadCsvTable.getPanel());

//         JScrollPane scrollPane = new JScrollPane(mainPanel);
//         scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

//         add(scrollPane);
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> new Main().setVisible(true));
//     }
// }
