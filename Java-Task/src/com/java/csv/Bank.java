package com.java.csv;

// import com.opencsv.CSVWriter;
import java.io.FileWriter;
// import com.opencsv.CSVWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.io.*;
// import com.opencsv.CSVWriter;
// import com.opencsv.CSVWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import java.util.Scanner;  
class Bank { 
    private ArrayList<Customer> myCustomer = new ArrayList<>();

    public Bank() {
        this.myCustomer = new ArrayList<Customer>();
    }
    Scanner sc = new Scanner(System.in);  


    public boolean addNewCustomer(Customer customer) {
        if(findCustomer(customer.getName()) >=0) {
            System.out.println("Customer is already on file");
            return false;
        }

        myCustomer.add(customer);
        return true;

    }

    public boolean updateCustomer(Customer oldCustomer, Customer newCustomer) {
        int foundPosition = findCustomer(oldCustomer);
        if(foundPosition <0) {
            System.out.println(oldCustomer.getName() +", was not found.");
            return false;
        }

        this.myCustomer.set(foundPosition, newCustomer);
        System.out.println("All Done");
        return true;
    }

    public boolean removeCustomer(Customer customer) {
        int foundPosition = findCustomer(customer);
        if(foundPosition <0) {
            System.out.println(customer.getName() +", was not found.");
            return false;
        }
        this.myCustomer.remove(foundPosition);
        System.out.println(customer.getName() + ", was deleted.");
        return true;
    }

    private int findCustomer(Customer customer) {
        return this.myCustomer.indexOf(customer);
    }

    private int findCustomer(String customerName) {
        for(int i=0; i<this.myCustomer.size(); i++) {
            Customer customer = this.myCustomer.get(i);
            if(customer.getName().equals(customerName)) {
                return i;
            }
        }
        return -1;
    }


    public String queryCustomer(Customer customer) {
        if(findCustomer(customer) >=0) {
            return customer.getName() + customer.getAccountNumber();
        }
        return null;
    }

    public Customer queryCustomer(String name) {
        int position = findCustomer(name);
        if(position >=0) {
            return this.myCustomer.get(position);
        }

        return null;
    }
    public void FileeReader() throws IOException{
        String row;
        BufferedReader csvReader = new BufferedReader(new FileReader("./BankingDetails.csv"));
        while ((row = csvReader.readLine()) != null) {
        String[] data = row.split(",");
        }
        csvReader.close();
    }

    public void FileWriter() throws IOException{
        File file = new File("./BankingDetails.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
    
        bw.write("Customer,Account Number,Account Balance");
        bw.newLine();
        for (int i = 0; i < myCustomer.size(); i++) {
            bw.write(myCustomer.get(i).getName() + " ,");
            bw.write(myCustomer.get(i).getAccountNumber() + " ,");
            bw.write(myCustomer.get(i).getAccountBalance());
            bw.newLine();
        }
        // bw.flush();
        bw.close();
        fw.close();
    }

    public void printCustomer(){
        // File csvFile = new File("./employees.csv");
        // FileWriter fileWriter = new FileWriter(csvFile);
        // BufferedWriter bw = new BufferedWriter(fw);

        System.out.println("Customer List");

        for(int i=0; i<this.myCustomer.size(); i++) {
            System.out.println((i+1) + " - " +
            this.myCustomer.get(i).getName() + "'s Account Number is : " +                
            this.myCustomer.get(i).getAccountNumber() +"\n have Account Balance : " + this.myCustomer.get(i).getAccountBalance());
            // String line1[] = {'"'+myCustomer.get(i).getName()+'"'+","+'"'+myCustomer.get(i).getAccountNumber()+'"'+","+'"'+myCustomer.get(i).getAccountBalance()+'"'};
            // for (int j=0; j<3;  j++){
            //     System.out.println(line1[j]);
            // }
        }
    }
}
