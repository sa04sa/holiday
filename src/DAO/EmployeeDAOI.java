package DAO;

import java.util.List;
import Model.Employee;

public interface EmployeeDAOI {
    public List<Employee> findByFullName(String firstname,String lastname);
    public List<Employee> findByEmail(String email);
    public List<Employee> findByFirstName(String firstname);
    public List<Employee> findByLastName(String lastname);
    public List<Employee> findByPhone(String phone);
    public List<Employee> findBySalaire(double salaire);
}