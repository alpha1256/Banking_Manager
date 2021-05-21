package com.bank;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList <user> allUsers = new ArrayList<>();

    public static void driver()
    {

        System.out.print("*******Welcome to bank management system*******\n");
        System.out.print("Enter 1 or 2\n1. Add new user\n2. Sign in to your account\n3. Delete your account: ");
        Scanner scan = new Scanner(System.in);
        String entry = scan.next();
        if (entry.equals("1"))
        {
            System.out.print("Enter your full name: ");
            Scanner newScan = new Scanner(System.in);
            String name = newScan.nextLine();
            System.out.print("\nEnter new username: ");
            String username = scan.next();
            System.out.print("Enter new password: ");
            String password = scan.next();
            allUsers.add(new user(name, username, password));
        }
        else if (entry.equals("2"))
        {
            user newUser = new user();
            boolean check = false;
            do {
                System.out.print("Enter your username: ");
                String username = scan.next();
                System.out.print("\nEnter your password: ");
                String password = scan.next();
                for (int i = 0; i < allUsers.size(); i++) {
                    if (allUsers.get(i).checkUseAndPassword(username, password)) {
                        newUser = allUsers.get(i);
                        check = true;
                        break;
                    }
                }
                if (!check)
                {
                    System.out.print("Invalid password or username\n");
                }
            }while (!check);
            String input = "";
            int depositCount = 0, transferCount = 0, withdrawalCount = 0, userTransferCount = 0;
            do {
                System.out.print("\n1. View account balance\n2. Make a deposit\n3. Make a transfer\n4. Make a Withdrawal\n5. Change Username \n6. Change Password\n7. Transfer money to another user\n8. View all transactions\n9. Log Out:");
                entry = scan.next();
                switch (entry)
                {
                    case "1":
                        newUser.getCheckings().viewAccountBalance();
                        newUser.getSavings().viewAccountBalance();
                        break;
                    case "2":
                        System.out.println("\nWhich account would you like to make a deposit into:\n1. Checkings\n2. Savings");
                        int acc = scan.nextInt();
                        System.out.println("Enter the amount you want to deposit: ");
                        double deposit = scan.nextDouble();
                        if(acc == 1) {
                            transaction.deposit(newUser, newUser.getCheckings(), deposit);
                            transaction newDeposit = new transaction(("Transaction" + String.valueOf(depositCount)+1), "Deposit",deposit);
                            newUser.getCheckings().transactions.add(newDeposit);
                            depositCount++;
                        }
                        if(acc == 2) {
                            transaction newDeposit = new transaction(("Transaction" + String.valueOf(depositCount)+1), "Deposit", deposit);
                            newUser.getSavings().transactions.add(newDeposit);
                            depositCount++;
                        }
                        break;
                    case "3":
                        System.out.println("\nHow would you like to complete this transfer: \n1. Transfer from checkings to savings\n2. Transfer from savings to checkings");
                        int t = scan.nextInt();
                        System.out.println("Enter the amount you want to transfer: ");
                        double transfer = scan.nextDouble();
                        if(t == 1) {
                            transaction.transfer(newUser, newUser.getCheckings(), newUser.getSavings(), transfer);
                            transaction newTransfer = new transaction(("Transaction" + String.valueOf(transferCount)+1), "Transfer", transfer);
                            newUser.getCheckings().transactions.add(newTransfer);
                            newUser.getSavings().transactions.add(newTransfer);
                            transferCount++;
                        }
                        if(t == 2) {
                            transaction.transfer(newUser, newUser.getSavings(), newUser.getCheckings(), transfer);
                            transaction newTransfer = new transaction(("Transaction" + String.valueOf(transferCount)+1), "Transfer", transfer);
                            newUser.getSavings().transactions.add(newTransfer);
                            newUser.getCheckings().transactions.add(newTransfer);
                            transferCount++;
                        }
                        break;
                    case "4":
                        System.out.println("\nWhich account would you like to make a withdrawal from:\n1. Checkings\n2. Savings");
                        acc = scan.nextInt();
                        System.out.println("Enter the amount you want to withdrawal: ");
                        double withdrawal = scan.nextDouble();
                        if(acc == 1) {
                            transaction.withdrawal(newUser, newUser.getCheckings(), withdrawal);
                            transaction newWithdrawal = new transaction(("Transaction" + String.valueOf(withdrawalCount)+1), "Withdrawal", withdrawal);
                            newUser.getCheckings().transactions.add(newWithdrawal);
                            withdrawalCount++;
                        }
                        if(acc == 2) {
                            transaction.withdrawal(newUser, newUser.getSavings(), withdrawal);
                            transaction newWithdrawal = new transaction(("Transaction" + String.valueOf(withdrawalCount)+1), "Withdrawal", withdrawal);
                            newUser.getSavings().transactions.add(newWithdrawal);
                            withdrawalCount++;
                        }
                        break;
                    case "5":
                        System.out.print("Enter your new username: ");
                        String username = scan.next();
                        newUser.setUserName(username);
                        break;
                    case "6":
                        System.out.print("Enter your new password: ");
                        String password = scan.next();
                        newUser.setPassword(password);
                        break;
                    case "7":
                        System.out.print("Enter username of user you want to transfer money to: ");
                        String newUsername = scan.next();
                        user foundUser = new user();
                        boolean findUser = false;
                        for (int i=0; i < allUsers.size(); i++)
                        {
                            if (allUsers.get(i).getUserName().equals(newUsername))
                            {
                                foundUser = allUsers.get(i);
                                findUser = true;
                            }
                        }
                        if (findUser)
                        {
                            System.out.println("\nWhich account would you like to transfer money from?\n1. Checkings\n2. Savings");
                            int a = scan.nextInt();
                            System.out.println("\nWhich account would you like to transfer the money to?\n1. User's checkings\n2. User's savings");
                            int u = scan.nextInt();
                            System.out.println("Enter the amount of money you would like to transfer to this user: ");
                            double transferAmount = scan.nextDouble();
                            // If transferring from checkings
                            if(a == 1) {
                                //If transferring to checkings
                                if(u == 1) {
                                    transaction.transferToUser(newUser.getCheckings(), foundUser.getCheckings(), transferAmount);
                                    transaction newUserTransfer = new transaction(("Transaction" + String.valueOf(userTransferCount)+1), "UserTransfer", transferAmount);
                                    newUser.getCheckings().transactions.add(newUserTransfer);
                                    foundUser.getCheckings().transactions.add(newUserTransfer);
                                    userTransferCount++;
                                }
                                //If transferring to savings
                                if(u == 2) {
                                    transaction.transferToUser(newUser.getCheckings(), foundUser.getSavings(), transferAmount);
                                    transaction newUserTransfer = new transaction(("Transaction" + String.valueOf(userTransferCount)+1), "UserTransfer", transferAmount);
                                    newUser.getCheckings().transactions.add(newUserTransfer);
                                    foundUser.getSavings().transactions.add(newUserTransfer);
                                    userTransferCount++;
                                }
                            }
                            // If transferring from savings
                            if(a == 2) {
                                //If transferring to checkings
                                if(u == 1) {
                                    transaction.transferToUser(newUser.getSavings(), foundUser.getCheckings(), transferAmount);
                                    transaction newUserTransfer = new transaction(("Transaction" + String.valueOf(userTransferCount)+1), "UserTransfer", transferAmount);
                                    newUser.getSavings().transactions.add(newUserTransfer);
                                    foundUser.getCheckings().transactions.add(newUserTransfer);
                                    userTransferCount++;
                                }
                                //If transferring to savings
                                if(u == 2) {
                                    transaction.transferToUser(newUser.getSavings(), foundUser.getSavings(), transferAmount);
                                    transaction newUserTransfer = new transaction(("Transaction" + String.valueOf(userTransferCount)+1), "UserTransfer", transferAmount);
                                    newUser.getSavings().transactions.add(newUserTransfer);
                                    foundUser.getSavings().transactions.add(newUserTransfer);
                                    userTransferCount++;
                                }
                            }
                        }
                        break;
                    case "8":
                        System.out.print("All transactions for checkings account:\n");
                        newUser.getCheckings().viewAllTransactions();
                        System.out.print("\nAll transactions for savings account:\n");
                        newUser.getSavings().viewAllTransactions();
                        break;
                    case "9":
                        System.out.print("Goodbye " + newUser.getName());
                        break;
                }
                System.out.print("\nDo you want to do another transaction y or n:");
                input = scan.next();
                if (input.equals("n"))
                    break;
            }while (input.equals("y"));

        }
        else if (entry.equals("3"))
        {
            user newUser = new user();
            boolean check = false;
            int counter = 0;
            System.out.print("Note your accounts balances must be zero to delete");
            do {
                System.out.print("\nEnter your username: ");
                String username = scan.next();
                System.out.print("\nEnter your password: ");
                String password = scan.next();
                for (int i = 0; i < allUsers.size(); i++) {
                    if (allUsers.get(i).checkUseAndPassword(username, password)) {
                        newUser = allUsers.get(i);
                        check = true;
                        counter = i;
                        break;
                    }
                }
            }while(!check);
            if (newUser.getSavings().getAmount() == 0 && newUser.getCheckings().getAmount() ==0)
            {
                System.out.println("Both your accounts balances are zero do you want to delete y or n");
                String delete = scan.next();
                if (delete.equals("y"))
                {
                    allUsers.remove(counter);
                    System.out.print("Your account have been deleted");
                }
            }
        }

    }

    public static void main(String[] args) {
        // write your code here
        String newTemp = "";
        do {
            driver();
            Scanner input = new Scanner(System.in);
            System.out.print("\nDo you want to continue y or n: ");
            newTemp = input.next();
            if (newTemp.equals("n"))
            {
                System.out.print("Have a good rest of your day :)");
                break;
            }
        }while(newTemp.equals("y"));
    }
}
