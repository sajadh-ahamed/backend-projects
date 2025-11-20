package org.example.employeemanagement_fx.service;

import org.example.employeemanagement_fx.dto.EmployeeDTO;
import java.util.List;

public interface EmployeeService {
    void saveEmployee(EmployeeDTO emp);
    boolean deleteEmployee(String idOrName);
    List<EmployeeDTO> getAllEmployees();
}
