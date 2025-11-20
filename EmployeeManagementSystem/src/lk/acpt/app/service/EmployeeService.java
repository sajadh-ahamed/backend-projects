package lk.acpt.app.service;

import lk.acpt.app.dto.Employee;

public interface EmployeeService {

    public Employee saveEmployee (Employee employee);

    public Employee[] getAllEmployee();

}
