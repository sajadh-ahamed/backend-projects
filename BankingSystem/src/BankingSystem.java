import java.util.Scanner;

class ConsoleBankingSystem {

    static Scanner scan = new Scanner(System.in);

    static String[] accountHolderNames = new String[5];
    static int[] accountNumbers = new int[5];
    static double[] accountBalances = new double[5];
    static String[] accountTypes = new String[5];
    static double[] loanAmounts = new double[5];
    static String[] loanDescriptions = new String[5];

    public static void main(String[] args) {
        System.out.println("Welcome to People's Bank!");

        while (true) {
            chooseOption();
        }
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    // Displaying menu options to the customer
    public static void chooseOption() {
        System.out.println("\nDear customer, please choose an option (Enter the number only):");
        System.out.println("1. Create Account\n2. Deposit\n3. Withdraw\n4. Check Balance\n5. Apply for Loan");
        System.out.println("6. View Loan Details\n7. Transfer Funds\n8. Exit");

        int option = scan.nextInt();

        switch (option) {
            case 1:
                createAccount();
                break;
            case 2:
                depositMoney();
                break;
            case 3:
                withdrawMoney();
                break;
            case 4:
                checkBalance();
                break;
            case 5:
                applyForLoan();
                break;
            case 6:
                viewLoanDetails();
                break;
            case 7:
                transferFunds();
                break;
            case 8:
                System.out.println("Exiting... Thank you for using People's Bank!");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please enter a number between 1 and 8.");
        }
    }

    // Helper method to find an account index
    public static int findAccountIndex(int accountNumber) {
        for (int i = 0; i < accountNumbers.length; i++) {
            if (accountNumbers[i] == accountNumber) {
                return i; // Return index if account found
            }
        }
        return -1; // When account is not found
    }

    // Create Account
    public static void createAccount() {
        for (int i = 0; i < accountHolderNames.length; i++) {
            if (accountHolderNames[i] == null) { // Check for the next available slot
                System.out.print("Enter your Name: ");
                accountHolderNames[i] = scan.next();

                System.out.print("Enter a unique Account Number: ");
                int accountNumber = scan.nextInt();
                if (findAccountIndex(accountNumber) != -1) {
                    System.out.println("Account number already exists! Please try again.");
                    return;
                }
                accountNumbers[i] = accountNumber;

                System.out.print("Choose your Account type (1)Savings (2)Current (3)Wanitha Wasana: ");
                int accountTypeChoice = scan.nextInt();
                accountTypes[i] = accountTypeChoice == 1 ? "Savings" : accountTypeChoice == 2 ? "Current" : accountTypeChoice == 3 ? "Wanitha Wasana" : null;

                if (accountTypes[i] == null) {
                    System.out.println("Invalid account type! Please try again.");
                    return;
                }

                System.out.print("Enter initial Deposit Amount: ");
                double initialDeposit = scan.nextDouble();
                if (initialDeposit <= 0) {
                    System.out.println("Initial deposit must be greater than 0! Please try again.");
                    return;
                }
                accountBalances[i] = initialDeposit;

                System.out.println("Account created successfully!");
                return;
            }
        }
        System.out.println("Account creation limit reached!");
    }

    // Deposit money
    public static void depositMoney() {
        System.out.print("Enter your Account Number: ");
        int accountNumber = scan.nextInt();

        int index = findAccountIndex(accountNumber);
        if (index != -1) {
            System.out.print("Enter Deposit Amount: ");
            double depositAmount = scan.nextDouble();
            if (depositAmount <= 0) {
                System.out.println("Deposit amount must be greater than 0!");
                return;
            }
            accountBalances[index] += depositAmount;
            System.out.println("Deposit successful! Your new balance is: " + accountBalances[index]);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Withdraw money
    public static void withdrawMoney() {
        System.out.print("Enter your Account Number: ");
        int accountNumber = scan.nextInt();

        int index = findAccountIndex(accountNumber);
        if (index != -1) {
            System.out.print("Enter Withdraw Amount: ");
            double withdrawAmount = scan.nextDouble();
            if (withdrawAmount > 0 && accountBalances[index] >= withdrawAmount) {
                accountBalances[index] -= withdrawAmount;
                System.out.println("Withdrawal successful! Your new balance is: " + accountBalances[index]);
            } else {
                System.out.println("Insufficient funds or invalid amount!");
            }
        } else {
            System.out.println("Account not found!");
        }
    }

    // Check balance
    public static void checkBalance() {
        System.out.print("Enter your Account Number: ");
        int accountNumber = scan.nextInt();

        int index = findAccountIndex(accountNumber);
        if (index != -1) {
            System.out.println("Your current balance is: " + accountBalances[index]);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Apply for loan
    public static void applyForLoan() {
        System.out.print("Enter your Account Number: ");
        int accountNumber = scan.nextInt();

        int index = findAccountIndex(accountNumber);
        if (index != -1) {
            System.out.print("Enter Loan Amount: ");
            double loanAmount = scan.nextDouble();
            if (loanAmount <= 0) {
                System.out.println("Loan amount must be greater than 0!");
                return;
            }

            scan.nextLine(); // Consume leftover newline
            System.out.print("Enter Loan Description: ");
            loanDescriptions[index] = scan.nextLine();

            loanAmounts[index] = loanAmount;
            System.out.println("Loan application successful!");
        } else {
            System.out.println("Account not found!");
        }
    }

    // View loan details
    public static void viewLoanDetails() {
        System.out.print("Enter your Account Number: ");
        int accountNumber = scan.nextInt();

        int index = findAccountIndex(accountNumber);
        if (index != -1) {
            System.out.println("Loan Amount: " + loanAmounts[index]);
            System.out.println("Loan Description: " + loanDescriptions[index]);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Transfer funds
    public static void transferFunds() {
        System.out.print("Enter your Account Number: ");
        int fromAccount = scan.nextInt();
        System.out.print("Enter the recipient's Account Number: ");
        int toAccount = scan.nextInt();
        System.out.print("Enter the Transfer Amount: ");
        double transferAmount = scan.nextDouble();

        int fromIndex = findAccountIndex(fromAccount);
        int toIndex = findAccountIndex(toAccount);

        if (fromIndex != -1 && toIndex != -1) {
            if (transferAmount > 0 && accountBalances[fromIndex] >= transferAmount) {
                accountBalances[fromIndex] -= transferAmount;
                accountBalances[toIndex] += transferAmount;
                System.out.println("Transfer successful! Your new balance is: " + accountBalances[fromIndex]);
            } else {
                System.out.println("Insufficient funds or invalid amount!");
            }
        } else {
            System.out.println("Invalid account numbers!");
        }
    }
}
