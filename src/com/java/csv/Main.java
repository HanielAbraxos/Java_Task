package com.java.csv;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
    private static Bank Bank = new Bank();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);  
        //create initial accounts  
        System.out.print("How many number of customers do you want to input? ");  
        int n = sc.nextInt();  
        Bank C[] = new Bank[n];  
        for (int i = 0; i < C.length; i++) {  
            C[i] = new Bank();  
            System.out.println("Enter new Customer Name: ");
            String name = scanner.nextLine();
            System.out.println("Enter Account Number: ");
            String accountNumber = scanner.nextLine();
            System.out.println("Enter Account Balance: ");
            String accountBalance = scanner.nextLine();
            Customer newCustomer = Customer.createCustomer(name, accountNumber, accountBalance);
            if(Bank.addNewCustomer(newCustomer)) {
                System.out.println((i+1) + " - New contact added:\nName : " + name + ", Account Number : "+ accountNumber + ", Balance is : "+accountBalance);
            } else {
                System.out.println("Cannot add, " + name + " already on file");
            }
        }   
        int ch; 
        int modifychar;
        
        do {  
            // int modifychar;
            System.out.println("\n ***Banking System Application***");  
            System.out.println("1 - Display all account details \n" +
                                              " 2 - Search by Account number\n" +
                                              " 3 - Modify Customer Details \n" +
                                              " 4 - Deposit the amount \n" +
                                              " 5 - Withdraw the amount \n" + 
                                              " 6 - Exit");    
            System.out.println("Enter your choice: ");  
            ch = sc.nextInt();  
            
                switch (ch) { 
                    
                    
                    case 1:
                        Bank.printCustomer();   
						Bank.FileWriter();           
                        break;  
                    case 2:  
                        queryCustomer();
                        break;
                    case 3:
                        do{
                            System.out.println("\n ***Modification***");  
                            System.out.println("1 - Add a New Customer \n" +
                                                " 2 - Update a Customer\n" +
                                                " 3 - Remove a Customer \n" +
                                                " 4 - Go Back \n" );  
                            System.out.println("Enter your choice: ");
                            modifychar = sc.nextInt();  
                                
                            switch (modifychar) {
                                case 1:  
                                    addNewCustomer();
									Bank.FileWriter(); 
                                    break;
                                case 2:
                                    updateCustomer();
									Bank.FileWriter(); 
                                    break;
                                case 3:
                                    removeCustomer();
									Bank.FileWriter(); 
                                    break;
                                case 4:
                                    printActions();
                                    break;
                            }
                        }
                        while (modifychar != 4); 
                        break;
                    case 4:  
                        depositBalance();
						Bank.FileWriter(); 
                        break;  
                    case 5:  
                        withdrawalBalance();
						Bank.FileWriter(); 
                        break;  
                    case 6:  
                        System.out.println("See you soon...");  
                        break;  
                }  
            }  
            while (ch != 6);         

    }

    private static void addNewCustomer() {
        System.out.println("Enter new Customer Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        System.out.println("Enter Account Balance: ");
        String accountBalance = scanner.nextLine();
        Customer newCustomer = Customer.createCustomer(name, accountNumber, accountBalance);
        if(Bank.addNewCustomer(newCustomer)) {
            System.out.println("New contact added: Name = " + name + ", Account Number = "+ accountNumber + " Balalce is ="+accountBalance);
        } else {
            System.out.println("Cannot add, " + name + " already on file");
        }
    }

    private static void updateCustomer() {
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();
        Customer existingCustomerRecord = Bank.queryCustomer(name);
        if(existingCustomerRecord == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter new Customer name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new Customer Account Number: ");
        String newNumber = scanner.nextLine();
        System.out.print("Enter new Customer Account Balance: ");
        String newBalance = scanner.nextLine();
        Customer newCustomer = Customer.createCustomer(newName, newNumber, newBalance);
        if(Bank.updateCustomer(existingCustomerRecord, newCustomer)) {
            System.out.println("Successfully updated record");
        } else {
            System.out.println("Error updating record.");
        }
    }

    private static void removeCustomer() {
        System.out.println("Enter existing Customer name: ");
        String name = scanner.nextLine();
        Customer existingCustomerRecord = Bank.queryCustomer(name);
        if (existingCustomerRecord == null) {
            System.out.println("Customer not found.");
            return;
        }

        if(Bank.removeCustomer(existingCustomerRecord)) {
            System.out.println("Successfully deleted");
        } else {
            System.out.println("Error deleting contact");
        }
    }

    private static void depositBalance(){
        System.out.println("Enter existing customer Account Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Money You want to Deposit: ");
        String money = scanner.nextLine();
        Customer existingCustomerRecord = Bank.queryCustomer(name);
        if (existingCustomerRecord == null) {
            System.out.println("Customer not found.");
            return;
        }
        int newBalance = Integer.parseInt(existingCustomerRecord.getAccountBalance()); 
        int m = Integer.parseInt(money); 
        newBalance += m;
        String newBalanceString = String.valueOf(newBalance);
        Customer newCustomer = Customer.createBalance(name, existingCustomerRecord.getAccountNumber(), newBalanceString);
        if(Bank.updateCustomer(existingCustomerRecord, newCustomer)) {
            System.out.println("Name: " + existingCustomerRecord.getName() + " with Account Number " + existingCustomerRecord.getAccountNumber() +
        "\n is having Balance : " + newBalanceString);
        } else {
            System.out.println("Error updating record.");
        }
    }

    private static void withdrawalBalance(){
        System.out.println("Enter existing customer Account Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Money You want to Withdraw: ");
        String money = scanner.nextLine();
        Customer existingCustomerRecord = Bank.queryCustomer(name);
        if (existingCustomerRecord == null) {
            System.out.println("Customer not found.");
            return;
        }
        int newBalance = Integer.parseInt(existingCustomerRecord.getAccountBalance()); 
        int m = Integer.parseInt(money); 
        if (newBalance >= m) {  
            newBalance = newBalance - m;  
            System.out.println("Balance after withdrawal: " + newBalance);  
        } else {  
            System.out.println("Your Balance is less than " + m + "\tTransaction failed...!!" );  
        }  
        String newBalanceString = String.valueOf(newBalance);
        Customer newCustomer = Customer.createBalance(name, existingCustomerRecord.getAccountNumber(), newBalanceString);
        if(Bank.updateCustomer(existingCustomerRecord, newCustomer)) {
            System.out.println("Name: " + existingCustomerRecord.getName() + " with Account Number " + existingCustomerRecord.getAccountNumber() +
        "\n is having Balance : " + newBalanceString);
        } else {
            System.out.println("Error updating record.");
        }
    }

    private static void queryCustomer() {
        System.out.println("Enter existing customer name: ");
        String name = scanner.nextLine();
        Customer existingCustomerRecord = Bank.queryCustomer(name);
        if (existingCustomerRecord == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("Name: " + existingCustomerRecord.getName() + "with Account Number " + existingCustomerRecord.getAccountNumber() +
        "\n is having Banalance " + existingCustomerRecord.getAccountBalance());
    }


    private static void printActions() {
        System.out.println("\n ***Banking System Application***");  
            System.out.println("1 - Display all account details \n" +
                                              " 2 - Search by Account number\n" +
                                              " 3 - Modify Customer Details \n" +
                                              " 4 - Deposit the amount \n" +
                                              " 5 - Withdraw the amount \n" + 
                                              " 6 - Exit");    
        System.out.println("Enter your choice: ");  
    }

}