package EmployeeManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeManagementSystem extends JFrame {

    private ArrayList<Employee> employeeList = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable employeeTable;
    private JTextField nameField, emailField, dobField, departmentField;
    private int currentEmployeeId = 1;

    public EmployeeManagementSystem() {
        // Set up JFrame properties
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Set up the layout
        setLayout(new BorderLayout());

        // Create a panel for the form
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create input fields and labels
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel dobLabel = new JLabel("Date of Birth (YYYY-MM-DD):");
        dobField = new JTextField();

        JLabel departmentLabel = new JLabel("Department:");
        departmentField = new JTextField();

        
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(dobLabel);
        formPanel.add(dobField);
        formPanel.add(departmentLabel);
        formPanel.add(departmentField);

        
        JButton addButton = new JButton("Add Employee");
        formPanel.add(new JLabel());
        formPanel.add(addButton);

        // Add form panel to the main window
        add(formPanel, BorderLayout.NORTH);

        // Create a table to display employee details
        String[] columnNames = {"ID", "Name", "Email", "Date of Birth", "Department"};
        tableModel = new DefaultTableModel(columnNames, 0);
        employeeTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(employeeTable);

        // Add table to the main window
        add(tableScrollPane, BorderLayout.CENTER);

        // Create panel for action buttons
        JPanel actionPanel = new JPanel();

        JButton updateButton = new JButton("Update Employee");
        JButton deleteButton = new JButton("Delete Employee");
        actionPanel.add(updateButton);
        actionPanel.add(deleteButton);

        // Add action buttons to the main window
        add(actionPanel, BorderLayout.SOUTH);

        // Action listeners for buttons

        // Add employee button action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        // Update employee button action
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployee();
            }
        });

        // Delete employee button action
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });
    }

    // Method to add employee to the table and list
    private void addEmployee() {
        String name = nameField.getText();
        String email = emailField.getText();
        String dob = dobField.getText();
        String department = departmentField.getText();

        if (!name.isEmpty() && !email.isEmpty() && !dob.isEmpty() && !department.isEmpty()) {
            Employee employee = new Employee(currentEmployeeId++, name, email, dob, department);
            employeeList.add(employee);
            tableModel.addRow(new Object[]{employee.getId(), employee.getName(), employee.getEmail(), employee.getDob(), employee.getDepartment()});
            clearFormFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to update employee
    private void updateEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String name = nameField.getText();
            String email = emailField.getText();
            String dob = dobField.getText();
            String department = departmentField.getText();

            if (!name.isEmpty() && !email.isEmpty() && !dob.isEmpty() && !department.isEmpty()) {
                // Update data in employee list
                Employee employee = employeeList.get(selectedRow);
                employeeList.set(selectedRow, new Employee(employee.getId(), name, email, dob, department));

                // Update data in the table
                tableModel.setValueAt(name, selectedRow, 1);
                tableModel.setValueAt(email, selectedRow, 2);
                tableModel.setValueAt(dob, selectedRow, 3);
                tableModel.setValueAt(department, selectedRow, 4);
                clearFormFields();
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to delete an employee
    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            employeeList.remove(selectedRow);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to clear form fields after submission
    private void clearFormFields() {
        nameField.setText("");
        emailField.setText("");
        dobField.setText("");
        departmentField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmployeeManagementSystem app = new EmployeeManagementSystem();
            app.setVisible(true);
        });
    }
}