package lk.acpt.app.db;

import lk.acpt.app.dto.Employee;

public class Database {

    public static Employee[] employees = new Employee[7];

    // create a method for get last free space ----> Static
    // should return last free index
    // if array is full return -1

    public static int getLastFreeIndex () {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                return i;
            }
        }
        return -1;
    }

}
