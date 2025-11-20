package org.example.employeemanagement_fx.databse;

import org.example.employeemanagement_fx.dto.EmployeeDTO;

import java.util.*;

public class EmployeeDatabase {
    private static final Map<String, EmployeeDTO> employeeTable = new HashMap<>();

    public static boolean save(EmployeeDTO employee) {
        employeeTable.put(employee.getId(), employee);
        return true;
    }

    public static boolean delete(String idOrName) {
        if (employeeTable.containsKey(idOrName)) {
            employeeTable.remove(idOrName);
            return true;
        } else {
            // Try delete by name
            String keyToRemove = null;
            for (Map.Entry<String, EmployeeDTO> entry : employeeTable.entrySet()) {
                if (entry.getValue().getName().equalsIgnoreCase(idOrName)) {
                    keyToRemove = entry.getKey();
                    break;
                }
            }
            if (keyToRemove != null) {
                employeeTable.remove(keyToRemove);
                return true;
            }
        }
        return false;
    }

    public static List<EmployeeDTO> getAll() {
        return new ArrayList<>(employeeTable.values());
    }
}
