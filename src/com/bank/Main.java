package com.bank;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList <user> allUsers = new ArrayList<>();

    public static void driver()
    {

        System.out.print("*******Welcome to bank management system*******\n");
        System.out.print("Enter 1 or 2\n1. Add new user\n2. Sign in to your account: ");
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
                    System.out.print("Invalid password or username");
                }
            }while (!check);
            String input = "";
            int depositCount = 0, transferCount = 0, withdrawalCount = 0;
            do {
                System.out.print("\n1. View account balance\n2. Make a deposit\n3. Make a transfer\n4. Make a Withdrawal\n5. Change Username \n6. Change Password \n7. Log Out:");
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
                        }
                        if(acc == 2) {
                            transaction.deposit(newUser, newUser.getSavings(), deposit);
                        }
                        transaction newDeposit = new transaction(("Transaction" + String.valueOf(depositCount)+1), "Deposit");
                        depositCount++;
                        break;
                    case "3":
                        System.out.println("\nHow would you like to complete this transfer: \n1. Transfer from checkings to savings\n2. Transfer from savings to checkings");
                        int t = scan.nextInt();
                        System.out.println("Enter the amount you want to transfer: ");
                        double transfer = scan.nextDouble();
                        if(t == 1) {
                            transaction.transfer(newUser, newUser.getCheckings(), newUser.getSavings(), transfer);
                        }
                        if(t == 2) {
                            transaction.transfer(newUser, newUser.getSavings(), newUser.getCheckings(), transfer);
                        }
                        transaction newTransfer = new transaction(("Transaction" + String.valueOf(transferCount)+1), "Transfer");
                        transferCount++;
                        break;
                    case "4":
                        System.out.println("\nWhich account would you like to make a withdrawal from:\n1. Checkings\n2. Savings");
                        acc = scan.nextInt();
                        System.out.println("Enter the amount you want to withdrawal: ");
                        double withdrawal = scan.nextDouble();
                        if(acc == 1) {
                            transaction.withdrawal(newUser, newUser.getCheckings(), withdrawal);
                        }
                        if(acc == 2) {
                            transaction.withdrawal(newUser, newUser.getSavings(), withdrawal);
                        }
                        transaction newWithdrawal = new transaction(("Transaction" + String.valueOf(withdrawalCount)+1), "Withdrawal");
                        withdrawalCount++;
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
                        System.out.print("Goodbye " + newUser.getName());
                        break;
                }
                System.out.print("Do you want to do another transaction y or n:");
                input = scan.next();
                if (input.equals("n"))
                    break;
            }while (input.equals("y"));

        }

    }

    public static void main(String[] args) {
        // write your code here
        String newTemp = "";
        do {
            driver();
            Scanner input = new Scanner(System.in);
            System.out.print("\nDo you want to continue y or n");
            newTemp = input.next();
            if (newTemp.equals("n"))
            {
                System.out.print("Have a good rest of your day :)");
                break;
            }
        }while(newTemp.equals("y"));
    }
}
