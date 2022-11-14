package com.java.csv;

public class Customer {
    private String name;
    private String accountNumber;
    private String accountBalance;

    public Customer(String name, String accountNumber, String accountBalance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public static Customer createCustomer(String name, String accountNumber,String accountBalance) {
        return new Customer(name, accountNumber, accountBalance);
    }

    public static Customer createBalance(String name, String accountNumber,String accountBalance) {
        return new Customer(name, accountNumber, accountBalance);
    }
}
