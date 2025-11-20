package org.example.employeemanagement_fx.service;

import org.example.employeemanagement_fx.dto.EmployeeDTO;
import java.util.*;

public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, EmployeeDTO> database = new HashMap<>();

    @Override
    public void saveEmployee(EmployeeDTO emp) {
        database.put(emp.getId(), emp);
    }

    @Override
    public boolean deleteEmployee(String idOrName) {
        if (database.containsKey(idOrName)) {
            database.remove(idOrName);
            return true;
        } else {
            Optional<String> key = database.values().stream()
                    .filter(emp -> emp.getName().equalsIgnoreCase(idOrName))
                    .map(EmployeeDTO::getId).findFirst();
            key.ifPresent(database::remove);
            return key.isPresent();
        }
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return new ArrayList<>(database.values());
    }
}
