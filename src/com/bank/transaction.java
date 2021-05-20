package com.bank;

import org.w3c.dom.ls.LSOutput;

public class transaction {

    private String transactionName;
    private String transactionType;

    // Setters
    public void setTransactionName(String transactionName){
        this.transactionName = transactionName;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    // Getters
    public String getTransactionName() {
        return transactionName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public transaction(String transactionName, String transactionType) {
        this.transactionName = transactionName;
        this.transactionType = transactionType;
    }

    public transaction()
    {

    }


    public static void withdrawal(user user, account account, double withdrawalAmount) {

        // If the account is their savings then attempt to withdrawal from savings
        if(account.getName().equalsIgnoreCase("savings")) {
            // If the withdrawal amount is greater than their account amount, they cannot withdrawal
            if(account.getAmount() < withdrawalAmount) {
                System.out.println("Insufficient funds available in this account to make the withdrawal");
            }
            if(account.getAmount() > withdrawalAmount) {
                account.setAmount(account.getAmount() - withdrawalAmount);
                System.out.println(user.getName() + " withdrew " + withdrawalAmount + " from their savings account");
                System.out.println("Their new balance is: " + account.getAmount());
            }
        }
        // If the account is their checkings then attempt to withdrawal from savings
        if(account.getName().equalsIgnoreCase("checkings")) {
            // If the withdrawal amount is greater than their account amount, they cannot withdrawal
            if(account.getAmount() < withdrawalAmount) {
                System.out.println("Insufficient funds available in this account to make the withdrawal");
            }
            account.setAmount(account.getAmount() - withdrawalAmount);
            System.out.println(user.getName() + " withdrew " + withdrawalAmount + " from their checkings account");
            System.out.println("Their new balance is: " + account.getAmount());
        }
    }

    public static void deposit(user user, account account, double depositAmount) {
        // If the account is their savings add the deposit to the account
        if(account.getName().equalsIgnoreCase("savings")) {
            account.setAmount(account.getAmount() + depositAmount) ;
            System.out.println(user.getName() + " deposited " + depositAmount + " to their savings account");
            System.out.println("Their new balance is: " + account.getAmount());
        }
        // If the account is their checkings add the deposit to the account
        if(account.getName().equalsIgnoreCase("checkings")) {
            account.setAmount(account.getAmount() + depositAmount);
            System.out.println(user.getName() + " deposited " + depositAmount + " to their checkings account");
            System.out.println("Their new balance is: " + account.getAmount());
        }
    }

    public static void transfer(user user, account accountFrom, account accountTo, double transferAmount) {
        // If the transfer amount is greater than the accountFrom amount, transfer cannot be completed
        if(accountFrom.getAmount() < transferAmount) {
            System.out.println("Transfer cannot be completed.");
            System.out.println("Insufficient funds available in the account you are attempting to transfer from.");
        }
        // If the transfer can be completed subtract the transferAmount from accountFrom and add transferAmount to accountTo
        if(accountFrom.getAmount() > transferAmount) {
            accountFrom.setAmount(accountFrom.getAmount() - transferAmount);
            accountTo.setAmount(accountTo.getAmount() + transferAmount);
            System.out.println("You have successfully transferred $" + transferAmount + " from " + accountFrom.getName()
                    + " to " + accountTo.getName());
            System.out.println("New " + accountFrom.getName() + " balance: " + accountFrom.getAmount());
            System.out.println("New " + accountTo.getName() + " balance: " + accountTo.getAmount());
        }
    }

    public static void transferToUser(account accountFrom, account accountTo, double transferAmount) {
        // Make sure that the userFrom has enough money in their account
        if(transferAmount > accountFrom.getAmount()) {
            System.out.println("Transfer cannot be completed.");
            System.out.println("Insufficient funds available in the account you are attempting to transfer from.");
        }
        if(transferAmount < accountFrom.getAmount()) {
            accountFrom.setAmount(accountFrom.getAmount() - transferAmount);
            accountTo.setAmount(accountTo.getAmount() + transferAmount);
            System.out.println("You have successfully transferred $" + transferAmount + " from " + accountFrom.getName() + " to " + accountTo.getName());
            System.out.println("New " + accountFrom.getName() + " balance: " + accountFrom.getAmount());
        }
    }

    @Override
    public String toString() {
        return "Transaction: " + transactionType + " " + transactionName;
    }

}
