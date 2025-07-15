package LibrarySystem.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainDashboard extends javax.swing.JFrame {
 
    public MainDashboard() {
       // Set up the main frame
        setTitle("Counseling Appointment Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Fixed size
        setLocationRelativeTo(null); // Center the window

        // Create a tabbed pane for navigation
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add tabs for each management section
        tabbedPane.addTab("Appointments", createAppointmentPanel());
        tabbedPane.addTab("Counselors", createCounselorPanel());
        tabbedPane.addTab("Feedback", createFeedbackPanel());

        // Add tabbed pane to the frame
        setLayout(new BorderLayout()); // Ensure BorderLayout is set
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private JPanel createAppointmentPanel() {
    JPanel panel = new JPanel(new BorderLayout(10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Form panel (North)
    JPanel formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Form fields
    JLabel studentLabel = new JLabel("Student Name:");
    JTextField studentField = new JTextField(20);
    JLabel counselorLabel = new JLabel("Counselor:");
    JComboBox<String> counselorCombo = new JComboBox<>();
    JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
    JTextField dateField = new JTextField(10);
    JLabel timeLabel = new JLabel("Time (HH:MM):");
    JTextField timeField = new JTextField(5);
    JLabel statusLabel = new JLabel("Status:");
    JComboBox<String> statusCombo = new JComboBox<>(new String[]{"Scheduled", "Completed", "Canceled"});
    JButton bookButton = new JButton("Book Appointment");

    // Add components to form panel
    gbc.gridx = 0;
    gbc.gridy = 0;
    formPanel.add(studentLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(studentField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    formPanel.add(counselorLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(counselorCombo, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    formPanel.add(dateLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(dateField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    formPanel.add(timeLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(timeField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    formPanel.add(statusLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(statusCombo, gbc);

    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 2;
    formPanel.add(bookButton, gbc);

    // Table panel (Center)
    String[] columns = {"ID", "Student", "Counselor", "Date", "Time", "Status"};
    DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
    JTable appointmentTable = new JTable(tableModel);
    JScrollPane tableScrollPane = new JScrollPane(appointmentTable);

    // Button panel (South)
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton updateButton = new JButton("Update Appointment");
    JButton cancelButton = new JButton("Cancel Appointment");
    buttonPanel.add(updateButton);
    buttonPanel.add(cancelButton);

    // Add panels to main panel
    panel.add(formPanel, BorderLayout.NORTH);
    panel.add(tableScrollPane, BorderLayout.CENTER);
    panel.add(buttonPanel, BorderLayout.SOUTH);

    // Populate counselor combo box (placeholder for now)
    counselorCombo.addItem("Select Counselor");
    // In a real app, query the Counselors table to populate this

    // Add action listeners
    bookButton.addActionListener(e -> {
        if (validateAppointmentForm(studentField, counselorCombo, dateField, timeField)) {
            // Add appointment to table (and eventually database)
            String[] row = {
                String.valueOf(tableModel.getRowCount() + 1),
                studentField.getText(),
                (String) counselorCombo.getSelectedItem(),
                dateField.getText(),
                timeField.getText(),
                (String) statusCombo.getSelectedItem()
            };
            tableModel.addRow(row);
            clearAppointmentForm(studentField, counselorCombo, dateField, timeField, statusCombo);
            JOptionPane.showMessageDialog(panel, "Appointment booked successfully!");
        }
    });

    updateButton.addActionListener(e -> {
        int selectedRow = appointmentTable.getSelectedRow();
        if (selectedRow >= 0) {
            if (validateAppointmentForm(studentField, counselorCombo, dateField, timeField)) {
                tableModel.setValueAt(studentField.getText(), selectedRow, 1);
                tableModel.setValueAt(counselorCombo.getSelectedItem(), selectedRow, 2);
                tableModel.setValueAt(dateField.getText(), selectedRow, 3);
                tableModel.setValueAt(timeField.getText(), selectedRow, 4);
                tableModel.setValueAt(statusCombo.getSelectedItem(), selectedRow, 5);
                JOptionPane.showMessageDialog(panel, "Appointment updated successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Please select an appointment to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    cancelButton.addActionListener(e -> {
        int selectedRow = appointmentTable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(panel, "Are you sure you want to cancel this appointment?", "Confirm Cancel", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(panel, "Appointment canceled successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Please select an appointment to cancel.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    // Populate table with sample data (replace with database query later)
    tableModel.addRow(new Object[]{"1", "John Doe", "Dr. Smith", "2025-07-20", "10:00", "Scheduled"});

    return panel;
}

private boolean validateAppointmentForm(JTextField studentField, JComboBox<String> counselorCombo, JTextField dateField, JTextField timeField) {
    if (studentField.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Student name cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    if (counselorCombo.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(null, "Please select a counselor.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    if (!dateField.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
        JOptionPane.showMessageDialog(null, "Date must be in YYYY-MM-DD format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    if (!timeField.getText().matches("\\d{2}:\\d{2}")) {
        JOptionPane.showMessageDialog(null, "Time must be in HH:MM format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    return true;
}

private void clearAppointmentForm(JTextField studentField, JComboBox<String> counselorCombo, JTextField dateField, JTextField timeField, JComboBox<String> statusCombo) {
    studentField.setText("");
    counselorCombo.setSelectedIndex(0);
    dateField.setText("");
    timeField.setText("");
    statusCombo.setSelectedIndex(0);
}

    private JPanel createCounselorPanel() {
    JPanel panel = new JPanel(new BorderLayout(10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Form panel (North)
    JPanel formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Form fields
    JLabel nameLabel = new JLabel("Counselor Name:");
    JTextField nameField = new JTextField(20);
    JLabel specializationLabel = new JLabel("Specialization:");
    JTextField specializationField = new JTextField(20);
    JLabel availabilityLabel = new JLabel("Availability:");
    JTextField availabilityField = new JTextField(20);
    JButton addButton = new JButton("Add Counselor");

    // Add components to form panel
    gbc.gridx = 0;
    gbc.gridy = 0;
    formPanel.add(nameLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(nameField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    formPanel.add(specializationLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(specializationField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    formPanel.add(availabilityLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(availabilityField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    formPanel.add(addButton, gbc);

    // Table panel (Center)
    String[] columns = {"ID", "Name", "Specialization", "Availability"};
    DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
    JTable counselorTable = new JTable(tableModel);
    JScrollPane tableScrollPane = new JScrollPane(counselorTable);

    // Button panel (South)
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton updateButton = new JButton("Update Counselor");
    JButton removeButton = new JButton("Remove Counselor");
    buttonPanel.add(updateButton);
    buttonPanel.add(removeButton);

    // Add panels to main panel
    panel.add(formPanel, BorderLayout.NORTH);
    panel.add(tableScrollPane, BorderLayout.CENTER);
    panel.add(buttonPanel, BorderLayout.SOUTH);

    // Add action listeners
    addButton.addActionListener(e -> {
        if (validateCounselorForm(nameField, specializationField, availabilityField)) {
            String[] row = {
                String.valueOf(tableModel.getRowCount() + 1),
                nameField.getText(),
                specializationField.getText(),
                availabilityField.getText()
            };
            tableModel.addRow(row);
            clearCounselorForm(nameField, specializationField, availabilityField);
            JOptionPane.showMessageDialog(panel, "Counselor added successfully!");
        }
    });

    updateButton.addActionListener(e -> {
        int selectedRow = counselorTable.getSelectedRow();
        if (selectedRow >= 0) {
            if (validateCounselorForm(nameField, specializationField, availabilityField)) {
                tableModel.setValueAt(nameField.getText(), selectedRow, 1);
                tableModel.setValueAt(specializationField.getText(), selectedRow, 2);
                tableModel.setValueAt(availabilityField.getText(), selectedRow, 3);
                JOptionPane.showMessageDialog(panel, "Counselor updated successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Please select a counselor to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    removeButton.addActionListener(e -> {
        int selectedRow = counselorTable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(panel, "Are you sure you want to remove this counselor?", "Confirm Remove", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(panel, "Counselor removed successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Please select a counselor to remove.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    // Populate table with sample data
    tableModel.addRow(new Object[]{"1", "Dr. Smith", "Career Counseling", "Mon-Fri, 9-5"});

    return panel;
}

private boolean validateCounselorForm(JTextField nameField, JTextField specializationField, JTextField availabilityField) {
    if (nameField.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Counselor name cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    if (specializationField.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Specialization cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    if (availabilityField.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Availability cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    return true;
}

private void clearCounselorForm(JTextField nameField, JTextField specializationField, JTextField availabilityField) {
    nameField.setText("");
    specializationField.setText("");
    availabilityField.setText("");
}

    private JPanel createFeedbackPanel() {
    JPanel panel = new JPanel(new BorderLayout(10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Form panel (North)
    JPanel formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Form fields
    JLabel studentLabel = new JLabel("Student Name:");
    JTextField studentField = new JTextField(20);
    JLabel ratingLabel = new JLabel("Rating (1-5):");
    JComboBox<Integer> ratingCombo = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
    JLabel commentsLabel = new JLabel("Comments:");
    JTextArea commentsArea = new JTextArea(5, 20);
    JScrollPane commentsScroll = new JScrollPane(commentsArea);
    JButton submitButton = new JButton("Submit Feedback");

    // Add components to form panel
    gbc.gridx = 0;
    gbc.gridy = 0;
    formPanel.add(studentLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(studentField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    formPanel.add(ratingLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(ratingCombo, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    formPanel.add(commentsLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(commentsScroll, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    formPanel.add(submitButton, gbc);

    // Table panel (Center)
    String[] columns = {"ID", "Student", "Rating", "Comments"};
    DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
    JTable feedbackTable = new JTable(tableModel);
    JScrollPane tableScrollPane = new JScrollPane(feedbackTable);

    // Button panel (South)
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton editButton = new JButton("Edit Feedback");
    JButton deleteButton = new JButton("Delete Feedback");
    buttonPanel.add(editButton);
    buttonPanel.add(deleteButton);

    // Add panels to main panel
    panel.add(formPanel, BorderLayout.NORTH);
    panel.add(tableScrollPane, BorderLayout.CENTER);
    panel.add(buttonPanel, BorderLayout.SOUTH);

    // Add action listeners
    submitButton.addActionListener(e -> {
        if (validateFeedbackForm(studentField, commentsArea)) {
            String[] row = {
                String.valueOf(tableModel.getRowCount() + 1),
                studentField.getText(),
                ratingCombo.getSelectedItem().toString(),
                commentsArea.getText()
            };
            tableModel.addRow(row);
            clearFeedbackForm(studentField, ratingCombo, commentsArea);
            JOptionPane.showMessageDialog(panel, "Feedback submitted successfully!");
        }
    });

    editButton.addActionListener(e -> {
        int selectedRow = feedbackTable.getSelectedRow();
        if (selectedRow >= 0) {
            if (validateFeedbackForm(studentField, commentsArea)) {
                tableModel.setValueAt(studentField.getText(), selectedRow, 1);
                tableModel.setValueAt(ratingCombo.getSelectedItem(), selectedRow, 2);
                tableModel.setValueAt(commentsArea.getText(), selectedRow, 3);
                JOptionPane.showMessageDialog(panel, "Feedback updated successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Please select feedback to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    deleteButton.addActionListener(e -> {
        int selectedRow = feedbackTable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(panel, "Are you sure you want to delete this feedback?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(panel, "Feedback deleted successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Please select feedback to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    // Populate table with sample data
    tableModel.addRow(new Object[]{"1", "Jane Doe", "4", "Great session!"});

    return panel;
}

private boolean validateFeedbackForm(JTextField studentField, JTextArea commentsArea) {
    if (studentField.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Student name cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    if (commentsArea.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Comments cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    return true;
}

private void clearFeedbackForm(JTextField studentField, JComboBox<Integer> ratingCombo, JTextArea commentsArea) {
    studentField.setText("");
    ratingCombo.setSelectedIndex(0);
    commentsArea.setText("");
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Wellness Management System");
        setPreferredSize(new java.awt.Dimension(900, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel (optional) */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        SwingUtilities.invokeLater(() -> {
            new MainDashboard().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
