// javac BankApplicationGUI.java
// appletviewer BankApplicationGUI.java

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* <applet code="BankApplicationGUI" width=1000 height=800></applet> */

public class BankApplicationGUI extends Applet implements ActionListener {
    private TextField nameField, idField;
    private Label loginMessage;
    private TextArea displayArea;
    private Button loginBtn, depositBtn, withdrawBtn, transactionsBtn, logoutBtn, balanceBtn;
    private BankAccount currentAccount;
    private CardLayout cardLayout;
    private Panel mainPanel, loginPanel, accountPanel;

    private List<BankAccount> accounts = new ArrayList<>();

    public void init() {
        setSize(1000, 800);
        setLayout(new BorderLayout());

        accounts.add(new BankAccount("Sitanshu", "123", 600000));
        accounts.add(new BankAccount("Rishabh", "456", 300000));
        accounts.add(new BankAccount("Priyansh", "789", 200000));
        accounts.add(new BankAccount("Jainam", "159", 1000000));
        accounts.add(new BankAccount("Manan", "357", 500000));

        cardLayout = new CardLayout();
        mainPanel = new Panel(cardLayout);

        loginPanel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        Label titleLabel = new Label("Welcome to Bank System", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        Label nameLabel = new Label("Enter Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 22));
        loginPanel.add(nameLabel, gbc);
        
        nameField = new TextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        nameField.setPreferredSize(new Dimension(200, 30)); 
        gbc.gridx = 1;
        loginPanel.add(nameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        Label idLabel = new Label("Enter Customer ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 22)); 
        loginPanel.add(idLabel, gbc);
        
        idField = new TextField(20);
        idField.setFont(new Font("Arial", Font.PLAIN, 18)); 
        idField.setPreferredSize(new Dimension(200, 30)); 
        gbc.gridx = 1;
        loginPanel.add(idField, gbc);

        loginBtn = new Button("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 24)); 
        loginBtn.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginPanel.add(loginBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        loginMessage = new Label("", Label.CENTER);
        loginMessage.setFont(new Font("Arial", Font.BOLD, 22)); 
        loginMessage.setPreferredSize(new Dimension(250, 20)); 
        loginPanel.add(loginMessage, gbc);

        accountPanel = new Panel(new BorderLayout());
        Panel centerPanel = new Panel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        Label accountLabel = new Label("Banking Dashboard", Label.CENTER);
        accountLabel.setFont(new Font("Arial", Font.BOLD, 26)); 
        centerPanel.add(accountLabel, gbc);

        gbc.gridy = 1;
        balanceBtn = new Button("Balance");
        depositBtn = new Button("Deposit");
        withdrawBtn = new Button("Withdraw");
        transactionsBtn = new Button("Transactions");
        logoutBtn = new Button("Logout");

        balanceBtn.setFont(new Font("Arial", Font.BOLD, 22));
        depositBtn.setFont(new Font("Arial", Font.BOLD, 22)); 
        withdrawBtn.setFont(new Font("Arial", Font.BOLD, 22)); 
        transactionsBtn.setFont(new Font("Arial", Font.BOLD, 22)); 
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 22)); 

        balanceBtn.addActionListener(this);
        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        transactionsBtn.addActionListener(this);
        logoutBtn.addActionListener(this);

        gbc.gridy = 2;
        centerPanel.add(balanceBtn, gbc);
        gbc.gridy = 3;
        centerPanel.add(depositBtn, gbc);
        gbc.gridy = 4;
        centerPanel.add(withdrawBtn, gbc);
        gbc.gridy = 5;
        centerPanel.add(transactionsBtn, gbc);
        gbc.gridy = 6;
        centerPanel.add(logoutBtn, gbc);

        accountPanel.add(centerPanel, BorderLayout.CENTER);

        displayArea = new TextArea("", 8, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
        displayArea.setFont(new Font("Courier New", Font.PLAIN, 22)); 
        displayArea.setEditable(false);
        accountPanel.add(displayArea, BorderLayout.SOUTH);

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(accountPanel, "Account");

        add(mainPanel, BorderLayout.CENTER);
    }

    private void openTransactionWindow(String action) {
        Frame transactionFrame = new Frame(action);
        transactionFrame.setSize(600, 200);
        transactionFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        Label amountLabel = new Label("Enter Amount:");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 22));
        transactionFrame.add(amountLabel, gbc);
    
        gbc.gridx = 1;
        TextField amountField = new TextField(10);
        amountField.setFont(new Font("Arial", Font.PLAIN, 18));
        amountField.setPreferredSize(new Dimension(200, 30)); 
        transactionFrame.add(amountField, gbc);
    
        // Submit button at the bottom
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        Button submitButton = new Button("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 24)); 
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
                    transactionFrame.dispose(); 
                } catch (NumberFormatException ex) {
                    displayArea.setText("Invalid Amount!\n");
                }
            }
        });
        transactionFrame.add(submitButton, gbc);
    
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
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException ex) {
                        }
                        cardLayout.show(mainPanel, "Account");
                    }).start();
                    displayArea.setText("");
                    displayArea.append("Welcome " + currentAccount.customerName + "!\n");
                    displayArea.append("Balance: " + currentAccount.getBalance() + "\n");
                    return;
                }
            }

            loginMessage.setForeground(Color.RED);
            loginMessage.setText("Invalid Name or Customer ID!");
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                }
                loginMessage.setText("");
            }).start();

        } else if (e.getSource() == balanceBtn) {
            displayArea.setText("Current Balance: " + currentAccount.getBalance() + "\n");
        } else if (e.getSource() == depositBtn) {
            openTransactionWindow("Deposit");
        } else if (e.getSource() == withdrawBtn) {
            openTransactionWindow("Withdraw");
        } else if (e.getSource() == transactionsBtn) {
            displayArea.setText("Transaction History:\n" + currentAccount.getTransactions() + "\n");
        } else if (e.getSource() == logoutBtn) {
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