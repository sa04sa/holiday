package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import Controller.EmployeeController;
import Model.Employee;
import Model.Poste;
import Model.Role;
import View.EmployeeView;

public class EmployeeDAOImpl implements EmployeeDAOI , GeneriqueDAOI<Employee>{
    private Connection connection;

    public EmployeeDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public List<Employee> afficher() {
        String SQL = "SELECT * FROM employee";
        EmployeeController.viderLesChamps();
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    int id = rset.getInt("id");
                    String nom = rset.getString("nom");
                    String prenom = rset.getString("prenom");
                    double salaire = rset.getDouble("salaire");
                    String email = rset.getString("email");
                    String phone = rset.getString("phone");
                    String role = rset.getString("role");
                    String poste = rset.getString("poste");
                    int holidayBalance = rset.getInt("holidayBalance");
                    employees.add(new Employee(id, nom, prenom, salaire, email, phone, Role.valueOf(role), Poste.valueOf(poste), holidayBalance));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (employees.isEmpty()) {
            EmployeeView.AfficherFail("Aucun employé a été trouvé.");
        }
        return employees;
    }
    @Override
    public void ajouter(Employee employee) {
        String SQL = "INSERT INTO employee (nom, prenom, salaire, email, phone, role, poste, holidayBalance) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        EmployeeController.viderLesChamps();
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setString(1, employee.getNom());
            stmt.setString(2, employee.getPrenom());
            stmt.setDouble(3, employee.getSalaire());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getPhone());            
            stmt.setString(6, employee.getRole().name());
            stmt.setString(7, employee.getPoste().name());
            stmt.setInt(8, employee.getHolidayBalance());
            stmt.executeUpdate();
            EmployeeView.AjouterSuccess(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Employee> findByEmail(String email) {
        String SQL = "SELECT * FROM employee WHERE email = ?";
        EmployeeController.viderLesChamps();
        List<Employee> employees = new ArrayList<Employee>();
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setString(1, email);
            try (ResultSet rset = stmt.executeQuery()) {
                while(rset.next()) {
                    employees.add(new Employee(rset.getInt("id"), rset.getString("nom"), rset.getString("prenom"), rset.getDouble("salaire"), rset.getString("email"), rset.getString("phone"), Role.valueOf(rset.getString("role")), Poste.valueOf(rset.getString("poste")), rset.getInt("holidayBalance")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (employees.isEmpty()) {
            EmployeeView.AfficherFail("Aucun employé a été trouvé avec cet adresse Email.");
        }
        return employees;
    }
    @Override
    public List<Employee> findByFullName(String firstname,String lastname) {
        String SQL = "SELECT * FROM employee WHERE nom = ? AND prenom = ?";
        EmployeeController.viderLesChamps();
        List<Employee> employees = new ArrayList<Employee>();
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setString(1, lastname);
            stmt.setString(2, firstname);
            try (ResultSet rset = stmt.executeQuery()) {
                while(rset.next()) {
                    employees.add(new Employee(rset.getInt("id"), rset.getString("nom"), rset.getString("prenom"), rset.getDouble("salaire"), rset.getString("email"), rset.getString("phone"), Role.valueOf(rset.getString("role")), Poste.valueOf(rset.getString("poste")), rset.getInt("holidayBalance")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (employees.isEmpty()) {
            EmployeeView.AfficherFail("Aucun employé a été trouvé avec ce nom et prenom.");
        }
        return employees;
    }
    @Override
    public List<Employee> findByFirstName(String firstname) {
        String SQL = "SELECT * FROM employee WHERE prenom = ?";
        List<Employee> employees = new ArrayList<Employee>();
        EmployeeController.viderLesChamps();
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setString(1, firstname);
            try (ResultSet rset = stmt.executeQuery()) {
                while(rset.next()) {
                    employees.add(new Employee(rset.getInt("id"), rset.getString("nom"), rset.getString("prenom"), rset.getDouble("salaire"), rset.getString("email"), rset.getString("phone"), Role.valueOf(rset.getString("role")), Poste.valueOf(rset.getString("poste")), rset.getInt("holidayBalance")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (employees.isEmpty()) {
            EmployeeView.AfficherFail("Aucun employé a été trouvé avec ce prenom.");
        }
        return employees;
    }
    @Override
    public List<Employee> findByLastName(String lastname) {
        String SQL = "SELECT * FROM employee WHERE nom = ?";
        List<Employee> employees = new ArrayList<Employee>();
        EmployeeController.viderLesChamps();
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setString(1, lastname);
            try (ResultSet rset = stmt.executeQuery()) {
                while(rset.next()) {
                    employees.add(new Employee(rset.getInt("id"), rset.getString("nom"), rset.getString("prenom"), rset.getDouble("salaire"), rset.getString("email"), rset.getString("phone"), Role.valueOf(rset.getString("role")), Poste.valueOf(rset.getString("poste")), rset.getInt("holidayBalance")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (employees.isEmpty()) {
            EmployeeView.AfficherFail("Aucun employé a été trouvé avec ce nom.");
        }
        return employees;
    }
    @Override
    public List<Employee> findByPhone(String phone) {
        String SQL = "SELECT * FROM employee WHERE phone = ?";
        List<Employee> employees = new ArrayList<Employee>();
        EmployeeController.viderLesChamps();
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setString(1, phone);
            try (ResultSet rset = stmt.executeQuery()) {
                while(rset.next()) {
                    employees.add(new Employee(rset.getInt("id"), rset.getString("nom"), rset.getString("prenom"), rset.getDouble("salaire"), rset.getString("email"), rset.getString("phone"), Role.valueOf(rset.getString("role")), Poste.valueOf(rset.getString("poste")), rset.getInt("holidayBalance")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (employees.isEmpty()) {
            EmployeeView.AfficherFail("Aucun employé a été trouvé avec ce numéro de telephone.");
        }
        return employees;
    }
    @Override
    public List<Employee> findBySalaire(double salaire) {
        String SQL = "SELECT * FROM employee WHERE salaire = ?";
        List<Employee> employees = new ArrayList<Employee>();
        EmployeeController.viderLesChamps();
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setDouble(1, salaire);
            try (ResultSet rset = stmt.executeQuery()) {
                while(rset.next()) {
                    employees.add(new Employee(rset.getInt("id"), rset.getString("nom"), rset.getString("prenom"), rset.getDouble("salaire"), rset.getString("email"), rset.getString("phone"), Role.valueOf(rset.getString("role")), Poste.valueOf(rset.getString("poste")), rset.getInt("holidayBalance")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (employees.isEmpty()) {
            EmployeeView.AfficherFail("Aucun employé a été trouvé avec ce salaire.");
        }
        return employees;
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
                    employee = new Employee(rset.getInt("id"), rset.getString("nom"), rset.getString("prenom"), rset.getDouble("salaire"), rset.getString("email"), rset.getString("phone"), Role.valueOf(rset.getString("role")), Poste.valueOf(rset.getString("poste")), rset.getInt("holidayBalance"));
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
    @Override
    public void modifier(Employee employee, int EmployeeId) {
        String SQL = "UPDATE employee SET nom = ?, prenom = ?, salaire = ?, email = ?, phone = ?, role = ?, poste = ? WHERE id = ?";
        EmployeeController.viderLesChamps();
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setString(1, employee.getNom());
            stmt.setString(2, employee.getPrenom());
            stmt.setDouble(3, employee.getSalaire());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getPhone());
            stmt.setString(6, employee.getRole().name());
            stmt.setString(7, employee.getPoste().name());
            stmt.setInt(8, EmployeeId);
            stmt.executeUpdate();
            EmployeeView.ModifierSuccess();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(int EmployeeId) {
        String SQL = "DELETE FROM employee WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            EmployeeController.viderLesChamps();
            stmt.setInt(1, EmployeeId);
            stmt.executeUpdate();
            EmployeeView.SupprimerSuccess();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}