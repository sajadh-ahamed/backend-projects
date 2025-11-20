package lk.afsd.demoee.service.impl;

import lk.afsd.demoee.db.DBConnection;
import lk.afsd.demoee.dto.AccountDTO;
import lk.afsd.demoee.service.AccountService;

import java.sql.*;

public class AccountServiceImpl implements AccountService {


    @Override
    public boolean createAccount(AccountDTO accountDTO) {

        //return false;
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement stm = connection.prepareStatement("insert into account (account_no,account_holder_name,account_type,balance) values (?,?,?,?);");

            stm.setObject(1, accountDTO.getAccountNumber());
            stm.setObject(2, accountDTO.getAccountHolderName());
            stm.setObject(3, accountDTO.getAccountType());
            stm.setObject(4, accountDTO.getAccountBalance());
            // delete,update student
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public AccountDTO getAccountByAccountNumber(String accountNumber) {
        try {
            //load the driver class to the ram
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create a connection with database server
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_accounts_management_system", "root", "12345");

            PreparedStatement stm = connection.prepareStatement("select * from account where account_no=?;");
            stm.setObject(1, accountNumber);

            ResultSet resultSet = stm.executeQuery();

            if (resultSet.next()) {
                return new AccountDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4));
            } else {
                return null;
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean deposit(String accountNumber, double amount) {

        try {
            //load the driver class to the ram
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create a connection with database server
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_accounts_management_system", "root", "12345");

            PreparedStatement stm = connection.prepareStatement("update account set balance=balance+? where account_no=?;");
            stm.setObject(1, amount);
            stm.setObject(2, accountNumber);

            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean withdraw(String accountNumber, double amount) {

        try {
            //load the driver class to the ram
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create a connection with database server
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_accounts_management_system", "root", "12345");

            PreparedStatement stm = connection.prepareStatement("update account set balance=balance-? where account_no=?;");
            stm.setObject(1, amount);
            stm.setObject(2, accountNumber);

            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean transferMoney(String fromAccountNumber, String toAccountNumber, double amount) {
        try {
            //load the driver class to the ram
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create a connection with database server
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_accounts_management_system", "root", "12345");

            //Disable autocomit feature

            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement("update account set balance=balance-? where account_no=?;");

            stm.setObject(1, amount);
            stm.setObject(2, fromAccountNumber);

            int from = stm.executeUpdate();

            if (from > 0) {
                PreparedStatement stm2 = connection.prepareStatement("update account set balance=balance+? where account_no=?;");
                stm2.setObject(1, amount);
                stm2.setObject(2, toAccountNumber);

                int to = stm2.executeUpdate();

                if (to > 0) {
                    connection.commit();
                    return true;
                }else  {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }else {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
