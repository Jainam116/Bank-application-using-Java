// javac BankApplicationGUI.java
// appletviewer BankApplicationGUI.java

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* <applet code="BankApplet" width=600 height=400></applet> */

public class BankApplicationGUI extends Applet implements ActionListener {
    private TextField nameField, idField;
    private Label loginMessage;
    private TextArea displayArea;
    private Button loginBtn, depositBtn, withdrawBtn, transactionsBtn, logoutBtn;
    private BankAccount currentAccount;
    private CardLayout cardLayout;
    private Panel mainPanel, loginPanel, accountPanel;

    private List<BankAccount> accounts = new ArrayList<>();

    public void init() {
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Sample bank accounts
        accounts.add(new BankAccount("Sitanshu", "123", 600000));
        accounts.add(new BankAccount("Rishabh", "456", 300000));
        accounts.add(new BankAccount("Priyansh", "789", 200000));
        accounts.add(new BankAccount("Jainam", "159", 1000000));
        accounts.add(new BankAccount("Manan", "357", 500000));

        cardLayout = new CardLayout();
        mainPanel = new Panel(cardLayout);

        // Login Panel
        loginPanel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        Label titleLabel = new Label("Welcome to Bank System", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1; gbc.gridy = 1;
        loginPanel.add(new Label("Enter Name:"), gbc);

        nameField = new TextField(20);
        gbc.gridx = 1;
        loginPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        loginPanel.add(new Label("Enter Customer ID:"), gbc);

        idField = new TextField(20);
        gbc.gridx = 1;
        loginPanel.add(idField, gbc);

        loginBtn = new Button("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.addActionListener(this);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        loginPanel.add(loginBtn, gbc);


        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        loginMessage = new Label("", Label.CENTER);
        loginMessage.setFont(new Font("Arial", Font.BOLD, 12));
        loginMessage.setPreferredSize(new Dimension(250, 20)); // Set a fixed width
        loginPanel.add(loginMessage, gbc);
        


        // Account Panel
        accountPanel = new Panel(new BorderLayout());
        Panel centerPanel = new Panel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        Label accountLabel = new Label("Banking Dashboard", Label.CENTER);
        accountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        centerPanel.add(accountLabel, gbc);

        gbc.gridy = 1;
        depositBtn = new Button("Deposit");
        withdrawBtn = new Button("Withdraw");
        transactionsBtn = new Button("Transactions");
        logoutBtn = new Button("Logout");

        depositBtn.setFont(new Font("Arial", Font.BOLD, 12));
        withdrawBtn.setFont(new Font("Arial", Font.BOLD, 12));
        transactionsBtn.setFont(new Font("Arial", Font.BOLD, 12));
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 12));

        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        transactionsBtn.addActionListener(this);
        logoutBtn.addActionListener(this);

        gbc.gridy = 2;
        centerPanel.add(depositBtn, gbc);
        gbc.gridy = 3;
        centerPanel.add(withdrawBtn, gbc);
        gbc.gridy = 4;
        centerPanel.add(transactionsBtn, gbc);
        gbc.gridy = 5;
        centerPanel.add(logoutBtn, gbc);

        accountPanel.add(centerPanel, BorderLayout.CENTER);

        displayArea = new TextArea("", 8, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
        displayArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        displayArea.setEditable(false);
        accountPanel.add(displayArea, BorderLayout.SOUTH);

        // Add panels to mainPanel
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(accountPanel, "Account");

        add(mainPanel, BorderLayout.CENTER);
    }

    private void openTransactionWindow(String action) {
        Frame transactionFrame = new Frame(action);
        transactionFrame.setSize(350, 200);
        transactionFrame.setLayout(new FlowLayout());

        Label amountLabel = new Label("Enter Amount:");
        TextField amountField = new TextField(10);
        Button submitButton = new Button("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    displayArea.setText(""); // Clear previous text

                    if (action.equals("Deposit")) {
                        currentAccount.deposit(amount);
                        displayArea.append("Deposited: " + amount + "\n");
                    } else if (action.equals("Withdraw")) {
                        if (currentAccount.withdraw(amount)) {
                            displayArea.append("Withdrawn: " + amount + "\n");
                        } else {
                            displayArea.append("Insufficient Balance!\n");
                        }
                    }
                    displayArea.append("New Balance: " + currentAccount.getBalance() + "\n");
                    transactionFrame.dispose(); // Close window after transaction
                } catch (NumberFormatException ex) {
                    displayArea.setText("Invalid Amount!\n");
                }
            }
        });

        transactionFrame.add(amountLabel);
        transactionFrame.add(amountField);
        transactionFrame.add(submitButton);
        transactionFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtn) {
            String name = nameField.getText();
            String id = idField.getText();

            for (BankAccount acc : accounts) {
                if (acc.customerName.equals(name) && acc.customerId.equals(id)) {
                    currentAccount = acc;
                    loginMessage.setForeground(Color.GREEN);
                    loginMessage.setText("Login Successful!");
                    new Thread(() -> {
                        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException ex) { }
                        cardLayout.show(mainPanel, "Account");
                    }).start();
                    displayArea.setText(""); // Clear previous text
                    displayArea.append("Welcome " + currentAccount.customerName + "!\n");
                    displayArea.append("Balance: " + currentAccount.getBalance() + "\n");
                    return;
                }
            }
            
            loginMessage.setForeground(Color.RED);
            loginMessage.setText("Invalid Name or Customer ID!");
            new Thread(() -> {
                try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException ex) { }
                loginMessage.setText("");
            }).start();
            
            
        } 
        else if (e.getSource() == depositBtn) {
            openTransactionWindow("Deposit");
        } 
        else if (e.getSource() == withdrawBtn) {
            openTransactionWindow("Withdraw");
        } 
        else if (e.getSource() == transactionsBtn) {
            displayArea.setText("Transaction History:\n" + currentAccount.getTransactions() + "\n");
        } 
        else if (e.getSource() == logoutBtn) {
            cardLayout.show(mainPanel, "Login");
            nameField.setText("");
            idField.setText("");
            displayArea.setText("");
            loginMessage.setText(""); 
        }
    }
}

class BankAccount {
    String customerName;
    String customerId;
    private double balance;
    private List<String> transactions;

    public BankAccount(String customerName, String customerId, double balance) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: " + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactions.add("Withdrawn: " + amount);
            return true;
        }
        return false;
    }

    public String getTransactions() {
        if (transactions.isEmpty()) {
            return "No Transactions Yet!";
        }
        return String.join("\n", transactions);
    }
}




