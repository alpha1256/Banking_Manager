package com.bank;

import java.util.ArrayList;

public class account {

    private String name;
    private double amount;
    public ArrayList<transaction> transactions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    /**
     * Function to view all transactions
     */
    public void viewAllTransactions() {
        for (int i =0; i < transactions.size(); i++)
        {
            System.out.print(transactions.get(i).toString()+"\n");
        }
    }

    /**
     * View All account balance
     */
    public void viewAccountBalance()
    {
        System.out.print("Your Account Balance for " + getName() + " is: " + getAmount() + "\n");
    }

    public account(String name)
    {
        amount =0;
        this.name = name;
    }
}
