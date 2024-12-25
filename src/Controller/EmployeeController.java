package Controller;

import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import Model.Employee;
import Model.EmployeeModel;
import Model.Poste;
import Model.Role;
import Utilities.Utils;
import View.EmployeeView;

public class EmployeeController {
    protected EmployeeModel employeeModel;
    protected static EmployeeView employeeView;

    public EmployeeController(EmployeeModel employeeModel, EmployeeView employeeView) {
        this.employeeModel = employeeModel;
        EmployeeController.employeeView = employeeView;

        // Ajouter des actionListeners aux boutons
        EmployeeController.employeeView.getAjouterButton().addActionListener(e -> this.ajouterEmployee());
        EmployeeController.employeeView.getAfficherButton().addActionListener(e -> {
            if (employeeView.getNomField().getText().isEmpty() && employeeView.getPrenomField().getText().isEmpty() &&
                    employeeView.getSalaireField().getText().isEmpty() && employeeView.getEmailField().getText().isEmpty() &&
                    employeeView.getPhoneField().getText().isEmpty()) {
                this.afficherEmployee();
            }
        });
        EmployeeController.employeeView.getSupprimerButton().addActionListener(e -> this.supprimerEmployee());
        EmployeeController.employeeView.getModifierButton().addActionListener(e -> this.updateEmployee());

        // Ajouter un ListSelectionListener pour détecter les sélections dans le tableau
        EmployeeController.employeeView.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    remplirLesChampsAvecDonneesSelectionnees();
                }
            }
        });

        this.afficherEmployee();
    }

    public void ajouterEmployee() {
        String nom = employeeView.getNomField().getText();
        String prenom = employeeView.getPrenomField().getText();
        String salaire = employeeView.getSalaireField().getText();
        String email = employeeView.getEmailField().getText();
        String phone = employeeView.getPhoneField().getText();
        Role role = (Role) employeeView.getRoleComboBox().getSelectedItem();
        Poste poste = (Poste) employeeView.getPosteComboBox().getSelectedItem();
        employeeModel.ajouterEmployee(nom, prenom, salaire, email, phone, role, poste);
        this.afficherEmployee();
    }

    public void afficherEmployee() {
        List<Employee> employees = employeeModel.afficherEmployee();
        DefaultTableModel tableModel = (DefaultTableModel) employeeView.getTable().getModel();
        tableModel.setRowCount(0);
        for (Employee e : employees) {
            tableModel.addRow(new Object[] {
                    e.getId(), e.getNom(), e.getPrenom(), e.getEmail(),
                    e.getSalaire(), e.getPhone(), e.getRole(), e.getPoste(), e.getHolidayBalance()
            });
        }
    }

    public void supprimerEmployee() {
        int selectedRow = employeeView.getTable().getSelectedRow();
        if (selectedRow != -1) {
            try {
                int id = Integer.parseInt(employeeView.getTable().getModel().getValueAt(selectedRow, 0).toString());
                employeeModel.supprimerEmployee(id);
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format.");
            }
        } else {
            EmployeeView.SupprimerFail("Veuillez choisir un employé.");
        }
        this.afficherEmployee();
    }

    public void updateEmployee() {
        int selectedRow = employeeView.getTable().getSelectedRow();
        if (selectedRow != -1) {
            try {
                int id = Integer.parseInt(employeeView.getTable().getModel().getValueAt(selectedRow, 0).toString());
                String nom = employeeView.getNomField().getText();
                String prenom = employeeView.getPrenomField().getText();
                String email = employeeView.getEmailField().getText();
                double salaire = Utils.parseDouble(employeeView.getSalaireField().getText());
                String phone = employeeView.getPhoneField().getText();
                Role role = (Role) employeeView.getRoleComboBox().getSelectedItem();
                Poste poste = (Poste) employeeView.getPosteComboBox().getSelectedItem();

                Employee employeeToUpdate = employeeModel.findById(id);
                if (employeeToUpdate != null) {
                    employeeModel.updateEmployee(employeeToUpdate, id, nom, prenom, email, salaire, phone, role, poste);

                    this.afficherEmployee();
                } else {
                    EmployeeView.ModifierFail("L'employé avec l'ID spécifié n'existe pas.");
                }
            } catch (NumberFormatException e) {
                EmployeeView.ModifierFail("Erreur lors de la mise à jour de l'employé.");
            }
        } else {
            EmployeeView.ModifierFail("Veuillez choisir un employé.");
        }
    }

    public void remplirLesChampsAvecDonneesSelectionnees() {
        int selectedRow = employeeView.getTable().getSelectedRow();
        if (selectedRow != -1) {
            try {
                int id = Integer.parseInt(employeeView.getTable().getModel().getValueAt(selectedRow, 0).toString());
                Employee employee = employeeModel.findById(id);
                if (employee != null) {
                    employeeView.getNomField().setText(employee.getNom());
                    employeeView.getPrenomField().setText(employee.getPrenom());
                    employeeView.getSalaireField().setText(String.valueOf(employee.getSalaire()));
                    employeeView.getEmailField().setText(employee.getEmail());
                    employeeView.getPhoneField().setText(employee.getPhone());
                    employeeView.getRoleComboBox().setSelectedItem(employee.getRole());
                    employeeView.getPosteComboBox().setSelectedItem(employee.getPoste());
                } else {
                    EmployeeView.ModifierFail("Aucun employé trouvé avec cet ID.");
                }
            } catch (NumberFormatException e) {
                EmployeeView.ModifierFail("Erreur de formatage de l'ID.");
            }
        } else {
            EmployeeView.ModifierFail("Veuillez sélectionner une ligne pour modifier.");
        }
    }

    public static int getId() {
        int selectedRow = employeeView.getTable().getSelectedRow();
        int id = -1;
        if (selectedRow != -1) {
            try {
                id = Integer.parseInt(employeeView.getTable().getModel().getValueAt(selectedRow, 0).toString());
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format.");
            }
        }
        return id;
    }

    public static void viderLesChamps() {
        EmployeeView employeeView = EmployeeView.getInstance();
        employeeView.getNomField().setText("");
        employeeView.getPrenomField().setText("");
        employeeView.getSalaireField().setText("");
        employeeView.getEmailField().setText("");
        employeeView.getPhoneField().setText("");
        employeeView.getRoleComboBox().setSelectedIndex(-1);
        employeeView.getPosteComboBox().setSelectedIndex(-1);
    }

    public static void ModifierSuccess(String message) {
        JOptionPane.showMessageDialog(null, message, "Succès", JOptionPane.INFORMATION_MESSAGE);
    }
}