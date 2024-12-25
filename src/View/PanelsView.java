package View;

import javax.swing.*;

public class PanelsView extends JFrame {
    private static PanelsView INSTANCE = new PanelsView();
    private JTabbedPane tabbedPane = new JTabbedPane();
    private EmployeeView employeeView = EmployeeView.getInstance();
    private HolidayView holidayView = HolidayView.getInstance();

    public PanelsView() {
        setTitle("Admin Dashboard - Gestion des Employés et Congés");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(930, 520);
        setLocationRelativeTo(null);
        employeeView.dispose();
        holidayView.dispose();
        tabbedPane.addTab("Gestion des Employés", employeeView.getContentPane());
        tabbedPane.addTab("Gestion des Congés", holidayView.getContentPane());
        add(tabbedPane);
        setVisible(true);
    }
    public static PanelsView getInstance() {
        return INSTANCE;
    }
}