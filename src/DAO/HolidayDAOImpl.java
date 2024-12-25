package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import Controller.EmployeeController;
import Model.Employee;
import Model.Holiday;
import Model.HolidayModel;
import Model.HolidayType;
import Model.Poste;
import View.EmployeeView;
import View.HolidayView;

public class HolidayDAOImpl implements GeneriqueDAOI<Holiday> {
    private Connection connection;
    public HolidayDAOImpl() {
        connection = DBConnection.getConnection();
    }
    public List<Employee> afficherEmployee() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                double salaire = resultSet.getDouble("salaire");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                Model.Role role = Model.Role.valueOf(resultSet.getString("role"));
                Model.Poste poste = Poste.valueOf(resultSet.getString("poste"));
                int holidayBalance = resultSet.getInt("holidayBalance");
                Employee employee = new Employee(id, nom, prenom, salaire, email, phone, role, poste, holidayBalance);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }
    public List<Holiday> afficher(){
        List<Holiday> holidays = new ArrayList<>();
        String query = "SELECT * FROM holiday";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int employeeId = resultSet.getInt("employee_id");
                HolidayType type = HolidayType.valueOf(resultSet.getString("type"));
                String startDate = resultSet.getString("start");
                String endDate = resultSet.getString("end");
                Holiday holiday = new Holiday(id,employeeId,type, startDate, endDate);
                holidays.add(holiday);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return holidays;
    }
    @Override
    public void ajouter(Holiday holiday) {
        String SQL = "INSERT INTO holiday (employee_id, type, start, end) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setInt(1, holiday.getIdEmployee());
            stmt.setString(2, holiday.getType().toString());
            stmt.setString(3,holiday.getStart());
            stmt.setString(4,holiday.getEnd());
            stmt.executeUpdate();
            HolidayView.success("Congé ajouté avec succéss !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void modifier(Holiday holiday,int holidayId) {
        String SQL = "UPDATE holiday SET employee_id = ?, type = ?, start = ?, end = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setInt(1, holiday.getIdEmployee());
            stmt.setString(2, holiday.getType().toString());
            stmt.setString(3, holiday.getStart());
            stmt.setString(4, holiday.getEnd());
            stmt.setInt(5, holidayId);
            stmt.executeUpdate();
            HolidayView.success("Congé modifié avec succéss !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void modifierEmployeeBalance (Employee employee, int EmployeeId) {
        String SQL = "UPDATE employee SET holidayBalance = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setInt(1, employee.getHolidayBalance());
            stmt.setInt(2, EmployeeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void supprimer(int holidayId) {
        String SQL = "DELETE FROM holiday WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setInt(1, holidayId);
            stmt.executeUpdate();
            HolidayView.success("Congé supprimé avec succéss !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Employee findById(int EmployeeId) {
        String SQL = "SELECT * FROM employee WHERE id = ?";
        Employee employee = null;
        EmployeeController.viderLesChamps();
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setInt(1, EmployeeId);
            try (ResultSet rset = stmt.executeQuery()) {                
                if(rset.next()) {
                    int id = rset.getInt("id");
                    String nom = rset.getString("nom");
                    String prenom = rset.getString("prenom");
                    double salaire = rset.getDouble("salaire");
                    String email = rset.getString("email");
                    String phone = rset.getString("phone");
                    Model.Role role = Model.Role.valueOf(rset.getString("role"));
                    Model.Poste poste = Poste.valueOf(rset.getString("poste"));
                    int holidayBalance = rset.getInt("holidayBalance");
                    employee = new Employee(id, nom, prenom, salaire, email, phone, role, poste, holidayBalance);
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
    public Holiday FindHolidayById(int holidayId) {
        String SQL = "SELECT * FROM holiday WHERE id = ?";
        Holiday holiday = null;
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setInt(1, holidayId);
            try (ResultSet rset = stmt.executeQuery()) {
                if (rset.next()) {
                    int id = rset.getInt("id");
                    int idEmployee = rset.getInt("employee_id");
                    Model.HolidayType type = Model.HolidayType.valueOf(rset.getString("type"));
                    String start = rset.getString("start");
                    String end = rset.getString("end");
                    holiday = new Holiday(id, idEmployee, type, start, end);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return holiday;
    }
}

