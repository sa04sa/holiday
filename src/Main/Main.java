package Main;

import Controller.EmployeeController;
import Controller.HolidayController;
import DAO.EmployeeDAOImpl;
import DAO.HolidayDAOImpl;
import Model.EmployeeModel;
import Model.HolidayModel;
import View.PanelsView;
import View.EmployeeView;
import View.HolidayView;

public class Main {

    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController(new EmployeeModel(new EmployeeDAOImpl()), EmployeeView.getInstance());
        HolidayController holidayController = new HolidayController(new HolidayModel(new HolidayDAOImpl()), HolidayView.getInstance());
        PanelsView.getInstance();
    }
}