

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BankApplication {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("\t\t\t\t\t╔═══════════════════════════════════════╗");
            System.out.println("\t\t\t\t\t║ ╔═══════════════════════════════════╗ ║");
            System.out.println("\t\t\t\t\t║ ║             CUSP Bank             ║ ║");
            System.out.println("\t\t\t\t\t║ ╚═══════════════════════════════════╝ ║");
            System.out.println("\t\t\t\t\t╚═══════════════════════════════════════╝");

            System.out.print("\n\t\t\t\t\tEnter your Name: ");
            String name = sc.nextLine();

            System.out.print("\t\t\t\t\tEnter your Customer ID: ");
            String customerId = sc.nextLine();

            if (name.equals("Sitanshu") && customerId.equals("123")) {
                loading();
                BankAccount obj1 = new BankAccount("Sitanshu", "123", 600000);
                obj1.menu();
            } 
            else if (name.equals("Rishabh") && customerId.equals("456")) {
                loading();
                BankAccount obj2 = new BankAccount("Rishabh", "456", 300000);
                obj2.menu();
            } 
            else if (name.equals("Priyansh") && customerId.equals("789")) {
                loading();
                BankAccount obj3 = new BankAccount("Priyansh", "789", 200000);
                obj3.menu();
            } 
            else if (name.equals("Jainam") && customerId.equals("159")) {
                loading();
                BankAccount obj4 = new BankAccount("Jainam", "159", 1000000);
                obj4.menu();
            } 
            else if (name.equals("Manan") && customerId.equals("357")) {
                loading();
                BankAccount obj5 = new BankAccount("Manan", "357", 500000);
                obj5.menu();
            } 
            else {
                loading();
                System.out.println("\n\n\t\t\t\t\tInvalid Name and Customer ID");
                System.out.println("\t\t\t\t\tEnter Value Again...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }

    public static void loading() {
        System.out.print("\n\n\t\t\t\t\tChecking Your Details In Our Database ");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.print(".");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

}

class BankAccount {

    double bal;
    String customerName;
    String customerId;
    List<Double> transactions;

    BankAccount(String customerName, String customerId, double balance) {

        System.out.print("\n\n\t\t\t\t\tLogin Successful ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        this.customerName = customerName;
        this.customerId = customerId;
        this.bal = balance;
        transactions = new ArrayList<>();
    }

    void deposit(double amount) {
        if (amount != 0) {
            bal += amount;
            transactions.add(amount);
        }
    }

    void withdraw(double amt) {
        if (amt != 0 && bal >= amt) {
            bal -= amt;
            transactions.add(-amt);
        } else if (bal < amt) {
            System.out.println("\t\t\t\t\tBank balance insufficient");
        }
    }

    void AllTransactions() {
        for (Double transaction : transactions) {
            if (transaction > 0) {
                System.out.println("\t\t\t\t\tDeposited: " + transaction);
            } else if (transaction < 0) {
                System.out.println("\t\t\t\t\tWithdrawn: " + Math.abs(transaction));
            }
        }
    }

    void exit_function() {

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("\t\t\t\t\t╔═══════════════════════════════════════╗");
        System.out.println("\t\t\t\t\t║ ╔═══════════════════════════════════╗ ║");
        System.out.println("\t\t\t\t\t║ ║             CUSP Bank             ║ ║");
        System.out.println("\t\t\t\t\t║ ╚═══════════════════════════════════╝ ║");
        System.out.println("\t\t\t\t\t╚═══════════════════════════════════════╝");

        System.out.println("\n\n\t\t\t\t\t********* Thank you for using our banking services *********");

        System.out.println("\n\n\n\t\t\t\t\tHere's the summary of all your transactions:");
        System.out.println("\t\t\t\t\t----------------------------------------------------");

        int transCount = 1;
        while (!transactions.isEmpty()) {
            double transaction = transactions.remove(0);
            if (transaction > 0) {
                System.out.println("\t\t\t\t\tTransaction " + transCount + ": Deposited " + transaction);
            } else if (transaction < 0) {
                System.out.println("\t\t\t\t\tTransaction " + transCount + ": Withdrawn " + Math.abs(transaction));
            }
            transCount++;
        }

        System.out.println("\n\t\t\t\t\tFinal Balance: " + bal);
        System.out.println("\t\t\t\t\t----------------------------------------------------");

        System.out.println("\n\n\t\t\t\t\t========== This Bank Application is Made By ==========\n");
        System.out.println("\t\t\t\t\t\tSitanshu  Pradhan      (216090307022)");
        System.out.println("\t\t\t\t\t\tRishabh  Shukla        (216090307025)");
        System.out.println("\t\t\t\t\t\tPriyansh  Masiyava     (216090307029)");
        System.out.println("\t\t\t\t\t\tJainam  Khara          (216090307055)");
        System.out.println("\t\t\t\t\t\tManan  Sanghvi         (216090307058)");
        System.out.println("\n\n\n");

        System.exit(0);
    }

    void menu() {
        String option;
        Scanner sc = new Scanner(System.in);

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("\n\n\t\t\t\t\tWelcome " + customerName);
        System.out.println("\t\t\t\t\tYour ID: " + customerId);

        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("\t\t\t\t\t╔═══════════════════════════════════════╗");
            System.out.println("\t\t\t\t\t║ ╔═══════════════════════════════════╗ ║");
            System.out.println("\t\t\t\t\t║ ║             CUSP Bank             ║ ║");
            System.out.println("\t\t\t\t\t║ ╚═══════════════════════════════════╝ ║");
            System.out.println("\t\t\t\t\t╚═══════════════════════════════════════╝");

            System.out.println("\n");
            System.out.println("\t\t\t\t\t1) Check Balance");
            System.out.println("\t\t\t\t\t2) Deposit Amount");
            System.out.println("\t\t\t\t\t3) Withdraw Amount");
            System.out.println("\t\t\t\t\t4) Previous Transaction");
            System.out.println("\t\t\t\t\t5) Show All Transactions");
            System.out.println("\t\t\t\t\t6) Exit");

            System.out.println("\n\t\t\t\t\t********************************************");
            System.out.print("\t\t\t\t\tChoose an option : ");
            option = sc.nextLine();
            System.out.println("\n\t\t\t\t\t");

            switch (option) {
                case "1":
                    System.out.println("\t\t\t\t\t......................................");
                    System.out.println("\t\t\t\t\tBalance = " + bal);
                    System.out.println("\t\t\t\t\t......................................");
                    System.out.print("\n\t\t\t\t\t");
                    sc.nextLine();
                    break;
                case "2":
                    System.out.println("\t\t\t\t\t......................................");
                    System.out.println("\t\t\t\t\tEnter an amount to deposit:");
                    System.out.println("\t\t\t\t\t......................................");
                    System.out.print("\n\t\t\t\t\t");
                    double amt = sc.nextDouble();
                    deposit(amt);
                    System.out.println("\n\t\t\t\t\t");
                    sc.nextLine();
                    break;
                case "3":
                    System.out.println("\t\t\t\t\t......................................");
                    System.out.println("\t\t\t\t\tEnter an amount to withdraw:");
                    System.out.println("\t\t\t\t\t......................................");
                    System.out.print("\n\t\t\t\t\t");
                    double amtW = sc.nextDouble();
                    withdraw(amtW);
                    System.out.println("\n\t\t\t\t\t");
                    sc.nextLine();
                    break;
                case "4":
                    System.out.println("\t\t\t\t\t......................................");
                    System.out.println("\t\t\t\t\tPrevious Transaction:");
                    if (transactions.isEmpty()) {
                        System.out.println("\t\t\t\t\tNo transaction occurred");
                    } else {
                        double prevTrans = transactions.get(transactions.size() - 1);
                        if (prevTrans > 0) {
                            System.out.println("\t\t\t\t\tDeposited: " + prevTrans);
                        } else if (prevTrans < 0) {
                            System.out.println("\t\t\t\t\tWithdrawn: " + Math.abs(prevTrans));
                        }
                    }
                    System.out.println("\t\t\t\t\t......................................");
                    System.out.println("\n\t\t\t\t\t");
                    sc.nextLine();
                    break;
                case "5":
                    System.out.println("\t\t\t\t\t......................................");
                    System.out.println("\t\t\t\t\tAll Transactions:");
                    System.out.println("\t\t\t\t\t......................................");
                    AllTransactions();
                    System.out.print("\n\t\t\t\t\t");
                    sc.nextLine();
                    break;
                case "6":
                    exit_function();
                    sc.next();
                    break;
                default:
                    System.out.println("\t\t\t\t\tInvalid Option! Please choose a valid option.");
                    break;
            }
        } while (!option.equals("6"));
    }
}