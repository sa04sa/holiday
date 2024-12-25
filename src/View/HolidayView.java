package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Model.Employee;
import Model.HolidayType;
import Model.Role;

import java.awt.*;

public class HolidayView extends JFrame {
    private static final HolidayView INSTANCE = new HolidayView();
    private JPanel generalPanel = new JPanel();
    private JLabel nomEmployeLabel = new JLabel("Nom de l'employé");
    private JComboBox<String> nomEmployeComboBox = new JComboBox<>();
    private JLabel typeLabel = new JLabel("Type");
    private JComboBox<HolidayType> typeComboBox = new JComboBox<>(HolidayType.values());
    private JLabel dateDebutLabel = new JLabel("Date de début");
    private JTextField dateDebut = new JTextField("YYYY-MM-DD");
    private JLabel dateFinLabel = new JLabel("Date de fin");
    private JTextField dateFin = new JTextField("YYYY-MM-DD");
    private DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Id","Employé","Type","Date début","Date fin"}, 0){
        @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
    };
    protected JTable holidayTable = new JTable(tableModel);
    private JScrollPane tableScrollPane = new JScrollPane(holidayTable);
    private JButton ajouterButton = new JButton("Ajouter");
    private JButton modifierButton = new JButton("Modifier");
    private JButton supprimerButton = new JButton("Supprimer");
    private JButton afficherButton = new JButton("Afficher");
    private JPanel inputPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    public HolidayView() {
        setTitle("Gestion des holidays");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(930, 520);
        setLocationRelativeTo(null);
        setVisible(true);
        
        generalPanel.setLayout(new BorderLayout());
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        
        inputPanel.add(nomEmployeLabel);
        inputPanel.add(nomEmployeComboBox);
        inputPanel.add(typeLabel);
        inputPanel.add(typeComboBox);
        inputPanel.add(dateDebutLabel);
        inputPanel.add(dateDebut);
        inputPanel.add(dateFinLabel);
        inputPanel.add(dateFin);
        generalPanel.add(inputPanel, BorderLayout.NORTH);
    
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        generalPanel.add(tableScrollPane, BorderLayout.CENTER);
    
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(ajouterButton);
        buttonPanel.add(modifierButton);
        buttonPanel.add(supprimerButton);
        buttonPanel.add(afficherButton);
        generalPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(generalPanel);
        
        holidayTable.setFillsViewportHeight(true);
        holidayTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    public JComboBox<String> getNomEmployeComboBox() {
        return nomEmployeComboBox;
    }
    public JComboBox<HolidayType> getTypeComboBox() {
        return typeComboBox;
    }
    public String getDateDebut() {
        return dateDebut.getText();
    }
    public String getDateFin() {
        return dateFin.getText();
    }
    public JButton getAfficherButton() {
        return afficherButton;
    }
    public JButton getAjouterButton() {
        return ajouterButton;
    }

    public JButton getModifierButton() {
        return modifierButton;
    }

    public JButton getSupprimerButton() {
        return supprimerButton;
    }
    public JTable getHolidayTable() {
        return holidayTable;
    }
    public static HolidayView getInstance() {
        return INSTANCE;
    }
    public static void success(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void fail(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public JTable getTable() {
        return holidayTable;
    }
}
