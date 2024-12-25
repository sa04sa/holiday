package Controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import DAO.HolidayDAOImpl;
import Model.Employee;
import Model.Holiday;
import Model.HolidayModel;
import Model.HolidayType;
import View.EmployeeView;
import View.HolidayView;
import Model.Employee;

public class HolidayController {
    private HolidayModel holidayModel;
    private HolidayView holidayView;

    public HolidayController(HolidayModel model, HolidayView view) {
        this.holidayModel = model;
        this.holidayView = view;
        setEmployeesInComboBox();
        holidayView.getAjouterButton().addActionListener(e -> this.ajouterHoliday());
        holidayView.getAfficherButton().addActionListener(e -> this.afficherHoliday());
        holidayView.getModifierButton().addActionListener(e -> this.ModifierHoliday());
        holidayView.getSupprimerButton().addActionListener(e -> this.supprimerHoliday());
        this.afficherHoliday();
    }

    public void ajouterHoliday() {
        JComboBox<String> nom = holidayView.getNomEmployeComboBox();
        int Employeeid = Integer.parseInt(nom.getSelectedItem().toString().split(" - ")[0]);
        HolidayType type = (HolidayType) holidayView.getTypeComboBox().getSelectedItem();
        String dateDebut = holidayView.getDateDebut();
        String dateFin = holidayView.getDateFin();
        Holiday holiday = new Holiday(1,Employeeid, type, dateDebut, dateFin);
        Employee employee = holidayModel.FindById(Employeeid);
        holidayModel.ajouterHoliday(holiday,employee);
        this.afficherHoliday();
    }
    public void afficherHoliday() {
        DefaultTableModel model = (DefaultTableModel) holidayView.getHolidayTable().getModel();
        Employee employee;
        model.setRowCount(0);
        List<Holiday> holidays = holidayModel.afficher();
        for (Holiday holiday : holidays) {
            employee = holidayModel.FindById(holiday.getIdEmployee());
            model.addRow(new Object[]{holiday.getId(), employee.getNom() + " " + employee.getPrenom(), holiday.getType(), holiday.getStart(), holiday.getEnd()});
        }
    }
    public void ModifierHoliday() {
        int selectedRow = holidayView.getTable().getSelectedRow();
    
        if (selectedRow == -1) {
            HolidayView.fail("Veuillez sélectionner une ligne.");
            return;
        }
        int idHoliday = Integer.parseInt(holidayView.getTable().getModel().getValueAt(selectedRow, 0).toString());
        Holiday oldHoliday = holidayModel.FindHolidayById(idHoliday);
        Holiday updatedHoliday = new Holiday();
        updatedHoliday.setId(idHoliday);
        updatedHoliday.setIdEmployee(Integer.parseInt(holidayView.getNomEmployeComboBox().getSelectedItem().toString().split(" - ")[0]));
        updatedHoliday.setType((HolidayType) holidayView.getTypeComboBox().getSelectedItem());
        updatedHoliday.setStart(holidayView.getDateDebut());
        updatedHoliday.setEnd(holidayView.getDateFin());
        holidayModel.ModifierHoliday(updatedHoliday, oldHoliday);
        this.afficherHoliday();
    }
    public void supprimerHoliday() {
        int selectedRow = holidayView.getTable().getSelectedRow();
        if(selectedRow == -1) {
            HolidayView.fail("Veuillez Sélectionner une ligne.");
            return;
        }else{
            int idHoliday = Integer.parseInt(holidayView.getTable().getModel().getValueAt(selectedRow, 0).toString());
            Holiday oldHoliday = holidayModel.FindHolidayById(idHoliday);
            holidayModel.supprimerHoliday(oldHoliday);
        }
        this.afficherHoliday();
    }

    public void setEmployeesInComboBox() {
        List<Employee> employees = holidayModel.afficherEmployee();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        
        for (Employee e : employees) {
            comboBoxModel.addElement(e.getId() + " - " + e.getNom() + " " + e.getPrenom());
        }
        
        holidayView.getNomEmployeComboBox().setModel(comboBoxModel);
    }
}