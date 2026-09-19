import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// =====================================================
// STUDENT CLASS
// =====================================================
class Student {

    private String rollNo;
    private String name;

    private double java;
    private double dbms;
    private double ai;
    private double web;
    private double ds;

    public Student(String rollNo, String name,
                   double java, double dbms,
                   double ai, double web, double ds) {

        this.rollNo = rollNo;
        this.name = name;
        this.java = java;
        this.dbms = dbms;
        this.ai = ai;
        this.web = web;
        this.ds = ds;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public double getJava() {
        return java;
    }

    public double getDbms() {
        return dbms;
    }

    public double getAi() {
        return ai;
    }

    public double getWeb() {
        return web;
    }

    public double getDs() {
        return ds;
    }

    public double getTotal() {
        return java + dbms + ai + web + ds;
    }

    public double getPercentage() {
        return getTotal() / 5.0;
    }

    public String getGrade() {

        double percentage = getPercentage();

        if (percentage >= 90)
            return "A+";
        else if (percentage >= 80)
            return "A";
        else if (percentage >= 70)
            return "B+";
        else if (percentage >= 60)
            return "B";
        else if (percentage >= 50)
            return "C";
        else if (percentage >= 40)
            return "D";
        else
            return "F";
    }
}


// =====================================================
// MAIN GUI CLASS
// =====================================================
public class StudentGradeTracker extends JFrame {

    private JTextField rollField;
    private JTextField nameField;

    private JTextField javaField;
    private JTextField dbmsField;
    private JTextField aiField;
    private JTextField webField;
    private JTextField dsField;

    private JTable studentTable;
    private DefaultTableModel tableModel;

    private JLabel averageLabel;
    private JLabel highestLabel;
    private JLabel lowestLabel;
    private JLabel studentCountLabel;

    private ArrayList<Student> students;


    // =================================================
    // CONSTRUCTOR
    // =================================================
    public StudentGradeTracker() {

        students = new ArrayList<>();

        setTitle("Student Grade Tracker - MCA");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createGUI();
    }


    // =================================================
    // CREATE GUI
    // =================================================
    private void createGUI() {

        setLayout(new BorderLayout(10, 10));


        // -------------------------------------------------
        // HEADER
        // -------------------------------------------------

        JLabel titleLabel =
                new JLabel("STUDENT GRADE TRACKER", SwingConstants.CENTER);

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(15, 10, 15, 10)
        );

        add(titleLabel, BorderLayout.NORTH);


        // -------------------------------------------------
        // INPUT PANEL
        // -------------------------------------------------

        JPanel inputPanel = new JPanel(
                new GridLayout(4, 4, 10, 10)
        );

        inputPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Student Information"
                )
        );


        inputPanel.add(new JLabel("Roll Number:"));
        rollField = new JTextField();
        inputPanel.add(rollField);

        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);


        inputPanel.add(new JLabel("Java:"));
        javaField = new JTextField();
        inputPanel.add(javaField);

        inputPanel.add(new JLabel("DBMS:"));
        dbmsField = new JTextField();
        inputPanel.add(dbmsField);


        inputPanel.add(new JLabel("Artificial Intelligence:"));
        aiField = new JTextField();
        inputPanel.add(aiField);

        inputPanel.add(new JLabel("Web Technology:"));
        webField = new JTextField();
        inputPanel.add(webField);


        inputPanel.add(new JLabel("Data Structures:"));
        dsField = new JTextField();
        inputPanel.add(dsField);


        JPanel formWrapper = new JPanel(
                new BorderLayout()
        );

        formWrapper.add(
                inputPanel,
                BorderLayout.CENTER
        );


        // -------------------------------------------------
        // BUTTON PANEL
        // -------------------------------------------------

        JPanel buttonPanel = new JPanel(
                new FlowLayout()
        );

        JButton addButton =
                new JButton("Add Student");

        JButton deleteButton =
                new JButton("Delete Selected");

        JButton clearButton =
                new JButton("Clear");

        JButton summaryButton =
                new JButton("Summary Report");


        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(summaryButton);


        formWrapper.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        add(
                formWrapper,
                BorderLayout.WEST
        );


        // -------------------------------------------------
        // TABLE
        // -------------------------------------------------

        String[] columns = {
                "Roll No",
                "Name",
                "Java",
                "DBMS",
                "AI",
                "Web",
                "DS",
                "Total",
                "Percentage",
                "Grade"
        };


        tableModel =
                new DefaultTableModel(columns, 0) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };


        studentTable =
                new JTable(tableModel);

        studentTable.setRowHeight(25);

        studentTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        )
                );


        JScrollPane scrollPane =
                new JScrollPane(studentTable);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Student Records"
                )
        );


        add(
                scrollPane,
                BorderLayout.CENTER
        );


        // -------------------------------------------------
        // STATISTICS PANEL
        // -------------------------------------------------

        JPanel statisticsPanel =
                new JPanel(
                        new GridLayout(1, 4, 10, 10)
                );


        averageLabel =
                new JLabel("Average: 0.00%",
                        SwingConstants.CENTER);

        highestLabel =
                new JLabel("Highest: 0.00%",
                        SwingConstants.CENTER);

        lowestLabel =
                new JLabel("Lowest: 0.00%",
                        SwingConstants.CENTER);

        studentCountLabel =
                new JLabel("Students: 0",
                        SwingConstants.CENTER);


        statisticsPanel.add(averageLabel);
        statisticsPanel.add(highestLabel);
        statisticsPanel.add(lowestLabel);
        statisticsPanel.add(studentCountLabel);


        add(
                statisticsPanel,
                BorderLayout.SOUTH
        );


        // -------------------------------------------------
        // BUTTON ACTIONS
        // -------------------------------------------------

        addButton.addActionListener(e ->
                addStudent()
        );


        deleteButton.addActionListener(e ->
                deleteStudent()
        );


        clearButton.addActionListener(e ->
                clearFields()
        );


        summaryButton.addActionListener(e ->
                showSummary()
        );
    }


    // =================================================
    // ADD STUDENT
    // =================================================
    private void addStudent() {

        try {

            String rollNo =
                    rollField.getText().trim();

            String name =
                    nameField.getText().trim();


            if (rollNo.isEmpty()
                    || name.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter Roll Number and Name.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            // Check duplicate roll number
            for (Student s : students) {

                if (s.getRollNo()
                        .equalsIgnoreCase(rollNo)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Roll Number already exists.",
                            "Duplicate Record",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }
            }


            double java =
                    getMarks(javaField);

            double dbms =
                    getMarks(dbmsField);

            double ai =
                    getMarks(aiField);

            double web =
                    getMarks(webField);

            double ds =
                    getMarks(dsField);


            Student student =
                    new Student(
                            rollNo,
                            name,
                            java,
                            dbms,
                            ai,
                            web,
                            ds
                    );


            students.add(student);


            // Add to table
            tableModel.addRow(
                    new Object[]{
                            student.getRollNo(),
                            student.getName(),
                            student.getJava(),
                            student.getDbms(),
                            student.getAi(),
                            student.getWeb(),
                            student.getDs(),
                            String.format(
                                    "%.2f",
                                    student.getTotal()
                            ),
                            String.format(
                                    "%.2f%%",
                                    student.getPercentage()
                            ),
                            student.getGrade()
                    }
            );


            updateStatistics();

            clearFields();


            JOptionPane.showMessageDialog(
                    this,
                    "Student added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );


        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid marks between 0 and 100.",
                    "Invalid Marks",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =================================================
    // GET MARKS
    // =================================================
    private double getMarks(
            JTextField field)
            throws NumberFormatException {

        double marks =
                Double.parseDouble(
                        field.getText().trim()
                );


        if (marks < 0 || marks > 100) {

            throw new NumberFormatException();
        }

        return marks;
    }


    // =================================================
    // DELETE STUDENT
    // =================================================
    private void deleteStudent() {

        int selectedRow =
                studentTable.getSelectedRow();


        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student from the table.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int option =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete selected student?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );


        if (option ==
                JOptionPane.YES_OPTION) {

            students.remove(selectedRow);

            tableModel.removeRow(selectedRow);

            updateStatistics();
        }
    }


    // =================================================
    // CLEAR FIELDS
    // =================================================
    private void clearFields() {

        rollField.setText("");
        nameField.setText("");

        javaField.setText("");
        dbmsField.setText("");
        aiField.setText("");
        webField.setText("");
        dsField.setText("");

        rollField.requestFocus();
    }


    // =================================================
    // UPDATE STATISTICS
    // =================================================
    private void updateStatistics() {

        if (students.isEmpty()) {

            averageLabel.setText(
                    "Average: 0.00%"
            );

            highestLabel.setText(
                    "Highest: 0.00%"
            );

            lowestLabel.setText(
                    "Lowest: 0.00%"
            );

            studentCountLabel.setText(
                    "Students: 0"
            );

            return;
        }


        double sum = 0;

        double highest =
                students.get(0).getPercentage();

        double lowest =
                students.get(0).getPercentage();


        for (Student s : students) {

            double percentage =
                    s.getPercentage();

            sum += percentage;

            if (percentage > highest)
                highest = percentage;

            if (percentage < lowest)
                lowest = percentage;
        }


        double average =
                sum / students.size();


        averageLabel.setText(
                String.format(
                        "Average: %.2f%%",
                        average
                )
        );


        highestLabel.setText(
                String.format(
                        "Highest: %.2f%%",
                        highest
                )
        );


        lowestLabel.setText(
                String.format(
                        "Lowest: %.2f%%",
                        lowest
                )
        );


        studentCountLabel.setText(
                "Students: " + students.size()
        );
    }


    // =================================================
    // SUMMARY REPORT
    // =================================================
    private void showSummary() {

        if (students.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No student records available.",
                    "Summary",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }


        double totalPercentage = 0;

        double highest = -1;
        double lowest = 101;

        String highestStudent = "";
        String lowestStudent = "";

        int passCount = 0;
        int failCount = 0;


        for (Student s : students) {

            double percentage =
                    s.getPercentage();

            totalPercentage += percentage;


            if (percentage > highest) {

                highest = percentage;
                highestStudent =
                        s.getName();
            }


            if (percentage < lowest) {

                lowest = percentage;
                lowestStudent =
                        s.getName();
            }


            if (percentage >= 40)
                passCount++;
            else
                failCount++;
        }


        double average =
                totalPercentage
                        / students.size();


        String report =
                "========== SUMMARY REPORT ==========\n\n"
                + "Total Students : "
                + students.size()
                + "\n\n"

                + "Average        : "
                + String.format(
                        "%.2f%%",
                        average
                )
                + "\n\n"

                + "Highest        : "
                + String.format(
                        "%.2f%%",
                        highest
                )
                + " ("
                + highestStudent
                + ")\n\n"

                + "Lowest         : "
                + String.format(
                        "%.2f%%",
                        lowest
                )
                + " ("
                + lowestStudent
                + ")\n\n"

                + "Passed         : "
                + passCount
                + "\n\n"

                + "Failed         : "
                + failCount
                + "\n\n"

                + "====================================";


        JOptionPane.showMessageDialog(
                this,
                report,
                "Student Summary Report",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // =================================================
    // MAIN METHOD
    // =================================================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            StudentGradeTracker app =
                    new StudentGradeTracker();

            app.setVisible(true);
        });
    }
}