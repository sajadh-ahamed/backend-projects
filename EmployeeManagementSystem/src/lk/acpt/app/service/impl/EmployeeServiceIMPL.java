package lk.acpt.app.service.impl;

import lk.acpt.app.db.Database;
import lk.acpt.app.dto.Employee;
import lk.acpt.app.service.EmployeeService;

public class EmployeeServiceIMPL implements EmployeeService {
    @Override
    public Employee saveEmployee(Employee employee) {
        int lastFreeIndex = Database.getLastFreeIndex();
        Database.employees[lastFreeIndex] = employee;
        return employee;
    }

    @Override
    public Employee[] getAllEmployee() {
        return Database.employees;
    }
}
