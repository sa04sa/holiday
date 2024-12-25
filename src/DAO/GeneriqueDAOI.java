package DAO;

import java.util.List;

import Model.Employee;

public interface GeneriqueDAOI<T> {
    public List<T> afficher();
    public void ajouter(T t);
    public void modifier(T t,int id);
    public void supprimer(int id);
    public Employee findById(int EmployeeId);
}
